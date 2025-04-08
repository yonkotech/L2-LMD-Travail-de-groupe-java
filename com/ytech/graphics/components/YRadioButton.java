package com.ytech.graphics.components;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Icon;
import javax.swing.JRadioButton;

public class YRadioButton extends JRadioButton {
    public YRadioButton(String text) {
        super(text);
        YComponent.applyCommonStyle(this);
        setIcon(new RadioButtonIcon());
        setSelectedIcon(new RadioButtonSelectedIcon());
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
    }

    private static class RadioButtonIcon implements Icon {
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(YComponent.primaryColor);
            g2d.drawOval(x, y, 16, 16);
            g2d.dispose();
        }

        public int getIconWidth() {
            return 16;
        }

        public int getIconHeight() {
            return 16;
        }

    }

    private static class RadioButtonSelectedIcon extends RadioButtonIcon {
        public void paintIcon(Component c, Graphics g, int x, int y) {
            super.paintIcon(c, g, x, y);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(YComponent.primaryColor);
            g2d.drawOval(x + 2, y + 2, 12, 12);
            g2d.fillOval(x + 5, y + 5, 7, 7);
            g2d.dispose();
        }

    }

}
