package com.mursico.xpeppersaddress;

public class User{

    private String name;
    private String surname;
    private String phone;
    private String id;

    public User(String name, String surname, String phone){
        this.surname=surname;
        this.name=name;
        this.phone=phone;
        this.id = this.name + " " + this.surname + "\n" + this.phone;
    }

    @Override
    public String toString() {
        return this.name + " " + this.surname + "\n" + this.phone;
    }

    public String getId() {
        return id;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
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

