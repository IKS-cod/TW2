package TW2.service;



import TW2.dto.CommentDto;
import TW2.dto.CommentsDto;
import TW2.dto.CreateOrUpdateCommentDto;
import TW2.exception.AdNotFoundException;
import TW2.exception.CommentNotFoundException;
import TW2.mapper.Mappers;
import TW2.model.Avatars;
import TW2.model.Comments;
import TW2.repository.AdsRepository;
import TW2.repository.AvatarsRepository;
import TW2.repository.CommentsRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Сервис для управления комментариями к объявлениям.
 * <p>
 * Этот класс предоставляет методы для получения, добавления, обновления и удаления комментариев,
 * а также для обработки связанных данных, таких как аватары пользователей.
 * </p>
 */
@Service
public class CommentService {
    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    private final UserContextService userContextService;
    private final Mappers mappers;
    private final AvatarsRepository avatarsRepository;
    private final CommentsRepository commentsRepository;
    private final AdsRepository adsRepository;

    public CommentService(UserContextService userContextService,
                          AvatarsRepository avatarsRepository,
                          CommentsRepository commentsRepository,
                          AdsRepository adsRepository,
                          Mappers mappers) {
        this.userContextService = userContextService;
        this.avatarsRepository = avatarsRepository;
        this.commentsRepository = commentsRepository;
        this.adsRepository = adsRepository;
        this.mappers = mappers;
    }

    /**
     * Получает список комментариев для указанного объявления.
     *
     * @param adId уникальный идентификатор объявления.
     * @return объект CommentsDto с комментариями и их количеством.
     */
    public CommentsDto getCommentsForAd(Integer adId) {
        List<Comments> commentsList = commentsRepository.findByAdsPk(adId);

        // Создаем CommentsDto и устанавливаем количество комментариев
        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setCount(commentsList.size());

        // Получаем список всех уникальных пользователей из комментариев
        Set<Integer> userIds = commentsList.stream()
                .map(comment -> comment.getUsers().getId())
                .collect(Collectors.toSet());

        // Загружаем аватары для всех пользователей за один запрос
        Map<Integer, String> avatarsMap = avatarsRepository.findByUsersIdIn(userIds)
                .stream()
                .collect(Collectors.toMap(avatar -> avatar.getUsers().getId(), Avatars::getPathForEndpoint));

        // Преобразуем список комментариев в список CommentDto
        List<CommentDto> commentDtoList = commentsList.stream()
                .map(comment -> new CommentDto(
                        comment.getUsers().getId(),
                        avatarsMap.get(comment.getUsers().getId()),  // Получаем путь к аватару из карты
                        comment.getUsers().getFirstName(),
                        comment.getCreatedAt(),
                        comment.getPk(),
                        comment.getText()
                ))
                .collect(Collectors.toList());

        commentsDto.setResults(commentDtoList);

        logger.info("Retrieved {} comments for ad ID: {}", commentsList.size(), adId);
        return commentsDto;
    }

    /**
     * Добавляет новый комментарий к объявлению.
     *
     * @param adId уникальный идентификатор объявления.
     * @param commentDto объект, содержащий данные нового комментария.
     * @return объект CommentDto с данными добавленного комментария.
     */
    public CommentDto addComment(Integer adId, CreateOrUpdateCommentDto commentDto) {
        if (!adsRepository.existsById(adId)) {
            throw new AdNotFoundException("Ad not found with ID: " + adId);
        }

        Comments comments = mappers.toComments(commentDto);
        comments.setAds(adsRepository.findById(adId).orElseThrow(() ->
                new AdNotFoundException("Ad not found with ID: " + adId)));
        comments.setUsers(userContextService.getCurrentUserFromDb());

        Comments savedComment = commentsRepository.save(comments);

        CommentDto commentDtoFromDb = mappers.toCommentDto(savedComment);

        avatarsRepository.findByUsersId(savedComment.getUsers().getId())
                .ifPresent(avatar -> commentDtoFromDb.setAuthorImage(avatar.getPathForEndpoint()));

        logger.info("Added comment for ad ID: {}", adId);

        return commentDtoFromDb;
    }

    /**
     * Обновляет существующий комментарий.
     *
     * @param adId уникальный идентификатор объявления.
     * @param commentId уникальный идентификатор комментария.
     * @param commentDto объект, содержащий обновленные данные комментария.
     * @return объект CommentDto с обновленными данными комментария.
     */
    public CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto commentDto) {
        Comments existingComment = Optional.ofNullable(commentsRepository.findByAdsPkAndPk(adId, commentId))
                .orElseThrow(() -> new CommentNotFoundException("Comment not found for ID: " + commentId));

        existingComment.setText(commentDto.getText());

        Comments updatedComment = commentsRepository.save(existingComment);

        CommentDto updatedCommentDto = mappers.toCommentDto(updatedComment);

        avatarsRepository.findByUsersId(updatedComment.getUsers().getId())
                .ifPresent(avatar -> updatedCommentDto.setAuthorImage(avatar.getPathForEndpoint()));

        logger.info("Updated comment ID: {} for ad ID: {}", commentId, adId);

        return updatedCommentDto;
    }

    /**
     * Удаляет комментарий по идентификатору объявления и идентификатору комментария.
     *
     * @param adId уникальный идентификатор объявления.
     * @param commentId уникальный идентификатор комментария.
     */
    public void deleteComment(Integer adId, Integer commentId) {
        if (!commentsRepository.existsByAdsPkAndPk(adId, commentId)) {
            throw new CommentNotFoundException("Comment not found for ID: " + commentId);
        }

        commentsRepository.deleteByAdsPkAndPk(adId, commentId);

        logger.info("Deleted comment ID: {} for ad ID: {}", commentId, adId);
    }

    /**
     * Удаляет все комментарии для указанного объявления.
     *
     * @param adId уникальный идентификатор объявления.
     */
    @Transactional
    public void deleteCommentForIdAds(Integer adId) {
        commentsRepository.deleteByAdsPk(adId);

        logger.info("Deleted all comments for ad ID: {}", adId);
    }
}
