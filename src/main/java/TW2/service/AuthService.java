package TW2.service;


import TW2.dto.Register;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(Register register);
}
