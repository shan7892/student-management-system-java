package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/school"; // lowercase jdbc
    private static final String USER = "root";
    private static final String PASSWORD = "sh@yan165";

    public static Connection getConnection() {
        try {
            // Load the MySQL driver explicitly
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("MySQL Driver not found");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to database");
        }
    }
}
