package com.example.typathon2;

public class DataWriter {
    public static void storeResultsNormal(int wpm, int acc, int score) {
        Database.writeResult("insert into playerresults_normal values (?, current_date(), current_time(), ?, ?, ?)",
                ProfilePage.getUsername(), wpm, acc, score);
    }
    public static void storeResultsQuotes(int wpm, int acc, int score) {
        Database.writeResult("insert into playerresults_quotes values (?, current_date(), current_time(), ?, ?, ?)",
                ProfilePage.getUsername(), wpm, acc, score);
    }
    public static void storeResultsWords(int word_count, int wpm, int acc, int score) {
        Database.writeResult("insert into playerresults_quotes values (?, current_date(), current_time(), ?, ?, ?, ?)",
                ProfilePage.getUsername(), word_count, wpm, acc, score);
    }
    public static void storeResultsWords(int word_count, int wpm, int acc, String mode) {
        Database.writeResult("insert into playerresults_quotes values (?, current_date(), current_time(), ?, ?, ?, ?)",
                ProfilePage.getUsername(), word_count, wpm, acc, mode);
    }
}
