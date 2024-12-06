package TW2.repository;

import TW2.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностями пользователей.
 * <p>
 * Этот интерфейс предоставляет методы для выполнения операций CRUD
 * с пользователями в базе данных, а также дополнительные методы поиска.
 * </p>
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    /**
     * Находит пользователя по его email.
     *
     * @param email логин пользователя (email).
     * @return объект Optional, содержащий найденного пользователя,
     *         или пустой объект, если пользователь не найден.
     */
    Optional<Users> findByEmail(String email);
}
