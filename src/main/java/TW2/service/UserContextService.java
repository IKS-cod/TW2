package TW2.service;

import TW2.exception.UserNotAuthenticatedException;
import TW2.exception.UserNotFoundException;
import TW2.model.Users;
import TW2.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Сервис для управления контекстом пользователя.
 * <p>
 * Этот класс предоставляет методы для получения текущего аутентифицированного пользователя
 * и его данных из базы данных.
 * </p>
 */
@Service
public class UserContextService {
    private static final Logger logger = LoggerFactory.getLogger(UserContextService.class);
    private final UsersRepository usersRepository;

    public UserContextService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    /**
     * Получает текущего аутентифицированного пользователя.
     *
     * @return объект UserDetails текущего пользователя.
     * @throws UserNotAuthenticatedException если пользователь не аутентифицирован.
     */
    public UserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            logger.warn("Attempt to access current user without authentication.");
            throw new UserNotAuthenticatedException("No authenticated user found");
        }
        logger.info("Current user retrieved: {}", authentication.getName());
        return (UserDetails) authentication.getPrincipal();
    }

    /**
     * Получает текущего аутентифицированного пользователя из базы данных.
     *
     * @return объект Users текущего пользователя.
     * @throws UserNotFoundException если пользователь не найден в базе данных.
     */
    public Users getCurrentUserFromDb() {
        String username = getCurrentUser().getUsername();
        logger.info("Fetching user from database with email: {}", username);

        return usersRepository.findByEmail(username)
                .orElseThrow(() -> {
                    logger.error("User not found with email: {}", username);
                    return new UserNotFoundException("User not found with email: " + username);
                });
    }
}
