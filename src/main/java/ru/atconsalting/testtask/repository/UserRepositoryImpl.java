package ru.atconsalting.testtask.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.atconsalting.testtask.model.User;

import java.util.Collection;

/**
 * Created by Vladimir_Sentso on 28.07.2016.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedJdbcTemplate;
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
