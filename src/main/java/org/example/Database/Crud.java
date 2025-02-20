package org.example.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

    public class Crud {
        public static void getUsers() throws SQLException {
            String query = "SELECT * FROM course";
            try (Connection conn = DatabaseConnection.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    System.out.println(rs.getInt("CourseId") + " - " + rs.getString("Title") + " - " + rs.getString("Credits"));
                }
            }
        }
    }



