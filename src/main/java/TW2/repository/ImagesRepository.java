package TW2.repository;

import TW2.model.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для работы с сущностями изображений.
 * <p>
 * Этот интерфейс предоставляет методы для выполнения операций CRUD
 * с изображениями в базе данных, а также дополнительные методы поиска и удаления.
 * </p>
 */
@Repository
public interface ImagesRepository extends JpaRepository<Images, Integer> {

    /**
     * Находит изображение по идентификатору объявления.
     *
     * @param adsId уникальный идентификатор объявления.
     * @return объект Images, соответствующий указанному объявлению,
     *         или null, если не найдено.
     */
    Images findByAdsPk(Integer adsId);

    /**
     * Находит все изображения по списку идентификаторов объявлений.
     *
     * @param adsPks список уникальных идентификаторов объявлений.
     * @return список объектов Images, соответствующих указанным объявлениям.
     */
    List<Images> findAllByAdsPkIn(List<Integer> adsPks);

    /**
     * Удаляет все изображения, связанные с указанным объявлением.
     *
     * @param adId уникальный идентификатор объявления.
     */
    void deleteByAdsPk(Integer adId);
}
