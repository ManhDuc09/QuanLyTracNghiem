package com.app.DAO;

import com.app.Database.DatabaseConnection;
import com.app.Models.Exams;
import com.app.Models.Logs;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class LogDAO {
    public static ArrayList<Logs> getLogs() throws SQLException {
        String query = "SELECT * FROM logs";
        ArrayList<Logs> arr = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (!rs.next()) {
                System.out.println("Empty");
                return null;

            }

            while (rs.next()) {

                int logID = rs.getInt("logID");
                String logContent = rs.getString("logContent");
                int logUserID = rs.getInt("logUserID");
                int logExCode = rs.getInt("logExCode");
                Date logDate = rs.getDate("logDate");


                Logs temp = new Logs(logID, logContent, logUserID, logExCode, logDate);
                arr.add(temp);
            }

        }
        return arr;

    }
}
