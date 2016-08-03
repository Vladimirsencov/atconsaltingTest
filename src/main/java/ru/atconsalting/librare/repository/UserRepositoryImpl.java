package ru.atconsalting.librare.repository;

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
import ru.atconsalting.librare.model.Role;
import ru.atconsalting.librare.model.User;

import javax.sql.DataSource;
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
    private final DataSource dataSource;

    private final ResultSetExtractor<Collection<User>> usersExtractor = rs -> {
        Map<Long, User> collector = new HashMap<>();
        while (rs.next()) {
            Long id = rs.getLong("id");
            if (!collector.containsKey(id)) {
                User user = new User();
                user.setId(id);
                user.setEmail(rs.getString("email"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
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
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedJdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedJdbcTemplate;
        this.dataSource = dataSource;
    }

    @Override
    public Collection<User> getAllUsers() {
        String query = "SELECT u.ID, u.EMAIL, u.USERNAME, u.PASSWORD, r.ROLE, r.USER_NAME ,r.USER_ID FROM USERS AS u LEFT JOIN USER_ROLES AS r ON u.USERNAME = r.USER_NAME";
        return jdbcTemplate.query(query, usersExtractor);
    }

    @Override
    @Transactional
    public User saveOrUpdateUser(User user) {
        return (user.getId() == null || user.getId() == 0) ? saveUser(user) : updateUser(user);
    }

    private User saveUser(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(dataSource);
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
        String query = "INSERT INTO USER_ROLES " +
                "SET USER_ID = :user_id, USER_NAME = :user_name, ROLE = :role";
        return namedParameterJdbcTemplate.batchUpdate(query, user.getRoles().stream()
                .map(role -> new MapSqlParameterSource("user_id", user.getId())
                        .addValue("role", String.valueOf(role))
                        .addValue("user_name", String.valueOf(user.getUserName())))
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
        String query = "DELETE FROM USERS  WHERE ID = ?";
        return jdbcTemplate.update(query, id) > 0;
    }

    @Override
    @Transactional
    public boolean deleteUserByName(String userName) {
        String query = "DELETE FROM USERS  WHERE USERNAME = ?";
        return jdbcTemplate.update(query, userName) > 0;
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

}
