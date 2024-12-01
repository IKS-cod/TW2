package TW2.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

public class CommentDto {
    @Schema(type = "integer", format = "int32", description = "id автора комментария")
    private Integer author;

    @Schema(type = "string", description = "ссылка на аватар автора комментария")
    private String authorImage;

    @Schema(type = "string", description = "имя создателя комментария")
    private String authorFirstName;

    @Schema(type = "integer", format = "int64", description = "дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970")
    private Integer createdAt;

    @Schema(type = "integer", format = "int32", description = "id комментария")
    private Integer pk;

    @Schema(type = "string", description = "текст комментария")
    private String text;

    public CommentDto(Integer author,
                      String authorImage,
                      String authorFirstName,
                      Integer createdAt,
                      Integer pk,
                      String text) {
        this.author = author;
        this.authorImage = authorImage;
        this.authorFirstName = authorFirstName;
        this.createdAt = createdAt;
        this.pk = pk;
        this.text = text;
    }

    public CommentDto() {
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public String getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

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
        CommentDto commentDto = (CommentDto) o;
        return Objects.equals(author, commentDto.author) && Objects.equals(authorImage, commentDto.authorImage) && Objects.equals(authorFirstName, commentDto.authorFirstName) && Objects.equals(createdAt, commentDto.createdAt) && Objects.equals(pk, commentDto.pk) && Objects.equals(text, commentDto.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, authorImage, authorFirstName, createdAt, pk, text);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "author=" + author +
                ", authorImage='" + authorImage + '\'' +
                ", authorFirstName='" + authorFirstName + '\'' +
                ", createdAt=" + createdAt +
                ", pk=" + pk +
                ", text='" + text + '\'' +
                '}';
    }
}
