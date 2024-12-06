package TW2.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * DTO (Data Transfer Object) для расширенного представления объявления.
 * <p>
 * Этот класс представляет данные объявления, включая информацию о авторе, описании,
 * цене и других атрибутах.
 * </p>
 */
public class ExtendedAdDto {
    @Schema(type = "integer", format = "int32", description = "id объявления")
    private Integer pk;

    @Schema(type = "string", description = "имя автора объявления")
    private String authorFirstName;

    @Schema(type = "string", description = "фамилия автора объявления")
    private String authorLastName;

    @Schema(type = "string", description = "описание объявления")
    private String description;

    @Schema(type = "string", description = "логин автора объявления")
    private String email;

    @Schema(type = "string", description = "ссылка на картинку объявления")
    private String image;

    @Schema(type = "string", description = "телефон автора объявления")
    private String phone;

    @Schema(type = "integer", format = "int32", description = "цена объявления")
    private Integer price;

    @Schema(type = "string", description = "заголовок объявления")
    private String title;

    /**
     * Конструктор для создания объекта ExtendedAdDto с заданными параметрами.
     *
     * @param pk              id объявления.
     * @param authorFirstName имя автора объявления.
     * @param authorLastName  фамилия автора объявления.
     * @param description     описание объявления.
     * @param email           логин автора объявления.
     * @param image           ссылка на картинку объявления.
     * @param phone           телефон автора объявления.
     * @param price           цена объявления.
     * @param title           заголовок объявления.
     */
    public ExtendedAdDto(Integer pk,
                         String authorFirstName,
                         String authorLastName,
                         String description,
                         String email,
                         String image,
                         String phone,
                         Integer price,
                         String title) {
        this.pk = pk;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.description = description;
        this.email = email;
        this.image = image;
        this.phone = phone;
        this.price = price;
        this.title = title;
    }

    /**
     * Конструктор по умолчанию.
     */
    public ExtendedAdDto() {
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
     * Получает имя автора объявления.
     *
     * @return имя автора объявления.
     */
    public String getAuthorFirstName() {
        return authorFirstName;
    }

    /**
     * Устанавливает имя автора объявления.
     *
     * @param authorFirstName имя автора объявления.
     */
    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    /**
     * Получает фамилию автора объявления.
     *
     * @return фамилия автора объявления.
     */
    public String getAuthorLastName() {
        return authorLastName;
    }

    /**
     * Устанавливает фамилию автора объявления.
     *
     * @param authorLastName фамилия автора объявления.
     */
    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    /**
     * Получает описание объявления.
     *
     * @return описание объявления.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Устанавливает описание объявления.
     *
     * @param description описание объявления.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Получает логин автора объявления.
     *
     * @return логин автора объявления.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Устанавливает логин автора объявления.
     *
     * @param email логин автора объявления.
     */
    public void setEmail(String email) {
        this.email = email;
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
     * Получает телефон автора объявления.
     *
     * @return телефон автора объявления.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Устанавливает телефон автора объявления.
     *
     * @param phone телефон автора объявления.
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     *@return заголовок  обьявления.
     */
    public String getTitle() {
        return title;
    }

    /**
     *@param title заголовок обьявления.
     */
    public void setTitle(String title) {
        this.title= title;
    }
}
