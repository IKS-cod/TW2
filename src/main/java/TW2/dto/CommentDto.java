package TW2.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO (Data Transfer Object) для комментария.
 * <p>
 * Этот класс представляет данные комментария, включая информацию о авторе, времени создания
 * и тексте комментария.
 * </p>
 */
public class CommentDto {
    @Schema(type = "integer", format = "int32", description = "id автора комментария")
    private Integer author;

    @Schema(type = "string", description = "ссылка на аватар автора комментария")
    private String authorImage;

    @Schema(type = "string", description = "имя создателя комментария")
    private String authorFirstName;

    @Schema(type = "integer", format = "int64", description = "дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970")
    private Long createdAt;

    @Schema(type = "integer", format = "int32", description = "id комментария")
    private Integer pk;

    @Schema(type = "string", description = "текст комментария")
    private String text;

    /**
     * Конструктор для создания объекта CommentDto с заданными параметрами.
     *
     * @param author          id автора комментария.
     * @param authorImage     ссылка на аватар автора комментария.
     * @param authorFirstName имя создателя комментария.
     * @param createdAt       дата и время создания комментария в миллисекундах.
     * @param pk              id комментария.
     * @param text            текст комментария.
     */
    public CommentDto(Integer author,
                      String authorImage,
                      String authorFirstName,
                      Long createdAt,
                      Integer pk,
                      String text) {
        this.author = author;
        this.authorImage = authorImage;
        this.authorFirstName = authorFirstName;
        this.createdAt = createdAt;
        this.pk = pk;
        this.text = text;
    }

    /**
     * Конструктор по умолчанию.
     */
    public CommentDto() {
    }

    /**
     * Получает id автора комментария.
     *
     * @return id автора комментария.
     */
    public Integer getAuthor() {
        return author;
    }

    /**
     * Устанавливает id автора комментария.
     *
     * @param author id автора комментария.
     */
    public void setAuthor(Integer author) {
        this.author = author;
    }

    /**
     * Получает ссылку на аватар автора комментария.
     *
     * @return ссылка на аватар автора комментария.
     */
    public String getAuthorImage() {
        return authorImage;
    }

    /**
     * Устанавливает ссылку на аватар автора комментария.
     *
     * @param authorImage ссылка на аватар автора комментария.
     */
    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
    }

    /**
     * Получает имя создателя комментария.
     *
     * @return имя создателя комментария.
     */
    public String getAuthorFirstName() {
        return authorFirstName;
    }

    /**
     * Устанавливает имя создателя комментария.
     *
     * @param authorFirstName имя создателя комментария.
     */
    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    /**
     * Получает дату и время создания комментария в миллисекундах.
     *
     * @return дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970.
     */
    public Long getCreatedAt() {
        return createdAt;
    }

    /**
     * Устанавливает дату и время создания комментария в миллисекундах.
     *
     * @param createdAt дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970.
     */
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Получает id комментария.
     *
     * @return id комментария.
     */
    public Integer getPk() {
        return pk;
    }

    /**
     * Устанавливает id комментария.
     *
     * @param pk id комментария.
     */
    public void setPk(Integer pk) {
        this.pk = pk;
    }

    /**
     * Получает текст комментария.
     *
     * @return текст комментария.
     */
    public String getText() {
        return text;
    }

    /**
     * Устанавливает текст комментария.
     *
     * @param text текст комментария.
     */
    public void setText(String text) {
        this.text = text;
    }
}
