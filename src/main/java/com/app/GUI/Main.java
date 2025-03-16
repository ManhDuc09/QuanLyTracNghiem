package com.app.GUI;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private JPanel MainPanel;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public Main() {
        setTitle("Đăng nhập");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null); // Center the window

        MainPanel = new JPanel();
        MainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblUsername = new JLabel("Tên đăng nhập");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        MainPanel.add(lblUsername, gbc);

        txtUsername = new JTextField(15);
        gbc = new GridBagConstraints(); 
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        MainPanel.add(txtUsername, gbc);

        JLabel lblPassword = new JLabel("Mật khẩu");
        gbc = new GridBagConstraints(); 
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        MainPanel.add(lblPassword, gbc);

        txtPassword = new JPasswordField(15);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        MainPanel.add(txtPassword, gbc);

        btnLogin = new JButton("Đăng nhập");
        gbc = new GridBagConstraints(); 
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        MainPanel.add(btnLogin, gbc);
        
        
        setContentPane(MainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {

        //SwingUtilities.invokeLater(Main::new);
        //UserMainView main = new UserMainView();
        AdminMainView mainView = new AdminMainView();
    }
}