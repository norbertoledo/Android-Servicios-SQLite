package com.norbertoledo.pac_desarrollo_m08;

public class User {
    public String name;
    public String surname;
    public String phone;
    public String gender;

    public User(String name, String surname, String phone, String gender){
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.gender = gender;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
