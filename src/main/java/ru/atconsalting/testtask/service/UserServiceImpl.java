package ru.atconsalting.testtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.atconsalting.testtask.model.User;
import ru.atconsalting.testtask.repository.UserRepository;

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
        return null;
    }

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public boolean deleteUser(long id) {
        return false;
    }

    @Override
    public boolean deleteUserByName(String userName) {
        return false;
    }

    @Override
    public boolean deleteUserByEmail(String email) {
        return false;
    }

    @Override
    public User get(long id) {
        return null;
    }

    @Override
    public User getUserByName(String userName) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }
}
