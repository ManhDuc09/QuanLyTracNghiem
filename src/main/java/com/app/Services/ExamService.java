package com.app.Services;

import com.app.DAO.ExamDAO;
import com.app.Models.Exams;

import java.util.ArrayList;

public class ExamService {
    public static ArrayList<Exams> getExams() throws Exception{
        return ExamDAO.getExams();
    }
}
