package TW2.service;

import TW2.exception.AdNotFoundException;
import TW2.exception.CommentNotFoundException;
import TW2.model.Ads;
import TW2.model.Comments;
import TW2.repository.AdsRepository;
import TW2.repository.CommentsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Сервис для проверки прав пользователя на доступ к объявлениям и комментариям.
 */
@Service
public class UserVerification {

    private static final Logger logger = LoggerFactory.getLogger(UserVerification.class);

    private final AdsRepository adsRepository;
    private final UserContextService userContextService;
    private final CommentsRepository commentsRepository;

    public UserVerification(AdsRepository adsRepository, UserContextService userContextService, CommentsRepository commentsRepository) {
        this.adsRepository = adsRepository;
        this.userContextService = userContextService;
        this.commentsRepository = commentsRepository;
    }

    /**
     * Проверяет, имеет ли текущий пользователь право редактировать комментарий.
     *
     * @param commentId уникальный идентификатор комментария.
     * @return true, если пользователь имеет право редактировать комментарий; иначе false.
     * @throws CommentNotFoundException если комментарий не найден.
     */
    public boolean verificationUserForComment(Integer commentId) {
        logger.info("Verifying user permissions for comment ID: {}", commentId);

        Comments comment = commentsRepository.findById(commentId)
                .orElseThrow(() -> {
                    logger.error("Comment not found with ID: {}", commentId);
                    return new CommentNotFoundException("Comment not found with ID: " + commentId);
                });

        boolean hasPermission = comment.getUsers().getId().equals(userContextService.getCurrentUserFromDb().getId());
        logger.info("User {} has permission to edit comment ID: {}", userContextService.getCurrentUserFromDb().getId(), commentId);

        return hasPermission;
    }

    /**
     * Проверяет, имеет ли текущий пользователь право редактировать объявление.
     *
     * @param adId уникальный идентификатор объявления.
     * @return true, если пользователь имеет право редактировать объявление; иначе false.
     * @throws AdNotFoundException если объявление не найдено.
     */
    public boolean verificationUserForAds(Integer adId) {
        logger.info("Verifying user permissions for ad ID: {}", adId);

        Ads ad = adsRepository.findById(adId)
                .orElseThrow(() -> {
                    logger.error("Ad not found with ID: {}", adId);
                    return new AdNotFoundException("Ad not found with ID: " + adId);
                });

        boolean hasPermission = ad.getUsers().getId().equals(userContextService.getCurrentUserFromDb().getId());
        logger.info("User {} has permission to edit ad ID: {}", userContextService.getCurrentUserFromDb().getId(), adId);

        return hasPermission;
    }
}
