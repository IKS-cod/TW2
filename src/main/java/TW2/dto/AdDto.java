package TW2.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO (Data Transfer Object) для объявления.
 * <p>
 * Этот класс представляет данные объявления, включая информацию о авторе, изображении,
 * идентификаторе, цене и заголовке.
 * </p>
 */
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

    /**
     * Конструктор для создания объекта AdDto с заданными параметрами.
     *
     * @param author id автора объявления.
     * @param image  ссылка на картинку объявления.
     * @param pk     id объявления.
     * @param price  цена объявления.
     * @param title  заголовок объявления.
     */
    public AdDto(Integer author, String image, Integer pk, Integer price, String title) {
        this.author = author;
        this.image = image;
        this.pk = pk;
        this.price = price;
        this.title = title;
    }

    /**
     * Конструктор по умолчанию.
     */
    public AdDto() {
    }

    /**
     * Получает id автора объявления.
     *
     * @return id автора объявления.
     */
    public Integer getAuthor() {
        return author;
    }

    /**
     * Устанавливает id автора объявления.
     *
     * @param author id автора объявления.
     */
    public void setAuthor(Integer author) {
        this.author = author;
    }

    /**
     * Получает ссылку на картинку объявления.
     *
     * @return ссылка на картинку объявления.
     */
    public String getImage() {
        return image;
    }

    /**
     * Устанавливает ссылку на картинку объявления.
     *
     * @param image ссылка на картинку объявления.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Получает id объявления.
     *
     * @return id объявления.
     */
    public Integer getPk() {
        return pk;
    }

    /**
     * Устанавливает id объявления.
     *
     * @param pk id объявления.
     */
    public void setPk(Integer pk) {
        this.pk = pk;
    }

    /**
     * Получает цену объявления.
     *
     * @return цена объявления.
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * Устанавливает цену объявления.
     *
     * @param price цена объявления.
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * Получает заголовок объявления.
     *
     * @return заголовок объявления.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Устанавливает заголовок объявления.
     *
     * @param title заголовок объявления.
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
