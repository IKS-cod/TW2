package TW2.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO (Data Transfer Object) для создания или обновления комментария.
 * <p>
 * Этот класс представляет данные, необходимые для создания или обновления комментария,
 * включая текст комментария.
 * </p>
 */
public class CreateOrUpdateCommentDto {
    @Schema(required = true, type = "string", description = "текст комментария", minLength = 8, maxLength = 64)
    private String text;

    /**
     * Конструктор по умолчанию.
     */
    public CreateOrUpdateCommentDto() {}

    /**
     * Конструктор для создания объекта CreateOrUpdateCommentDto с заданным текстом комментария.
     *
     * @param text Текст комментария.
     */
    public CreateOrUpdateCommentDto(String text) {
        this.text = text;
    }

    /**
     * Получает текст комментария.
     *
     * @return Текст комментария.
     */
    public String getText() {
        return text;
    }

    /**
     * Устанавливает текст комментария.
     *
     * @param text Текст комментария.
     */
    public void setText(String text) {
        this.text = text;
    }
}