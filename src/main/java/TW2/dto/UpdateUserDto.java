package TW2.dto;


import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO (Data Transfer Object) для обновления информации о пользователе.
 * <p>
 * Этот класс представляет данные, необходимые для обновления информации о пользователе,
 * включая имя, фамилию и телефон.
 * </p>
 */
public class UpdateUserDto {
    @Schema(type = "string", description = "имя пользователя", minLength = 3, maxLength = 10)
    private String firstName;

    @Schema(type = "string", description = "фамилия пользователя", minLength = 3, maxLength = 10)
    private String lastName;

    @Schema(type = "string", description = "телефон пользователя",
            pattern = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    /**
     * Конструктор для создания объекта UpdateUserDto с заданными параметрами.
     *
     * @param firstName имя пользователя.
     * @param lastName  фамилия пользователя.
     * @param phone     телефон пользователя.
     */
    public UpdateUserDto(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    /**
     * Конструктор по умолчанию.
     */
    public UpdateUserDto() {
    }

    /**
     * Получает имя пользователя.
     *
     * @return имя пользователя.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Устанавливает имя пользователя.
     *
     * @param firstName имя пользователя.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Получает фамилию пользователя.
     *
     * @return фамилия пользователя.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Устанавливает фамилию пользователя.
     *
     * @param lastName фамилия пользователя.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Получает телефон пользователя.
     *
     * @return телефон пользователя.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Устанавливает телефон пользователя.
     *
     * @param phone телефон пользователя.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
