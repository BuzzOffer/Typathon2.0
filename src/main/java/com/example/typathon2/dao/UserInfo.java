package com.example.typathon2.dao;

public class UserInfo extends InfoReader {
        /*
    index of userinfo.txt:
    username = 0
    password = 1
    email = 2
     */

    public static String getPassword(String email) {
        return find("userinfo.txt", email, 2, 1);
    }
    public static String getEmail(String username) {
        return find("userinfo.txt", username, 0, 2);
    }
    public static String getUsername(String email) {
        return find("userinfo.txt", email, 2, 0);
    }
    public static String getUsernameFromData(String username) {
        return Database.getString("select username from playerstats where username=?", username);
    }

    public static int getWpm10(String username) {
        return Database.getValue("select wpm_10 from playerstats where username=?", username);
    }
    public static int getWpmAll(String username) {
        return Database.getValue("select wpm_all from playerstats where username=?", username);
    }
    public static int getAcc10(String username) {
        return Database.getValue("select acc_10 from playerstats where username=?", username);
    }
    public static int getAccAll(String username) {
        return Database.getValue("select acc_all from playerstats where username=?", username);
    }

    //use these methods to update stats after each game
    public static void updateWpm10(String username, int value) {
        Database.updateValue("update playerstats set wpm_10=? where username=?", username, value);
    }
    public static void updateWpmAll(String username, int value) {
        Database.updateValue("update playerstats set wpm_all=? where username=?", username, value);
    }
    public static void updateAcc10(String username, int value) {
        Database.updateValue("update playerstats set acc_10=? where username=?", username, value);
    }
    public static void updateAccAll(String username, int value) {
        Database.updateValue("update playerstats set acc_all=? where username=?", username, value);
    }
    public static void updateDeathScore(String username, int value) {
        Database.updateValue("update playerstats set death_score=? where username=?", username, value);
    }

    public static boolean emailInUse(String email) {
        return find("userinfo.txt", email, 2, 2).equals(email);
    }

    public static boolean usernameInUse(String username) {
        return find("userinfo.txt", username, 0, 0).equals(username);
    }
}
