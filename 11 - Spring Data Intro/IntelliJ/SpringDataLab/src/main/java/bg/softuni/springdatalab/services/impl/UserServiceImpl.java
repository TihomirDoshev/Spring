package bg.softuni.springdatalab.services.impl;

import bg.softuni.springdatalab.models.entities.User;
import bg.softuni.springdatalab.repositories.UserRepository;
import bg.softuni.springdatalab.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void registerUser(User user) {
        this.userRepository.saveAndFlush(user);

    }

    @Override
    public User findUserById(int id) {
        Optional<User> optional = this.userRepository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        System.out.println("No such user with given id - "+ id);
        return null;
    }
}
