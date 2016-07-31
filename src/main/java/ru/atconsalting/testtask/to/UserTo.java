package ru.atconsalting.testtask.to;

/**
 * Created by Vladimir_Sentso on 31.07.2016.
 */
public class UserTo {

    private final Long id;
    private final String userName;
    private final String password;
    private final String email;

    public UserTo(Long id, String userName, String password, String email) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
