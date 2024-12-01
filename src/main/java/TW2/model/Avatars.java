package TW2.model;

import javax.persistence.*;

@Entity
@Table(name="avatars")
public class Avatars {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "filepath")
    private String filePath;
    @Column(name = "filesize")
    private long fileSize;
    @Column(name = "mediatype")
    private String mediaType;
    private byte[] data;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    public Avatars(Integer id) {
        this.id = id;
    }

    public Avatars() {
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}