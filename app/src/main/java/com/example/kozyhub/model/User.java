package com.example.kozyhub.model;

public class User {
    private String name, email, phone;
    private float balance;

    public User(String name, String email, String phone, float balance) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public float getBalance() {
        return balance;
    }
}
