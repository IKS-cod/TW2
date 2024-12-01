package TW2.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pk;
    private Integer price;
    private String title;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="users_id")
    private Users users;

    public Ads(Integer pk, Integer price, String title, String description) {
        this.pk = pk;
        this.price = price;
        this.title = title;
        this.description = description;
    }

    public Ads() {
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ads ads = (Ads) o;
        return Objects.equals(pk, ads.pk) && Objects.equals(price, ads.price) && Objects.equals(title, ads.title) && Objects.equals(description, ads.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, price, title, description);
    }

    @Override
    public String toString() {
        return "Ads{" +
                "pk=" + pk +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}