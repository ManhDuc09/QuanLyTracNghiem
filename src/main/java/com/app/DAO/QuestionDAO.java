package com.app.DAO;

import com.app.Database.DatabaseConnection;
import com.app.Models.Answer;
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
//            if (!rs.next()) {
//                System.out.println("Empty");
//                return null;
//
//            }

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


    public static boolean addQuestion(Questions questions) throws SQLException {

        String query = "INSERT INTO questions (qContent, qPictures, qTopicID, qLevel, qStatus) VALUES (?,?,?,?,1)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, questions.getqContent());
            stmt.setString(2, questions.getqPicture());
            stmt.setInt(3, questions.getTopicID());
            stmt.setString(4, questions.getqLevel());

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
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
    public static int getLastInsertedQuestionID() throws SQLException {
        String query = "SELECT qID FROM questions ORDER BY qID DESC LIMIT 1";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("qID");
            }
        }
        return -1; // Trả về -1 nếu không tìm thấy
    }



    public static boolean deleteQuestion(int qId) throws SQLException {
        // 🟢 Xóa tất cả câu trả lời trước
        boolean deletedAnswers = AnswerDAO.deleteAnswersByQuestionId(qId);

        // 🟢 Sau khi xóa đáp án thành công, mới tiếp tục xóa câu hỏi
        String query = "DELETE FROM questions WHERE qID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, qId);
            int row = stmt.executeUpdate();
            return row > 0; // Trả về true nếu xóa thành công

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




}
