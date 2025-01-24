package org.mql.java.ui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import org.mql.java.controller.ProjectParser;
import org.mql.java.format.*;

public class ClassDiagramPanel extends JPanel {
    private ProjectFormat project;
    private Map<String, Rectangle> classRectangles;

    public ClassDiagramPanel(String projectPath) {
        this.project = parseProject(projectPath);
        this.classRectangles = new HashMap<>();
        setBackground(Color.WHITE);
    }

    private ProjectFormat parseProject(String projectPath) {
        try {
            ProjectParser parser = new ProjectParser(projectPath);
            return parser.parseProject();
        } catch (Exception e) {
            System.err.println("Error parsing project: " + e.getMessage());
            return null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (project == null) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = 50, y = 50;
        int maxHeight = 0;

        for (var pkg : project.getPackages()) {
            for (var cls : pkg.getClasses()) {
                Rectangle rect = drawClass(g2, cls, x, y);
                classRectangles.put(cls.getName(), rect);
                x += rect.width + 50;
                maxHeight = Math.max(maxHeight, rect.height);

                if (x + rect.width > getWidth()) {
                    x = 50;
                    y += maxHeight + 50;
                    maxHeight = 0;
                }
            }
        }

        for (var pkg : project.getPackages()) {
            for (var cls : pkg.getClasses()) {
                for (var relationship : cls.getRelationships()) {
                    drawRelationship(g2, relationship);
                }
            }
        }

        setPreferredSize(new Dimension(getWidth(), y + maxHeight + 50));
        revalidate();
    }

    private Rectangle drawClass(Graphics2D g2, ClassFormat cls, int x, int y) {
        FontMetrics metrics = g2.getFontMetrics();
        int padding = 10;
        int lineHeight = metrics.getHeight();
        int width = 260;
        int height = padding * 2 + lineHeight;

        if (!cls.getFields().isEmpty()) {
            height += lineHeight * cls.getFields().size() + padding;
        }
        if (!cls.getMethods().isEmpty()) {
            height += lineHeight * cls.getMethods().size();
        }

        g2.setColor(new Color(0X99CCFF));
        g2.fillRect(x, y, width, padding * 2 + lineHeight);
        g2.setColor(Color.BLACK);
        g2.drawRect(x, y, width, height);

        Font originalFont = g2.getFont();
        g2.setFont(new Font(originalFont.getName(), Font.BOLD, 14));
        int textY = y + padding + lineHeight;
        g2.drawString(cls.getName(), x + padding, textY);
        g2.setFont(originalFont);

        textY += 15;

        if (!cls.getFields().isEmpty()) {
            textY += padding;

            g2.setColor(new Color(0Xcceeff));
            g2.fillRect(x, textY - padding, width, lineHeight * cls.getFields().size() + padding * 2);
            g2.setColor(Color.BLACK);
            g2.drawRect(x, textY - padding, width, lineHeight * cls.getFields().size() + padding * 2);

            for (var field : cls.getFields()) {
                g2.drawString(field.getName() + ": " + field.getType(), x + padding, textY);
                textY += lineHeight;
            }
            textY += padding;
        }

        if (!cls.getMethods().isEmpty()) {
            g2.setColor(new Color(0Xcceeff));
            g2.fillRect(x, textY, width, lineHeight * cls.getMethods().size() + padding * 2);
            g2.setColor(Color.BLACK);
            g2.drawRect(x, textY, width, lineHeight * cls.getMethods().size() + padding * 2);

            for (var method : cls.getMethods()) {
                g2.drawString(method.getName() + "(): " + method.getReturnType(), x + padding, textY + lineHeight);
                textY += lineHeight;
            }
        }

        return new Rectangle(x, y, width, height);
    }

    private void drawRelationship(Graphics2D g2, RelationshipFormat relationship) {
        Rectangle fromRect = classRectangles.get(relationship.getFromClass());
        Rectangle toRect = classRectangles.get(relationship.getToClass());
        if (fromRect == null || toRect == null) return;

        Point fromPoint = getNearestPoint(fromRect, toRect);
        Point toPoint = getNearestPoint(toRect, fromRect);

        g2.drawLine(fromPoint.x, fromPoint.y, toPoint.x, toPoint.y);

        if (relationship.getRelationshipType().equals("Aggregation")) {
            RelationshipRender.drawDiamondHead(g2, fromPoint, toPoint);
        } else {
            RelationshipRender.drawArrowhead(g2, fromPoint, toPoint);
        }

        int midX = (fromPoint.x + toPoint.x) / 2;
        int midY = (fromPoint.y + toPoint.y) / 2;
        g2.drawString(relationship.getRelationshipType(), midX, midY);
    }

    private Point getNearestPoint(Rectangle fromRect, Rectangle toRect) {
        int x = fromRect.x + fromRect.width / 2;
        int y = fromRect.y + fromRect.height / 2;

        if (toRect.x > fromRect.x + fromRect.width) {
            x = fromRect.x + fromRect.width;
        } else if (toRect.x + toRect.width < fromRect.x) {
            x = fromRect.x;
        }

        if (toRect.y > fromRect.y + fromRect.height) {
            y = fromRect.y + fromRect.height;
        } else if (toRect.y + toRect.height < fromRect.y) {
            y = fromRect.y;
        }

        return new Point(x, y);
    }
}