package com.ytech.graphics.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;

public class YComponent {
    public static final Color primaryColor = new Color(30, 144, 255);
    public static final Color backgroundColor = new Color(241, 241, 241);

    public static void applyCommonStyle(JComponent component) {
        component.setFont(new Font("Segeo UI", Font.BOLD, 16));
        component.setBackground(backgroundColor);
        component.setForeground(primaryColor);
    }

}
