package TW2.service.impl;

import TW2.dto.RegisterDto;
import TW2.mapper.Mappers;
import TW2.model.Users;
import TW2.repository.UsersRepository;
import TW2.service.AuthService;
import TW2.service.AvatarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Реализация сервиса аутентификации пользователей.
 * <p>
 * Этот класс предоставляет методы для входа и регистрации пользователей,
 * а также управления их аватарами.
 * </p>
 */
@Service
public class AuthServiceImpl implements AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final Mappers mappers;
    private final UsersRepository usersRepository;
    private final AvatarService avatarService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(Mappers mappers,
                           UsersRepository usersRepository,
                           AvatarService avatarService,
                           UserDetailsService userDetailsService,
                           PasswordEncoder passwordEncoder) {
        this.mappers = mappers;
        this.usersRepository = usersRepository;
        this.avatarService = avatarService;
        this.userDetailsService = userDetailsService;
        this.encoder = passwordEncoder;
    }

    /**
     * Выполняет аутентификацию пользователя по имени пользователя и паролю.
     *
     * @param userName имя пользователя (логин).
     * @param password пароль пользователя.
     * @return true, если аутентификация прошла успешно; иначе false.
     */
    @Override
    public boolean login(String userName, String password) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            boolean isPasswordMatch = encoder.matches(password, userDetails.getPassword());
            logger.info("User {} logged in successfully: {}", userName, isPasswordMatch);
            return isPasswordMatch;
        } catch (UsernameNotFoundException e) {
            logger.warn("Login attempt failed for username: {}", userName);
            return false; // Пользователь не найден
        }
    }

    /**
     * Регистрирует нового пользователя в системе.
     *
     * @param registerDto объект, содержащий данные для регистрации пользователя.
     * @return true, если регистрация прошла успешно; иначе false.
     * @throws IOException если возникает ошибка при сохранении данных или загрузке аватара.
     */
    @Override
    public boolean register(RegisterDto registerDto) throws IOException {
        if (usersRepository.findByEmail(registerDto.getUsername()).isPresent()) {
            logger.warn("Registration attempt failed: User with email {} already exists", registerDto.getUsername());
            return false; // Пользователь с таким email уже существует
        }

        Users users = mappers.toUsers(registerDto);
        users.setPassword(encoder.encode(registerDto.getPassword()));
        usersRepository.save(users);

        // Загружаем дефолтное изображение
        avatarService.saveDefaultAvatar(users);

        logger.info("User registered successfully with email: {}", registerDto.getUsername());
        return true; // Регистрация успешна
    }
}