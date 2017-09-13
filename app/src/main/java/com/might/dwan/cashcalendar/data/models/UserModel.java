package com.might.dwan.cashcalendar.data.models;

/**
 * Created by Might on 24.08.2017.
 */

public class UserModel {
    private String nickname;
    private String name;
    private String surname;

    public UserModel(String nickname, String name, String surname) {
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
    }

    public UserModel() {
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
