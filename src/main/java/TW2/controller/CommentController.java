package TW2.controller;
import TW2.dto.CommentDto;
import TW2.dto.CommentsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/ads")
@Tag(name = "Комментарии")
public class CommentController {

    @Autowired
    private CommentService commentService; // Сервис для работы с комментариями

    @GetMapping("/{id}/comments")
    @Operation(summary = "Получение комментариев объявления")
    public CommentsDto getComments(@PathVariable Integer id) {
        // Логика получения комментариев для объявления
        return commentService.getCommentsForAd(id);
    }

    @PostMapping("/{id}/comments")
    @Operation(summary = "Добавление комментария к объявлению")
    public CommentDto addComment(@PathVariable Integer adId, @RequestBody CreateOrUpdateComment comment) {
        // Логика добавления комментария к объявлению
        return commentService.addComment(adId, comment);
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Обновление комментария")
    public String updateComment(@PathVariable Integer adId, @PathVariable Integer commentId,
                                @RequestBody CreateOrUpdateComment comment) {
        // Логика обновления комментария
        commentService.updateComment(adId, commentId, comment);
        return "Комментарий успешно обновлён";
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Удаление комментария")
    public String deleteComment(@PathVariable Integer adId, @PathVariable Integer commentId) {
        // Логика удаления комментария
        commentService.deleteComment(adId, commentId);
        return "Комментарий успешно удалён";
    }
}