package com.ytech.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
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
import com.ytech.graphics.layouts.ListItem;
// import com.ytech.graphics.layouts.Orphelinat;
import com.ytech.graphics.layouts.Orphelinats;
import com.ytech.graphics.views.OrphanageView;
import com.ytech.main.App;

public class Window extends JFrame {

    public static boolean isAdmin = false;

    YPanel panel = new YPanel();
    public static List<Component> pages;

    public Window(int w, int h) {

        pages = new ArrayList<>();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        orphelinatsPage();

        panel.add(new OrphanageView());

        this.setSize(w, h);
        this.setContentPane((Container) pages.get(0));
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void orphelinatsPage() {
        YPanel mainPanel = new YPanel();
        mainPanel.setBackground(Color.DARK_GRAY);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.DARK_GRAY);
        JLabel title = new JLabel("Orphelinats");
        title.setForeground(YComponent.primaryColor);
        title.setFont(new Font("Monospace", Font.BOLD, 48));
        topPanel.add(title);
        topPanel.setSize(topPanel.getWidth(), 48);
        mainPanel.add(topPanel);

        // Carte
        Orphelinats orphelinats = new Orphelinats();
        orphelinats.addOp("Tu ungane", "Moi", "Lemba");
        orphelinats.addOp("Besto lorem ipsum a sdljfs lskfjkdsaf lsdkfjsdfj", "Je suis fier", "Lemba");
        orphelinats.addOp("Enfin", "Toujours", "Lemba");
        orphelinats.addOp("Test", "Moi", "Lemba");
        orphelinats.addOp("Test", "Moi", "Lemba");
        orphelinats.addOp("Test", "Moi", "Lemba");

        JScrollPane scrollPane = new JScrollPane(orphelinats);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension((int) scrollPane.getPreferredSize().getWidth(), 700));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        mainPanel.add(scrollPane);

        if (Window.isAdmin) {
            JPanel addOp = new JPanel();
            addOp.setBackground(Color.DARK_GRAY);

            addOp.add(new YButton("AJOUTER"));
            addOp.setMaximumSize(new Dimension(100, 48));

            mainPanel.add(addOp);
        }

        pages.add(mainPanel);
    }

    private void orphelinatPage() {
        YPanel mainPanel = new YPanel();
        mainPanel.setBackground(Color.DARK_GRAY);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.DARK_GRAY);
        JLabel title = new JLabel("Orphelinats");
        title.setForeground(YComponent.primaryColor);
        title.setFont(new Font("Monospace", Font.BOLD, 48));
        topPanel.add(title);
        topPanel.setSize(topPanel.getWidth(), 48);
        mainPanel.add(topPanel);

        // Carte

        App.orphelinats.addOp("Tu ungane", "Moi", "Lemba");
        App.orphelinats.addOp("Besto lorem ipsum a sdljfs lskfjkdsaf lsdkfjsdfj", "Je suis fier", "Lemba");
        App.orphelinats.addOp("Enfin", "Toujours", "Lemba");
        App.orphelinats.addOp("Test", "Moi", "Lemba");
        App.orphelinats.addOp("Test", "Moi", "Lemba");
        App.orphelinats.addOp("Test", "Moi", "Lemba");

        JScrollPane scrollPane = new JScrollPane(App.orphelinats);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension((int) scrollPane.getPreferredSize().getWidth(), 700));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        mainPanel.add(scrollPane);

        if (Window.isAdmin) {
            JPanel addOp = new JPanel();
            addOp.setBackground(Color.DARK_GRAY);

            addOp.add(new YButton("AJOUTER"));
            addOp.setMaximumSize(new Dimension(100, 48));

            mainPanel.add(addOp);
        }

        pages.add(mainPanel);
    }
}
