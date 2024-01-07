package com.example.typathon2;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    static int getValue(String statement, String playername) {
        String url = "jdbc:mysql://localhost:3306/fopdb";
        String username = "root";
        String password = "Simongohhawyee123456";


        try {
            Connection con = DriverManager.getConnection(url, username, password);
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

    static String getString(String statement, String playername) {
        String url = "jdbc:mysql://localhost:3306/fopdb";
        String username = "root";
        String password = "Simongohhawyee123456";


        try {
            Connection con = DriverManager.getConnection(url, username, password);
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

    static Date getDate(String statement, String playername) {
        String url = "jdbc:mysql://localhost:3306/fopdb";
        String username = "root";
        String password = "Simongohhawyee123456";


        try {
            Connection con = DriverManager.getConnection(url, username, password);
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

    static void updateValue(String statement, String playername, int new_value) {
        String url = "jdbc:mysql://localhost:3306/fopdb";
        String username = "root";
        String password = "Simongohhawyee123456";

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = con.prepareStatement(statement);
            stmt.setInt(1, new_value);
            stmt.setString(2, playername);
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Couldn't update value!");
        }
    }

    static void newUser(String playername) {
        String url = "jdbc:mysql://localhost:3306/fopdb";
        String username = "root";
        String password = "Simongohhawyee123456";

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = con.prepareStatement("insert into playerstats values (?, curdate(), 0, 0, 0, 0, 0)");
            stmt.setString(1, playername);
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Couldn't write value!");
        }
    }
    static int[] getLast10(String statement, String playername) {
        String url = "jdbc:mysql://localhost:3306/fopdb";
        String username = "root";
        String password = "Simongohhawyee123456";
        int[] value_set = new int[10];

        try {
            Connection con = DriverManager.getConnection(url, username, password);
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

    static int[] getValueSet(String statement, String playername) {
        String url = "jdbc:mysql://localhost:3306/fopdb";
        String username = "root";
        String password = "Simongohhawyee123456";
        int[] value_set = new int[10];
        int count = 0;
        int index = 0;

        try {
            Connection con = DriverManager.getConnection(url, username, password);
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
}
