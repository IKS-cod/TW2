package TW2.dto;

import TW2.service.ValidationService;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO (Data Transfer Object) для обновления пароля пользователя.
 * <p>
 * Этот класс представляет данные, необходимые для изменения пароля, включая текущий и новый пароли.
 * Оба пароля должны содержать от 8 до 16 символов.
 * </p>
 */
public class NewPasswordDto {
    @Schema(type = "string", description = "Текущий пароль пользователя", minLength = 8, maxLength = 16)
    private String currentPassword;

    @Schema(type = "string", description = "Новый пароль пользователя", minLength = 8, maxLength = 16)
    private String newPassword;

    /**
     * Конструктор для создания объекта NewPasswordDto с заданными параметрами.
     *
     * @param currentPassword текущий пароль пользователя. Должен содержать от 8 до 16 символов.
     * @param newPassword     новый пароль пользователя. Должен содержать от 8 до 16 символов.
     */
    public NewPasswordDto(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    /**
     * Конструктор по умолчанию.
     */
    public NewPasswordDto() {}

    /**
     * Получает текущий пароль пользователя.
     *
     * @return текущий пароль пользователя.
     */
    public String getCurrentPassword() {
        return currentPassword;
    }

    /**
     * Устанавливает текущий пароль пользователя.
     *
     * @param currentPassword текущий пароль пользователя. Должен содержать от 8 до 16 символов.
     * @throws IllegalArgumentException Если длина текущего пароля не соответствует требованиям.
     */
    public void setCurrentPassword(String currentPassword) {
        if (ValidationService.isValidLength(currentPassword, 8, 16)) {
            this.currentPassword = currentPassword;
        } else {
            throw new IllegalArgumentException("Пароль должен содержать от 8 до 16 символов");
        }
    }

    /**
     * Получает новый пароль пользователя.
     *
     * @return новый пароль пользователя.
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Устанавливает новый пароль пользователя.
     *
     * @param newPassword новый пароль пользователя. Должен содержать от 8 до 16 символов.
     * @throws IllegalArgumentException Если длина нового пароля не соответствует требованиям.
     */
    public void setNewPassword(String newPassword) {
        if (ValidationService.isValidLength(newPassword, 8, 16)) {
            this.newPassword = newPassword;
        } else {
            throw new IllegalArgumentException("Пароль должен содержать от 8 до 16 символов");
        }
    }
}
