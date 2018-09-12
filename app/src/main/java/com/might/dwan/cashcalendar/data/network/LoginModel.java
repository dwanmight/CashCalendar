package com.might.dwan.cashcalendar.data.network;

public class LoginModel implements ILoginModel {

    private String name;


    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public boolean isValid() {
        return name != null && surname != null;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    private String surname;
}
