package com.example.miaula.Models;

public class Student {

    private String surname;
    private String name;
    private String gender;

    public Student(String surname, String name, String gender) {
        this.surname = surname;
        this.name = name;
        this.gender = gender;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
