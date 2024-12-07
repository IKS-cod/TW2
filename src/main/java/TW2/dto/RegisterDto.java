package TW2.dto;
import TW2.service.ValidationService;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO (Data Transfer Object) для регистрации пользователя.
 * <p>
 * Этот класс представляет данные, необходимые для регистрации нового пользователя,
 * включая логин, пароль, имя, фамилию, телефон и роль.
 * Логин должен содержать от 4 до 32 символов и быть в формате электронной почты.
 * Пароль должен содержать от 8 до 16 символов.
 * Имя и фамилия должны содержать от 2 до 16 букв.
 * Телефон должен быть в формате +7(000)000-00-00.
 * </p>
 */
public class RegisterDto {

    @Schema(type = "string", description = "Логин пользователя (формат: mail@gmail.com)", minLength = 4, maxLength = 32)
    private String username;

    @Schema(type = "string", description = "Пароль пользователя", minLength = 8, maxLength = 16)
    private String password;

    @Schema(type = "string", description = "Имя пользователя", minLength = 2, maxLength = 16)
    private String firstName;

    @Schema(type = "string", description = "Фамилия пользователя", minLength = 2, maxLength = 16)
    private String lastName;

    @Schema(type = "string", description = "Телефон пользователя (формат: +7(000)000-00-00)",
            pattern = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    @Schema(type = "string", description = "Роль пользователя", allowableValues = {"USER", "ADMIN"})
    private Role role;

    /**
     * Конструктор для создания объекта RegisterDto с заданными параметрами.
     *
     * @param username  логин пользователя. Должен содержать от 4 до 32 символов и быть в формате электронной почты.
     * @param password  пароль пользователя. Должен содержать от 8 до 16 символов.
     * @param firstName имя пользователя. Должно содержать от 2 до 16 букв.
     * @param lastName  фамилия пользователя. Должна содержать от 2 до 16 букв.
     * @param phone     телефон пользователя. Должен быть в формате +7(000)000-00-00.
     * @param role      роль пользователя. Может быть USER или ADMIN.
     */
    public RegisterDto(String username,
                       String password,
                       String firstName,
                       String lastName,
                       String phone,
                       Role role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.role = role;
    }

    /**
     * Конструктор по умолчанию.
     */
    public RegisterDto() {}

    /**
     * Получает логин пользователя.
     *
     * @return логин пользователя.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Устанавливает логин пользователя.
     *
     * @param username логин пользователя. Должен содержать от 4 до 32 символов и быть в формате электронной почты.
     * @throws IllegalArgumentException Если логин не соответствует требованиям по длине или формату.
     */
    public void setUsername(String username) {
        if (ValidationService.isValidLength(username, 4, 32) && ValidationService.isValidUsername(username)) {
            this.username = username;
        } else {
            throw new IllegalArgumentException("Логин должен содержать от 4 до 32 символов и быть в формате \"mail@gmail.com\"");
        }
    }

    /**
     * Получает пароль пользователя.
     *
     * @return пароль пользователя.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Устанавливает пароль пользователя.
     *
     * @param password пароль пользователя. Должен содержать от 8 до 16 символов.
     * @throws IllegalArgumentException Если пароль не соответствует требованиям по длине.
     */
    public void setPassword(String password) {
        if (ValidationService.isValidLength(password, 8, 16)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Пароль должен содержать от 8 до 16 символов");
        }
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
     * @param firstName имя пользователя. Должно содержать от 2 до 16 букв.
     * @throws IllegalArgumentException Если имя не соответствует требованиям по длине или символам.
     */
    public void setFirstName(String firstName) {
        if (ValidationService.isValidSymbol(firstName) && ValidationService.isValidLength(firstName, 2, 16)) {
            this.firstName = firstName;
        } else {
            throw new IllegalArgumentException("Имя должно содержать от 2 до 16 букв");
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
     * @param lastName фамилия пользователя. Должна содержать от 2 до 16 букв.
     * @throws IllegalArgumentException Если фамилия не соответствует требованиям по длине или символам.
     */
    public void setLastName(String lastName) {
        if (ValidationService.isValidLength(lastName, 2, 16) && ValidationService.isValidSymbol(lastName)) {
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException("Фамилия должна содержать от 2 до 16 букв");
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

    /**
     * Получает роль пользователя.
     *
     * @return роль пользователя.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Устанавливает роль пользователя.
     *
     * @param role роль пользователя. Может быть USER или ADMIN.
     */
    public void setRole(Role role) {
        this.role = role;
    }
}