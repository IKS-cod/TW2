package TW2.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO (Data Transfer Object) для обновления пароля пользователя.
 * <p>
 * Этот класс представляет данные, необходимые для изменения пароля, включая текущий пароль
 * и новый пароль.
 * </p>
 */
public class NewPasswordDto {
    @Schema(type = "string", description = "текущий пароль", minLength = 8, maxLength = 16)
    private String currentPassword;

    @Schema(type = "string", description = "новый пароль", minLength = 8, maxLength = 16)
    private String newPassword;

    /**
     * Конструктор для создания объекта NewPasswordDto с заданными параметрами.
     *
     * @param currentPassword текущий пароль пользователя.
     * @param newPassword     новый пароль пользователя.
     */
    public NewPasswordDto(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    /**
     * Конструктор по умолчанию.
     */
    public NewPasswordDto() {
    }

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
     * @param currentPassword текущий пароль пользователя.
     */
    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
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
     * @param newPassword новый пароль пользователя.
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

