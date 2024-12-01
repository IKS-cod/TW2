package TW2.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

public class CreateOrUpdateCommentDto {
    @Schema(required = true, type = "string", description = "текст комментария", minLength = 8, maxLength = 64)
    private String text;

    // Конструкторы
    public CreateOrUpdateCommentDto() {}

    public CreateOrUpdateCommentDto(String text) {
        this.text = text;
    }

    // Геттеры и сеттеры
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateOrUpdateCommentDto that = (CreateOrUpdateCommentDto) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "CreateOrUpdateComment{" +
                "text='" + text + '\'' +
                '}';
    }
}
