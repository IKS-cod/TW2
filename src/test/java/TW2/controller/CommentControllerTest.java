package TW2.controller;
import TW2.dto.CommentDto;
import TW2.dto.CreateOrUpdateCommentDto;
import TW2.dto.Role;
import TW2.model.Ads;
import TW2.model.Avatars;
import TW2.model.Comments;
import TW2.model.Users;
import TW2.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;

import java.util.List;

import static TW2.dto.Role.USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CommentControllerTest {
    @Autowired
    private UserDetailsService userDetailsService;
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

    @BeforeEach
    public void setup() {
        // Set up an admin user manually
        Authentication auth = new UsernamePasswordAuthenticationToken("admin", "password",
                AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @AfterEach
    void clear() {
        commentsRepository.deleteAll();
        imagesRepository.deleteAll();
        avatarsRepository.deleteAll();
        adsRepository.deleteAll();
        usersRepository.deleteAll();
    }


    @Test///!!!!!!!!!!!!!!!!!!!!!МЕТОД РАБОТАЕТ!!!!!!!!!!!!!!!!!!!!!!!
    public void addCommentTest() {
        //Поля для создания Users
        Integer idUser = null;
        String email = "aa@gmail.com";
        String firstName = "FirstName";
        String lastName = "LastName";
        String phone = "+7(000)000-00-00";
        Role roleUser = USER;
        String passwordForUser = "123456789";

        //Создание и сохранение Users с полями в БД
        Users usersForSaveDb = new Users();
        usersForSaveDb.setId(idUser);
        usersForSaveDb.setEmail(email);
        usersForSaveDb.setFirstName(firstName);
        usersForSaveDb.setLastName(lastName);
        usersForSaveDb.setPhone(phone);
        usersForSaveDb.setRole(roleUser);
        usersForSaveDb.setPassword(passwordEncoder.encode(passwordForUser));

        //Добавляем Users в базу данных
        usersRepository.save(usersForSaveDb);

        //Проверка наличия Users в БД
        assertEquals(usersRepository.findByEmail(email).get(), usersForSaveDb);
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

        //Получаем Users из БД для получения id
        Users usersFromDB = usersRepository.findByEmail(email).get();

        //Поля для создания Ads
        Integer pkAds = null;
        Integer price = 1000;
        String title = "Title";
        String description = "Description";

        //Создание и сохранение Ads с полями в БД
        Ads adsForSaveDb = new Ads();
        adsForSaveDb.setPk(pkAds);
        adsForSaveDb.setPrice(price);
        adsForSaveDb.setTitle(title);
        adsForSaveDb.setDescription(description);
        adsForSaveDb.setUsers(usersFromDB);
        adsRepository.save(adsForSaveDb);

        //Создаем CreateOrUpdateCommentDto
        CreateOrUpdateCommentDto createOrUpdateCommentDto = new CreateOrUpdateCommentDto();
        String textForCreateOrUpdateCommentDto = "CreateOrUpdateCommentDto";
        createOrUpdateCommentDto.setText(textForCreateOrUpdateCommentDto);

        //Получаем из базы объявление List<Ads> чтобы получить его pk
        List<Ads> adsList = adsRepository.findByUsersId(usersFromDB.getId());
        Integer idAds = adsList.get(0).getPk();

        //Создаем аватарку для пользователя которого создали
        Avatars avatarsForUser = new Avatars();
        avatarsForUser.setId(null);
        avatarsForUser.setFilePath("defaultAvatars/java.png");
        avatarsForUser.setPathForEndpoint("/image/avatar/" + usersFromDB.getId());
        avatarsForUser.setMediaType("image/png");
        avatarsForUser.setUsers(usersFromDB);

        headers.setContentType(MediaType.APPLICATION_JSON);

        // Prepare the request entity.
        HttpEntity<CreateOrUpdateCommentDto> requestEntity = new HttpEntity<>(createOrUpdateCommentDto, headers);

        // Send POST request to add a comment.
        ResponseEntity<CommentDto> responseEntity = testRestTemplate.exchange(
                "/ads/" + idAds + "/comments",
                HttpMethod.POST,
                requestEntity,
                CommentDto.class);

        // Assert the response status and body.
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200); // Assuming 201 Created is expected
        assertThat(responseEntity.getBody()).isNotNull();
        List<Comments> comments = commentsRepository.findByAdsPk(idAds);
        assertEquals(textForCreateOrUpdateCommentDto, comments.get(0).getText());

    }
}