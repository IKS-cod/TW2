package TW2.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Objects;

public class CommentsDto {
    @Schema(type = "integer", format = "int32", description = "общее количество комментариев")
    private Integer count;

    @Schema(description = "Список комментариев")
    private List<CommentDto> results;

    public CommentsDto(Integer count, List<CommentDto> results) {
        this.count = count;
        this.results = results;
    }
    public CommentsDto() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<CommentDto> getResults() {
        return results;
    }

    public void setResults(List<CommentDto> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentsDto commentsDto = (CommentsDto) o;
        return Objects.equals(count, commentsDto.count) && Objects.equals(results, commentsDto.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, results);
    }

    @Override
    public String toString() {
        return "Comments{" +
                "count=" + count +
                ", results=" + results +
                '}';
    }
}
