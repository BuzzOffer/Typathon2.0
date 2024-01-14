package com.example.typathon2.dao;

import java.io.*;
import java.util.Scanner;
public class InfoReader {

    protected static String find(String file_name, String info, int info_index, int target_index) {
        String[] user_info;
        try {
            Scanner inputStream = new Scanner(new FileInputStream(file_name));
            while (inputStream.hasNextLine()) {
                user_info = inputStream.nextLine().split(",");
                if (user_info[info_index].equals(info)) {
                    return user_info[target_index];
                }
            }
            inputStream.close();
        } catch (IOException e) {
            System.out.println("Problem with input!");
        }
        return "com.example.typathon2.dao.User not found!";
    }
}
