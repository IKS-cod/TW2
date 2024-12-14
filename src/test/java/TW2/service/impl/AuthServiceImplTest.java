package TW2.service.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import TW2.dto.RegisterDto;
import TW2.mapper.Mappers;
import TW2.model.Users;
import TW2.repository.UsersRepository;
import TW2.service.AvatarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;
import java.util.Optional;

public class AuthServiceImplTest {

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private Mappers mappers;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private AvatarService avatarService;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private PasswordEncoder encoder;

    private RegisterDto registerDto;
    private Users mockUser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Создаем тестовые данные для регистрации
        registerDto = new RegisterDto();
        registerDto.setUsername("test@example.com");
        registerDto.setPassword("password");

        mockUser = new Users();
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("encodedPassword");
    }

    @Test
    public void testLoginSuccess() {
        String userName = "test@example.com";
        String password = "password";

        UserDetails userDetails = mock(UserDetails.class);
        when(userDetailsService.loadUserByUsername(userName)).thenReturn(userDetails);
        when(userDetails.getPassword()).thenReturn("encodedPassword");
        when(encoder.matches(password, "encodedPassword")).thenReturn(true);

        boolean result = authService.login(userName, password);

        verify(userDetailsService).loadUserByUsername(userName);
        assertTrue(result);
    }

    @Test
    public void testLoginFailure_UserNotFound() {
        String userName = "unknown@example.com";
        String password = "password";

        when(userDetailsService.loadUserByUsername(userName)).thenThrow(new UsernameNotFoundException("User not found"));

        boolean result = authService.login(userName, password);

        verify(userDetailsService).loadUserByUsername(userName);
        assertFalse(result);
    }

    @Test
    public void testRegisterSuccess() throws IOException {
        when(usersRepository.findByEmail(registerDto.getUsername())).thenReturn(Optional.empty());
        when(mappers.toUsers(registerDto)).thenReturn(mockUser);
        when(encoder.encode(registerDto.getPassword())).thenReturn("encodedPassword");

        boolean result = authService.register(registerDto);

        verify(usersRepository).save(mockUser);
        verify(avatarService).saveDefaultAvatar(mockUser);
        assertTrue(result);
    }

    @Test
    public void testRegisterFailure_UserAlreadyExists() throws IOException {
        when(usersRepository.findByEmail(registerDto.getUsername())).thenReturn(Optional.of(mockUser));

        boolean result = authService.register(registerDto);

        verify(usersRepository, never()).save(any(Users.class));
        assertFalse(result);
    }
}