package TW2.controller;


import TW2.dto.NewPasswordDto;
import TW2.dto.UpdateUserDto;
import TW2.dto.UserDto;
import TW2.service.AvatarService;
import TW2.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final AvatarService avatarService;
    private final UserService userService;

    public UserController(AvatarService avatarService, UserService userService) {
        this.avatarService = avatarService;
        this.userService = userService;
    }

    /**
     * Обновление пароля пользователя.
     *
     * @param newPasswordDto Данные для обновления пароля.
     */

    @PostMapping("/set_password")
    @Operation(summary = "Обновление пароля")
    public void updatePassword(@RequestBody NewPasswordDto newPasswordDto) {
        logger.info("Updating password for user: {}", newPasswordDto.getNewPassword());
        userService.updatePassword(newPasswordDto);
        logger.info("Password updated successfully for user: {}", newPasswordDto.getNewPassword());
    }

    /**
     * Получение информации об авторизованном пользователе.
     *
     * @return Объект UserDto, содержащий информацию о пользователе.
     */

    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизованном пользователе")
    public UserDto getUser() {
        logger.info("Fetching information for the authenticated user");
        UserDto user = userService.getUser();
        logger.info("Successfully fetched information for the authenticated user: {}", user.getEmail());
        return user;
    }

    /**
     * Обновление информации об авторизованном пользователе.
     *
     * @param updateUserDto Данные для обновления пользователя.
     * @return Объект UpdateUserDto с обновленной информацией.
     */

    @PatchMapping("/me")
    @Operation(summary = "Обновление информации об авторизованном пользователе")
    public UpdateUserDto updateUser(@RequestBody UpdateUserDto updateUserDto) {
        logger.info("Updating information for the authenticated user: {}", updateUserDto.getFirstName());
        UpdateUserDto updatedUser = userService.updateUser(updateUserDto);
        logger.info("Successfully updated information for the authenticated user: {}", updatedUser.getFirstName());
        return updatedUser;
    }

    /**
     * Обновление аватара авторизованного пользователя.
     *
     * @param image Новое изображение для аватара.
     * @throws IOException Если произошла ошибка при обработке изображения.
     */

    @PatchMapping("/me/image")
    @Operation(summary = "Обновление аватара авторизованного пользователя")
    public void updateUserAvatar(@RequestParam("image") MultipartFile image) throws IOException {
        logger.info("Updating avatar for the authenticated user");
        avatarService.updateUserAvatar(image);
        logger.info("Successfully updated avatar for the authenticated user");
    }
}

