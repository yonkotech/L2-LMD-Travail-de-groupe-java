package com.ytech.graphics;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.ytech.graphics.components.YButton;
import com.ytech.graphics.components.YCheckbox;
import com.ytech.graphics.components.YComboBox;
import com.ytech.graphics.components.YComponent;
import com.ytech.graphics.components.YPanel;
import com.ytech.graphics.components.YRadioButton;
import com.ytech.graphics.components.YTextField;
import com.ytech.graphics.layouts.ListItem;
import com.ytech.graphics.views.OrphanageDetailView;
import com.ytech.graphics.views.OrphanageView;
import com.ytech.main.App;

public class Window extends JFrame {

    public static boolean isAdmin = false;

    public static List<Component> pages;
    public YPanel panel = new YPanel();

    public Window(
            int w,
            int h) {

        pages = new ArrayList<>();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        panel.add(new OrphanageView());

        this.setSize(w, h);

        this.setContentPane(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
