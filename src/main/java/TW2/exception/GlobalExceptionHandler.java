package TW2.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Глобальный обработчик исключений, который обрабатывает исключения,
 * выбрасываемые любым контроллером в приложении.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Обрабатывает все исключения, выбрасываемые контроллерами.
     *
     * @param ex исключение, которое было выброшено
     * @return ResponseEntity, содержащий сообщение об ошибке и HTTP статус
     */
    @ExceptionHandler(Exception.class) // Обрабатывает все исключения
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        // Логируем исключение
        logger.error("Произошла ошибка: {}", ex.getMessage(), ex);

        // Возвращаем ответ с сообщением об ошибке и статусом 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Произошла ошибка: " + ex.getMessage());
    }
}
