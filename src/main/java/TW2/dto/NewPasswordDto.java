package TW2.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

public class NewPasswordDto {
    @Schema(type = "string", description = "текущий пароль", minLength = 8, maxLength = 16)
    private String currentPassword;

    @Schema(type = "string", description = "новый пароль", minLength = 8, maxLength = 16)
    private String newPassword;

    public NewPasswordDto(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public NewPasswordDto() {

    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewPasswordDto that = (NewPasswordDto) o;
        return Objects.equals(currentPassword, that.currentPassword) && Objects.equals(newPassword, that.newPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPassword, newPassword);
    }

    @Override
    public String toString() {
        return "NewPassword{" +
                "currentPassword='" + currentPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}

