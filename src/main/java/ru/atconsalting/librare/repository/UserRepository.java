package ru.atconsalting.librare.repository;

import ru.atconsalting.librare.model.User;

import java.util.Collection;

/**
 * Created by Vladimir_Sentso on 28.07.2016.
 */
public interface UserRepository {

    Collection<User> getAllUsers();

    User saveOrUpdateUser(User user);

    boolean deleteUser(long id);

    boolean deleteUserByName(String userName);

    User get(Long id);

    User getUserByName(String userName);


}
