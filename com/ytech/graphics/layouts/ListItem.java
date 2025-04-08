package com.ytech.graphics.layouts;

import javax.swing.*;

import com.ytech.graphics.components.YComponent;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ListItem extends JPanel {
    private JLabel titleLabel;
    private JLabel subtitleLabel;
    private JPanel buttonsPanel;
    private List<JButton> buttons;
    private boolean odd;
    private boolean hover = false;
    private boolean pressed = false;

    private Color primaryColor = YComponent.primaryColor;
    private Color backgroundColor = new Color(200, 200, 200);
    private Color backgroundOddColor = new Color(230, 230, 230);
    private Color hoverColor = new Color(50, 50, 50);
    private Color pressedColor = new Color(160, 160, 160);

    public ListItem() {
        this("", "", false);

    }

    public ListItem(String title, String subtitle, boolean odd) {
        this.buttons = new ArrayList<>();
        this.odd = odd;
        initComponents(title, subtitle);
        layoutComponents();
        setupMouseListeners();
        updateAppearance();

        // setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    }

    private void initComponents(String title, String subtitle) {
        titleLabel = new JLabel(title);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 24f));
        titleLabel.setForeground(YComponent.primaryColor);
        titleLabel.setPreferredSize(new Dimension(getWidth() / 2, titleLabel.getHeight()));

        subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(subtitleLabel.getFont().deriveFont(Font.ITALIC, 14f));
        subtitleLabel.setForeground(Color.BLUE);

        buttonsPanel = new JPanel();
    }

    private void layoutComponents() {
        removeAll();
        setLayout(new BorderLayout(10, 10));

        JPanel textPanel = new JPanel();
        textPanel.setBackground(Color.DARK_GRAY);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        textPanel.add(titleLabel);
        textPanel.add(subtitleLabel);

        add(textPanel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 5, 5));
        rightPanel.setBackground(Color.DARK_GRAY);
        rightPanel.setOpaque(false);
        rightPanel.add(buttonsPanel);
        buttonsPanel.setBackground(Color.DARK_GRAY);
        add(rightPanel, BorderLayout.EAST);

        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        buttonsPanel.setOpaque(false);
    }

    private void setupMouseListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                pressed = false;
                updateAppearance();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                pressed = false;
                updateAppearance();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                pressed = true;
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                updateAppearance();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pressed = false;
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                updateAppearance();
            }
        });
    }

    private void updateAppearance() {
        Color bgColor;

        if (pressed) {
            bgColor = pressedColor;
        } else if (hover) {
            bgColor = hoverColor;
        } else if (odd) {
            bgColor = backgroundOddColor;
        } else {
            bgColor = backgroundColor;
        }

        setBackground(bgColor);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dégradé pour un effet plus moderne
        if (hover || pressed) {
            Graphics2D g2d = (Graphics2D) g;
            Color startColor = pressed ? pressedColor : hoverColor;
            Color endColor = new Color(startColor.getRed(), startColor.getGreen(), startColor.getBlue(), 150);

            GradientPaint gp = new GradientPaint(
                    0, 0, startColor,
                    getWidth(), getHeight(), endColor);

            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    @Override
    public Dimension getPreferredSize() {
        int height = Math.max(48, titleLabel.getPreferredSize().height + subtitleLabel.getPreferredSize().height + 20);
        height = 68;
        int width = super.getPreferredSize().width;
        return new Dimension(width, height);
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(super.getMaximumSize().width, getPreferredSize().height);
    }

    public void addButton(JButton button) {
        buttons.add(button);
        buttonsPanel.add(button);
        updateLayout();
    }

    public void removeButton(JButton button) {
        buttons.remove(button);
        buttonsPanel.remove(button);
        updateLayout();
    }

    public void setTitle(String title) {
        titleLabel.setText(title);
        updateLayout();
    }

    public void setSubtitle(String subtitle) {
        subtitleLabel.setText(subtitle);
        updateLayout();
    }

    private void updateLayout() {
        revalidate();
        repaint();
    }
}