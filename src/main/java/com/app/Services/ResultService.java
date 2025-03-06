package com.app.Services;

import com.app.DAO.ExamDAO;
import com.app.DAO.ResultDAO;
import com.app.Models.Exams;
import com.app.Models.Result;

import java.util.ArrayList;

public class ResultService {
    public static ArrayList<Result> getResults() throws Exception{
        return ResultDAO.getResults();
    }
}
