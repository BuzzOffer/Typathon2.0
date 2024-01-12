package com.example.typathon2;

public class DataWriter {
    public static void storeResultsNormal(int wpm, int acc, int score, int duration) {
        Database.writeResult("insert into playerresults_normal values (?, ?, ?, ?, now(), )",
                ProfilePage.getUsername(), wpm, acc, score, duration);
    }
    public static void storeResultsQuotes(int wpm, int acc, int score, int duration) {
        Database.writeResult("insert into playerresults_quotes values (?, ?, ?, ?, now(), ?)",
                ProfilePage.getUsername(), wpm, acc, score, duration);
    }
    public static void storeResultsWords(int word_count, int wpm, int acc, int score, int duration) {
        Database.writeResult("insert into playerresults_quotes values (?, ?, ?, ?, ?, now(), ?)",
                ProfilePage.getUsername(), word_count, wpm, acc, score, duration);
    }
    public static void storeResultsDeath(int wpm, int acc, int score, int duration) {
        Database.writeResult("insert into playerresults_quotes values (?, ?, ?, ?, now(), ?)",
                ProfilePage.getUsername(),wpm, acc, score, duration);
    }
    public static void storeGameHistory(String gamemode, int wpm, int acc, int score) {
        Database.writeResult("insert into gamehistory values (?, ?, now(), ?, ?, ?)",
                ProfilePage.getUsername(), gamemode, wpm, acc, score);
    }
}
