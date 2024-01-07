package com.example.typathon2;

public class DataWriter {
    public static void storeResultsNormal(int wpm, int acc, int score) {
        Database.writeResult("insert into playerresults_normal values (?, current_date(), current_time(), ?, ?, ?)",
                ProfilePage.getUsername(), wpm, acc, score);
    }
}
