package com.example.miaula.Models;

import android.net.Uri;
import java.io.Serializable;

public class User implements Serializable {

    private String idUser;
    private String email;
    private String name;
    private String number;
//    private Uri photo;



    public User(String idUser, String email, String name, String number, Uri photo) {
        this.idUser = idUser;
        this.email = email;
        this.name = name;
        this.number = number;
//        this.photo = photo;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

//    public Uri getPhoto() {
//        return photo;
//    }
//
//    public void setPhoto(Uri photo) {
//        this.photo = photo;
//    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\''
                ;
    }
}
