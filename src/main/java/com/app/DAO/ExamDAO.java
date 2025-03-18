package com.app.DAO;

import com.app.Database.DatabaseConnection;
import com.app.Models.Exams;
import com.app.Models.Users;
import java.util.stream.Collectors;

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


    public static void insertExam(String testCode, String exOrder, String exCode, ArrayList<Integer> questionIds) {
        String query = "INSERT INTO exams (testCode, exOrder, exCode, ex_quesIDs) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            // Convert ArrayList<Integer> to CSV String using normal loop
            StringBuilder csvString = new StringBuilder();
            for (int i = 0; i < questionIds.size(); i++) {
                csvString.append(questionIds.get(i));
                if (i < questionIds.size() - 1) {
                    csvString.append(","); // Add comma except for the last element
                }
            }

            // Set parameters
            pstmt.setString(1, testCode);
            pstmt.setString(2, exOrder);
            pstmt.setString(3, exCode);
            pstmt.setString(4, csvString.toString());  // Store as CSV string

            // Execute update
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(" Exam inserted successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error inserting exam: " + e.getMessage());
        }
    }
    public static Exams getExamById(String exCode) {
        String query = "SELECT * FROM exams WHERE testCode = ?";
        Exams exam = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, exCode); // Set exCode parameter

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String testCode = rs.getString("testCode");
                    String exOrder = rs.getString("exOrder");
                    String exQuesIDs = rs.getString("ex_quesIDs"); // Stored as CSV

                    // Convert CSV String back to ArrayList<Integer>
                    ArrayList<Integer> questionIds = new ArrayList<>();
                    if (exQuesIDs != null && !exQuesIDs.isEmpty()) {
                        String[] idsArray = exQuesIDs.split(",");
                        for (String id : idsArray) {
                            questionIds.add(Integer.parseInt(id.trim())); // Trim spaces and convert to int
                        }
                    }

                    exam = new Exams(testCode, exOrder, exCode, questionIds);
                }
            }
        } catch (SQLException e) {
            System.err.println(" Error fetching exam: " + e.getMessage());
        }

        return exam;
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
