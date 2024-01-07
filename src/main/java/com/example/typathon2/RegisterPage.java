package com.example.typathon2;

import java.util.Scanner;
public class RegisterPage {
    public static void registerUser() {
        String username, password, confirm_pass, email;
        Scanner scan = new Scanner(System.in);
        System.out.print("Username: ");
        username = scan.nextLine();

        System.out.print("Password: ");
        password = scan.nextLine();

        System.out.print("Confirm Password: ");
        confirm_pass = scan.nextLine();

        System.out.print("Email: ");
        email = scan.nextLine();

        if (confirm_pass.equals(password) && !UserInfo.emailInUse(email) && !UserInfo.usernameInUse(username)) {
            User user = new User(username, password, email);
            user.storeUserInfo();
            user.storeUserStats();
        }
        else if (!confirm_pass.equals(password)){
            System.out.println("Passwords do not match!");
        }
        else if (UserInfo.usernameInUse(username)) {
            System.out.println("Username is taken!");
        }
        else if (UserInfo.emailInUse(email)) {
            System.out.println("Email is in use!");
        }
        else {
            System.out.println("Problem with profile registration!");
        }

    }


}
