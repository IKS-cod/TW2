package TW2.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * DTO (Data Transfer Object) для списка объявлений.
 * <p>
 * Этот класс представляет данные, связанные с объявлениями, включая общее количество
 * объявлений и список самих объявлений.
 * </p>
 */
public class AdsDto {
    @Schema(type = "integer", format = "int32", description = "общее количество объявлений")
    private Integer count;

    @Schema(description = "Список объявлений")
    private List<AdDto> results;

    /**
     * Конструктор для создания объекта AdsDto с заданными параметрами.
     *
     * @param count   Общее количество объявлений.
     * @param results Список объявлений.
     */
    public AdsDto(Integer count, List<AdDto> results) {
        this.count = count;
        this.results = results;
    }

    /**
     * Конструктор по умолчанию.
     */
    public AdsDto() {
    }

    /**
     * Получает общее количество объявлений.
     *
     * @return Общее количество объявлений.
     */
    public Integer getCount() {
        return count;
    }

    /**
     * Устанавливает общее количество объявлений.
     *
     * @param count Общее количество объявлений.
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * Получает список объявлений.
     *
     * @return Список объектов AdDto, представляющих объявления.
     */
    public List<AdDto> getResults() {
        return results;
    }

    /**
     * Устанавливает список объявлений.
     *
     * @param results Список объектов AdDto, представляющих объявления.
     */
    public void setResults(List<AdDto> results) {
        this.results = results;
    }
}