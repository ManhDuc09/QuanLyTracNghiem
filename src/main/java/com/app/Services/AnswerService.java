package com.app.Services;

import com.app.DAO.AnswerDAO;
import com.app.Models.Answer;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnswerService {
    public static ArrayList<Answer> getAnswers () throws SQLException {
        return AnswerDAO.getAnswers();
    }
}
