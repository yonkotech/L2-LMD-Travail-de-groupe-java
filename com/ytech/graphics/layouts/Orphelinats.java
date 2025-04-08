package com.ytech.graphics.layouts;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.ytech.graphics.Window;
import com.ytech.graphics.components.YButton;

public class Orphelinats extends JPanel {

    private List<String> orphelinats;
    private List<String> responsibles;
    private GridBagConstraints gbc = new GridBagConstraints();

    public Orphelinats() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        orphelinats = new ArrayList<>();
        responsibles = new ArrayList<>();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        setBackground(Color.DARK_GRAY);

    }

    public String getName(int index) {
        return orphelinats.get(index);
    }

    public void addOp(String name, String resposible, String location) {
        orphelinats.add(name);
        responsibles.add(resposible);
        this.removeAll();

        for (int i = 0; i < orphelinats.size(); i++) {
            boolean isOdd = i % 2 == 0;
            ListItem item = new ListItem(orphelinats.get(i), responsibles.get(i), isOdd);
            if (Window.isAdmin) {
                item.addButton(new YButton("MODIFIER", 0));
            }
            item.addButton(new YButton("DETAILS"));
            if (Window.isAdmin) {
                item.addButton(new YButton("SUPPRIMER", -1));
            }
            add(item);
        }
        repaint();
        System.out.println(orphelinats.get(0));
    }

    public void removeOp(int id) {
        orphelinats.remove(id);
    }

}
