package TW2.controller;


import TW2.dto.NewPasswordDto;
import TW2.dto.Role;
import TW2.dto.UserDto;
import TW2.mapper.Mappers;
import TW2.model.Users;
import TW2.repository.*;
import TW2.service.AuthService;
import TW2.service.UserContextService;
import io.swagger.v3.oas.annotations.Operation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private Mappers mappers;
    @Autowired
    private AuthService authService;
    @Autowired
    private UserContextService userContextService;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private AdsRepository adsRepository;
    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private AvatarsRepository avatarsRepository;

    @Autowired
    private ImagesRepository imagesRepository;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @AfterEach
    void clear() {
        commentsRepository.deleteAll();
        imagesRepository.deleteAll();
        avatarsRepository.deleteAll();
        adsRepository.deleteAll();
        usersRepository.deleteAll();
    }

    @Test///!!!!!!!!!!!!!!!!!!МЕТОД РАБОТАЕТ!!!!!!!!!!!!!!!!!!!!!!!!!
    public void updatePasswordTest() throws IOException {
        testRestTemplate = testRestTemplate.withBasicAuth("aa@gmail.com", "123456789");
        // Поля для создания Users
        String email = "aa@gmail.com";
        String firstName = "FirstName";
        String lastName = "LastName";
        String phone = "+7(000)000-00-00";
        Role roleUser = Role.USER;
        String passwordForUser = "123456789";

        // Создание и сохранение Users с полями в БД
        Users usersForSaveDb = new Users();
        usersForSaveDb.setEmail(email);
        usersForSaveDb.setFirstName(firstName);
        usersForSaveDb.setLastName(lastName);
        usersForSaveDb.setPhone(phone);
        usersForSaveDb.setRole(roleUser);
        usersForSaveDb.setPassword(passwordEncoder.encode(passwordForUser));

        // Добавляем Users в базу данных
        usersRepository.save(usersForSaveDb);

        // Проверка наличия Users в БД
        assertEquals(usersRepository.findByEmail(email).get(), usersForSaveDb);

        NewPasswordDto newPasswordDto = new NewPasswordDto("123456789", "1234567890");
        String passwordOld = usersRepository.findByEmail(email).get().getPassword();
        ResponseEntity<Void> response = testRestTemplate.exchange(
                "/users/set_password",
                HttpMethod.POST,
                new HttpEntity<>(newPasswordDto, new HttpHeaders()),
                Void.class
        );

        // Проверка статуса ответа
        assertEquals(200, response.getStatusCodeValue());
        String passwordNew = usersRepository.findByEmail(email).get().getPassword();
        assertNotEquals(passwordOld, passwordNew);
    }

    @Test///!!!!!!!!!!!!!!!!!!МЕТОД РАБОТАЕТ!!!!!!!!!!!!!!!!!!!!!!!!!
    public void getUserTest() throws IOException {
        testRestTemplate = testRestTemplate.withBasicAuth("aa@gmail.com", "123456789");
        // Поля для создания Users
        String email = "aa@gmail.com";
        String firstName = "FirstName";
        String lastName = "LastName";
        String phone = "+7(000)000-00-00";
        Role roleUser = Role.USER;
        String passwordForUser = "123456789";

        // Создание и сохранение Users с полями в БД
        Users usersForSaveDb = new Users();
        usersForSaveDb.setEmail(email);
        usersForSaveDb.setFirstName(firstName);
        usersForSaveDb.setLastName(lastName);
        usersForSaveDb.setPhone(phone);
        usersForSaveDb.setRole(roleUser);
        usersForSaveDb.setPassword(passwordEncoder.encode(passwordForUser));

        // Добавляем Users в базу данных
        usersRepository.save(usersForSaveDb);

        // Проверка наличия Users в БД
        assertEquals(usersRepository.findByEmail(email).get(), usersForSaveDb);

        ResponseEntity<UserDto> response = testRestTemplate.getForEntity("/users/me", UserDto.class);

        // Проверка статуса ответа и содержимого
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(usersForSaveDb.getFirstName(), response.getBody().getFirstName());
        assertEquals(usersForSaveDb.getLastName(), response.getBody().getLastName());
        assertEquals(usersForSaveDb.getEmail(), response.getBody().getEmail());


    }
}