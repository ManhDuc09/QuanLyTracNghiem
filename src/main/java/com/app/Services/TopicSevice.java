package com.app.Services;

import com.app.DAO.ExamDAO;
import com.app.DAO.TopicDAO;
import com.app.GUI.Topic;
import com.app.Models.Exams;
import com.app.Models.Topics;

import java.sql.SQLException;
import java.util.ArrayList;

public class TopicSevice {
    public static ArrayList<Topics> getTopics() {
        try {
            return TopicDAO.getTopics(); // Gọi DAO để lấy dữ liệu
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi ra console để debug
            return new ArrayList<>(); // Trả về danh sách rỗng nếu có lỗi
        }
    }
    public static boolean addTopic(String title, int parent) {
        try {
            return TopicDAO.addTopics(title, parent); // Gọi DAO
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi để debug
            return false; // Trả về false nếu có lỗi
        }
    }

    public static boolean deleteTopic(int tpID) {
        try {
            return TopicDAO.deleteTopics(tpID); // Gọi DAO để xóa
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi để debug
            return false; // Trả về false nếu có lỗi
        }
    }

//    public static int getNextAvailableID() throws Exception{
//        return TopicDAO.getNextAvailableID();
//    }

    public static boolean updateTopic(int tpID, String newTitle, int newParent) {
        try {
            return TopicDAO.updateTopic(tpID, newTitle, newParent); // Gọi DAO để cập nhật
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi ra console để debug
            return false;
        }
    }
}
