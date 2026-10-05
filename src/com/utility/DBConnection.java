package com.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private String url = "jdbc:mysql://localhost:3306/";
    private String userDb = "root";
    private String password = "deepcoder";
    private String dbName = "library_db";
    private Connection conn;

    public Connection dbConnect() {
        // Step 1: Load the driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // System.out.println("driver loaded...");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Step 2: Establish the connection
        try {
            conn = DriverManager.getConnection(url + dbName, userDb, password);
            // System.out.println("connection established at memory loc: " + conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public void dbClose() {
        try {
            conn.close();
            // System.out.println("connection closed at loc: " + conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}