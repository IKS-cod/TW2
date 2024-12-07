package TW2.dto;


import TW2.service.ValidationService;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO (Data Transfer Object) для обновления информации о пользователе.
 * <p>
 * Этот класс представляет данные, необходимые для обновления информации о пользователе,
 * включая имя, фамилию и телефон. Имя и фамилия должны содержать от 3 до 10 символов.
 * Телефон должен быть в формате +7(000)000-00-00.
 * </p>
 */
public class UpdateUserDto {
    @Schema(type = "string", description = "Имя пользователя", minLength = 3, maxLength = 10)
    private String firstName;

    @Schema(type = "string", description = "Фамилия пользователя", minLength = 3, maxLength = 10)
    private String lastName;

    @Schema(type = "string", description = "Телефон пользователя (формат: +7(000)000-00-00)",
            pattern = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    /**
     * Конструктор для создания объекта UpdateUserDto с заданными параметрами.
     *
     * @param firstName имя пользователя. Должно содержать от 3 до 10 символов.
     * @param lastName  фамилия пользователя. Должна содержать от 3 до 10 символов.
     * @param phone     телефон пользователя. Должен быть в формате +7(000)000-00-00.
     */
    public UpdateUserDto(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    /**
     * Конструктор по умолчанию.
     */
    public UpdateUserDto() {}

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
     * @param firstName имя пользователя. Должно содержать от 3 до 10 символов.
     * @throws IllegalArgumentException Если имя не соответствует требованиям по длине или символам.
     */
    public void setFirstName(String firstName) {
        if (ValidationService.isValidSymbol(firstName) && ValidationService.isValidLength(firstName, 3, 10)) {
            this.firstName = firstName;
        } else {
            throw new IllegalArgumentException("Имя должно содержать от 3 до 10 букв");
        }
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
     * @param lastName фамилия пользователя. Должна содержать от 3 до 10 символов.
     * @throws IllegalArgumentException Если фамилия не соответствует требованиям по длине или символам.
     */
    public void setLastName(String lastName) {
        if (ValidationService.isValidLength(lastName, 3, 10) && ValidationService.isValidSymbol(lastName)) {
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException("Фамилия должна содержать от 3 до 10 букв");
        }
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
     * @param phone телефон пользователя. Должен быть в формате +7(000)000-00-00.
     * @throws IllegalArgumentException Если телефон не соответствует требованиям по формату.
     */
    public void setPhone(String phone) {
        if (ValidationService.isValidPhone(phone)) {
            this.phone = phone;
        } else {
            throw new IllegalArgumentException("Телефон должен быть в формате +7(000)000-00-00");
        }
    }
}