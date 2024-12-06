package TW2.exception;

/**
 * Исключение, которое выбрасывается, когда изображение не найдено.
 * <p>
 * Этот класс является подклассом {@link RuntimeException} и используется для обработки
 * ситуаций, когда запрашиваемое изображение отсутствует в системе.
 * </p>
 */
public class ImageNotFoundException extends RuntimeException {

    /**
     * Создает новое исключение {@code ImageNotFoundException} с заданным сообщением.
     *
     * @param message сообщение, описывающее причину исключения
     */
    public ImageNotFoundException(String message) {
        super(message);
    }
}