package com.example.typathon2.dao;

import com.example.typathon2.utils.SQLConnector;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Database {
    public static int getValue(String statement, String playername) {
        try {
            Connection con = SQLConnector.startConnection();
            PreparedStatement stmt = con.prepareStatement(statement);
            stmt.setString(1, playername);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int value = rs.getInt(1);
            con.close();
            return value;
        } catch (SQLException e) {
            return -1;
        }
    }

    public static String getString(String statement, String playername) {
        try {
            Connection con = SQLConnector.startConnection();
            PreparedStatement stmt = con.prepareStatement(statement);
            stmt.setString(1, playername);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String value = rs.getString(1);
            con.close();
            return value;
        } catch (SQLException e) {
            return "Problem with database";
        }
    }

    public static Date getDate(String statement, String playername) {
        try {
            Connection con = SQLConnector.startConnection();
            PreparedStatement stmt = con.prepareStatement(statement);
            stmt.setString(1, playername);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            Date value = rs.getDate(1);
            con.close();
            return value;
        } catch (SQLException e) {
            return null;
        }
    }

    public static void updateValue(String statement, String playername, int new_value) {
        try {
            Connection con = SQLConnector.startConnection();
            PreparedStatement stmt = con.prepareStatement(statement);
            stmt.setInt(1, new_value);
            stmt.setString(2, playername);
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Couldn't update value!");
        }
    }

    public static void newUser(String playername) {
        try {
            Connection con = SQLConnector.startConnection();
            PreparedStatement stmt = con.prepareStatement("insert into playerstats values (?, curdate(), 0, 0, 0, 0, 0, 0)");
            stmt.setString(1, playername);
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Couldn't write value!");
        }
    }
    public static int[] getLast10(String statement, String playername) {
        int[] value_set = new int[10];

        try {
            Connection con = SQLConnector.startConnection();
            PreparedStatement stmt = con.prepareStatement(statement);
            stmt.setString(1, playername);
            ResultSet rs = stmt.executeQuery();
            for (int i = 0; i < value_set.length; i++) {
                if (rs.next()) {
                    value_set[i] = rs.getInt(1);
                } else
                    break;
            }

            con.close();
            return value_set;
        } catch (SQLException e) {
            return value_set;
        }
    }

    public static int[] getValueSet(String statement, String playername) {
        int[] value_set = new int[10];
        int count = 0;
        int index = 0;

        try {
            Connection con = SQLConnector.startConnection();
            PreparedStatement stmt = con.prepareStatement(statement);
            stmt.setString(1, playername);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (value_set.length == count) {
                    int[] new_set = new int[2 * count];
                    for (int j = 0; j < count; j++) {
                        new_set[j] = value_set[j];
                    }
                    value_set = new_set;
                }
                value_set[index] = rs.getInt(1);
                count++;
                index++;
            }

            con.close();
            return value_set;
        } catch (SQLException e) {
            return value_set;
        }
    }
    public static void writeResult(String statement, String playername, int wpm, int acc, int score, int duration) {
        try {
            Connection con = SQLConnector.startConnection();
            PreparedStatement stmt = con.prepareStatement(statement);
            stmt.setString(1, playername);
            stmt.setInt(2, wpm);
            stmt.setInt(3, acc);
            stmt.setInt(4, score);
            stmt.setInt(5, duration);
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Couldn't write value!");
        }
    }
    public static void writeResult(String statement, String playername, int word_count, int wpm, int acc, int score, int duration) {
        try {
            Connection con = SQLConnector.startConnection();
            PreparedStatement stmt = con.prepareStatement(statement);
            stmt.setString(1, playername);
            stmt.setInt(2, word_count);
            stmt.setInt(3, wpm);
            stmt.setInt(4, acc);
            stmt.setInt(5, score);
            stmt.setInt(6, duration);
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Couldn't write value!");
        }
    }

    public static void writeResult(String statement, String playername, String gamemode, int wpm, int acc, int score) {
        try {
            Connection con = SQLConnector.startConnection();
            PreparedStatement stmt = con.prepareStatement(statement);
            stmt.setString(1, playername);
            stmt.setString(2, gamemode);
            stmt.setInt(3, wpm);
            stmt.setInt(4, acc);
            stmt.setInt(5, score);
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Couldn't write value!");
        }
    }
    public static Object[] getForLB(int mode, int number) {

        try {
            Connection con = SQLConnector.startConnection();
            PreparedStatement stmt = con.prepareStatement("select * from playerresults_normal where duration=? order by score desc limit ?, 1");
            stmt.setInt(1, mode);
            stmt.setInt(2, number);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String playername = rs.getString(1);
                int wpm = rs.getInt(2);
                int acc = rs.getInt(3);
                Timestamp ts = rs.getTimestamp(5);
                LocalDateTime ldt = ts.toLocalDateTime();
                LocalDate date = ldt.toLocalDate();
                String time = ldt.toLocalTime().toString();

                return new Object[]{playername, wpm, acc, date, time};
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("SQL exception!");
        }
        return null;
    }
}
