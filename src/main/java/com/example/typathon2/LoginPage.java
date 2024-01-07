package com.example.typathon2;

import java.util.Scanner;
public class LoginPage {
    static String email;
    public static void LoginUser() {
        String password;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter email: ");
        email = scan.nextLine();

        System.out.print("Enter password: ");
        password = scan.nextLine();

        if (password.equals(UserInfo.getPassword(email))) {
            System.out.println("Login successful! Welcome back!");

        }
        else {
            System.out.println("Email or password is incorrect");
        }
    }
}
