package TW2.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Исключение, выбрасываемое, когда реклама не найдена.
 * <p>
 * Это исключение наследуется от {@link RuntimeException} и используется для
 * индикации ошибок, связанных с отсутствием объявления в системе.
 * </p>
 */
public class AdNotFoundException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(AdNotFoundException.class);

    /**
     * Конструктор, создающий новое исключение с заданным сообщением.
     *
     * @param message сообщение об ошибке, которое будет передано при выбрасывании исключения.
     */
    public AdNotFoundException(String message) {
        super(message);
        logger.error("AdNotFoundException: {}", message); // Логируем сообщение об ошибке
    }
}
