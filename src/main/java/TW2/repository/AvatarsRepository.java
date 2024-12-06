package TW2.repository;

import TW2.model.Avatars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Репозиторий для работы с сущностями аватаров.
 * <p>
 * Этот интерфейс предоставляет методы для выполнения операций CRUD
 * с аватарами в базе данных, а также дополнительные методы поиска.
 * </p>
 */
@Repository
public interface AvatarsRepository extends JpaRepository<Avatars, Integer> {

    /**
     * Находит аватар по идентификатору пользователя.
     *
     * @param userId уникальный идентификатор пользователя.
     * @return объект Optional, содержащий аватар, соответствующий указанному пользователю,
     *         или пустой объект, если аватар не найден.
     */
    Optional<Avatars> findByUsersId(Integer userId);

    /**
     * Находит список аватаров по множеству идентификаторов пользователей.
     *
     * @param userIds множество уникальных идентификаторов пользователей.
     * @return список аватаров, принадлежащих указанным пользователям.
     */
    @Query("SELECT a FROM Avatars a WHERE a.users.id IN :userIds")
    List<Avatars> findByUsersIdIn(@Param("userIds") Set<Integer> userIds);
}