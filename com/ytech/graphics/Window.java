package com.ytech.graphics;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import com.ytech.graphics.components.YPanel;
import com.ytech.graphics.views.OrphanageView;

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
        this.setMinimumSize(new Dimension(w, h));
        this.setTitle("Orphelinats manager");

        this.setContentPane(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
