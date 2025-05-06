package com.ytech.graphics.layouts;

import javax.swing.*;

import com.ytech.common.AppTheme;
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

    private Color backgroundColor = AppTheme.theme.onSurfaceColor;
    private Color backgroundOddColor = AppTheme.theme.onCardColor;

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getBackgroundOddColor() {
        return backgroundOddColor;
    }

    public void setBackgroundOddColor(Color backgroundOddColor) {
        this.backgroundOddColor = backgroundOddColor;
    }

    private Color hoverColor = AppTheme.theme.tertiaryColor;

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
        titleLabel.getInsets(new Insets(10, 100, 10, 10));

        subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(subtitleLabel.getFont().deriveFont(Font.BOLD, 14f));
        subtitleLabel.setForeground(YComponent.primaryColor);

        buttonsPanel = new JPanel();
    }

    private Color getColor() {
        if (odd) {
            return new Color(50, 50, 50);
        }
        return new Color(100, 100, 100);
    }

    private void layoutComponents() {
        removeAll();
        setLayout(new BorderLayout(10, 10));

        JPanel textPanel = new JPanel();
        textPanel.setBackground(getColor());
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setPreferredSize(new Dimension(getWidth() / 2, getHeight()));
        textPanel.setOpaque(false);
        textPanel.add(titleLabel);
        textPanel.add(subtitleLabel);
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));

        add(textPanel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 5, 5));
        rightPanel.setBackground(getColor());
        rightPanel.setOpaque(false);
        rightPanel.add(buttonsPanel);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        buttonsPanel.setBackground(getColor());
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
                updateAppearance();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pressed = false;
                updateAppearance();
            }
        });
    }

    private void updateAppearance() {

        if (pressed) {
        } else if (hover) {
            setBorder(BorderFactory.createMatteBorder(0, 4, 0, 0, YComponent.primaryColor));
        } else {
            if (odd) {
                setBorder(BorderFactory.createMatteBorder(0, 4, 0, 0, backgroundColor));
            } else {
                setBorder(BorderFactory.createMatteBorder(0, 4, 0, 0, backgroundOddColor));

            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Degrade pour un effet plus moderne
        if (hover) {
            Graphics2D g2d = (Graphics2D) g;
            Color startColor = hoverColor;
            Color endColor = new Color(startColor.getRed(), startColor.getGreen(), startColor.getBlue(), 200);

            GradientPaint gp = new GradientPaint(
                    0, 0, startColor,
                    getWidth(), getHeight(), endColor);

            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        } else if (odd) {
            Graphics2D g2d = (Graphics2D) g;
            Color startColor = backgroundOddColor;

            GradientPaint gp = new GradientPaint(
                    0, 0, startColor,
                    getWidth(), getHeight(), startColor);

            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        } else

        {
            Graphics2D g2d = (Graphics2D) g;
            Color startColor = backgroundColor;

            GradientPaint gp = new GradientPaint(
                    0, 0, startColor,
                    getWidth(), getHeight(), startColor);

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