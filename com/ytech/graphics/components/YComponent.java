package com.ytech.graphics.components;

import java.awt.*;
import javax.swing.JComponent;

import com.ytech.common.AppTheme;

public class YComponent {
    public static final Color primaryColor = AppTheme.theme.primaryColor;
    public static final Color backgroundColor = AppTheme.theme.surfaceColor;

    public static void applyCommonStyle(JComponent component) {
        component.setFont(new Font("Segeo UI", Font.BOLD, 16));
        component.setBackground(backgroundColor);
        component.setForeground(primaryColor);
    }

}
