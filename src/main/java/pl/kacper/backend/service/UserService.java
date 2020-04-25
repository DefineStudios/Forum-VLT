package pl.kacper.backend.service;

import pl.kacper.backend.model.User;
import pl.kacper.backend.repository.UserRepository;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserService {
    private UserRepository userRepository = new UserRepository(new File("src/main/resources/users.json"));

    public void save(User user){
        try {
            userRepository.save(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<User> getAll(){
        try {
            return userRepository.getAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashSet<>();
    }
}
