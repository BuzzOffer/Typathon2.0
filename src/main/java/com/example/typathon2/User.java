package com.example.typathon2;

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

    public void storeUserStats() {
        try {
            PrintWriter outputStream = new PrintWriter(new FileOutputStream("userstats.txt", true));
            outputStream.printf("%s,%d,%d,%d,%d%n", this.username, this.avg_wpm10, this.avg_wpm_all, this.accuracy10, this.accuracy_all);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Problem with writing stats");
        }
    }
}
