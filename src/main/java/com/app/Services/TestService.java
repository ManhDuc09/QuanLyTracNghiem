package com.app.Services;

import com.app.DAO.TestDAO;
import com.app.Models.Test;

import java.util.ArrayList;

public class TestService {
    public static ArrayList<Test> getTests() throws Exception{
        return TestDAO.getTests();
    }
}
