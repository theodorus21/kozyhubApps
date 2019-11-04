package com.example.kozyhub.model;

public class User {
    private String name, email;
    private float balance;

    public User(String name, String email, float balance) {
        this.name = name;
        this.email = email;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public float getBalance() {
        return balance;
    }
}
