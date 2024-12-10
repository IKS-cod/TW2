package TW2.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Сервис валидации для проверки различных типов данных.
 * <p>
 * Этот класс предоставляет методы для валидации длины строк, символов,
 * формата электронной почты, телефонных номеров и цен.
 * </p>
 */
public class ValidationService {
    private static final Logger logger = Logger.getLogger(ValidationService.class.getName());

    /**
     * Проверяет, соответствует ли длина строки заданным минимальным и максимальным значениям.
     *
     * @param name строка для проверки.
     * @param min  минимальная допустимая длина.
     * @param max  максимальная допустимая длина.
     * @return true, если длина строки находится в пределах заданного диапазона; false в противном случае.
     */
    public static boolean isValidLength(String name, int min, int max) {
        boolean isValid = name.length() >= min && name.length() <= max;
        logger.log(Level.INFO, "Проверка длины: {0} (min: {1}, max: {2}) - Результат: {3}",
                new Object[]{name, min, max, isValid});
        return isValid;
    }

    /**
     * Проверяет, состоит ли строка только из букв (латиница и кириллица).
     *
     * @param name строка для проверки.
     * @return true, если строка состоит только из букв; false в противном случае.
     */
    public static boolean isValidSymbol(String name) {
        boolean isValid = Pattern.matches("[a-zA-Zа-яА-ЯёЁ]+", name);
        logger.log(Level.INFO, "Проверка символов: {0} - Результат: {1}",
                new Object[]{name, isValid});
        return isValid;
    }

    /**
     * Проверяет, соответствует ли строка формату электронного адреса.
     *
     * @param username строка для проверки.
     * @return true, если строка соответствует формату электронной почты; false в противном случае.
     */
    public static boolean isValidUsername(String username) {
        boolean isValid = Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", username);
        logger.log(Level.INFO, "Проверка логина (электронной почты): {0} - Результат: {1}",
                new Object[]{username, isValid});
        return isValid;
    }

    /**
     * Проверяет, соответствует ли строка формату телефонного номера.
     *
     * @param phone строка для проверки.
     * @return true, если строка соответствует формату +7(000)000-00-00; false в противном случае.
     */
    public static boolean isValidPhone(String phone) {
        String regex = "^\\+7\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$";
        boolean isValid = Pattern.matches(regex, phone);
        logger.log(Level.INFO, "Проверка телефона: {0} - Результат: {1}",
                new Object[]{phone, isValid});
        return isValid;
    }

    /**
     * Проверяет, находится ли цена в заданном диапазоне значений.
     *
     * @param price цена для проверки.
     * @param min   минимально допустимое значение.
     * @param max   максимально допустимое значение.
     * @return true, если цена не равна null и находится в пределах заданного диапазона; false в противном случае.
     */
    public static boolean isValidPrice(Integer price, int min, int max) {
        boolean isValid = price != null && price >= min && price <= max;
        logger.log(Level.INFO, "Проверка цены: {0} (min: {1}, max: {2}) - Результат: {3}",
                new Object[]{price, min, max, isValid});
        return isValid;
    }
}
