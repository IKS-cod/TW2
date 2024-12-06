package TW2.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * DTO (Data Transfer Object) для списка комментариев.
 * <p>
 * Этот класс представляет данные, связанные с комментариями, включая общее количество
 * комментариев и список самих комментариев.
 * </p>
 */
public class CommentsDto {
    @Schema(type = "integer", format = "int32", description = "общее количество комментариев")
    private Integer count;

    @Schema(description = "Список комментариев")
    private List<CommentDto> results;

    /**
     * Конструктор для создания объекта CommentsDto с заданными параметрами.
     *
     * @param count   Общее количество комментариев.
     * @param results Список комментариев.
     */
    public CommentsDto(Integer count, List<CommentDto> results) {
        this.count = count;
        this.results = results;
    }

    /**
     * Конструктор по умолчанию.
     */
    public CommentsDto() {
    }

    /**
     * Получает общее количество комментариев.
     *
     * @return Общее количество комментариев.
     */
    public Integer getCount() {
        return count;
    }

    /**
     * Устанавливает общее количество комментариев.
     *
     * @param count Общее количество комментариев.
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * Получает список комментариев.
     *
     * @return Список объектов CommentDto, представляющих комментарии.
     */
    public List<CommentDto> getResults() {
        return results;
    }

    /**
     * Устанавливает список комментариев.
     *
     * @param results Список объектов CommentDto, представляющих комментарии.
     */
    public void setResults(List<CommentDto> results) {
        this.results = results;
    }
}