package TW2.service;


import TW2.dto.RegisterDto;

import java.io.IOException;

/**
 * Сервис для аутентификации пользователей.
 * <p>
 * Этот интерфейс определяет методы для входа и регистрации пользователей в системе.
 * </p>
 */
public interface AuthService {

    /**
     * Выполняет аутентификацию пользователя по имени и паролю.
     *
     * @param userName имя пользователя (логин).
     * @param password пароль пользователя.
     * @return true, если аутентификация прошла успешно; иначе false.
     */
    boolean login(String userName, String password);

    /**
     * Регистрирует нового пользователя в системе.
     *
     * @param registerDto объект, содержащий данные для регистрации пользователя.
     * @throws IOException если возникает ошибка при обработке данных регистрации.
     * @return true, если регистрация прошла успешно; иначе false.
     */
    boolean register(RegisterDto registerDto) throws IOException;
}
