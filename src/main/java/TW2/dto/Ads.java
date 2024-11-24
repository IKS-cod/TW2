package TW2.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Ads {
    @Schema(type = "integer", format = "int32", description = "общее количество объявлений")
    private Integer count;

    @Schema(description = "Список объявлений")
    private List<Ad> results;

    public Ads(Integer count, List<Ad> results) {
        this.count = count;
        this.results = results;
    }
    public Ads() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Ad> getResults() {
        return results;
    }

    public void setResults(List<Ad> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ads)) return false;
        Ads ads = (Ads) o;
        return Objects.equals(getCount(), ads.getCount()) && Objects.equals(getResults(), ads.getResults());
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