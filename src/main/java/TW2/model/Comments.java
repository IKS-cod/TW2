package TW2.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Сущность для представления комментариев.
 * <p>
 * Этот класс содержит информацию о комментарии, включая уникальный идентификатор,
 * текст комментария, дату создания, а также связи с пользователями и объявлениями.
 * </p>
 */
@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pk; // Уникальный идентификатор комментария

    private String text; // Текст комментария

    @Column(name = "createdat")
    private Long createdAt; // Дата создания комментария

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users; // Связь с пользователем, который оставил комментарий

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ads_id")
    private Ads ads; // Связь с объявлением, к которому относится комментарий

    /**
     * Конструктор для создания объекта Comments с заданными параметрами.
     *
     * @param pk        уникальный идентификатор комментария.
     * @param text      текст комментария.
     * @param createdAt дата создания комментария.
     */
    public Comments(Integer pk, String text, Long createdAt) {
        this.pk = pk;
        this.text = text;
        this.createdAt = createdAt;
    }

    /**
     * Конструктор по умолчанию.
     */
    public Comments() {
    }

    /**
     * Получает объявление, к которому относится комментарий.
     *
     * @return объект Ads, представляющий объявление.
     */
    public Ads getAds() {
        return ads;
    }

    /**
     * Устанавливает объявление, к которому относится комментарий.
     *
     * @param ads объект Ads, представляющий объявление.
     */
    public void setAds(Ads ads) {
        this.ads = ads;
    }

    /**
     * Получает пользователя, который оставил комментарий.
     *
     * @return объект Users, представляющий пользователя.
     */
    public Users getUsers() {
        return users;
    }

    /**
     * Устанавливает пользователя, который оставил комментарий.
     *
     * @param users объект Users, представляющий пользователя.
     */
    public void setUsers(Users users) {
        this.users = users;
    }

    /**
     * Получает уникальный идентификатор комментария.
     *
     * @return уникальный идентификатор комментария.
     */
    public Integer getPk() {
        return pk;
    }

    /**
     * Устанавливает уникальный идентификатор комментария.
     *
     * @param pk уникальный идентификатор комментария.
     */
    public void setPk(Integer pk) {
        this.pk = pk;
    }

    /**
     * Получает текст комментария.
     *
     * @return текст комментария.
     */
    public String getText() {
        return text;
    }

    /**
     * Устанавливает текст комментария.
     *
     * @param text текст комментария.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Получает дату создания комментария.
     *
     * @return дата создания комментария в миллисекундах с 01.01.1970.
     */
    public Long getCreatedAt() {
        return createdAt;
    }

    /**
     * Устанавливает дату создания комментария.
     *
     * @param createdAt дата создания комментария в миллисекундах с 01.01.1970.
     */
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comments comments = (Comments) o;
        return Objects.equals(pk, comments.pk) &&
                Objects.equals(text, comments.text) &&
                Objects.equals(createdAt, comments.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, text, createdAt);
    }

    @Override
    public String toString() {
        return "Comments{" +
                "pk=" + pk +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
