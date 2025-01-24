package org.mql.java.ui;

import java.awt.*;

public class RelationshipRender {
    public static void drawArrowhead(Graphics2D g2, Point from, Point to) {
        double angle = Math.atan2(to.y - from.y, to.x - from.x);
        int arrowSize = 10;

        int x1 = (int) (to.x - arrowSize * Math.cos(angle - Math.PI / 6));
        int y1 = (int) (to.y - arrowSize * Math.sin(angle - Math.PI / 6));
        int x2 = (int) (to.x - arrowSize * Math.cos(angle + Math.PI / 6));
        int y2 = (int) (to.y - arrowSize * Math.sin(angle + Math.PI / 6));

        g2.drawLine(to.x, to.y, x1, y1);
        g2.drawLine(to.x, to.y, x2, y2);
    }

    public static void drawDiamondHead(Graphics2D g2, Point from, Point to) {
        double angle = Math.atan2(to.y - from.y, to.x - from.x);
        int diamondSize = 10; 
        
        int x1 = (int) (to.x - diamondSize * Math.cos(angle + Math.PI / 2)); 
        int y1 = (int) (to.y - diamondSize * Math.sin(angle + Math.PI / 2));
        int x2 = (int) (to.x - diamondSize * Math.cos(angle)); 
        int y2 = (int) (to.y - diamondSize * Math.sin(angle));
        int x3 = (int) (to.x - diamondSize * Math.cos(angle - Math.PI / 2));
        int y3 = (int) (to.y - diamondSize * Math.sin(angle - Math.PI / 2));
        int x4 = (int) (to.x - diamondSize * Math.cos(angle + Math.PI));
        int y4 = (int) (to.y - diamondSize * Math.sin(angle + Math.PI));

        int[] xPoints = {x1, x2, x3, x4};
        int[] yPoints = {y1, y2, y3, y4};
        g2.drawPolygon(xPoints, yPoints, 4); 
    }
}