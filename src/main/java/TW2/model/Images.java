package TW2.model;

import javax.persistence.*;

/**
 * Сущность для представления изображений, связанных с объявлениями.
 * <p>
 * Этот класс содержит информацию о файле изображения, включая его путь, тип медиа,
 * а также связь с объявлением, к которому это изображение относится.
 * </p>
 */
@Entity
@Table(name = "images")
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; // Уникальный идентификатор изображения

    @Column(name = "filepath")
    private String filePath; // Путь к файлу изображения

    @Column(name = "pathforendpoint")
    private String pathForEndpoint; // Путь для доступа к изображению через API

    @Column(name = "mediatype")
    private String mediaType; // Тип медиа (например, image/png)

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ads_id")
    private Ads ads; // Связь с объявлением, к которому относится изображение

    /**
     * Конструктор для создания объекта Images с заданным идентификатором.
     *
     * @param id уникальный идентификатор изображения.
     */
    public Images(Integer id) {
        this.id = id;
    }

    /**
     * Конструктор по умолчанию.
     */
    public Images() {
    }

    /**
     * Получает путь для доступа к изображению через API.
     *
     * @return путь для доступа к изображению.
     */
    public String getPathForEndpoint() {
        return pathForEndpoint;
    }

    /**
     * Устанавливает путь для доступа к изображению через API.
     *
     * @param pathForEndpoint путь для доступа к изображению.
     */
    public void setPathForEndpoint(String pathForEndpoint) {
        this.pathForEndpoint = pathForEndpoint;
    }

    /**
     * Получает путь к файлу изображения.
     *
     * @return путь к файлу изображения.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Устанавливает путь к файлу изображения.
     *
     * @param filePath путь к файлу изображения.
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Получает тип медиа файла изображения.
     *
     * @return тип медиа (например, image/png).
     */
    public String getMediaType() {
        return mediaType;
    }

    /**
     * Устанавливает тип медиа файла изображения.
     *
     * @param mediaType тип медиа (например, image/png).
     */
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    /**
     * Получает объявление, к которому относится изображение.
     *
     * @return объект Ads, представляющий объявление.
     */
    public Ads getAds() {
        return ads;
    }

    /**
     * Устанавливает объявление, к которому относится изображение.
     *
     * @param ads объект Ads, представляющий объявление.
     */
    public void setAds(Ads ads) {
        this.ads = ads;
    }

    /**
     * Получает уникальный идентификатор изображения.
     *
     * @return уникальный идентификатор изображения.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор изображения.
     *
     * @param id уникальный идентификатор изображения.
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
