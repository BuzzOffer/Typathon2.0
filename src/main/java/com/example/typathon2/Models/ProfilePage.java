package com.example.typathon2.Models;

import com.example.typathon2.dao.Database;
import com.example.typathon2.dao.UserInfo;

import java.sql.Date;

public class ProfilePage {
    private static String profile_username, current_user;

    public static String getProfUsername() {
        return profile_username;
    }
    public static String getCurrentUser() {
        return current_user;
    }

    public static void setProfUsername(String n) {
        profile_username = n;
    }
    public static void setUsername(String email) {
        current_user = UserInfo.getUsername(email);
    }


    public static int wpm10() {
        return UserInfo.getWpm10(profile_username);
    }
    public static int wpmAll() {
        return UserInfo.getWpmAll(profile_username);
    }
    public static int acc10() {
        return UserInfo.getAcc10(profile_username);
    }
    public static int accAll() {
        return UserInfo.getAccAll(profile_username);
    }

    public static int testsCompleted() {
        return Database.getValue("select tests_completed from playerstats where username=?", profile_username);
    }

    public static Date dateJoined() {
        return Database.getDate("select date_joined from playerstats where username=?", profile_username);
    }
    public static int deathScore() {
        return Database.getValue("select score from playerresults_death where username=?", profile_username);
    }

    public static int calcWpm10() {
        int[] values = Database.getLast10("select wpm from gamehistory where playername=? order by date_played desc", profile_username);
        int sum = 0;
        int count = 0;
        for (int value : values) {
            sum += value;
            count++;
            if (value <= 0)
                count--;
        }
        return (sum / count);
    }
    public static int calcWpmAll() {
        int[] values = Database.getValueSet("select wpm from gamehistory where playername=? order by date_played desc", profile_username);
        int sum = 0;
        int count = 0;
        for (int value : values) {
            sum += value;
            count++;
            if (value <= 0)
                count--;
        }
        return (sum / count);
    }
    public static int calcAcc10() {
        int[] values = Database.getLast10("select acc from gamehistory where playername=? order by date_played desc", profile_username);
        int sum = 0;
        int count = 0;
        for (int value : values) {
            sum += value;
            count++;
            if (value <= 0)
                count--;
        }
        return (sum / count);
    }
    public static int calcAccAll() {
        int[] values = Database.getValueSet("select acc from gamehistory where playername=? order by date_played desc", profile_username);
        int sum = 0;
        int count = 0;
        for (int value : values) {
            sum += value;
            count++;
            if (value <= 0)
                count--;
        }
        return (sum / count);
    }
    public void updateStats() {
        UserInfo.updateWpm10(profile_username, calcWpm10());
        UserInfo.updateWpmAll(profile_username, calcWpmAll());
        UserInfo.updateAcc10(profile_username, calcAcc10());
        UserInfo.updateAccAll(profile_username, calcAccAll());
    }
    public static boolean isLoggedIn() {
        return profile_username != null;
    }

}
