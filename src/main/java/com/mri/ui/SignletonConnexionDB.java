package com.mri.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SignletonConnexionDB {
    private static String url = "jdbc:mysql://localhost:3306/test_exam";
    private static String user = "root";
    private static String password = "yahya";

    public static Connection getConnexion() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
