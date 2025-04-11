package com.ytech.graphics.components;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class YTextField extends JPanel {
    private JTextField textField;
    private JLabel floatingLabel;
    private Color borderColor;

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    private Color focusColor;
    private Color labelColor;

    public YTextField(String label) {
        this(label, Color.WHITE, YComponent.primaryColor, YComponent.primaryColor);
    }

    public YTextField(String label, Color borderColor, Color focusColor, Color labelColor) {
        this.borderColor = borderColor;
        this.focusColor = focusColor;
        this.labelColor = labelColor;

        setLayout(new OverlayLayout(this));
        setOpaque(false);
        setPreferredSize(new Dimension(200, 35)); // Hauteur reduite

        // Configuration du champ de texte
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(textField.getWidth(), 35));
        textField.setBorder(BorderFactory.createEmptyBorder(15, 2, 3, 2)); // Padding ajuste
        textField.setOpaque(false);
        textField.setFont(textField.getFont().deriveFont(Font.BOLD, 14f));
        textField.setForeground(YComponent.primaryColor);

        // Configuration du label flottant
        floatingLabel = new JLabel(label);
        floatingLabel.setForeground(labelColor);
        floatingLabel.setFont(textField.getFont().deriveFont(Font.PLAIN));
        floatingLabel.setAlignmentX(0f);
        floatingLabel.setAlignmentY(0f);

        // Panel de fond pour la bordure
        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int y = getHeight() - 3;
                g2.setColor(borderColor);
                g2.setStroke(new BasicStroke(1.2f));
                g2.drawLine(0, y, getWidth(), y);

                if (textField.hasFocus() || !textField.getText().isEmpty()) {
                    g2.setStroke(new BasicStroke(2f));
                    g2.drawLine(0, y, getWidth(), y);
                    g2.setColor(focusColor);
                    g2.drawLine(0, y, getWidth(), y);
                }
            }
        };
        backgroundPanel.setOpaque(false);
        backgroundPanel.add(textField, BorderLayout.SOUTH);

        add(backgroundPanel);
        add(floatingLabel);

        setupListeners();
    }

    private void setupListeners() {
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                animateLabel(true);
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                animateLabel(!textField.getText().isEmpty());
                repaint();
            }
        });

        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                checkText();
            }

            public void removeUpdate(DocumentEvent e) {
                checkText();
            }

            public void changedUpdate(DocumentEvent e) {
                checkText();
            }

            private void checkText() {
                animateLabel(textField.hasFocus() || !textField.getText().isEmpty());
            }
        });
    }

    private void animateLabel(boolean floatUp) {
        if (floatUp) {
            floatingLabel.setFont(floatingLabel.getFont().deriveFont(11f)); // Taille reduite
            floatingLabel.setForeground(focusColor);
            floatingLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0)); // Position ajustee
        } else {
            floatingLabel.setFont(textField.getFont());
            floatingLabel.setForeground(labelColor);
            floatingLabel.setBorder(BorderFactory.createEmptyBorder(8, 2, 0, 0));
        }
    }

    // Methodes d'accès
    public String getText() {
        return textField.getText();
    }

    public void setText(String text) {
        textField.setText(text);
        animateLabel(!text.isEmpty());
    }

}