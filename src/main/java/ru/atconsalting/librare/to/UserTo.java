package ru.atconsalting.librare.to;

/**
 * Created by Vladimir_Sentso on 31.07.2016.
 */
public class UserTo {

    private Long id;
    private String userName;
    private String password;
    private String email;

    public UserTo(Long id, String userName, String password, String email) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public UserTo() {
    }

    public Long getId() {
        return id;
    }

    public UserTo setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserTo setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserTo setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserTo setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserTo{");
        sb.append("id=").append(id);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
