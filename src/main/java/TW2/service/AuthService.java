package TW2.service;


import TW2.dto.RegisterDto;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(RegisterDto registerDto);
}
