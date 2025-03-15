package com.app.DAO;

import com.app.Database.DatabaseConnection;
import com.app.Models.Questions;

import java.sql.*;
import java.util.ArrayList;

public class QuestionDAO {
    public static Questions getQuestionById(int id){
        String sql = "SELECT * FROM questions WHERE qId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Questions temp = new Questions(
                        rs.getInt("qID"),
                        rs.getString("qContent"),
                        rs.getString("qPictures"),
                        rs.getInt("qTopicID"),
                        rs.getString("qLevel"),
                        rs.getBoolean("qStatus")
                );
                System.out.println(temp);
                return temp;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static ArrayList<Questions> getQuestions() throws SQLException {
        String query = "SELECT * FROM questions";
        ArrayList<Questions> arr = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (!rs.next()) {
                System.out.println("Empty");
                return null;

            }

            while (rs.next()) {
                int qId = rs.getInt("qID");
                String qContent = rs.getString("qContent");
                String qPicture = rs.getString("qPictures");
                int topicID = rs.getInt("qTopicID");
                String qLevel = rs.getString("qLevel");
                boolean qStatus = rs.getBoolean("qStatus");

                Questions temp = new Questions(qId, qContent, qPicture, topicID, qLevel, qStatus);
                arr.add(temp);
            }

        }
        return arr;

    }

    public static boolean addQuestion(Questions questions) throws SQLException {

        String query = "INSERT INTO questions (qContent, qPictures, qTopicID, qLevel, qStatus) VALUES (?,?,?,?,?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, questions.getqContent());
            stmt.setString(2, questions.getqPicture());
            stmt.setInt(3, questions.getTopicID());
            stmt.setString(4, questions.getqLevel());
            stmt.setBoolean(5, questions.getqStatus());

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public static boolean deleteQuestion(int qId) throws Exception {
        String query = "DELETE FROM questions WHERE qID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, qId);
            int row = stmt.executeUpdate();
            return row > 0;
        }
    }
}
