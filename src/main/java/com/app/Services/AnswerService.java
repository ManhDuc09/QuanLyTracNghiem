package com.app.Services;

import com.app.DAO.AnswerDAO;
import com.app.DAO.QuestionDAO;
import com.app.Models.Answer;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnswerService {
    public static ArrayList<Answer> getAnswersByQuestionID(int qID) throws SQLException {
        return AnswerDAO.getAnswersByQuestionID(qID);
    }

    public static boolean addAnswer(Answer answer) throws SQLException {
        return AnswerDAO.addAnswer(answer);
    }
    public static boolean deleteAnswersByQuestionId(int qId) throws SQLException{
        return AnswerDAO.deleteAnswersByQuestionId(qId);
    }

    public static boolean updateAnswer(Answer answer) throws SQLException {
        return AnswerDAO.updateAnswer(answer);
    }
}
