package ru.atconsalting.testtask.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.atconsalting.testtask.model.Role;
import ru.atconsalting.testtask.model.User;

import java.util.EnumSet;
import java.util.Set;

/**
 * Created by Vladimir_Sentso on 29.07.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-app.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserServiceImplTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService service;
    @Autowired
    private JdbcTemplate template;

    @Test
    public void testGetAllUsers() throws Exception {
        String str = "[User{id=1, userName='PETR_IVANOV', email='petr_ivanov@yandex.ru', roles=[ROLE_USER]}, User{id=2, userName='IVAN_PETROV', email='ivan_petrov@yandex.ru', roles=[ROLE_USER]}, User{id=3, userName='MICHAIL_KONEV', email='michail_konev@gmail.com', roles=[ROLE_USER]}, User{id=4, userName='NICKOLAY_VODKIN', email='null', roles=[ROLE_USER]}, User{id=5, userName='PETR NICKOLAYEV', email='null', roles=[ROLE_USER]}]";
        Assert.assertEquals(str, service.getAllUsers().toString());
    }

    @Test
    public void testSaveUser() throws Exception {
        User user = new User();
        user.setEmail("vty@mail.com");
        user.setPassword("115x5xsx");
        Set<Role> roles = EnumSet.allOf(Role.class);
        user.setRoles(roles);
        user.setUserName("PETR_VASIKOV");
        service.saveUser(user);
        Assert.assertEquals(true, service.getAllUsers().contains(user));
        Assert.assertEquals(true, service.get(user.getId()).equals(user));
    }

    @Test
    public void testDeleteUser() throws Exception {
        User user = service.get(5);
        service.deleteUser(user);
        Assert.assertEquals(true, !service.getAllUsers().contains(user));
        String query = "SELECT * FROM USER_ROLES WHERE USER_ID = ? ";
        Assert.assertEquals(0, template.query(query, (rs, rowNum) -> rs.getString("role"), user.getId()).size());
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = service.get(5);
        user.setEmail("dcjd@dcjdd.com");
        service.updateUser(user);
        Assert.assertEquals(true, service.getAllUsers().contains(user));
    }

    @Test
    public void testGetUserByUserName() {
        Assert.assertEquals(true, service.get(5L).equals(service.getUserByName("PETR NICKOLAYEV")));
    }

}