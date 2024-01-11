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
    public static void storeResultsDeath(int wpm, int acc, int score, String mode) {
        Database.writeResult("insert into playerresults_quotes values (?, current_date(), current_time(), ?, ?, ?, ?)",
                ProfilePage.getUsername(),wpm, acc, score);
    }
    public static void storeGameHistory(String gamemode, int wpm, int acc, int score) {
        Database.writeResult("insert into gamehistory values (?, ?, now(), ?, ?, ?",
                ProfilePage.getUsername(), gamemode, wpm, acc, score);
    }
}
