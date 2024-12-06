package TW2.dto;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO (Data Transfer Object) для данных авторизации пользователя.
 * <p>
 * Этот класс представляет данные, необходимые для входа в систему, включая логин и пароль.
 * </p>
 */
public class LoginDto {

    @Schema(type = "string", description = "пароль", minLength = 8, maxLength = 16)
    private String password;

    @Schema(type = "string", description = "логин", minLength = 4, maxLength = 32)
    private String username;

    /**
     * Конструктор для создания объекта LoginDto с заданными параметрами.
     *
     * @param username логин пользователя.
     * @param password пароль пользователя.
     */
    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Конструктор по умолчанию.
     */
    public LoginDto() {
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
}
