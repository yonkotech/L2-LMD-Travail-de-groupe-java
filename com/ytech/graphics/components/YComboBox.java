package com.ytech.graphics.components;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

public class YComboBox<T> extends JComboBox<T> {

    public YComboBox() {
        YComponent.applyCommonStyle(this);
        setRenderer(new StyledComboBoxRenderer());
        setUI(new StyledComboBoxUI());
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(YComponent.primaryColor, 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
    }

    private static class StyledComboBoxUI extends BasicComboBoxUI {
        @Override
        protected JButton createArrowButton() {
            JButton button = new JButton();

            button.setIcon(new ArrowIcon());
            button.setContentAreaFilled(false);
            button.setBorder(BorderFactory.createEmptyBorder());
            return button;
        }

        @Override
        protected ComboPopup createPopup() {
            BasicComboPopup popup = (BasicComboPopup) super.createPopup();
            popup.setBorder(BorderFactory.createLineBorder(YComponent.primaryColor, 2));
            return popup;
        }

        public void layoutContainer(Container parent) {
            // super.layoutContainer(parent);

            if (arrowButton != null) {
                Insets insets = comboBox.getInsets();
                int width = comboBox.getWidth();
                int height = comboBox.getHeight();

                arrowButton.setBounds(insets.left, insets.top, 30, height - insets.top - insets.bottom);
                if (editor != null) {
                    editor.setBounds(insets.left + 30, insets.top, width - insets.left - insets.right - 30,
                            height - insets.top - insets.bottom);
                }
            }
        }

        private static class ArrowIcon implements Icon {
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(YComponent.primaryColor);
                g2d.fillPolygon(new int[] { x, x + 10, x + 5 }, new int[] { y, y, y + 6 }, 3);
                g2d.dispose();
            }

            @Override
            public int getIconWidth() {
                return 10;
            }

            @Override
            public int getIconHeight() {
                return 6;
            }
        }

        // @Override
        // public void setSelectedItem(Object anObject) {
        // super.setSelectedItem(anObject);
        // if (getRenderer() instanceof StyledComboBoxRenderer) {
        // StyledComboBoxRenderer renderer = (StyledComboBoxRenderer) getRenderer();
        // Component c = renderer.getListCellRendererComponent(null, anObject, -1,
        // false, false);
        // getEditor().getEditorComponent().setBackground(c.getBackground());
        // getEditor().getEditorComponent().setForeground(c.getForeground());
        // ((JComponent) getEditor().getEditorComponent()).setBorder(c.getBorder());

        // }
        // }
    }

    private static class StyledComboBoxRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            if (isSelected) {
                setBackground(YComponent.primaryColor);
                setForeground(YComponent.backgroundColor);
            } else {
                setBackground(YComponent.backgroundColor);
                setForeground(YComponent.primaryColor);
            }
            return this;
        }

    }

}
