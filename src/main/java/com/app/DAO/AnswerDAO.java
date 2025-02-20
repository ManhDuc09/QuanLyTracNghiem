package com.app.DAO;

import com.app.Database.DatabaseConnection;
import com.app.Models.Answer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AnswerDAO {
        public static ArrayList<Answer> getAnswers() throws SQLException {
            String query = "SELECT * FROM answers";
            ArrayList<Answer> answers = new ArrayList<>();
            try (Connection conn = DatabaseConnection.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                if (!rs.next()){
                    System.out.println("Empty table");
                    return null;
                }

                while (rs.next()) {
                        int aId = rs.getInt("awId");
                        int qId = rs.getInt("qId");
                        String aContent = rs.getString("awContent");
                        String aPicture = rs.getString("awPicture");
                        Boolean isRight = rs.getBoolean("isRight");
                        Boolean aStatus = rs.getBoolean("awStatus");

                        Answer temp =  new Answer(aId , qId , aContent , aPicture ,aStatus , isRight);
                        answers.add(temp);
                      }
            }
            return answers;

        }
    }



