package TW2.controller;

import TW2.dto.CommentDto;
import TW2.dto.CommentsDto;
import TW2.dto.CreateOrUpdateCommentDto;
import TW2.service.CommentService;
import TW2.service.UserVerification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для работы с комментариями к объявлениям.
 * <p>
 * Этот контроллер предоставляет API для получения, добавления, обновления и удаления комментариев.
 * </p>
 */
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@Tag(name = "Комментарии")
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final UserVerification userVerification;
    private final CommentService commentService;

    public CommentController(UserVerification userVerification, CommentService commentService) {
        this.userVerification = userVerification;
        this.commentService = commentService;
    }

    /**
     * Получение комментариев для указанного объявления.
     *
     * @param id Идентификатор объявления.
     * @return Объект CommentsDto, содержащий список комментариев.
     */
   // @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    @GetMapping("/{id}/comments")
    @Operation(summary = "Получение комментариев объявления")
    public CommentsDto getComments(@PathVariable Integer id) {
        logger.info("Fetching comments for ad ID: {}", id);
        CommentsDto comments = commentService.getCommentsForAd(id);
        logger.info("Successfully fetched comments for ad ID: {}", id);
        return comments;
    }

    /**
     * Добавление комментария к указанному объявлению.
     *
     * @param id Идентификатор объявления.
     * @param comment Данные комментария для добавления.
     * @return Объект CommentDto, представляющий добавленный комментарий.
     */
   // @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    @PostMapping("/{id}/comments")
    @Operation(summary = "Добавление комментария к объявлению")
    public CommentDto addComment(@PathVariable Integer id, @RequestBody CreateOrUpdateCommentDto comment) {
        logger.info("Adding comment to ad ID: {}", id);
        CommentDto addedComment = commentService.addComment(id, comment);
        logger.info("Successfully added comment to ad ID: {}. Comment ID: {}", id, addedComment.getPk());
        return addedComment;
    }

    /**
     * Обновление существующего комментария.
     *
     * @param adId Идентификатор объявления.
     * @param commentId Идентификатор комментария.
     * @param comment Данные для обновления комментария.
     * @return Объект CommentDto, представляющий обновленный комментарий.
     */
    @PreAuthorize("@userVerification.verificationUserForComment(#commentId) || hasRole('ADMIN')")
    @PatchMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Обновление комментария")
    public CommentDto updateComment(@PathVariable Integer adId, @PathVariable Integer commentId,
                                    @RequestBody CreateOrUpdateCommentDto comment) {
        logger.info("Updating comment ID: {} for ad ID: {}", commentId, adId);
        CommentDto updatedComment = commentService.updateComment(adId, commentId, comment);
        logger.info("Successfully updated comment ID: {} for ad ID: {}", commentId, adId);
        return updatedComment;
    }

    /**
     * Удаление существующего комментария.
     *
     * @param adId Идентификатор объявления.
     * @param commentId Идентификатор комментария для удаления.
     */
    @PreAuthorize("@userVerification.verificationUserForComment(#commentId) || hasRole('ADMIN')")
    @Transactional
    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Удаление комментария")
    public void deleteComment(@PathVariable Integer adId, @PathVariable Integer commentId) {
        logger.info("Deleting comment ID: {} for ad ID: {}", commentId, adId);
        commentService.deleteComment(adId, commentId);
        logger.info("Successfully deleted comment ID: {} for ad ID: {}", commentId, adId);
    }
}