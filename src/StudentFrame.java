import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentFrame extends JFrame {

    private StudentManager manager;
    private JTable studentTable;
    private DefaultTableModel tableModel;

    private JTextField idField;
    private JTextField nameField;
    private JTextField gpaField;
    private JTextField searchField;

    // Stats Labels
    private JLabel totalStudentsLabel;
    private JLabel avgGpaLabel;
    private JLabel maxGpaLabel;

    public StudentFrame() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        manager = new StudentManager();

        setTitle("Student Management System");
        setSize(850, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 1. Top Panel (Header + Search Bar)
        JPanel northPanel = new JPanel(new BorderLayout(5, 5));
        northPanel.setBackground(new Color(245, 247, 250));

        JLabel titleLabel = new JLabel("🎓 Student Management Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(new Color(44, 62, 80));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));

        JPanel searchPanel = createSearchPanel();
        searchPanel.setBackground(new Color(245, 247, 250));

        northPanel.add(titleLabel, BorderLayout.NORTH);
        northPanel.add(searchPanel, BorderLayout.SOUTH);
        add(northPanel, BorderLayout.NORTH);

        // 2. Center Panel (Stats Bar + JTable)
        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));

        JPanel statsPanel = createStatsPanel();
        centerPanel.add(statsPanel, BorderLayout.NORTH);

        // Table Setup
        String[] columnNames = {"ID", "Name", "GPA"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        studentTable = new JTable(tableModel);
        studentTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        studentTable.setRowHeight(28);
        studentTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        studentTable.getTableHeader().setBackground(new Color(52, 73, 94));
        studentTable.getTableHeader().setForeground(Color.WHITE);
        studentTable.setSelectionBackground(new Color(174, 214, 241));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < studentTable.getColumnCount(); i++) {
            studentTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 15, 10, 15));
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // 3. Bottom Control Panel
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.SOUTH);

        loadStudentData();
    }

    private JPanel createStatsPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 8));
        panel.setBackground(new Color(236, 240, 241));
        panel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(189, 195, 199)));

        totalStudentsLabel = new JLabel("Total: 0");
        avgGpaLabel = new JLabel("Average GPA: 0.00");
        maxGpaLabel = new JLabel("Highest GPA: 0.00");

        Font font = new Font("Segoe UI", Font.BOLD, 13);
        totalStudentsLabel.setFont(font);
        avgGpaLabel.setFont(font);
        maxGpaLabel.setFont(font);

        panel.add(totalStudentsLabel);
        panel.add(new JLabel("|"));
        panel.add(avgGpaLabel);
        panel.add(new JLabel("|"));
        panel.add(maxGpaLabel);

        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        searchField = new JTextField(12);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JButton searchButton = createStyledButton("Search ID", new Color(52, 152, 219));
        JButton showAllButton = createStyledButton("Show All", new Color(54, 69, 79));

        panel.add(new JLabel("Search ID:"));
        panel.add(searchField);
        panel.add(searchButton);
        panel.add(showAllButton);

        searchButton.addActionListener(e -> searchStudentAction());
        showAllButton.addActionListener(e -> loadStudentData());

        return panel;
    }

    private JPanel createControlPanel() {
        JPanel mainPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));

        // Input Fields
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        idField = new JTextField(9);
        nameField = new JTextField(12);
        gpaField = new JTextField(4);

        inputPanel.add(new JLabel("ID (9 Digits):"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("GPA:"));
        inputPanel.add(gpaField);

        // Action Buttons with High Contrast
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        JButton addButton = createStyledButton("Add Student", new Color(39, 174, 96));
        JButton deleteButton = createStyledButton("Delete Selected", new Color(192, 57, 43));
        JButton sortButton = createStyledButton("Sort by GPA", new Color(142, 68, 173));
        JButton refreshButton = createStyledButton("Refresh", new Color(44, 62, 80));

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(refreshButton);

        addButton.addActionListener(e -> addStudentAction());
        deleteButton.addActionListener(e -> deleteStudentAction());
        sortButton.addActionListener(e -> sortStudentsAction());
        refreshButton.addActionListener(e -> loadStudentData());

        mainPanel.add(inputPanel);
        mainPanel.add(buttonPanel);

        return mainPanel;
    }

    // SINGLE COPY of the helper method for high-contrast button styling
    private JButton createStyledButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bg.darker(), 1),
                BorderFactory.createEmptyBorder(6, 14, 6, 14)
        ));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void loadStudentData() {
        tableModel.setRowCount(0);
        List<Student> students = manager.getStudentList();
        for (Student s : students) {
            Object[] row = {s.getId(), s.getName(), s.getGpa()};
            tableModel.addRow(row);
        }
        searchField.setText("");
        updateStatistics();
    }

    private void updateStatistics() {
        List<Student> students = manager.getStudentList();
        if (students.isEmpty()) {
            totalStudentsLabel.setText("Total: 0");
            avgGpaLabel.setText("Average GPA: 0.00");
            maxGpaLabel.setText("Highest GPA: 0.00");
            return;
        }

        totalStudentsLabel.setText("Total Students: " + students.size());
        avgGpaLabel.setText(String.format("Average GPA: %.2f", manager.calculateAverageGpa()));

        double maxGpa = students.stream().mapToDouble(Student::getGpa).max().orElse(0.0);
        maxGpaLabel.setText(String.format("Highest GPA: %.2f", maxGpa));
    }

    private void sortStudentsAction() {
        manager.sortByGpaDescending();
        loadStudentData();
    }

    private void searchStudentAction() {
        String searchText = searchField.getText().trim();
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Student ID to search.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int searchId = Integer.parseInt(searchText);
            Student s = manager.searchStudentById(searchId);

            tableModel.setRowCount(0);
            Object[] row = {s.getId(), s.getName(), s.getGpa()};
            tableModel.addRow(row);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID must be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (StudentNotFoundException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Not Found", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addStudentAction() {
        try {
            String idText = idField.getText().trim();
            String name = nameField.getText().trim();
            String gpaText = gpaField.getText().trim();

            if (!idText.matches("^\\d{9}$")) {
                JOptionPane.showMessageDialog(this, "ID must be exactly 9 digits.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = Integer.parseInt(idText);

            if (name.isEmpty() || !name.matches("^[a-zA-Z\\s]+$")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid alphabetic name.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double gpa = Double.parseDouble(gpaText);
            if (gpa < 0.0 || gpa > 5.0) {
                JOptionPane.showMessageDialog(this, "GPA must be between 0.0 and 5.0.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                if (manager.searchStudentById(id) != null) {
                    JOptionPane.showMessageDialog(this, "Student ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (StudentNotFoundException e) {
                // ID is available
            }

            manager.addStudent(new Student(id, name, gpa));
            loadStudentData();

            idField.setText("");
            nameField.setText("");
            gpaField.setText("");

            JOptionPane.showMessageDialog(this, "Student added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStudentAction() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a student from the table to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);

        try {
            manager.deleteStudent(id);
            loadStudentData();
            JOptionPane.showMessageDialog(this, "Student deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (StudentNotFoundException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentFrame().setVisible(true));
    }
}