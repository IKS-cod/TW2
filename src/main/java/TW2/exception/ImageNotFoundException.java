package TW2.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Исключение, которое выбрасывается, когда изображение не найдено.
 * <p>
 * Этот класс является подклассом {@link RuntimeException} и используется для обработки
 * ситуаций, когда запрашиваемое изображение отсутствует в системе.
 * </p>
 */
public class ImageNotFoundException extends RuntimeException {
    private static final Logger logger = Logger.getLogger(ImageNotFoundException.class.getName());

    /**
     * Создает новое исключение {@code ImageNotFoundException} с заданным сообщением.
     *
     * @param message сообщение, описывающее причину исключения
     */
    public ImageNotFoundException(String message) {
        super(message);
        logger.log(Level.SEVERE, "ImageNotFoundException: {0}", message);
    }
}