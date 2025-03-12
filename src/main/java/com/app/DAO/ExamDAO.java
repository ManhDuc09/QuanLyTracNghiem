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
                String exQuesIDs = rs.getString("ex_quesIDs");


                Exams temp = new Exams(testCode, exOrder, exCode, exQuesIDs);
                arr.add(temp);
            }

        }
        return arr;

    }
    public static boolean addExams(String exOrder, String exCode, String quesIDs) throws SQLException{

        String query = "INSERT INTO exams (exOrder, exCode, ex_quesIDs) VALUES (?,?,?)";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, exOrder);
            stmt.setString(2, exCode);
            stmt.setString(3, quesIDs);

            int row = stmt.executeUpdate();
            return row > 0;
        }
    }
    public static boolean deleteExams(String testCode) throws SQLException{
        String query = "DELETE FROM exams WHERE testCode = ?";
        //String resetQuery = "SET @num = 0; UPDATE topics SET tpID = @num := (@num+1); ALTER TABLE topics AUTO_INCREMENT = 1;";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, testCode);
            int row = stmt.executeUpdate();

            if (isTableEmpty(connection)) {
                try (Statement resetStmt = connection.createStatement()) {
                    resetStmt.execute("ALTER TABLE exams AUTO_INCREMENT = 1;");
                }
            }
            return row > 0;
        }
    }

    public static boolean updateExams(String testCode, String exOrder, String exCode, String quesIDs) throws SQLException{
        String query = "UPDATE exams SET exOrder = ?, exCode = ?, ex_questIDs = ? WHERE testCode = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query)){

            stmt.setString(1, exOrder);
            stmt.setString(2, exCode);
            stmt.setString(3, quesIDs);

            int row = stmt.executeUpdate();
            return row > 0;
        }
    }

    private static boolean isTableEmpty(Connection connection) throws SQLException {
        String query = "SELECT COUNT(*) FROM exams";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
            return false;
        }
    }
}
