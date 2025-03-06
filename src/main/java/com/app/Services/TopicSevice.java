package com.app.Services;

import com.app.DAO.ExamDAO;
import com.app.DAO.TopicDAO;
import com.app.GUI.Topic;
import com.app.Models.Exams;
import com.app.Models.Topics;

import java.util.ArrayList;

public class TopicSevice {
    public static ArrayList<Topics> getTopics() throws Exception{
        return TopicDAO.getTopics();
    }
}
