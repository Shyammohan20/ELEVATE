package app.todo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Task6 {

    private DefaultListModel<String> taskModel;
    private JList<String> taskList;
    private JTextField todoField;

    public Task6() {
        JFrame frame = new JFrame("To Do List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 500);

        todoField = new JTextField();
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Remorve");

        taskModel = new DefaultListModel<>();
        taskList = new JList<>(taskModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(todoField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);

        addButton.addActionListener(e -> {
            String todo = todoField.getText().trim();
            if (!todo.isEmpty()) {
            	taskModel.addElement(todo);
                todoField.setText("");
            }
        });

        deleteButton.addActionListener(e -> {
            int pos = taskList.getSelectedIndex();
            if (pos != -1) {
            	taskModel.remove(pos);
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Task6::new);
    }
}
