package TW2.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Исключение, выбрасываемое, когда пользователь не прошел аутентификацию.
 * Это исключение расширяет класс RuntimeException.
 */
public class UserNotAuthenticatedException extends RuntimeException {

    private static final Logger logger = Logger.getLogger(UserNotAuthenticatedException.class.getName());

    /**
     * Конструктор класса UserNotAuthenticatedException.
     *
     * @param message сообщение об ошибке, которое сохраняется для последующего получения методом {@link #getMessage()}.
     */
    public UserNotAuthenticatedException(String message) {
        super(message);
        logger.log(Level.SEVERE, "UserNotAuthenticatedException: {0}", message);
    }
}