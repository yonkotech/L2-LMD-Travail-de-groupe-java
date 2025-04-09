package com.ytech.graphics.components;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class YPanel extends JPanel {

    public YPanel() {
        init();
    }

    private void init() {
        YComponent.applyCommonStyle(this);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.setBackground(new Color(30, 30, 30));
    }

    public YPanel(CardLayout mainCardLayout) {
        super(mainCardLayout);
        init();
    }

}
