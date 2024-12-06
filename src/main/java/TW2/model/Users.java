package TW2.model;

import TW2.dto.Role;

import javax.persistence.*;
import java.util.Objects;

/**
 * Сущность для представления пользователя в системе.
 * <p>
 * Этот класс содержит информацию о пользователе, включая его идентификатор,
 * контактные данные, роль и пароль.
 * </p>
 */
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; // Уникальный идентификатор пользователя

    private String email; // Логин пользователя (email)

    private String firstName; // Имя пользователя

    private String lastName; // Фамилия пользователя

    private String phone; // Телефон пользователя

    @Enumerated(EnumType.STRING)
    private Role role; // Роль пользователя (ADMIN, USER)

    private String password; // Пароль пользователя

    /**
     * Конструктор для создания объекта Users с заданными параметрами.
     *
     * @param id        уникальный идентификатор пользователя.
     * @param email     логин пользователя (email).
     * @param firstName имя пользователя.
     * @param lastName  фамилия пользователя.
     * @param phone     телефон пользователя.
     * @param role      роль пользователя.
     * @param password  пароль пользователя.
     */
    public Users(Integer id, String email, String firstName, String lastName, String phone, Role role, String password) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.role = role;
        this.password = password;
    }

    /**
     * Конструктор по умолчанию.
     */
    public Users() {
    }

    /**
     * Получает пароль пользователя.
     *
     * @return пароль пользователя.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Устанавливает пароль пользователя.
     *
     * @param password пароль пользователя.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Получает уникальный идентификатор пользователя.
     *
     * @return уникальный идентификатор пользователя.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор пользователя.
     *
     * @param id уникальный идентификатор пользователя.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Получает логин пользователя (email).
     *
     * @return логин пользователя (email).
     */
    public String getEmail() {
        return email;
    }

    /**
     * Устанавливает логин пользователя (email).
     *
     * @param email логин пользователя (email).
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Получает имя пользователя.
     *
     * @return имя пользователя.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Устанавливает имя пользователя.
     *
     * @param firstName имя пользователя.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Получает фамилию пользователя.
     *
     * @return фамилия пользователя.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Устанавливает фамилию пользователя.
     *
     * @param lastName фамилия пользователя.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Получает телефон пользователя.
     *
     * @return телефон пользователя.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Устанавливает телефон пользователя.
     *
     * @param phone телефон пользователя.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Получает роль пользователя.
     *
     * @return роль пользователя (ADMIN, USER).
     */
    public Role getRole() {
        return role;
    }

    /**
     * Устанавливает роль пользователя.
     *
     * @param role роль пользователя (ADMIN, USER).
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Проверяет равенство двух объектов Users.
     *
     *@param o объект для сравнения.
     *@return true, если объекты равны; false в противном случае.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(id, users.id) &&
                Objects.equals(email, users.email) &&
                Objects.equals(firstName, users.firstName) &&
                Objects.equals(lastName, users.lastName) &&
                Objects.equals(phone, users.phone) &&
                role == users.role &&
                Objects.equals(password, users.password);
    }

    /**
     *@return хэш-код объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, email, firstName, lastName, phone, role, password);
    }

    /**
     *@return строковое представление объекта.
     */
    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                ", password='" + password + '\'' +
                '}';
    }

}
