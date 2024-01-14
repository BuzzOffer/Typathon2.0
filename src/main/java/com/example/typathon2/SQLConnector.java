package com.example.typathon2;
import java.net.CookieHandler;
import java.sql.*;

public class SQLConnector {
    private static String url = "jdbc:mysql://localhost:3306/fopdb";
    private static String username = "root";
    private static String password = "Simongohhawyee123456";

    public static Connection startConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

}
