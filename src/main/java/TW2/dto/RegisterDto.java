package TW2.dto;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO (Data Transfer Object) для регистрации пользователя.
 * <p>
 * Этот класс представляет данные, необходимые для регистрации нового пользователя,
 * включая логин, пароль, имя, фамилию, телефон и роль.
 * </p>
 */
public class RegisterDto {
    @Schema(type = "string", description = "логин", minLength = 4, maxLength = 32)
    private String username;

    @Schema(type = "string", description = "пароль", minLength = 8, maxLength = 16)
    private String password;

    @Schema(type = "string", description = "имя пользователя", minLength = 2, maxLength = 16)
    private String firstName;

    @Schema(type = "string", description = "фамилия пользователя", minLength = 2, maxLength = 16)
    private String lastName;

    @Schema(type = "string", description = "телефон пользователя", pattern = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    @Schema(type = "string", description = "роль пользователя", allowableValues = {"USER", "ADMIN"})
    private Role role;

    /**
     * Конструктор для создания объекта RegisterDto с заданными параметрами.
     *
     * @param username   логин пользователя.
     * @param password   пароль пользователя.
     * @param firstName  имя пользователя.
     * @param lastName   фамилия пользователя.
     * @param phone      телефон пользователя.
     * @param role       роль пользователя.
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
    public RegisterDto() {
    }

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
     * @param username логин пользователя.
     */
    public void setUsername(String username) {
        this.username = username;
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
     * @param password пароль пользователя.
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @param role роль пользователя.
     */
    public void setRole(Role role) {
        this.role = role;
    }
}
