package TW2.repository;

import TW2.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для работы с сущностями комментариев.
 * <p>
 * Этот интерфейс предоставляет методы для выполнения операций CRUD
 * с комментариями в базе данных, а также дополнительные методы поиска и удаления.
 * </p>
 */
@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer> {

    /**
     * Находит список комментариев по идентификатору объявления.
     *
     * @param adsId уникальный идентификатор объявления.
     * @return список комментариев, связанных с указанным объявлением.
     */
    List<Comments> findByAdsPk(Integer adsId);

    /**
     * Удаляет комментарий по идентификатору объявления и идентификатору комментария.
     *
     * @param adId уникальный идентификатор объявления.
     * @param pk   уникальный идентификатор комментария.
     */
    void deleteByAdsPkAndPk(Integer adId, Integer pk);

    /**
     * Находит комментарий по идентификатору объявления и идентификатору комментария.
     *
     * @param adsId уникальный идентификатор объявления.
     * @param pk    уникальный идентификатор комментария.
     * @return объект Comments, соответствующий указанному объявлению и комментарию,
     * или null, если не найден.
     */
    Comments findByAdsPkAndPk(Integer adsId, Integer pk);

    /**
     * Удаляет все комментарии, связанные с указанным объявлением.
     *
     * @param adId уникальный идентификатор объявления.
     */
    void deleteByAdsPk(Integer adId);

    /**
     * Проверяет существование комментария по идентификатору объявления и идентификатору комментария.
     *
     * @param adId      уникальный идентификатор объявления.
     * @param commentId уникальный идентификатор комментария.
     * @return true, если комментарий существует; иначе false.
     */
    boolean existsByAdsPkAndPk(Integer adId, Integer commentId);
}