package com.app.Services;

import com.app.DAO.ExamDAO;
import com.app.DAO.UserDAO;
import com.app.Models.Exams;
import com.app.Models.Users;

import java.util.ArrayList;

public class UserService {
    public static ArrayList<Users> getUsers() throws Exception{
        return UserDAO.getUsers();
    }
}
