package com.ytech.graphics.views;

import com.ytech.graphics.components.*;
import com.ytech.graphics.layouts.ListItem;
import com.ytech.main.App;
import com.ytech.models.Orphanage;
import com.ytech.models.Orphan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrphanSubscriptionView extends YPanel {
    private List<Orphan> orphans = new ArrayList<>();
    private JPanel listPanel;

    public OrphanSubscriptionView() {

        // Configurer le layout principal
        setLayout(new BorderLayout());
        // setPadding(20);

        // Créer le titre
        JLabel titleLabel = new JLabel("AJOUTER UN ORPHELIN");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 48f));
        titleLabel.setForeground(YComponent.primaryColor);
        add(titleLabel, BorderLayout.NORTH);

        // Panel pour la liste des orphelinats
        listPanel = new YPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.add(new YButton("BOUTON"));

        // // Bouton d'ajout
        YButton addButton = new YButton("ENREGISTRER", 0);
        addButton.addActionListener(e -> goToPage(new OrphanageDetailView(App.lastOrphanage)));
        add(addButton, BorderLayout.SOUTH);

    }

    private void goToPage(YPanel target) {
        App.window.panel.removeAll();
        App.window.panel.add(target);
        App.window.revalidate();
        App.window.repaint();
    }

}