package com.example.kozyhub.model;

public class User {

    public int PkPerson;
    public String PersonName, UserName, PersonEmail, PersonPhone1;

    public User(int pkPerson, String personName, String userName, String personEmail, String personPhone1) {
        PkPerson = pkPerson;
        PersonName = personName;
        UserName = userName;
        PersonEmail = personEmail;
        PersonPhone1 = personPhone1;
    }
}
