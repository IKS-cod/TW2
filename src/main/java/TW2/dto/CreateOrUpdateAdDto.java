package TW2.dto;

import TW2.service.ValidationService;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO (Data Transfer Object) для создания или обновления объявления.
 * <p>
 * Этот класс представляет данные, необходимые для создания или обновления объявления,
 * включая заголовок, цену и описание. Заголовок должен содержать от 4 до 32 символов,
 * цена - от 0 до 10,000,000, а описание - от 8 до 64 символов.
 * </p>
 */
public class CreateOrUpdateAdDto {
    @Schema(type = "string", description = "Заголовок объявления", minLength = 4, maxLength = 32)
    private String title;

    @Schema(type = "integer", format = "int32", description = "Цена объявления", minimum = "0", maximum = "10_000_000")
    private Integer price;

    @Schema(type = "string", description = "Описание объявления", minLength = 8, maxLength = 64)
    private String description;

    /**
     * Конструктор по умолчанию.
     */
    public CreateOrUpdateAdDto() {
    }

    /**
     * Конструктор для создания объекта CreateOrUpdateAdDto с заданными параметрами.
     *
     * @param title       Заголовок объявления. Должен содержать от 4 до 32 символов.
     * @param price       Цена объявления. Должна быть в диапазоне от 0 до 10,000,000.
     * @param description Описание объявления. Должно содержать от 8 до 64 символов.
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
     * @param title Заголовок объявления. Должен содержать от 4 до 32 символов.
     * @throws IllegalArgumentException Если длина заголовка не соответствует требованиям.
     */
    public void setTitle(String title) {
        if (ValidationService.isValidLength(title, 4, 32)) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Заголовок должен содержать от 4 до 32 символов");
        }
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
     * @param price Цена объявления. Должна быть в диапазоне от 0 до 10,000,000.
     * @throws IllegalArgumentException Если цена не соответствует требованиям.
     */
    public void setPrice(Integer price) {
        if (ValidationService.isValidPrice(price, 0, 10000000)) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Цена должна быть в диапазоне от 0 до 10,000,000");
        }
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
     * @param description Описание объявления. Должно содержать от 8 до 64 символов.
     * @throws IllegalArgumentException Если длина описания не соответствует требованиям.
     */
    public void setDescription(String description) {
        if (ValidationService.isValidLength(description, 8, 64)) {
            this.description = description;
        } else {
            throw new IllegalArgumentException("Описание должно содержать от 8 до 64 символов");
        }
    }
}