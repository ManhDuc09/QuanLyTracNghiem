package com.app.DAO;

import com.app.Database.DatabaseConnection;
import com.app.Models.Exams;
import com.app.Models.Result;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class ResultDAO {
    public static ArrayList<Result> getResults() throws SQLException {
        String query = "SELECT * FROM result";
        ArrayList<Result> arr = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (!rs.next()) {
                System.out.println("Empty");
                return null;

            }

            while (rs.next()) {
                int rs_num = rs.getInt("rs_num");
                int userID = rs.getInt("userID");
                String exCode = rs.getString("exCode");
                String rs_answer = rs.getString("rs_answers");
                BigDecimal rs_mark = rs.getBigDecimal("rs_mark");
                Date rs_date = rs.getDate("rs_date");


                Result temp = new Result(rs_num, userID, exCode, rs_answer, rs_mark, rs_date);
                arr.add(temp);
            }

        }
        return arr;

    }
}
