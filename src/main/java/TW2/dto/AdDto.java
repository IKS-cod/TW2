package TW2.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

public class AdDto {
    @Schema(type = "integer", format = "int32", description = "id автора объявления")
    private Integer author;

    @Schema(type = "string", description = "ссылка на картинку объявления")
    private String image;

    @Schema(type = "integer", format = "int32", description = "id объявления")
    private Integer pk;

    @Schema(type = "integer", format = "int32", description = "цена объявления")
    private Integer price;

    @Schema(type = "string", description = "заголовок объявления")
    private String title;

    public AdDto(Integer author, String image, Integer pk, Integer price, String title) {
        this.author = author;
        this.image = image;
        this.pk = pk;
        this.price = price;
        this.title = title;
    }

    public AdDto() {
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdDto adDto = (AdDto) o;
        return Objects.equals(author, adDto.author) && Objects.equals(image, adDto.image) && Objects.equals(pk, adDto.pk) && Objects.equals(price, adDto.price) && Objects.equals(title, adDto.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, image, pk, price, title);
    }

    @Override
    public String toString() {
        return "Ad{" +
                "author=" + author +
                ", image='" + image + '\'' +
                ", pk=" + pk +
                ", price=" + price +
                ", title='" + title + '\'' +
                '}';
    }
}
