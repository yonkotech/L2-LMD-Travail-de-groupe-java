package com.ytech.graphics.components;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Icon;
import javax.swing.JCheckBox;

public class YCheckbox extends JCheckBox {

    public YCheckbox(String text) {
        super(text);
        YComponent.applyCommonStyle(this);
        setIcon(new CheckBoxIcon());
        setSelectedIcon(new CheckBoxSelectedIcon());
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
    }

    private static class CheckBoxIcon implements Icon {
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(YComponent.primaryColor);
            g2d.drawRect(x, y, 16, 16);
            g2d.dispose();
        }

        public int getIconWidth() {
            return 16;
        }

        public int getIconHeight() {
            return 16;
        }

    }

    private static class CheckBoxSelectedIcon extends CheckBoxIcon {
        public void paintIcon(Component c, Graphics g, int x, int y) {
            super.paintIcon(c, g, x, y);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(YComponent.primaryColor);
            g2d.fillRect(x + 4, y + 4, 9, 9);
            g2d.dispose();
        }

    }

}
