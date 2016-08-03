package ru.atconsalting.librare.util.userUtil;

import ru.atconsalting.librare.model.Role;
import ru.atconsalting.librare.model.User;
import ru.atconsalting.librare.to.UserTo;

import java.util.EnumSet;

/**
 * Created by Vladimir_Sentso on 31.07.2016.
 */
public class UserUtil {
    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static User createFromTo(UserTo newUser) {
        User user = new User();
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        user.setUserName(newUser.getUserName());
        user.setRoles(EnumSet.of(Role.ROLE_USER));
        return user;
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getUserName(), user.getPassword(), user.getEmail());
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setUserName(userTo.getUserName());
        user.setEmail(userTo.getEmail());
        user.setPassword(userTo.getPassword());
        return prepareToSave(user);
    }

    public static User prepareToSave(User user) {
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
