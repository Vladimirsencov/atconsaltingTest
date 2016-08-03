package ru.atconsalting.library;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.atconsalting.library.model.User;
import ru.atconsalting.library.to.UserTo;
import ru.atconsalting.library.util.userUtil.UserUtil;

import static java.util.Objects.requireNonNull;

/**
 * Created by Vladimir_Sentso on 30.07.2016.
 */
public class LoggedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private UserTo userTo;

    public LoggedUser(User user) {
        super(user.getEmail(), user.getPassword(), true, true, true, true, user.getRoles());
        this.userTo = UserUtil.asTo(user);
    }

    public static LoggedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object user = auth.getPrincipal();
        return (user instanceof LoggedUser) ? (LoggedUser) user : null;
    }

    public static LoggedUser get() {
        LoggedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public static Long id() {
        return get().userTo.getId();
    }


    public void update(UserTo newTo) {
        userTo = newTo;
    }

    public UserTo getUserTo() {
        return userTo;
    }

    @Override
    public String toString() {
        return userTo.toString();
    }
}

