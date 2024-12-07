package TW2.dto;
import TW2.service.ValidationService;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO (Data Transfer Object) для данных авторизации пользователя.
 * <p>
 * Этот класс представляет данные, необходимые для входа в систему, включая логин и пароль.
 * Логин должен содержать от 4 до 32 символов и быть в формате электронной почты.
 * Пароль должен содержать от 8 до 16 символов.
 * </p>
 */
public class LoginDto {

    @Schema(type = "string", description = "Пароль пользователя", minLength = 8, maxLength = 16)
    private String password;

    @Schema(type = "string", description = "Логин пользователя (формат: mail@gmail.com)", minLength = 4, maxLength = 32)
    private String username;

    /**
     * Конструктор для создания объекта LoginDto с заданными параметрами.
     *
     * @param username логин пользователя. Должен содержать от 4 до 32 символов и быть в формате электронной почты.
     * @param password пароль пользователя. Должен содержать от 8 до 16 символов.
     */
    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Конструктор по умолчанию.
     */
    public LoginDto() {}

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
}
