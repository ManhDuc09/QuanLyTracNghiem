package com.app.DAO;

import com.app.Database.DatabaseConnection;
import com.app.Models.Questions;
import com.app.Models.Topics;

import java.sql.*;
import java.util.ArrayList;

public class TopicDAO {
    public static ArrayList<Topics> getTopics() throws SQLException {
        String query = "SELECT * FROM topics";
        ArrayList<Topics> arr = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
//            if (!rs.next()) {
//                System.out.println("Empty");
//                return null;
//
//            }

            while (rs.next()) {
                int tpID = rs.getInt("tpID");
                String tpTitle = rs.getString("tpTitle");
                int tpParent = rs.getInt("tpParent");
                boolean tpStatus = rs.getBoolean("tpStatus");


                Topics temp = new Topics(tpID, tpTitle, tpParent, tpStatus);
                arr.add(temp);
            }

        }
        return arr;

    }
    public static boolean addTopics(String title, int parent) throws SQLException{

        String query = "INSERT INTO topics (tpTitle, tpParent, tpStatus) VALUES (?,?,1)";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, title);
            stmt.setInt(2, parent);

            int row = stmt.executeUpdate();
            return row > 0;
        }
    }

    public static boolean deleteTopics(int tpID) throws SQLException{
        String query = "DELETE FROM topics WHERE tpID = ?";
        //String resetQuery = "SET @num = 0; UPDATE topics SET tpID = @num := (@num+1); ALTER TABLE topics AUTO_INCREMENT = 1;";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, tpID);
            int row = stmt.executeUpdate();

            if (isTableEmpty(connection)) {
                try (Statement resetStmt = connection.createStatement()) {
                    resetStmt.execute("ALTER TABLE topics AUTO_INCREMENT = 1;");
                }
            }
            return row > 0;
        }
    }

    //    public static int getNextAvailableID() throws SQLException {
//        String query = "SELECT MIN(tpID + 1) AS nextID FROM topics WHERE (tpID + 1) NOT IN (SELECT tpID FROM topics)";
//        try (Connection connection = DatabaseConnection.getConnection();
//             Statement stmt = connection.createStatement();
//             ResultSet rs = stmt.executeQuery(query)) {
//            if (rs.next()) {
//                return rs.getInt("nextID");
//            }
//            return 1; // Nếu bảng rỗng, trả về 1
//        }
//    }
    private static boolean isTableEmpty(Connection connection) throws SQLException {
        String query = "SELECT COUNT(*) FROM topics";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
            return false;
        }
    }
    public static boolean updateTopic(int tpID, String newTitle, int newParent) throws SQLException{
        String query = "UPDATE topics SET tpTitle = ?, tpParent = ? WHERE tpID = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query)){

            stmt.setString(1, newTitle);
            stmt.setInt(2, newParent);
            stmt.setInt(3, tpID);

            int row = stmt.executeUpdate();
            return row > 0;
        }
    }
}
