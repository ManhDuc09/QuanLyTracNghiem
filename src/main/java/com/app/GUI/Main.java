package com.app.GUI;

import com.app.DAO.AnswerDAO;

import javax.swing.*;
import java.sql.SQLException;

public class Main extends JFrame{
    private JButton button1;
    private JPanel MainPanel;
    private JTextField textField1;

    public Main (){
        setContentPane(MainPanel);
        setTitle("Main");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,200);
        setVisible(true);
    }
    public static void main (String[] arg) throws SQLException {
        AnswerDAO.getAnswers();
        new Main();
    }
}
