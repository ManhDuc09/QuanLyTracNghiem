package com.app.DAO;

import com.app.Database.DatabaseConnection;
import com.app.Models.Exams;
import com.app.Models.Users;

import java.sql.*;
import java.util.ArrayList;

public class ExamDAO {
    public static ArrayList<Exams> getExams() throws SQLException {
        String query = "SELECT * FROM exams";
        ArrayList<Exams> arr = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (!rs.next()) {
                System.out.println("Empty");
                return null;

            }

            while (rs.next()) {

                String testCode = rs.getString("testCode");
                String exOrder = rs.getString("exOrder");
                String exCode = rs.getString("exCode");
               // String exQuesIDs = rs.getString("ex_quesIDs");


                Exams temp = new Exams(testCode, exOrder, exCode, new ArrayList<Integer>());
                arr.add(temp);
            }

        }
        return arr;

    }
    public static Exams getExamById(int id) {
        String sql = "SELECT * FROM exams WHERE exCode=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Exams(
                        rs.getString("testCode") ,
                        rs.getString("exOrder") ,
                        rs.getString("exCode"),
                        new ArrayList<Integer>()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;


    }
}
