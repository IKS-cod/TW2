package TW2.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO (Data Transfer Object) для создания или обновления объявления.
 * <p>
 * Этот класс представляет данные, необходимые для создания или обновления объявления,
 * включая заголовок, цену и описание.
 * </p>
 */
public class CreateOrUpdateAdDto {
    @Schema(type = "string", description = "заголовок объявления", minLength = 4, maxLength = 32)
    private String title;

    @Schema(type = "integer", format = "int32", description = "цена объявления", minimum = "0", maximum = "10000000")
    private Integer price;

    @Schema(type = "string", description = "описание объявления", minLength = 8, maxLength = 64)
    private String description;

    /**
     * Конструктор по умолчанию.
     */
    public CreateOrUpdateAdDto() {
    }

    /**
     * Конструктор для создания объекта CreateOrUpdateAdDto с заданными параметрами.
     *
     * @param title       Заголовок объявления.
     * @param price       Цена объявления.
     * @param description Описание объявления.
     */
    public CreateOrUpdateAdDto(String title, Integer price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }

    /**
     * Получает заголовок объявления.
     *
     * @return Заголовок объявления.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Устанавливает заголовок объявления.
     *
     * @param title Заголовок объявления.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Получает цену объявления.
     *
     * @return Цена объявления.
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * Устанавливает цену объявления.
     *
     * @param price Цена объявления.
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * Получает описание объявления.
     *
     * @return Описание объявления.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Устанавливает описание объявления.
     *
     * @param description Описание объявления.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}