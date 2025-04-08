package com.ytech.graphics.components;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class YPanel extends JPanel {

    public YPanel() {
        YComponent.applyCommonStyle(this);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.setBackground(new Color(30, 30, 30));
    }

}
