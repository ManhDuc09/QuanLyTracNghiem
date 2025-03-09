import javax.swing.*;
import java.awt.*;

public class testing {
    private static DefaultListModel<String> selectedTopicsModel; // Stores selected topics
    private static JComboBox<String> topicDropdown; // Dropdown for selecting topics

    public static void main(String[] args) {
        SwingUtilities.invokeLater(testing::createGUI);
    }

    public static void createGUI() {
        JFrame frame = new JFrame("Select Topics for Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Topics dropdown
        String[] topics = {"Math", "Science", "History", "English", "Geography"};
        topicDropdown = new JComboBox<>(topics);

        JButton addButton = new JButton("Add Topic");

        // List to show selected topics
        selectedTopicsModel = new DefaultListModel<>();
        JList<String> selectedTopicsList = new JList<>(selectedTopicsModel);
        JScrollPane scrollPane = new JScrollPane(selectedTopicsList);

        // Remove button
        JButton removeButton = new JButton("Remove Selected");

        // Add selected topic
        addButton.addActionListener(e -> {
            String selectedTopic = (String) topicDropdown.getSelectedItem();
            if (!selectedTopicsModel.contains(selectedTopic)) {
                selectedTopicsModel.addElement(selectedTopic);
            }
        });

        // Remove selected topic
        removeButton.addActionListener(e -> {
            int selectedIndex = selectedTopicsList.getSelectedIndex();
            if (selectedIndex != -1) {
                selectedTopicsModel.remove(selectedIndex);
            }
        });

        // Top panel (Dropdown + Add button)
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Select Topic:"));
        topPanel.add(topicDropdown);
        topPanel.add(addButton);

        // Bottom panel (Remove button)
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(removeButton);

        // Add components
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }
}
