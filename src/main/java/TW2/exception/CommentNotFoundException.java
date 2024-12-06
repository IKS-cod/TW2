package TW2.exception;

/**
 * Исключение, которое выбрасывается, когда комментарий не найден.
 * <p>
 * Этот класс является подклассом {@link RuntimeException} и используется для обработки
 * ситуаций, когда запрашиваемый комментарий отсутствует в системе.
 * </p>
 */
public class CommentNotFoundException extends RuntimeException {

    /**
     * Создает новое исключение {@code CommentNotFoundException} с заданным сообщением.
     *
     * @param message сообщение, описывающее причину исключения
     */
    public CommentNotFoundException(String message) {
        super(message);
    }
}