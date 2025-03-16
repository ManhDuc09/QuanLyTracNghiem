package com.app.DAO;

import com.app.Database.DatabaseConnection;
import com.app.Models.Answer;
import com.app.Models.Questions;

import java.sql.*;
import java.util.ArrayList;

public class AnswerDAO {
//        public static ArrayList<Answer> getAnswers() throws SQLException {
//            String query = "SELECT * FROM answers";
//            ArrayList<Answer> answers = new ArrayList<>();
//            try (Connection conn = DatabaseConnection.getConnection();
//                 Statement stmt = conn.createStatement();
//                 ResultSet rs = stmt.executeQuery(query)) {
//                if (!rs.next()){
//                    System.out.println("Empty table");
//                    return null;
//                }
//
//                while (rs.next()) {
//                        int aId = rs.getInt("awId");
//                        int qId = rs.getInt("qId");
//                        String aContent = rs.getString("awContent");
//                        String aPicture = rs.getString("awPicture");
//                        Boolean isRight = rs.getBoolean("isRight");
//                        Boolean aStatus = rs.getBoolean("awStatus");
//
//                        Answer temp =  new Answer(aId , qId , aContent , aPicture ,aStatus , isRight);
//                        answers.add(temp);
//                      }
//            }
//            return answers;
//
//        }

    public static ArrayList<Answer> getAnswersByQuestionID(int qID) throws SQLException {
        String query = "SELECT * FROM answers WHERE qID = ? AND awStatus = 1"; // Chỉ lấy câu trả lời đang active
        ArrayList<Answer> answersList = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, qID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int awID = rs.getInt("awID");
                String awContent = rs.getString("awContent");
                String awPictures = rs.getString("awPictures");
                boolean isRight = rs.getBoolean("isRight");
                boolean awStatus = rs.getBoolean("awStatus");

                Answer answer = new Answer(awID, qID, awContent, awPictures, isRight, awStatus);
                answersList.add(answer);
            }
        }
        return answersList;
    }
    public static boolean addAnswer(Answer answer) throws SQLException {
        String query = "INSERT INTO answers (qID, awContent, awPictures, isRight, awStatus) VALUES (?, ?, ?, ?, 1)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, answer.getqId());
            stmt.setString(2, answer.getaContent());
            stmt.setString(3, answer.getaPicture());
            stmt.setInt(4, answer.isRight() ? 1 : 0);


            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }
    public static boolean deleteAnswersByQuestionId(int qId) throws SQLException {
        String query = "DELETE FROM answers WHERE qID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, qId);
            int row = stmt.executeUpdate();
            return row > 0; // Trả về true nếu có đáp án bị xóa

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static ArrayList<Answer> getAnswersByqId(int id) {
            String sql = "SELECT * FROM answers WHERE qId = ?";
        ArrayList<Answer> answers = new ArrayList<Answer>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int aId = rs.getInt("awId");
                int qId = rs.getInt("qId");
                String aContent = rs.getString("awContent");
                String aPicture = rs.getString("awPictures");
                Boolean isRight = rs.getBoolean("isRight");
                Boolean aStatus = rs.getBoolean("awStatus");

                Answer temp =  new Answer(aId , qId , aContent , aPicture ,aStatus , isRight);
                System.out.println(temp);
                answers.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return answers;

    }


}



