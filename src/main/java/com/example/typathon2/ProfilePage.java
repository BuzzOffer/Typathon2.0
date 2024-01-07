package com.example.typathon2;

import java.sql.Date;

public class ProfilePage {
    private static String username, current_user;

    public static String getUsername() {
        return username;
    }
    public static String getCurrentUser() {
        return current_user;
    }
    public static void setCurrentUser(String cu) {
        current_user = cu;
    }

    public static void setUsername(String email) {
        username = UserInfo.getUsername(email);
    }
    public static void setTempUser(String player_name) {
        username = player_name;
    }


    public static int wpm10() {
        return Database.getValue("select wpm_10 from playerstats where username=?", username);
    }
    public static int wpmAll() {
        return Database.getValue("select wpm_all from playerstats where username=?", username);
    }
    public static int acc10() {
        return Database.getValue("select acc_10 from playerstats where username=?", username);
    }
    public static int accAll() {
        return Database.getValue("select acc_all from playerstats where username=?", username);
    }

    public static int testsCompleted() {
        return Database.getValue("select tests_completed from playerstats where username=?", username);
    }

    public static Date dateJoined() {
        return Database.getDate("select date_joined from playerstats where username=?", username);
    }

    public static int calcWpm10() {
        int[] values = Database.getLast10("select wpm from gamehistory where playername=? order by date_played desc", username);
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
        int[] values = Database.getValueSet("select wpm from gamehistory where playername=? order by date_played desc", username);
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
        int[] values = Database.getLast10("select acc from gamehistory where playername=? order by date_played desc", username);
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
        int[] values = Database.getValueSet("select acc from gamehistory where playername=? order by date_played desc", username);
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
}
