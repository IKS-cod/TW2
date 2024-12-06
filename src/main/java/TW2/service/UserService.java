package TW2.service;

import TW2.dto.NewPasswordDto;
import TW2.dto.UpdateUserDto;
import TW2.dto.UserDto;
import TW2.mapper.Mappers;
import TW2.model.Users;
import TW2.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Сервис для управления пользователями.
 * <p>
 * Этот класс предоставляет методы для обновления пароля, получения информации о текущем пользователе
 * и обновления данных пользователя. Он также управляет взаимодействием с репозиториями пользователей
 * и аватаров.
 * </p>
 */
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final Mappers mappers;
    private final UserContextService userContextService;
    private final AvatarsRepository avatarsRepository;
    private final PasswordEncoder encoder;
    private final UsersRepository usersRepository;

    public UserService(Mappers mappers,
                       UserContextService userContextService,
                       AvatarsRepository avatarsRepository,
                       PasswordEncoder encoder,
                       UsersRepository usersRepository) {
        this.mappers = mappers;
        this.userContextService = userContextService;
        this.avatarsRepository = avatarsRepository;
        this.encoder = encoder;
        this.usersRepository = usersRepository;
    }

    /**
     * Обновляет пароль текущего пользователя.
     *
     * @param newPasswordDto объект, содержащий новый пароль.
     */
    public void updatePassword(NewPasswordDto newPasswordDto) {
        Users userFromDb = userContextService.getCurrentUserFromDb();
        String encodedPassword = encoder.encode(newPasswordDto.getNewPassword());
        userFromDb.setPassword(encodedPassword);
        usersRepository.save(userFromDb);
        logger.info("Password updated for user ID: {}", userFromDb.getId());
    }

    /**
     * Получает информацию о текущем пользователе.
     *
     * @return объект UserDto с данными текущего пользователя.
     */
    public UserDto getUser() {
        Users userFromDb = userContextService.getCurrentUserFromDb();
        UserDto userDto = mappers.toUserDto(userFromDb);

        avatarsRepository.findByUsersId(userFromDb.getId())
                .ifPresent(avatar -> userDto.setImage(avatar.getPathForEndpoint()));

        logger.info("Retrieved user information for ID: {}", userFromDb.getId());
        return userDto;
    }

    /**
     * Обновляет информацию о пользователе.
     *
     * @param updateUserDto объект, содержащий обновленные данные пользователя.
     * @return объект UpdateUserDto с обновленными данными.
     */
    public UpdateUserDto updateUser(UpdateUserDto updateUserDto) {
        Users userFromDb = userContextService.getCurrentUserFromDb();

        // Обновление данных пользователя
        userFromDb.setFirstName(updateUserDto.getFirstName());
        userFromDb.setLastName(updateUserDto.getLastName());
        userFromDb.setPhone(updateUserDto.getPhone());

        Users updatedUser = usersRepository.save(userFromDb);

        logger.info("Updated user information for ID: {}", updatedUser.getId());
        return mappers.toUpdateUserDto(updatedUser);
    }
}