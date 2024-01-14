package com.example.typathon2.dao;

import java.io.*;
public class User {
    String username;
    String password;
    String email;
    int avg_wpm10 = 0;
    int avg_wpm_all = 0;
    int accuracy10 = 0;
    int accuracy_all = 0;

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
