package com.companyZ.employeeapp;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/employeeData";
    private static final String USER = "root";              // <-- your MySQL username
    private static final String PASSWORD = "Drogba11"; // <-- your MySQL password

    public static Connection getConnection() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Try connecting
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to MySQL successfully.");
            return conn;

        } catch (Exception e) {
            System.out.println("❌ Database connection failed!");
            e.printStackTrace();
            return null;
        }
    }
}
