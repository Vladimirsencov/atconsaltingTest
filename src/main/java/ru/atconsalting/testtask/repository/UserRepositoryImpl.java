package ru.atconsalting.testtask.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.atconsalting.testtask.model.Role;
import ru.atconsalting.testtask.model.User;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vladimir_Sentso on 28.07.2016.
 */
@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final ResultSetExtractor<Collection<User>> usersExtractor = rs -> {
        Map<Long, User> collector = new HashMap<>();
        while (rs.next()) {
            Long id = rs.getLong("id");
            if (!collector.containsKey(id)) {
                User user = new User();
                user.setId(id);
                user.setEmail(rs.getString("email"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("password"));
                user.setRoles(EnumSet.noneOf(Role.class));
                collector.put(id, user);
            }
            Long usersId = rs.getLong("user_id");
            if (usersId > 0) {
                Role role = Role.valueOf(rs.getString("role").toUpperCase());
                collector.get(usersId).getRoles().add(role);
            }
        }
        return collector.values();
    };


    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedJdbcTemplate, SimpleJdbcInsert jdbcInsert) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedJdbcTemplate;
        this.jdbcInsert = jdbcInsert;
    }

    @Override
    public Collection<User> getAllUsers() {
        String query = "SELECT u.ID, u.EMAIL, u.USERNAME, u.PASSWORD, r.ROLE, r.USER_ID FROM USERS AS u LEFT JOIN USER_ROLES AS r ON u.ID = r.USER_ID";
        return jdbcTemplate.query(query, usersExtractor);
    }

    @Override
    @Transactional
    public User saveOrUpdateUser(User user) {
        return user.getId() == null ? saveUser(user) : updateUser(user);
    }

    private User saveUser(User user) {
        jdbcInsert.withTableName("users").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", user.getEmail());
        parameters.put("username", user.getUserName());
        parameters.put("password", user.getPassword());
        Number key = jdbcInsert.executeAndReturnKey(parameters);
        user.setId(key.longValue());
        saveUserRoles(user);
        return user;
    }

    private int[] saveUserRoles(final User user) {
        return jdbcInsert
                .withTableName("user_roles")
                .executeBatch(user.getRoles().stream()
                        .map(role -> new MapSqlParameterSource("user_id", user.getId()).addValue("role", String.valueOf(role)))
                        .toArray(MapSqlParameterSource[]::new));
    }


    private User updateUser(User user) {
        String query = "UPDATE  USERS SET EMAIL = ?, USERNAME = ?, PASSWORD = ?  WHERE ID = ?";
        jdbcTemplate.update(query, user.getEmail(), user.getUserName(), user.getPassword(), user.getId());
        updateUserRoles(user);
        return user;
    }

    private int[] updateUserRoles(User user) {
        String query = "DELETE FROM USER_ROLES  WHERE USER_ID = ?";
        jdbcTemplate.update(query, user.getId());
        return saveUserRoles(user);
    }

    @Override
    @Transactional
    public boolean deleteUser(long id) {
        return false;
    }

    @Override
    @Transactional
    public boolean deleteUserByName(String userName) {
        return false;
    }

    @Override
    @Transactional
    public boolean deleteUserByEmail(String email) {
        return false;
    }

    @Override
    public User get(Long id) {
        String query = "SELECT u.ID, u.EMAIL, u.USERNAME, u.PASSWORD, r.ROLE, r.USER_ID FROM USERS AS u LEFT JOIN USER_ROLES AS r ON u.ID = r.USER_ID WHERE u.ID = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        Collection<User> users = namedParameterJdbcTemplate.query(query, parameterSource, usersExtractor);
        return DataAccessUtils.singleResult(users);

    }

    @Override
    public User getUserByName(String userName) {
        String query = "SELECT u.ID, u.EMAIL, u.USERNAME, u.PASSWORD, r.ROLE, r.USER_ID FROM USERS AS u LEFT JOIN USER_ROLES AS r ON u.ID = r.USER_ID WHERE u.USERNAME = :userName";
        SqlParameterSource parameterSource = new MapSqlParameterSource("userName", userName);
        Collection<User> users = namedParameterJdbcTemplate.query(query, parameterSource, usersExtractor);
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public User getUserByEmail(String email) {
        String query = "SELECT u.ID, u.EMAIL, u.USERNAME, u.PASSWORD, r.ROLE, r.USER_ID FROM USERS AS u LEFT JOIN USER_ROLES AS r ON u.ID = r.USER_ID WHERE u.EMAIL= :email";
        SqlParameterSource parameterSource = new MapSqlParameterSource("email", email);
        Collection<User> users = namedParameterJdbcTemplate.query(query, parameterSource, usersExtractor);
        return DataAccessUtils.singleResult(users);
    }
}
