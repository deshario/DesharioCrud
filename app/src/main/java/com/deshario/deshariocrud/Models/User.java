package com.deshario.deshariocrud.Models;

import java.io.Serializable;

/**
 * Created by Deshario on 9/5/2017.
 */

public class User implements Serializable {

    private int id;
    private String nickname;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
