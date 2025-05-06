package com.ytech.graphics.components;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.ytech.common.AppTheme;

public class YPanel extends JPanel {

    public YPanel() {
        init();
    }

    private void init() {
        YComponent.applyCommonStyle(this);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.setBackground(AppTheme.theme.surfaceColor);
    }

    public YPanel(CardLayout mainCardLayout) {
        super(mainCardLayout);
        init();
    }

}
