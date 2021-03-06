package ru.atconsalting.library.util.userUtil;

import ru.atconsalting.library.model.Role;
import ru.atconsalting.library.model.User;
import ru.atconsalting.library.to.UserTo;

import java.util.EnumSet;
import java.util.Objects;

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
        user.setId(userTo.getId());
        user.setUserName(userTo.getUserName());
        if (Objects.nonNull(userTo.getEmail())) {
            user.setEmail(userTo.getEmail());
        }
        user.setPassword(userTo.getPassword());
        return prepareToSave(user);
    }

    public static User prepareToSave(User user) {
        if (Objects.nonNull(user.getEmail())) {
            user.setEmail(user.getEmail().toLowerCase());
        }
        //TODO костыль чтобы не падало по NullPointer
        if (Objects.isNull(user.getRoles())) {
            user.setRoles(EnumSet.of(Role.ROLE_USER));
        }
        return user;
    }
}
