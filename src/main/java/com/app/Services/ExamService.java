package com.app.Services;

import com.app.DAO.ExamDAO;
import com.app.DAO.TopicDAO;
import com.app.Models.Exams;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExamService {
    public static ArrayList<Exams> getExams() throws Exception{
        try {
            return ExamDAO.getExams(); // Gọi DAO để lấy dữ liệu
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi ra console để debug
            return new ArrayList<>(); // Trả về danh sách rỗng nếu có lỗi
        }
    }
    public static boolean addExam(String exOrder, String exCode, String questIDs) {
        try {
            return ExamDAO.addExams(exOrder, exCode, questIDs); // Gọi DAO
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi để debug
            return false; // Trả về false nếu có lỗi
        }
    }

    public static boolean deleteExam(String testCode) {
        try {
            return ExamDAO.deleteExams(testCode); // Gọi DAO để xóa
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi để debug
            return false; // Trả về false nếu có lỗi
        }
    }


    public static boolean updateExams(String testCode, String exOrder, String exCode, String quesIDs) {
        try {
            return ExamDAO.updateExams(testCode, exOrder, exCode, quesIDs); // Gọi DAO để cập nhật
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi ra console để debug
            return false;
        }
    }
}
