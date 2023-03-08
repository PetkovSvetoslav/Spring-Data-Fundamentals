package com.resellerapp.util;

import com.resellerapp.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.UUID;

@Component
@SessionScope
public class LoggedUser {
    private UUID id;
    private String username;
    private String email;

    public LoggedUser() {
    }

    public UUID getId() {
        return id;
    }

    public LoggedUser setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LoggedUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public LoggedUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public void clearValues(){
        this.id = null;
        this.username = null;
        this.email = null;
    }


    public void login(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

    public boolean isLogged() {
        return this.id != null;
    }
}
