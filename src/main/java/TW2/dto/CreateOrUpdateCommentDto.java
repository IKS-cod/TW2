package TW2.dto;

import TW2.service.ValidationService;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO (Data Transfer Object) для создания или обновления комментария.
 * <p>
 * Этот класс представляет данные, необходимые для создания или обновления комментария,
 * включая текст комментария. Текст должен содержать от 8 до 64 символов.
 * </p>
 */
public class CreateOrUpdateCommentDto {
    @Schema(required = true, type = "string", description = "Текст комментария", minLength = 8, maxLength = 64)
    private String text;

    /**
     * Конструктор по умолчанию.
     */
    public CreateOrUpdateCommentDto() {}

    /**
     * Конструктор для создания объекта CreateOrUpdateCommentDto с заданным текстом комментария.
     *
     * @param text Текст комментария. Должен содержать от 8 до 64 символов.
     * @throws IllegalArgumentException Если длина текста не соответствует требованиям.
     */
    public CreateOrUpdateCommentDto(String text) {
        if (ValidationService.isValidLength(text, 8, 64)) {
            this.text = text;
        } else {
            throw new IllegalArgumentException("Комментарий должен содержать от 8 до 64 символов");
        }
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
     * @param text Текст комментария. Должен содержать от 8 до 64 символов.
     * @throws IllegalArgumentException Если длина текста не соответствует требованиям.
     */
    public void setText(String text) {
        if (ValidationService.isValidLength(text, 8, 64)) {
            this.text = text;
        } else {
            throw new IllegalArgumentException("Комментарий должен содержать от 8 до 64 символов");
        }
    }
}