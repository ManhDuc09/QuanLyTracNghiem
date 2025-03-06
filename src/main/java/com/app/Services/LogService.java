package com.app.Services;

import com.app.DAO.LogDAO;
import com.app.Models.Logs;

import java.util.ArrayList;

public class LogService {
    public static ArrayList<Logs> getLogs() throws Exception{
        return LogDAO.getLogs();
    }
}
