package com.example.typathon2;

public class TesterProfile {
    public static void main(String[] args) {
        ProfilePage.setTempUser("Simon");
        System.out.println(ProfilePage.getUsername());
        System.out.println(ProfilePage.calcWpm10());
        System.out.println(ProfilePage.calcWpmAll());
    }
}
