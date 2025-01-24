package org.mql.java.ui;

import javax.swing.*;
import java.awt.*;

public class ClassDiagramFrame extends JFrame {
    public ClassDiagramFrame(String projectPath) {
        setTitle("Class Diagram Viewer");
        setSize(1200, 800);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        ClassDiagramPanel diagramPanel = new ClassDiagramPanel(projectPath);
        JScrollPane scrollPane = new JScrollPane(diagramPanel); 
        add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}