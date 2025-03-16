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
//    public static boolean deleteAnswersByQuestionId(int qId) throws SQLException {
//        String query = "DELETE FROM answers WHERE qID = ?";
//        //String resetAutoIncrement = "ALTER TABLE answers AUTO_INCREMENT = (SELECT MAX(awID) + 1 FROM answers)";
//
//        try (Connection connection = DatabaseConnection.getConnection();
//             PreparedStatement stmt = connection.prepareStatement(query)) {
//
//            stmt.setInt(1, qId);
//            int row = stmt.executeUpdate();
////            if (row > 0) {
////                stmt.executeUpdate(resetAutoIncrement);
////            }
//            return row > 0;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//
//    }

    public static boolean deleteAnswersByQuestionId(int qId) throws SQLException {
        String deleteQuery = "DELETE FROM answers WHERE qID = ?";
        String getMaxID = "SELECT MAX(awID) FROM answers";
        String resetAutoIncrement = "ALTER TABLE answers AUTO_INCREMENT = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery);
             Statement getMaxStmt = connection.createStatement();
             PreparedStatement resetStmt = connection.prepareStatement(resetAutoIncrement)) {

            deleteStmt.setInt(1, qId);
            int rowsAffected = deleteStmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = getMaxStmt.executeQuery(getMaxID);
                int maxID = 1;
                if (rs.next() && rs.getInt(1) > 0) {
                    maxID = rs.getInt(1) + 1;
                }

                resetStmt.setInt(1, maxID);
                resetStmt.executeUpdate();
            }

            return rowsAffected > 0;
        }
    }

    public static boolean updateAnswer(Answer answer) throws SQLException {
        String query = "UPDATE answers SET awContent = ?, awPictures = ?, isRight = ? WHERE awID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, answer.getaContent());  // Nội dung câu trả lời
            stmt.setString(2, answer.getaPicture());  // Đường dẫn ảnh
            stmt.setBoolean(3, answer.isRight());     // Đúng/Sai
            stmt.setInt(4, answer.getaId());          // ID câu trả lời cần sửa

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
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



