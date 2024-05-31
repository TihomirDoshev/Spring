package bg.softuni.mobilele.service;

import bg.softuni.mobilele.models.dto.UserRegistrationDTO;

public interface UserService {
    void registerUser(UserRegistrationDTO userRegistrationDTO);
}
