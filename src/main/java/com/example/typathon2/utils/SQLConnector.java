package com.example.typathon2.utils;
import java.net.CookieHandler;
import java.sql.*;

public class SQLConnector {

    public static Connection startConnection() throws SQLException {
        String username = "root";
        String password = "Simongohhawyee123456";
        String url = "jdbc:mysql://localhost:3306/fopdb";
        return DriverManager.getConnection(url, username, password);
    }

}
