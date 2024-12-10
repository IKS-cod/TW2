package TW2.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Исключение, которое выбрасывается, когда комментарий не найден.
 * <p>
 * Этот класс является подклассом {@link RuntimeException} и используется для обработки
 * ситуаций, когда запрашиваемый комментарий отсутствует в системе.
 * </p>
 */
public class CommentNotFoundException extends RuntimeException {
    private static final Logger logger = Logger.getLogger(CommentNotFoundException.class.getName());

    /**
     * Создает новое исключение {@code CommentNotFoundException} с заданным сообщением.
     *
     * @param message сообщение, описывающее причину исключения
     */
    public CommentNotFoundException(String message) {
        super(message);
        logger.log(Level.SEVERE, "CommentNotFoundException: {0}", message);
    }
}