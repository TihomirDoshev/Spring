package bg.softuni.springdatalab.services;

import bg.softuni.springdatalab.models.entities.User;

public interface UserService {
    void registerUser(User user);

    User findUserById(int id);
}
