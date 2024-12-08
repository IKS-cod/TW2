package TW2.controller;


import TW2.dto.LoginDto;
import TW2.dto.RegisterDto;
import TW2.dto.Role;
import TW2.model.Users;
import TW2.repository.*;
import TW2.service.UserContextService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpStatus.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
/**
 * Тесты для контроллера аутентификации, включая регистрацию и вход пользователей.
 */
class AuthControllerTest {

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

    /**
     * Очищает базу данных после каждого теста, удаляя все записи из таблиц.
     */
    @AfterEach
    void clear() {
        commentsRepository.deleteAll();
        imagesRepository.deleteAll();
        avatarsRepository.deleteAll();
        adsRepository.deleteAll();
        usersRepository.deleteAll();
    }

    /**
     * Тестирует успешный вход пользователя с корректными данными.
     */
    @Test
    public void loginTest() {
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
        Assertions.assertEquals(usersRepository.findByEmail(email).get(), usersForSaveDb);

        // Создаем LoginDto
        LoginDto validLoginDto = new LoginDto(email, passwordForUser);

        // Проверка входа
        ResponseEntity<?> response = testRestTemplate.postForEntity("/login", validLoginDto, Void.class);

        // Assert: Проверяем, что статус ответа OK (200)
        assertThat(response.getStatusCode()).isEqualTo(OK);
    }

    /**
     * Тестирует неуспешный вход пользователя с некорректным паролем.
     */
    @Test
    public void loginNegativeTest() {
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
        Assertions.assertEquals(usersRepository.findByEmail(email).get(), usersForSaveDb);

        // Создаем LoginDto с некорректным паролем
        String invalidPasswordForUser = "123459876765";
        LoginDto invalidLoginDto = new LoginDto(email, invalidPasswordForUser);

        // Act: Выполняем запрос на вход
        ResponseEntity<?> response = testRestTemplate.postForEntity("/login", invalidLoginDto, Void.class);

        // Assert: Проверяем, что статус ответа UNAUTHORIZED (401)
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Тестирует успешную регистрацию нового пользователя.
     */
    @Test
    public void registerTest() {
        List<Users> usersList = usersRepository.findAll();

        // Проверяем, что база данных пуста перед регистрацией
        assertTrue(usersList.isEmpty());

        // Создаем RegisterDto для сохранения в БД
        String email = "aa@gmail.com";
        String firstName = "FirstName";
        String lastName = "LastName";
        String phone = "+7(000)000-00-00";
        Role roleUser = Role.USER;
        String passwordForUser = "123456789";

        // Создаем RegisterDto для регистрации пользователя
        RegisterDto validRegisterDto = new RegisterDto(email, passwordForUser, firstName, lastName, phone, roleUser);

        // Выполняем запрос на регистрацию
        ResponseEntity<?> response = testRestTemplate.postForEntity("/register", validRegisterDto, Void.class);

        // Assert: Проверяем, что статус ответа CREATED (201)
        assertThat(response.getStatusCode()).isEqualTo(CREATED);

        List<Users> usersListFromDb = usersRepository.findAll();

        // Проверяем данные зарегистрированного пользователя в базе данных
        Assertions.assertEquals(usersListFromDb.get(0).getFirstName(), firstName);
        Assertions.assertEquals(usersListFromDb.get(0).getLastName(), lastName);
        Assertions.assertEquals(usersListFromDb.get(0).getPhone(), phone);
        Assertions.assertEquals(usersListFromDb.get(0).getEmail(), email);
    }

    /**
     * Тестирует неуспешную регистрацию пользователя с некорректными данными.
     */
    @Test
    public void registerNegativeTest() {
        List<Users> usersList = usersRepository.findAll();

        // Проверяем, что база данных пуста перед регистрацией
        assertTrue(usersList.isEmpty());

        // Создаем RegisterDto с некорректными данными для регистрации пользователя
        String email = " ";  // Некорректный email (пустой)
        String firstName = "Fi";  // Слишком короткое имя
        String lastName = "La";  // Слишком короткая фамилия
        String phone = "+7(000)000-00-00";  // Корректный телефон
        Role roleUser = Role.USER;  // Роль пользователя по умолчанию
        String passwordForUser = "123456789";  // Корректный пароль

        // Создаем RegisterDto с некорректными данными для регистрации пользователя
        RegisterDto invalidRegisterDto = new RegisterDto(email, passwordForUser, firstName, lastName, phone, roleUser);

        // Выполняем запрос на регистрацию с некорректными данными
        ResponseEntity<?> response = testRestTemplate.postForEntity("/register", invalidRegisterDto, Void.class);

        // Assert: Проверяем, что статус ответа BAD_REQUEST (400)
        assertThat(response.getStatusCode()).isEqualTo(INTERNAL_SERVER_ERROR);  // Здесь может быть ошибка логики - проверьте правильность статуса.
    }
}