package ru.atconsalting.testtask.service;

import ru.atconsalting.testtask.model.User;

import java.util.Collection;

/**
 * Created by Vladimir_Sentso on 29.07.2016.
 */
public interface UserService {

    Collection<User> getAllUsers();

    User saveUser(User user);

    boolean deleteUser(long id);

    boolean deleteUserByName(String userName);

    boolean deleteUserByEmail(String email);

    User get(long id);

    User getUserByName(String userName);

    User getUserByEmail(String email);
}
