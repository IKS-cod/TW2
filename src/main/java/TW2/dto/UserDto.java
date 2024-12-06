package TW2.dto;


import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO (Data Transfer Object) для представления информации о пользователе.
 * <p>
 * Этот класс содержит данные пользователя, включая идентификатор, логин, имя, фамилию,
 * телефон, роль и ссылку на аватар.
 * </p>
 */
public class UserDto {
    @Schema(type = "integer", format = "int32", description = "id пользователя")
    private Integer id;

    @Schema(type = "string", description = "логин пользователя")
    private String email;

    @Schema(type = "string", description = "имя пользователя")
    private String firstName;

    @Schema(type = "string", description = "фамилия пользователя")
    private String lastName;

    @Schema(type = "string", description = "телефон пользователя")
    private String phone;

    @Schema(type = "string", description = "роль пользователя", allowableValues = {"USER", "ADMIN"})
    private Role role;

    @Schema(type = "string", description = "ссылка на аватар пользователя")
    private String image;

    /**
     * Конструктор для создания объекта UserDto с заданными параметрами.
     *
     * @param id        id пользователя.
     * @param email     логин пользователя.
     * @param firstName имя пользователя.
     * @param lastName  фамилия пользователя.
     * @param phone     телефон пользователя.
     * @param role      роль пользователя.
     * @param image     ссылка на аватар пользователя.
     */
    public UserDto(Integer id,
                   String email,
                   String firstName,
                   String lastName,
                   String phone,
                   Role role,
                   String image) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.role = role;
        this.image = image;
    }

    /**
     * Конструктор по умолчанию.
     */
    public UserDto() {
    }

    /**
     * Получает id пользователя.
     *
     * @return id пользователя.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Устанавливает id пользователя.
     *
     * @param id id пользователя.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Получает логин пользователя.
     *
     * @return логин пользователя.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Устанавливает логин пользователя.
     *
     * @param email логин пользователя.
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
     * @return роль пользователя.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Устанавливает роль пользователя.
     *
     * @param role роль пользователя.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Получает ссылку на аватар пользователя.
     *
     *@return ссылка на аватар.
     */
    public String getImage() {
        return image;
    }

    /**
     *@param image ссылка на аватар.
     */
    public void setImage(String image) {
        this.image= image;
    }
}

