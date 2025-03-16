package com.app.Services;

import com.app.DAO.QuestionDAO;
import com.app.Models.Answer;
import com.app.Models.Questions;

import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionService {
    public static ArrayList<Questions> getQuestions() throws Exception{
        return QuestionDAO.getQuestions();
    }

    public static boolean addQuestion(Questions question) throws Exception {
        return QuestionDAO.addQuestion(question);
    }
    public static int getLastInsertedQuestionID() throws SQLException {
        return QuestionDAO.getLastInsertedQuestionID();
    }
    public static boolean deleteQuestion(int qId) throws SQLException {
        //Xóa câu trả lời trước
        boolean deletedAnswers = AnswerService.deleteAnswersByQuestionId(qId);

        //Nếu xóa đáp án thành công thì tiếp tục xóa câu hỏi
        if (deletedAnswers) {
            return QuestionDAO.deleteQuestion(qId);
        } else {
            return false; // Nếu xóa câu trả lời thất bại, không xóa câu hỏi
        }
    }

}
