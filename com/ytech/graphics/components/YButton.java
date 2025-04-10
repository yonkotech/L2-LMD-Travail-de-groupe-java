package com.ytech.graphics.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class YButton extends JButton {

    private Color primaryColor = new Color(30, 144, 255);
    private Color backgroundColor = new Color(241, 241, 241);

    public YButton(String text, int type) {
        super(text);
        if (type == -1) {
            primaryColor = new Color(150, 0, 0);

        } else if (type == 0) {
            primaryColor = new Color(0, 150, 0);
        } else if (type == 1) {
            primaryColor = new Color(0, 0, 150);

        } else if (type == 2) {
            primaryColor = new Color(50, 100, 50);

        }
        init();
    }

    public YButton(String text) {
        super(text);
        init();
    }

    public void init() {

        // Appliquer les styles de base
        setFont(getFont().deriveFont(Font.BOLD).deriveFont(16f));
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(primaryColor, 2),
                BorderFactory.createEmptyBorder(3, 8, 3, 8)));
        setBackground(backgroundColor);
        setForeground(primaryColor);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Effet d'ombre
        setBorder(BorderFactory.createCompoundBorder(getBorder(), BorderFactory.createEmptyBorder(2, 2, 4, 2)));

        // Gestion des etats
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(primaryColor);
                setForeground(backgroundColor);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(backgroundColor);
                setForeground(primaryColor);
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

        });

    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        super.paintComponent(g2d);
        g2d.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.height = Math.max(34, size.height);
        return size;
    }

    // Methodes pour definier les couleurs personnalises
    public void setPrimaryColor(Color color) {
        this.primaryColor = color;
        repaint();
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        setBackground(color);
        repaint();
    }
}
