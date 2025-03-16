package com.app.GUI;

import com.app.DAO.TopicDAO;
import com.app.Models.Topics;
import com.app.Services.TopicSevice;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class Topic extends JPanel {
    private JPanel panel1;
    private JLabel CA;
    private JTable table1;
    private JButton thêmButton;
    private JButton xóaButton;
    private JButton sửaButton;
    private JTextField TPtextField1;
    private JTextField TPtextFieldParent;

    public Topic() {
        $$$setupUI$$$();
        add(panel1);
        initComponent();
        loadTopics();
    }

    public void initComponent() {
        table1.revalidate();
        table1.repaint();
        thêmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Lấy dữ liệu từ text fields
                    String title = TPtextField1.getText().trim();
                    String parentText = TPtextFieldParent.getText().trim();

                    // Kiểm tra xem title có rỗng không
                    if (title.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Tên chủ đề không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Kiểm tra xem parent có nhập không, nếu không thì đặt mặc định là 0 (root topic)
                    int parentTp = 0;
                    if (!parentText.isEmpty()) {
                        try {
                            parentTp = Integer.parseInt(parentText);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Mã chủ đề cha phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }

                    // Gọi phương thức thêm chủ đề
                    boolean success = TopicSevice.addTopic(title, parentTp);

                    if (success) {
                        JOptionPane.showMessageDialog(null, "Thêm chủ đề thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                        // Xóa nội dung trong text field sau khi thêm thành công
                        TPtextField1.setText("");
                        TPtextFieldParent.setText("");

                        // Load lại danh sách chủ đề trong bảng (JTable)
                        loadTopics();
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm chủ đề thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        xóaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectRow = table1.getSelectedRow();

                if (selectRow == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn chủ đề để xóa!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int tpID = (int) table1.getValueAt(selectRow, 0);

                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa chủ đề này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        boolean success = TopicSevice.deleteTopic(tpID); // Gọi Service

                        if (success) {
                            JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Xóa thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }

                        // Load lại danh sách sau khi xóa
                        loadTopics();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        sửaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một chủ đề để sửa!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Lấy thông tin hiện tại từ bảng
                int tpID = (int) table1.getValueAt(selectedRow, 0);
                String currentTitle = (String) table1.getValueAt(selectedRow, 1);
                int currentParent = (int) table1.getValueAt(selectedRow, 2);

                // Lấy thông tin mới từ các text fields
                String newTitle = TPtextField1.getText().trim();
                String parentText = TPtextFieldParent.getText().trim();

                // Kiểm tra nếu người dùng không nhập gì thì giữ giá trị cũ
                if (newTitle.isEmpty()) {
                    newTitle = currentTitle;
                }

                int newParent = currentParent;
                if (!parentText.isEmpty()) {
                    try {
                        newParent = Integer.parseInt(parentText);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Mã chủ đề cha phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Gọi Service để cập nhật
                boolean success = TopicSevice.updateTopic(tpID, newTitle, newParent);

                if (success) {
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadTopics(); // Tải lại danh sách chủ đề sau khi cập nhật
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table1.getSelectedRow(); // Lấy chỉ mục dòng được chọn

                if (selectedRow != -1) {
                    // Lấy dữ liệu từ các cột trong bảng
                    int tpID = (int) table1.getValueAt(selectedRow, 0); // ID
                    String title = (String) table1.getValueAt(selectedRow, 1); // Tên chủ đề
                    int parent = (int) table1.getValueAt(selectedRow, 2); // Chủ đề cha

                    // Đưa dữ liệu vào các JTextField
                    TPtextField1.setText(title); // Hiển thị tên chủ đề
                    TPtextFieldParent.setText(String.valueOf(parent)); // Hiển thị mã chủ đề cha
                }
            }
        });

    }

    private void loadTopics() {
        try {
            ArrayList<Topics> topicsList = TopicSevice.getTopics();
            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"ID", "Tên chủ đề", "Chủ đề cha", "Trạng thái"}, 0
            );
            table1.setModel(model);

            // Xóa dữ liệu cũ trong bảng
            model.setRowCount(0);

            // Thêm dữ liệu mới vào bảng
            for (Topics topic : topicsList) {
                model.addRow(new Object[]{topic.getTpID(), topic.getTpTitle(), topic.getTpParent(), topic.getTpStatus()});
            }

        } catch (Exception e) {
            System.out.println("Lỗi khi tải dữ liệu chủ đề");
            e.printStackTrace();
        }
    }


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 5, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 100), null, 4, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel2.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(700, 200), null, 1, false));
        table1 = new JTable();
        table1.setCellSelectionEnabled(true);
        table1.setPreferredSize(new Dimension(550, 200));
        scrollPane1.setViewportView(table1);
        thêmButton = new JButton();
        thêmButton.setText("Thêm");
        panel2.add(thêmButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        xóaButton = new JButton();
        xóaButton.setText("Xóa");
        panel2.add(xóaButton, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sửaButton = new JButton();
        sửaButton.setText("Sửa");
        panel2.add(sửaButton, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        TPtextField1 = new JTextField();
        panel2.add(TPtextField1, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        CA = new JLabel();
        CA.setText("Topic");
        panel2.add(CA, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 2), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Parent");
        panel2.add(label1, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        TPtextFieldParent = new JTextField();
        panel2.add(TPtextFieldParent, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
