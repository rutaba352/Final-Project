package com.example.demo35;

import java.io.Serializable;

public class User implements Serializable {

    private String fullName;
    private String username;
    private String password;
    private String email;
    private String city;
    private String gender;
    private double balance;
    private String pin;

    public User(String username, String password, double balance, String pin, String fullName, String email, String city, String gender) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.pin = pin;
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
        this.city = city;
    }
    public User(String username, double initialBalance){
        this.username = username;
        this.balance = initialBalance;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}
