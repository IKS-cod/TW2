package TW2.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

public class CreateOrUpdateAdDto {
    @Schema(type = "string", description = "заголовок объявления", minLength = 4, maxLength = 32)
    private String title;

    @Schema(type = "integer", format = "int32", description = "цена объявления", minimum = "0", maximum = "10000000")
    private Integer price;

    @Schema(type = "string", description = "описание объявления", minLength = 8, maxLength = 64)
    private String description;

    public CreateOrUpdateAdDto() {}

    public CreateOrUpdateAdDto(String title, Integer price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateOrUpdateAdDto that = (CreateOrUpdateAdDto) o;
        return Objects.equals(title, that.title) && Objects.equals(price, that.price) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, description);
    }

    @Override
    public String toString() {
        return "CreateOrUpdateAd{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
