package ru.atconsalting.testtask.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Vladimir_Sentso on 27.07.2016.
 */
public enum Role implements GrantedAuthority {
    ROLE_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
