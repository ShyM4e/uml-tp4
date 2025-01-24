package org.mql.java.ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainFrame extends JFrame {
    private JTextField pathField;
    private JButton browseButton, parseButton;

    public MainFrame() {
        setTitle("Java Project Parser");
        setSize(600, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new BorderLayout());
        pathField = new JTextField();
        browseButton = new JButton("Browse");
        parseButton = new JButton("Parse Project");

        inputPanel.add(pathField, BorderLayout.CENTER);
        inputPanel.add(browseButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.NORTH);
        add(parseButton, BorderLayout.SOUTH);

        browseButton.addActionListener(e -> chooseFolder());
        parseButton.addActionListener(e -> parseProject());

        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void chooseFolder() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = fileChooser.getSelectedFile();
            pathField.setText(selectedFolder.getAbsolutePath());
        }
    }

    private void parseProject() {
        String projectPath = pathField.getText();
        if (projectPath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a project folder.");
        } else {
            new ClassDiagramFrame(projectPath);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
