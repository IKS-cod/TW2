package TW2.controller;

import TW2.dto.Role;
import TW2.model.Ads;
import TW2.model.Avatars;
import TW2.model.Images;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AvatarAndImageControllerTest {

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

    @Test
    public void getImageFromFsTest() throws IOException {
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
        //Создаем Ads adsForDb и сохраняем в БД
        Ads adsForDb = new Ads(1, 1000, "Title", "Description");
        //Берем юзера из БД для получения id
        Users usersFromDb = usersRepository.findByEmail(email).get();
        adsForDb.setUsers(usersFromDb);
        adsRepository.save(adsForDb);
        //Получаем объявление из БД для сравнения
        Ads adsFromDb = adsRepository.findByUsersId(usersFromDb.getId()).get(0);
        //Создаем и сохраняем в БД Images images
        Images images = new Images(1, "./defaultAvatars/1.png", "pathForEndpoint", "image/png", adsFromDb);
        imagesRepository.save(images);
        String strFilePath = "./defaultAvatars/1.png";
        //   images.setFilePath(strFilePath);
        Files.write(Paths.get(strFilePath), "test image content".getBytes());
        //Создаем String pathForEndpoint
        String pathForEndpoint = String.valueOf(imagesRepository.findByAdsPk(adsFromDb.getPk()).getId());
        ResponseEntity<byte[]> response = testRestTemplate.getForEntity("/image/image/{id}", byte[].class, pathForEndpoint);
        // Assert: Check that the response status is OK (200)
        assertThat(response.getStatusCode()).isEqualTo(OK);
        // Optionally, assert that the body is not null and contains expected data
        byte[] imageData = response.getBody();
        assertThat(imageData.length).isEqualTo("test image content".getBytes().length);
        Files.delete(Paths.get(strFilePath));
    }
    @Test //!!!!!!!!!!!!!!!!!!!!МЕТОД РАБОТАЕТ !!!!!!!!!!!!!!!!!!!!!!!!
    public void getAvatarFromFsTest() throws IOException {
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
        //Получаем юзера из БД
        Users userFromDb = usersRepository.findByEmail(email).get();
        //Создаем Avatars avatar и сохраняем в БД
        Avatars avatar = new Avatars(1, "./defaultAvatars/1av.png", "pathForEndpoint", "image/png", userFromDb);
        avatarsRepository.save(avatar);
        String strFilePath = "./defaultAvatars/1av.png";
        Files.write(Paths.get(strFilePath), "test image content".getBytes());
        String pathForEndpoint = String.valueOf(userFromDb.getId());
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("pathForEndpoint из теста " + pathForEndpoint);
        ResponseEntity<byte[]> response = testRestTemplate.getForEntity("/image/avatar/{id}", byte[].class, pathForEndpoint);
        // Assert: Check that the response status is OK (200)
        assertThat(response.getStatusCode()).isEqualTo(OK);
        // Optionally, assert that the body is not null and contains expected data
        byte[] imageData = response.getBody();
        assertThat(imageData.length).isEqualTo("test image content".getBytes().length);
        Files.delete(Paths.get(strFilePath));

    }
}
