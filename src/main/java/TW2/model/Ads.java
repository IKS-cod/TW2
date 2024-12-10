package TW2.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Сущность для представления объявления.
 * <p>
 * Этот класс представляет данные объявления, включая идентификатор, цену, заголовок,
 * описание и связь с пользователем, который создал это объявление.
 * </p>
 */
@Entity
//@Transactional
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pk; // Уникальный идентификатор объявления

    private Integer price; // Цена объявления
    private String title; // Заголовок объявления
    private String description; // Описание объявления

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users; // Связь с пользователем, который создал объявление

    /**
     * Конструктор для создания объекта Ads с заданными параметрами.
     *
     * @param pk          уникальный идентификатор объявления.
     * @param price       цена объявления.
     * @param title       заголовок объявления.
     * @param description описание объявления.
     */
    public Ads(Integer pk, Integer price, String title, String description) {
        this.pk = pk;
        this.price = price;
        this.title = title;
        this.description = description;
    }

    /**
     * Конструктор по умолчанию.
     */
    public Ads() {
    }

    /**
     * Получает пользователя, создавшего объявление.
     *
     * @return объект Users, представляющий пользователя.
     */
    public Users getUsers() {
        return users;
    }

    /**
     * Устанавливает пользователя, создавшего объявление.
     *
     * @param users объект Users, представляющий пользователя.
     */
    public void setUsers(Users users) {
        this.users = users;
    }

    /**
     * Получает уникальный идентификатор объявления.
     *
     * @return уникальный идентификатор объявления.
     */
    public Integer getPk() {
        return pk;
    }

    /**
     * Устанавливает уникальный идентификатор объявления.
     *
     * @param pk уникальный идентификатор объявления.
     */
    public void setPk(Integer pk) {
        this.pk = pk;
    }

    /**
     * Получает цену объявления.
     *
     * @return цена объявления.
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * Устанавливает цену объявления.
     *
     * @param price цена объявления.
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * Получает заголовок объявления.
     *
     * @return заголовок объявления.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Устанавливает заголовок объявления.
     *
     * @param title заголовок объявления.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Получает описание объявления.
     *
     * @return описание объявления.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Устанавливает описание объявления.
     *
     * @param description описание объявления.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ads ads = (Ads) o;
        return Objects.equals(pk, ads.pk) &&
                Objects.equals(price, ads.price) &&
                Objects.equals(title, ads.title) &&
                Objects.equals(description, ads.description);
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