package com.app.GUI;

import com.app.DAO.TopicDAO;
import com.app.Models.Questions;
import com.app.Models.Answer;
import com.app.Models.Topics;
import com.app.Services.AnswerService;
import com.app.Services.QuestionService;
import com.app.Services.TopicSevice;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public class Question extends JPanel {
    private JPanel panel1;
    private DefaultTableModel tableModel;
    private JTextField textField1;
    private JCheckBox aCheckBox;
    private JLabel CA;
    private JTextArea textArea1;
    private JCheckBox bCheckBox;
    private JTextField textField2;
    private JCheckBox cCheckBox;
    private JCheckBox dCheckBox;
    private JCheckBox eCheckBox;
    private JTable table1;
    private JButton thêmButton;
    private JButton xóaButton;
    private JButton sửaButton;
    private JRadioButton đápÁnĐúngRadioButton;
    private JComboBox comboBox1;
    private JButton thêmẢnhAButton;
    private JButton thêmẢnhBButton;
    private JButton thêmẢnhCButton;
    private JButton thêmẢnhDButton;
    private JButton thêmẢnhEButton;
    private JComboBox comboBox2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JRadioButton đápÁnĐúngRadioButton2;
    private JRadioButton đápÁnĐúngRadioButton3;
    private JRadioButton đápÁnĐúngRadioButton4;
    private JRadioButton đápÁnĐúngRadioButton5;

    public Question() {
        //loadTopicsComboBox();
        $$$setupUI$$$(); // Call IntelliJ's auto-generated UI method
        add(panel1); // Add the generated UI panel to this JPanel
        //setupTableData();
        loadQuestions();
        addImageButtonListener(thêmẢnhAButton);
        addImageButtonListener(thêmẢnhBButton);
        addImageButtonListener(thêmẢnhCButton);
        addImageButtonListener(thêmẢnhDButton);
        addImageButtonListener(thêmẢnhEButton);
        thêmẢnhBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null); // Open File Dialog

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(null, "Selected: " + selectedFile.getAbsolutePath());
                }
            }
        });

        thêmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Lấy nội dung câu hỏi từ textArea
                    String content = textArea1.getText().trim();
                    if (content.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nội dung câu hỏi không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Lấy đường dẫn ảnh (hiện tại chỉ minh họa, có thể cập nhật từ button upload)
                    String imagePath = "no_image.png"; // Nếu có thêm ảnh, cập nhật đường dẫn ở đây.

                    // Lấy ID chủ đề từ comboBox1 (Giả sử comboBox1 chứa danh sách chủ đề)


                    // Lấy mức độ từ comboBox2
                    String level = comboBox1.getSelectedItem().toString();


                    // Kiểm tra dữ liệu đầu vào hợp lệ
                    int topicID = comboBox2.getSelectedIndex();
                    if (topicID == 0) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn chủ đề!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (!TopicSevice.isTopicExists(topicID)) {
                        JOptionPane.showMessageDialog(null, "Chủ đề không tồn tại trong hệ thống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }


                    // Tạo đối tượng `Questions`
                    Questions newQuestion = new Questions(0, content, imagePath, topicID, level, true);

                    // Gọi Service để thêm vào database
                    boolean success = QuestionService.addQuestion(newQuestion);

                    if (success) {
                        int qID = QuestionService.getLastInsertedQuestionID();
                        addAnswers(qID);
                        JOptionPane.showMessageDialog(null, "Thêm câu hỏi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                        // Xóa nội dung sau khi thêm thành công
                        textArea1.setText("");
                        comboBox1.setSelectedIndex(0);
                        comboBox2.setSelectedIndex(0);

                        // Load lại dữ liệu lên bảng
                        loadQuestions();
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm câu hỏi thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        xóaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow(); // Lấy hàng được chọn trong bảng
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một câu hỏi để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Lấy ID của câu hỏi từ bảng (giả sử cột 0 là qID)
                int qId = (int) table1.getValueAt(selectedRow, 0);

                // Xác nhận trước khi xóa
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa câu hỏi này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        boolean success = QuestionService.deleteQuestion(qId);
                        if (success) {
                            JOptionPane.showMessageDialog(null, "Xóa câu hỏi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            loadQuestions(); // Cập nhật lại bảng câu hỏi
                        } else {
                            JOptionPane.showMessageDialog(null, "Xóa câu hỏi thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Lỗi khi xóa câu hỏi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        sửaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow(); // Lấy hàng đang chọn
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một câu hỏi để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    // Lấy ID câu hỏi từ bảng
                    int qId = (int) table1.getValueAt(selectedRow, 0);

                    // Lấy nội dung câu hỏi
                    String content = textArea1.getText().trim();
                    if (content.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nội dung câu hỏi không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Lấy đường dẫn ảnh (hiện tại giả định, có thể cập nhật từ nút tải ảnh)
                    String imagePath = "no_image.png";

                    // Lấy ID chủ đề
                    int topicID = comboBox2.getSelectedIndex();
                    if (topicID == 0) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn chủ đề!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Kiểm tra chủ đề tồn tại không
                    if (!TopicSevice.isTopicExists(topicID)) {
                        JOptionPane.showMessageDialog(null, "Chủ đề không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Lấy mức độ từ comboBox1
                    String level = comboBox1.getSelectedItem().toString();

                    // Tạo đối tượng `Questions`
                    Questions updatedQuestion = new Questions(qId, content, imagePath, topicID, level, true);

                    // Gọi Service để cập nhật câu hỏi
                    boolean questionUpdated = QuestionService.updateQuestion(updatedQuestion);

                    // Nếu cập nhật câu hỏi thành công, cập nhật câu trả lời
                    if (questionUpdated) {
                        // Xóa câu trả lời cũ
                        AnswerService.deleteAnswersByQuestionId(qId);

                        // Thêm câu trả lời mới
                        addAnswers(qId);

                        JOptionPane.showMessageDialog(null, "Cập nhật câu hỏi & câu trả lời thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        loadQuestions(); // Load lại bảng câu hỏi
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật câu hỏi thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });




    }

    private void addAnswers(int qID) throws SQLException {
        // 🟢 Lấy nội dung các đáp án từ text field
        String[] answers = {
                textField1.getText().trim(),
                textField2.getText().trim(),
                textField3.getText().trim(),
                textField4.getText().trim(),
                textField5.getText().trim()
        };

        // 🟢 Tạo danh sách các RadioButton tương ứng
        JRadioButton[] radioButtons = {đápÁnĐúngRadioButton, đápÁnĐúngRadioButton2, đápÁnĐúngRadioButton3, đápÁnĐúngRadioButton4, đápÁnĐúngRadioButton5};

        // 🟢 Xác định đáp án nào được chọn là đúng
        int correctIndex = -1;
        for (int i = 0; i < radioButtons.length; i++) {
            if (radioButtons[i].isSelected()) {
                correctIndex = i;
                break;
            }
        }

        // 🚨 Nếu không có đáp án đúng nào được chọn, báo lỗi
        if (correctIndex == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một đáp án đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 🟢 Lặp qua từng đáp án và thêm vào database
        for (int i = 0; i < answers.length; i++) {
            if (!answers[i].isEmpty()) { // Chỉ thêm nếu nội dung không rỗng
                boolean isRight = (i == correctIndex); // Chỉ đáp án đúng có giá trị 1
                Answer answer = new Answer(0, qID, answers[i], "no_image.png", isRight, true);
                AnswerService.addAnswer(answer);
            }
        }
    }

    private void addImageButtonListener(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println(selectedFile.getAbsolutePath());
                }
            }
        });
    }

//    private void setupTableData() {
//        // Define column names
//        String[] columnNames = {"ID", "Question", "Answer"};
//
//        // Define row data
//        Object[][] data = {
//                {1, "What is Java?", "A programming language"},
//                {2, "What is OOP?", "Object-Oriented Programming"},
//                {3, "What is Swing?", "A GUI framework for Java"}
//        };
//
//        // Set up table model
//        tableModel = new DefaultTableModel(data, columnNames);
//        table1.setModel(tableModel);
//    }

    private void loadQuestions() {
        try {
            ArrayList<Questions> questionList = QuestionService.getQuestions(); // Lấy danh sách câu hỏi
            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"ID", "Nội dung", "Ảnh", "Chủ đề", "Mức độ", "Trạng thái"}, 0
            );
            table1.setModel(model);

            // Xóa dữ liệu cũ trong bảng
            model.setRowCount(0);

            // Thêm dữ liệu mới vào bảng
            for (Questions q : questionList) {
                model.addRow(new Object[]{q.getqID(), q.getqContent(), q.getqPicture(), q.getTopicID(), q.getqLevel(), q.getqStatus()});
            }
            //  Bắt sự kiện khi click vào dòng để hiển thị câu trả lời
            table1.getSelectionModel().addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table1.getSelectedRow();
                    if (selectedRow != -1) {
                        int qID = (int) model.getValueAt(selectedRow, 0);
                        loadQuestionToForm(qID);
                        loadAnswers(qID); // Gọi hàm hiển thị câu trả lời
                    }
                }
            });

        } catch (Exception e) {
            System.out.println("Lỗi tải câu hỏi!");
            e.printStackTrace();
        }
    }

    private void loadQuestionToForm(int qID) {
        try {
            Questions question = QuestionService.getQuestionById(qID);
            if (question != null) {
                textArea1.setText(question.getqContent());  // Hiển thị nội dung câu hỏi
                textField1.setText(question.getqPicture()); // Hiển thị ảnh (nếu có)
                comboBox2.setSelectedItem(question.getTopicID()); // Chủ đề
                comboBox1.setSelectedItem(question.getqLevel()); // Mức độ
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAnswers(int qID) {
        try {
            ArrayList<Answer> answerList = AnswerService.getAnswersByQuestionID(qID);

            // Xóa các text field trước đó
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            textField4.setText("");
            textField5.setText("");

            // Xóa trạng thái checkbox
            aCheckBox.setSelected(false);
            bCheckBox.setSelected(false);
            cCheckBox.setSelected(false);
            dCheckBox.setSelected(false);
            eCheckBox.setSelected(false);

            // Đổ dữ liệu vào các text field và checkbox
            int index = 0;
            for (Answer ans : answerList) {
                if (index == 0) {
                    textField1.setText(ans.getaContent());
                    aCheckBox.setSelected(ans.isRight());
                } else if (index == 1) {
                    textField2.setText(ans.getaContent());
                    bCheckBox.setSelected(ans.isRight());
                } else if (index == 2) {
                    textField3.setText(ans.getaContent());
                    cCheckBox.setSelected(ans.isRight());
                } else if (index == 3) {
                    textField4.setText(ans.getaContent());
                    dCheckBox.setSelected(ans.isRight());
                } else if (index == 4) {
                    textField5.setText(ans.getaContent());
                    eCheckBox.setSelected(ans.isRight());
                }
                index++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi tải câu trả lời!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

//    private void loadTopicsComboBox() {
//        try {
//            ArrayList<Topics> topics = TopicDAO.getTopics(); // Lấy danh sách chủ đề từ DB
//            comboBox1.removeAllItems(); // Xóa dữ liệu cũ
//            comboBox1.addItem(new Topics(0, "Chọn chủ đề", 0, true)); // Thêm mục mặc định
//
//            for (Topics topic : topics) {
//                comboBox1.addItem(topic); // Thêm đối tượng Topics vào comboBox1
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(11, 6, new Insets(0, 0, 0, 0), -1, -1));
        aCheckBox = new JCheckBox();
        aCheckBox.setText("A");
        panel1.add(aCheckBox, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        CA = new JLabel();
        CA.setText("Câu hỏi");
        panel1.add(CA, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(126, 2), null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 20), null, 0, false));
        scrollPane1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-2104859)), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        textArea1 = new JTextArea();
        textArea1.setLineWrap(true);
        textArea1.setMargin(new Insets(0, 0, 0, 0));
        textArea1.setMaximumSize(new Dimension(2147483647, 1));
        textArea1.setPreferredSize(new Dimension(250, 1));
        scrollPane1.setViewportView(textArea1);
        bCheckBox = new JCheckBox();
        bCheckBox.setText("B");
        panel1.add(bCheckBox, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        cCheckBox = new JCheckBox();
        cCheckBox.setText("C");
        panel1.add(cCheckBox, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        dCheckBox = new JCheckBox();
        dCheckBox.setText("D");
        panel1.add(dCheckBox, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        eCheckBox = new JCheckBox();
        eCheckBox.setText("E");
        panel1.add(eCheckBox, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        panel1.add(scrollPane2, new com.intellij.uiDesigner.core.GridConstraints(10, 0, 1, 6, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(550, 200), null, 1, false));
        table1 = new JTable();
        table1.setCellSelectionEnabled(true);
        table1.setPreferredSize(new Dimension(150, 200));
        scrollPane2.setViewportView(table1);
        thêmButton = new JButton();
        thêmButton.setText("Thêm");
        panel1.add(thêmButton, new com.intellij.uiDesigner.core.GridConstraints(9, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 20), null, 0, false));
        xóaButton = new JButton();
        xóaButton.setText("Xóa");
        panel1.add(xóaButton, new com.intellij.uiDesigner.core.GridConstraints(9, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sửaButton = new JButton();
        sửaButton.setText("Sửa");
        panel1.add(sửaButton, new com.intellij.uiDesigner.core.GridConstraints(9, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Độ khó");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(126, 17), null, 0, false));
        comboBox1 = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Dễ");
        defaultComboBoxModel1.addElement("Trung bình");
        defaultComboBoxModel1.addElement("Khó");
        comboBox1.setModel(defaultComboBoxModel1);
        panel1.add(comboBox1, new com.intellij.uiDesigner.core.GridConstraints(2, 4, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        đápÁnĐúngRadioButton = new JRadioButton();
        đápÁnĐúngRadioButton.setText("Đáp án đúng");
        panel1.add(đápÁnĐúngRadioButton, new com.intellij.uiDesigner.core.GridConstraints(4, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        đápÁnĐúngRadioButton2 = new JRadioButton();
        đápÁnĐúngRadioButton2.setText("Đáp án đúng");
        panel1.add(đápÁnĐúngRadioButton2, new com.intellij.uiDesigner.core.GridConstraints(5, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        đápÁnĐúngRadioButton3 = new JRadioButton();
        đápÁnĐúngRadioButton3.setText("Đáp án đúng");
        panel1.add(đápÁnĐúngRadioButton3, new com.intellij.uiDesigner.core.GridConstraints(6, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        đápÁnĐúngRadioButton4 = new JRadioButton();
        đápÁnĐúngRadioButton4.setText("Đáp án đúng");
        panel1.add(đápÁnĐúngRadioButton4, new com.intellij.uiDesigner.core.GridConstraints(7, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        đápÁnĐúngRadioButton5 = new JRadioButton();
        đápÁnĐúngRadioButton5.setText("Đáp án đúng");
        panel1.add(đápÁnĐúngRadioButton5, new com.intellij.uiDesigner.core.GridConstraints(8, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        panel1.add(textField1, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), new Dimension(150, -1), 0, false));
        textField2 = new JTextField();
        panel1.add(textField2, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), new Dimension(150, -1), 0, false));
        textField3 = new JTextField();
        panel1.add(textField3, new com.intellij.uiDesigner.core.GridConstraints(6, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), new Dimension(150, -1), 0, false));
        textField4 = new JTextField();
        panel1.add(textField4, new com.intellij.uiDesigner.core.GridConstraints(7, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), new Dimension(150, -1), 0, false));
        textField5 = new JTextField();
        textField5.setText("");
        panel1.add(textField5, new com.intellij.uiDesigner.core.GridConstraints(8, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), new Dimension(150, -1), 0, false));
        thêmẢnhAButton = new JButton();
        thêmẢnhAButton.setText("Thêm ảnh");
        panel1.add(thêmẢnhAButton, new com.intellij.uiDesigner.core.GridConstraints(4, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(126, 34), null, 0, false));
        thêmẢnhBButton = new JButton();
        thêmẢnhBButton.setText("Thêm ảnh");
        panel1.add(thêmẢnhBButton, new com.intellij.uiDesigner.core.GridConstraints(5, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(126, 34), null, 0, false));
        thêmẢnhCButton = new JButton();
        thêmẢnhCButton.setText("Thêm ảnh");
        panel1.add(thêmẢnhCButton, new com.intellij.uiDesigner.core.GridConstraints(6, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(126, 34), null, 0, false));
        thêmẢnhDButton = new JButton();
        thêmẢnhDButton.setText("Thêm ảnh");
        panel1.add(thêmẢnhDButton, new com.intellij.uiDesigner.core.GridConstraints(7, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(126, 34), null, 0, false));
        thêmẢnhEButton = new JButton();
        thêmẢnhEButton.setText("Thêm ảnh");
        panel1.add(thêmẢnhEButton, new com.intellij.uiDesigner.core.GridConstraints(8, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(1, 34), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Topic");
        panel1.add(label2, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBox2 = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("Chọn chử đề");
        defaultComboBoxModel2.addElement("Toán");
        defaultComboBoxModel2.addElement("Lý");
        defaultComboBoxModel2.addElement("Anh");
        comboBox2.setModel(defaultComboBoxModel2);
        panel1.add(comboBox2, new com.intellij.uiDesigner.core.GridConstraints(3, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(3, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(đápÁnĐúngRadioButton2);
        buttonGroup.add(đápÁnĐúngRadioButton);
        buttonGroup.add(đápÁnĐúngRadioButton3);
        buttonGroup.add(đápÁnĐúngRadioButton4);
        buttonGroup.add(đápÁnĐúngRadioButton5);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
