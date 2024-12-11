package TW2.controller;

import TW2.dto.AdDto;
import TW2.dto.CreateOrUpdateAdDto;
import TW2.dto.ExtendedAdDto;
import TW2.dto.Role;
import TW2.model.Ads;
import TW2.model.Images;
import TW2.model.Users;
import TW2.repository.*;
import TW2.service.AuthService;
import TW2.service.UserContextService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AdControllerTest {
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
    public void addAdTest() throws IOException {
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

        // Настройка аутентификации
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn(email);

        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getPrincipal()).thenReturn(userDetails);

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

        // Устанавливаем заголовки для базовой аутентификации
        HttpHeaders headers = new HttpHeaders();

        String authUsername = email;
        String authPassword = passwordForUser;
        String auth = authUsername + ":" + authPassword;
        String encodedAuth = Base64Utils.encodeToString(auth.getBytes());

        headers.set("Authorization", "Basic " + encodedAuth);

        // Создаем объект CreateOrUpdateAdDto
        CreateOrUpdateAdDto createOrUpdateAdDto = new CreateOrUpdateAdDto("Title", 1000, "Description");

        // Создаем MockMultipartFile
        MockMultipartFile multipartFile = new MockMultipartFile(
                "image", // имя параметра должно совпадать с вашим контроллером
                "test-image.jpg", // имя файла
                "image/png", // тип контента
                "test image content".getBytes() // содержимое файла в байтах
        );

        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Создаем MultiValueMap для передачи параметров
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        body.add("properties", createOrUpdateAdDto); // Добавляем DTO как параметр

        body.add("image", new ByteArrayResource(multipartFile.getBytes()) {
            @Override
            public String getFilename() {
                return multipartFile.getOriginalFilename(); // Возвращаем имя файла
            }
        });

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<AdDto> response = testRestTemplate.exchange(
                "/ads", // URL для теста
                HttpMethod.POST,
                requestEntity,
                AdDto.class
        );

        // Проверяем статус ответа и содержимое
        Assertions.assertEquals(200, response.getStatusCodeValue());
        // Проверка содержимого ответа
        AdDto adDtoResponse = response.getBody();
        Assertions.assertNotNull(adDtoResponse);
        Assertions.assertEquals("Title", adDtoResponse.getTitle());
        Assertions.assertEquals(1000, adDtoResponse.getPrice());
        //Берем юзера из БД для получения id
        Users usersFromDb = usersRepository.findByEmail(email).get();
        Assertions.assertEquals(usersFromDb.getId(), adDtoResponse.getAuthor());
        // Проверка состояния базы данных: убедитесь, что объявление было добавлено в базу данных.
        Ads savedAd = adsRepository.findById(adDtoResponse.getPk()).orElse(null);
        Assertions.assertNotNull(savedAd);
        Assertions.assertEquals("Title", savedAd.getTitle());
        Assertions.assertEquals(1000, savedAd.getPrice());
        Assertions.assertEquals("Description", savedAd.getDescription());
    }


    @Test///!!!!!!!!!!!!!!!!!!МЕТОД РАБОТАЕТ!!!!!!!!!!!!!!!!!!!!!!!!!
    public void removeAdTest() throws IOException {

        // Поля для создания Users
        String email = "aa@gmail.com";
        String firstName = "FirstName";
        String lastName = "LastName";
        String phone = "+7(000)000-00-00";
        Role roleUser = Role.USER; // Предположим, что у вас есть enum Role
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

        // Настройка аутентификации
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn(email);

        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getPrincipal()).thenReturn(userDetails);

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

        // Устанавливаем заголовки для базовой аутентификации
        HttpHeaders headers = new HttpHeaders();

        String authUsername = email;
        String authPassword = passwordForUser;
        String auth = authUsername + ":" + authPassword;
        String encodedAuth = Base64Utils.encodeToString(auth.getBytes());

        headers.set("Authorization", "Basic " + encodedAuth);
        //Создаем Ads adsForDb и сохраняем в БД
        Ads adsForDb = new Ads(1, 1000, "Title", "Description");
        //Берем юзера из БД для получения id
        Users usersFromDb = usersRepository.findByEmail(email).get();
        adsForDb.setUsers(usersFromDb);
        adsRepository.save(adsForDb);

        //Получаем объявление из БД для сравнения
        Ads adsFromDb = adsRepository.findByUsersId(usersFromDb.getId()).get(0);
        //Создаем image в БД
        Images images = new Images(1, "./defaultAvatars/1.png", "pathForEndpoint", "image/png", adsFromDb);
        imagesRepository.save(images);
        MockMultipartFile multipartFile = new MockMultipartFile(
                "image", // имя параметра должно совпадать с вашим контроллером
                "test-image.jpg", // имя файла
                "image/png", // тип контента
                "test image content".getBytes() // содержимое файла в байтах
        );
        Files.write(Paths.get("./defaultAvatars/1.png"), "test image content".getBytes());

        // Сравниваем объекты, игнорируя поле id
        Assertions.assertEquals(adsForDb.getPrice(), adsFromDb.getPrice());
        Assertions.assertEquals(adsForDb.getTitle(), adsFromDb.getTitle());
        Assertions.assertEquals(adsForDb.getDescription(), adsFromDb.getDescription());

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers); // Создаем HttpEntity с заголовками

        // Выполняем DELETE запрос
        ResponseEntity<Void> response = testRestTemplate.exchange(
                "/ads/" + adsFromDb.getPk(), // URL для удаления объявления
                HttpMethod.DELETE,
                requestEntity,
                Void.class
        );

        // Проверяем статус ответа
        assertThat(response.getStatusCodeValue()).isEqualTo(200); // Ожидаем статус 200 No Content
        List<Ads> ads = adsRepository.findByUsersId(usersFromDb.getId());
        assertTrue(ads.isEmpty(), "The repository should be empty for this user.");

    }

    @Test ///!!!!!!!!!!!!!!!!!!МЕТОД РАБОТАЕТ!!!!!!!!!!!!!!!!!!!!!!!!!
    public void getAdByIdTest() {
        testRestTemplate = testRestTemplate.withBasicAuth("aa@gmail.com","123456789");

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
        Ads adsForDb = new Ads(1, 1000, "Title", "Description");
        //Берем юзера из БД для получения id
        Users usersFromDb = usersRepository.findByEmail(email).get();
        adsForDb.setUsers(usersFromDb);
        adsRepository.save(adsForDb);

        //Получаем объявление из БД для сравнения
        Ads adsFromDb = adsRepository.findByUsersId(usersFromDb.getId()).get(0);
        //Создаем image в БД
        Images images = new Images(1, "./defaultAvatars/1.png", "pathForEndpoint", "image/png", adsFromDb);
        Images imagesFromDb = imagesRepository.save(images);

        Assertions.assertNotNull(imagesFromDb);
        List<Ads> adsList = adsRepository.findAll();
        ResponseEntity<ExtendedAdDto> response = testRestTemplate.getForEntity("/ads/" + adsFromDb.getPk(), ExtendedAdDto.class);
        // Проверка статуса ответа и содержимого
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getTitle()).isEqualTo(adsForDb.getTitle());
    }



}