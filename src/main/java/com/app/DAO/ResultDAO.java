package com.app.DAO;

import com.app.Database.DatabaseConnection;
import com.app.Models.Exams;
import com.app.Models.Result;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class ResultDAO {
    public static ArrayList<Result> getResults() throws SQLException {
        String query = "SELECT * FROM result";
        ArrayList<Result> arr = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (!rs.next()) {
                System.out.println("Empty");
                return null;

            }

            while (rs.next()) {
                int rs_num = rs.getInt("rs_num");
                int userID = rs.getInt("userID");
                String exCode = rs.getString("exCode");
                String rs_answer = rs.getString("rs_answers");
                BigDecimal rs_mark = rs.getBigDecimal("rs_mark");
                Date rs_date = rs.getDate("rs_date");


                Result temp = new Result(rs_num, userID, exCode, rs_answer, rs_mark, rs_date);
                arr.add(temp);
            }

        }
        return arr;

    }
    public static int getLatestRsNum(int userID, String exCode)  {
        String query = "SELECT MAX(rs_num) FROM result WHERE userID = ? AND exCode = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, userID);
            stmt.setString(2, exCode);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int rsNum = rs.getInt(1);
                    return rs.wasNull() ? -1 : rsNum; // Handle case where MAX(rs_num) is NULL
                }
            }
        }
        catch (SQLException e) {}

        return -1;
    }
    public static boolean insertResult(Result result)  {
        String query = "INSERT INTO result (rs_num, userID, exCode, rs_answers, rs_mark, rs_date) VALUES (?, ?, ?, ?, ?, ?)";


        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, result.getRsNum());
            stmt.setInt(2, result.getUserID());
            stmt.setString(3, result.getExCode());
            stmt.setString(4, result.getRsAnswer());
            stmt.setBigDecimal(5, result.getRsMark());
            stmt.setDate(6, result.getRsDate());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Returns true if insert was successful
        }
        catch (SQLException e){
            return false;
        }
    }
}
