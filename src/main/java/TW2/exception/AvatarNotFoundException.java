package TW2.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Исключение, которое выбрасывается, когда аватар не найден.
 * <p>
 * Это исключение является подклассом {@link RuntimeException} и используется для
 * обработки случаев, когда запрашиваемый аватар не может быть найден в системе.
 * </p>
 */
public class AvatarNotFoundException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(AvatarNotFoundException.class);

    /**
     * Создает новое исключение с заданным сообщением и логирует его.
     *
     * @param message Сообщение, описывающее причину исключения.
     */
    public AvatarNotFoundException(String message) {
        super(message);
        logger.error("AvatarNotFoundException: {}", message); // Логируем сообщение об ошибке
    }
}
