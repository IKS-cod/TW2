package TW2.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Objects;

public class AdsDto {
    @Schema(type = "integer", format = "int32", description = "общее количество объявлений")
    private Integer count;

    @Schema(description = "Список объявлений")
    private List<AdDto> results;

    public AdsDto(Integer count, List<AdDto> results) {
        this.count = count;
        this.results = results;
    }
    public AdsDto() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<AdDto> getResults() {
        return results;
    }

    public void setResults(List<AdDto> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdsDto)) return false;
        AdsDto adsDto = (AdsDto) o;
        return Objects.equals(getCount(), adsDto.getCount()) && Objects.equals(getResults(), adsDto.getResults());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCount(), getResults());
    }

    @Override
    public String toString() {
        return "Ads{" +
                "count=" + count +
                ", results=" + results +
                '}';
    }
}