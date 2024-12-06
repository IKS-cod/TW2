package TW2.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Исключение, выбрасываемое, когда пользователь не найден.
 * Это исключение расширяет класс RuntimeException.
 */
public class UserNotFoundException extends RuntimeException {

    private static final Logger logger = Logger.getLogger(UserNotFoundException.class.getName());

    /**
     * Конструктор класса UserNotFoundException.
     *
     * @param message сообщение об ошибке, которое сохраняется для последующего получения методом {@link #getMessage()}.
     */
    public UserNotFoundException(String message) {
        super(message);
        logger.log(Level.SEVERE, "UserNotFoundException: {0}", message);
    }
}
