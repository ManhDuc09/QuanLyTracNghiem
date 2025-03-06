package com.app.DAO;

import com.app.Database.DatabaseConnection;
import com.app.Models.Questions;
import com.app.Models.Topics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TopicDAO {
    public static ArrayList<Topics> getTopics() throws SQLException {
        String query = "SELECT * FROM topics";
        ArrayList<Topics> arr = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (!rs.next()) {
                System.out.println("Empty");
                return null;

            }

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
}
