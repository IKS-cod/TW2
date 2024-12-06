package TW2.controller;

import TW2.dto.LoginDto;
import TW2.dto.RegisterDto;
import TW2.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;

/**
 * Контроллер для управления авторизацией и регистрацией пользователей.
 * <p>
 * Этот контроллер предоставляет API для выполнения операций входа и регистрации пользователей.
 * </p>
 */
@CrossOrigin(value = "http://localhost:3000")
@RestController
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AdController.class);
    private final AuthService authService;

    /**
     * Конструктор контроллера AuthController.
     *
     * @param authService Сервис для управления авторизацией и регистрацией пользователей.
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Авторизация пользователя.
     *
     * @param loginDto Данные для авторизации, содержащие имя пользователя и пароль.
     * @return Ответ с кодом состояния:
     *         - 200 (OK) если авторизация успешна,
     *         - 401 (Unauthorized) если авторизация не удалась.
     */
    @PostMapping("/login")
    @Tag(name = "Авторизация")
    @Operation(summary = "Авторизация пользователя")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        logger.info("User login attempt for username: {}", loginDto.getUsername());
        if (authService.login(loginDto.getUsername(), loginDto.getPassword())) {
            logger.info("User successfully logged in: {}", loginDto.getUsername());
            return ResponseEntity.ok().build();
        } else {
            logger.warn("Unauthorized login attempt for username: {}", loginDto.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * Регистрация пользователя.
     *
     * @param registerDto Данные для регистрации, содержащие необходимую информацию о пользователе.
     * @return Ответ с кодом состояния:
     *         - 201 (Created) если регистрация успешна,
     *         - 400 (Bad Request) если регистрация не удалась.
     * @throws IOException Если произошла ошибка при обработке данных регистрации.
     */
    @PostMapping("/register")
    @Tag(name = "Регистрация")
    @Operation(summary = "Регистрация пользователя")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) throws IOException {
        logger.info("User registration attempt for username: {}", registerDto.getUsername());
        if (authService.register(registerDto)) {
            logger.info("User successfully registered: {}", registerDto.getUsername());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            logger.warn("Registration failed for username: {}", registerDto.getUsername());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
