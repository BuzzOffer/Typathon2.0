package com.example.typathon2.dao;

import java.io.*;
public class User {
    private String username;
    private String password;
    private String email;
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void storeUserInfo() {
        try {
            PrintWriter outputStream = new PrintWriter(new FileOutputStream("userinfo.txt", true));
            outputStream.printf("%s,%s,%s%n", this.username, this.password, this.email);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Problem with writing info");
        }

    }
}
