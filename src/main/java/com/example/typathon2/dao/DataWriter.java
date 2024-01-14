package com.example.typathon2.dao;

import com.example.typathon2.Database;
import com.example.typathon2.ProfilePage;

public class DataWriter {
    public static void storeResultsNormal(int wpm, int acc, int score, int duration) {
        Database.writeResult("insert into playerresults_normal values (?, ?, ?, ?, now(), )",
                ProfilePage.getCurrentUser(), wpm, acc, score, duration);
    }
    public static void storeResultsQuotes(int wpm, int acc, int score, int duration) {
        Database.writeResult("insert into playerresults_quotes values (?, ?, ?, ?, now(), ?)",
                ProfilePage.getCurrentUser(), wpm, acc, score, duration);
    }
    public static void storeResultsWords(int word_count, int wpm, int acc, int score, int duration) {
        Database.writeResult("insert into playerresults_quotes values (?, ?, ?, ?, ?, now(), ?)",
                ProfilePage.getCurrentUser(), word_count, wpm, acc, score, duration);
    }
    public static void storeResultsDeath(int wpm, int acc, int score, int duration) {
        Database.writeResult("insert into playerresults_quotes values (?, ?, ?, ?, now(), ?)",
                ProfilePage.getCurrentUser(),wpm, acc, score, duration);
    }
    public static void storeGameHistory(String gamemode, int wpm, int acc, int score) {
        Database.writeResult("insert into gamehistory values (?, ?, now(), ?, ?, ?)",
                ProfilePage.getCurrentUser(), gamemode, wpm, acc, score);
    }
}
