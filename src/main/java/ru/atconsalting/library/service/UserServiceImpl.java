package ru.atconsalting.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.atconsalting.library.model.User;
import ru.atconsalting.library.repository.UserRepository;

import java.util.Collection;

/**
 * Created by Vladimir_Sentso on 29.07.2016.
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.saveOrUpdateUser(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        return userRepository.deleteUser(id);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.saveOrUpdateUser(user);
    }


    @Override
    public User get(long id) {
        return userRepository.get(id);
    }

    @Override
    public User getUserByName(String userName) {
        return userRepository.getUserByName(userName);
    }


}
