package com.app.DAO;

import com.app.Database.DatabaseConnection;
import com.app.Models.Logs;
import com.app.Models.Test;

import java.sql.*;
import java.util.ArrayList;

public class TestDAO {
    public static ArrayList<Test> getTests() throws SQLException {
        String query = "SELECT * FROM test";
        ArrayList<Test> arr = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (!rs.next()) {
                System.out.println("Empty");
                return null;

            }

            while (rs.next()) {

                int testID = rs.getInt("testID");
                String testCode = rs.getString("testCode");
                String testTitle = rs.getString("testTitle");
                int testTime = rs.getInt("testTime");
                int tpID = rs.getInt("tpID");
                int numEasy = rs.getInt("num_easy");
                int numMedium = rs.getInt("num_medium");
                int numDifficult = rs.getInt("num_diff");
                int testLimit = rs.getInt("testLimit");
                Date testDate = rs.getDate("testDate");
                boolean testStatus = rs.getBoolean("testStatus");


                Test temp = new Test(testID, testCode, testTitle, testTime, tpID, numEasy,numMedium,numDifficult,testLimit,testDate,testStatus);
                arr.add(temp);
            }

        }
        return arr;

    }
}
