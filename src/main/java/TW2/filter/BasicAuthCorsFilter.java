package TW2.filter;


import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Фильтр для обработки CORS (Cross-Origin Resource Sharing) запросов.
 * <p>
 * Этот фильтр добавляет заголовок "Access-Control-Allow-Credentials" в ответ,
 * позволяя браузерам отправлять запросы с учетными данными (например, куки).
 * </p>
 */
@Component
public class BasicAuthCorsFilter extends OncePerRequestFilter {

    /**
     * Метод, который выполняется для каждого запроса.
     *
     * @param httpServletRequest  объект запроса.
     * @param httpServletResponse объект ответа.
     * @param filterChain        цепочка фильтров для обработки запроса.
     * @throws ServletException если происходит ошибка при обработке запроса.
     * @throws IOException      если происходит ошибка ввода-вывода.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        // Добавление заголовка, разрешающего отправку учетных данных
        httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
        // Продолжение обработки цепочки фильтров
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}