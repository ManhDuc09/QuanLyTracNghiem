package com.app.Services;

import com.app.DAO.QuestionDAO;
import com.app.Models.Questions;

import java.util.ArrayList;

public class QuestionService {
    public static ArrayList<Questions> getQuestions() throws Exception{
        return QuestionDAO.getQuestions();
    }
}
