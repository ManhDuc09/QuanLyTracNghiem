package com.app.DAO;

import com.app.Database.DatabaseConnection;
import com.app.Models.Topics;
import com.app.Models.Users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAO {
    public static ArrayList<Users> getUsers() throws SQLException {
        String query = "SELECT * FROM users";
        ArrayList<Users> arr = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (!rs.next()) {
                System.out.println("Empty");
                return null;

            }

            while (rs.next()) {
                int uID = rs.getInt("userID");
                String uName = rs.getString("userName");
                String uEmail = rs.getString("userEmail");
                String uPassword = rs.getString("userPassword");
                String uFullName = rs.getString("userFullName");
                boolean uIsAdmin = rs.getBoolean("isAdmin");

                Users temp = new Users(uID, uName, uEmail, uPassword, uFullName, uIsAdmin);
                arr.add(temp);
            }

        }
        return arr;

    }
}
