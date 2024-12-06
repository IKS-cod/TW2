package TW2.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Сущность для представления аватара пользователя.
 * <p>
 * Этот класс содержит информацию о файле аватара, включая его путь, тип медиа,
 * а также связь с пользователем, которому принадлежит аватар.
 * </p>
 */
@Entity
@Table(name = "avatars")
public class Avatars {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; // Уникальный идентификатор аватара

    @Column(name = "filepath")
    private String filePath; // Путь к файлу аватара

    @Column(name = "pathforendpoint")
    private String pathForEndpoint; // Путь для доступа к аватару через API

    @Column(name = "mediatype")
    private String mediaType; // Тип медиа (например, image/png)

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users; // Связь с пользователем, которому принадлежит аватар

    /**
     * Конструктор по умолчанию.
     */
    public Avatars() {
    }

    /**
     * Получает путь для доступа к аватару через API.
     *
     * @return путь для доступа к аватару.
     */
    public String getPathForEndpoint() {
        return pathForEndpoint;
    }

    /**
     * Устанавливает путь для доступа к аватару через API.
     *
     * @param pathForEndpoint путь для доступа к аватару.
     */
    public void setPathForEndpoint(String pathForEndpoint) {
        this.pathForEndpoint = pathForEndpoint;
    }

    /**
     * Получает путь к файлу аватара.
     *
     * @return путь к файлу аватара.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Устанавливает путь к файлу аватара.
     *
     * @param filePath путь к файлу аватара.
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Получает тип медиа файла аватара.
     *
     * @return тип медиа (например, image/png).
     */
    public String getMediaType() {
        return mediaType;
    }

    /**
     * Устанавливает тип медиа файла аватара.
     *
     * @param mediaType тип медиа (например, image/png).
     */
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    /**
     * Получает пользователя, которому принадлежит аватар.
     *
     * @return объект Users, представляющий пользователя.
     */
    public Users getUsers() {
        return users;
    }

    /**
     * Устанавливает пользователя, которому принадлежит аватар.
     *
     * @param users объект Users, представляющий пользователя.
     */
    public void setUsers(Users users) {
        this.users = users;
    }

    /**
     * Получает уникальный идентификатор аватара.
     *
     * @return уникальный идентификатор аватара.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор аватара.
     *
     * @param id уникальный идентификатор аватара.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avatars avatars = (Avatars) o;
        return Objects.equals(id, avatars.id) && Objects.equals(filePath, avatars.filePath) && Objects.equals(pathForEndpoint, avatars.pathForEndpoint) && Objects.equals(mediaType, avatars.mediaType) && Objects.equals(users, avatars.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filePath, pathForEndpoint, mediaType, users);
    }

    @Override
    public String toString() {
        return "Avatars{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", pathForEndpoint='" + pathForEndpoint + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", users=" + users +
                '}';
    }
}
