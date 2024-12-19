package com.example.demo35;

import java.io.*;
import java.util.HashMap;

public class UserManager {
    private HashMap<String, User> users;
    private final String dataFilePath = "file.dat";

    public UserManager() {
        users = readUserDataFromFile();
    }

    public boolean authenticateUser(String username, String password) {
        User user = users.get(username);
        return user != null && user.getPassword().equals(password);
    }

    public void createAccount(String username, String password, double balance, String pin, String fullName, String email, String city, String gender) {
        if (users.containsKey(username)) {
            throw new IllegalArgumentException("Username already exists.");
        }
        users.put(username, new User(username, password, balance, pin, fullName, email, city, gender));
        saveUserDataToFile();
    }

    public boolean verifyMpin(String username, String enteredMpin) {
        User user = users.get(username);
        if (user != null) {
            return user.getPin().equals(enteredMpin);
        }
        return false;
    }

    public boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }

        return hasLetter && hasDigit && hasSpecialChar;
    }

    public double getUserBalance(String username) {
        User user = users.get(username);
        return user.getBalance();
    }

    public boolean transferFunds(String senderUsername, String recipientUsername, double amount) {
        User sender = users.get(senderUsername);
        User recipient = users.get(recipientUsername);

        if (sender == null || recipient == null) {
            return false;
        }

        if (sender.getBalance() < amount) {
            return false;
        }

        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);
        return true;
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public void deleteAccount(String username) {
        users.remove(username);
        saveUserDataToFile();
    }

    private HashMap<String, User> readUserDataFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFilePath))) {
            return (HashMap<String, User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new HashMap<>();
        }
    }

    public void saveUserDataToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFilePath))) {
            oos.writeObject(users);
        } catch (IOException e) {
            throw new RuntimeException("Error saving user data to file", e);
        }
    }
}
