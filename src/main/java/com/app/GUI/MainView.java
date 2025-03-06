    package com.app.GUI;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;

    public class MainView extends JFrame {

        private JButton thốngKêButton;
        private JButton ngườiDùngButton;
        private JButton đềThiButton;
        private JButton câuHỏiButton;
        private JButton đăngXuấtButton;
        private JButton chủĐềButton;
        private JPanel MainPanel;
        private JPanel MainContentPanel;

        public MainView() {
            setContentPane(MainPanel);
            setTitle("Main");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(700, 700);
            setLocationRelativeTo(null);
            setVisible(true);
            CardLayout layout = new CardLayout();
            JPanel topic = new Topic();
            JPanel question = new Question();
            MainContentPanel.setLayout(layout);
            MainContentPanel.add(question, "question");
            MainContentPanel.add(topic, "topic");
            câuHỏiButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    layout.show(MainContentPanel, "question");
                }
            });
            chủĐềButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    layout.show(MainContentPanel, "topic");
                }
            });
        }

        {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
            $$$setupUI$$$();
        }

        /**
         * Method generated by IntelliJ IDEA GUI Designer
         * >>> IMPORTANT!! <<<
         * DO NOT edit this method OR call it in your code!
         *
         * @noinspection ALL
         */
        private void $$$setupUI$$$() {
            MainPanel = new JPanel();
            MainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(8, 5, new Insets(0, 0, 0, 0), -1, -1));
            final JLabel label1 = new JLabel();
            label1.setText("Admin");
            MainPanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
            thốngKêButton = new JButton();
            thốngKêButton.setText("Thống kê");
            MainPanel.add(thốngKêButton, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
            ngườiDùngButton = new JButton();
            ngườiDùngButton.setText("Người dùng");
            MainPanel.add(ngườiDùngButton, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
            đềThiButton = new JButton();
            đềThiButton.setText("Đề thi");
            MainPanel.add(đềThiButton, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
            chủĐềButton = new JButton();
            chủĐềButton.setText("Chủ đề");
            MainPanel.add(chủĐềButton, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
            đăngXuấtButton = new JButton();
            đăngXuấtButton.setText("Đăng xuất");
            MainPanel.add(đăngXuấtButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
            câuHỏiButton = new JButton();
            câuHỏiButton.setText("Câu hỏi");
            MainPanel.add(câuHỏiButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
            MainContentPanel = new JPanel();
            MainContentPanel.setLayout(new CardLayout(0, 0));
            MainPanel.add(MainContentPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 8, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        }

        /**
         * @noinspection ALL
         */
        public JComponent $$$getRootComponent$$$() {
            return MainPanel;
        }

    }
