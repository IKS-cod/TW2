package TW2.config;

import TW2.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Конфигурация безопасности веб-приложения.
 * <p>
 * Этот класс настраивает безопасность приложения, включая авторизацию,
 * аутентификацию и управление пользователями.
 * </p>
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
    private final UsersRepository usersRepository;

    /**
     * Конструктор класса WebSecurityConfig.
     *
     * @param usersRepository Репозиторий пользователей для управления данными пользователей.
     */
    public WebSecurityConfig(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        logger.info("WebSecurityConfig initialized with UsersRepository: {}", usersRepository);
    }

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/webjars/**",
            "/login",
            "/register"
    };

    /**
     * Создает бин UserDetailsService для управления пользователями.
     *
     * @param dataSource Источник данных для подключения к базе данных.
     * @return UserDetailsService для работы с пользователями.
     */
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        logger.info("Creating UserDetailsService with data source: {}", dataSource);
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // Устанавливаем запрос для получения пользователя по email
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT email AS username, password, TRUE AS enabled FROM users WHERE email = ?");
        logger.debug("Set usersByUsernameQuery for JdbcUserDetailsManager");

        // Устанавливаем запрос для получения ролей пользователя
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT email AS username, role AS authority FROM users WHERE email = ?");
        logger.debug("Set authoritiesByUsernameQuery for JdbcUserDetailsManager");

        return jdbcUserDetailsManager;
    }

    /**
     * Настраивает цепочку фильтров безопасности для HTTP-запросов.
     *
     * @param http Объект HttpSecurity для настройки безопасности.
     * @return Настроенная цепочка фильтров безопасности.
     * @throws Exception В случае ошибки настройки безопасности.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        logger.info("Configuring HttpSecurity");
        http.csrf()
                .disable()
                .authorizeHttpRequests(
                        authorization ->
                                authorization
                                        .mvcMatchers(AUTH_WHITELIST)
                                        .permitAll()
                                        .mvcMatchers("/ads/**", "/users/**")
                                        .authenticated())
                .cors()
                .and()
                .httpBasic(withDefaults());

        logger.info("HttpSecurity configured successfully");
        return http.build();
    }

    /**
     * Создает бин PasswordEncoder для кодирования паролей.
     *
     * @return PasswordEncoder для безопасного хранения паролей.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        logger.info("Creating PasswordEncoder");
        return new BCryptPasswordEncoder();
    }
}
