package com.ytech.graphics.views;

import com.ytech.graphics.components.*;
import com.ytech.graphics.layouts.ListItem;
import com.ytech.main.App;
import com.ytech.models.Orphanage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrphanageView extends YPanel {
    private List<Orphanage> orphanages = new ArrayList<>();
    private JPanel listPanel;

    public OrphanageView() {

        // Configurer le layout principal
        setLayout(new BorderLayout());
        // setPadding(20);

        // Créer le titre
        JLabel titleLabel = new JLabel("Gestion des Orphelinats");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 48f));
        titleLabel.setForeground(YComponent.primaryColor);
        add(titleLabel, BorderLayout.NORTH);

        // Panel pour les contrôles de filtrage
        YPanel filterPanel = new YPanel();
        filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        filterPanel.add(new YCheckbox("Filtrer par capacité"));
        filterPanel.add(new YButton("Rechercher"));

        add(filterPanel, BorderLayout.CENTER);

        // Panel pour la liste des orphelinats
        listPanel = new YPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(listPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Bouton d'ajout
        YButton addButton = new YButton("Ajouter un orphelinat", 0); // Type 0 = vert
        addButton.addActionListener(e -> goToPage(new OrphanageSubscriptionView()));
        add(addButton, BorderLayout.SOUTH);

        // Charger la liste
        refreshOrphanageList();
    }

    private void refreshOrphanageList() {
        listPanel.removeAll();

        for (Orphanage orphanage : App.orphanages) {
            ListItem item = new ListItem(orphanage.getName(),
                    "Lieu: " + orphanage.getLocation() +
                            " | Capacité: " + orphanage.getCapacity(),
                    orphanages.indexOf(orphanage) % 2 == 0);

            // Boutons d'action
            YButton detailsButton = new YButton("Details");
            detailsButton.addActionListener(e -> showDetails(orphanage));
            YButton orphains = new YButton("Orphelins", 2);
            orphains.addActionListener(e -> goToPage(orphanage));

            YButton editButton = new YButton("Modifier", 1);
            editButton.addActionListener(e -> goToPage(new OrphanageSubscriptionView(orphanage)));

            YButton deleteButton = new YButton("Supprimer", -1); // Type -1 = rouge
            deleteButton.addActionListener(e -> deleteOrphanage(orphanage));

            item.addButton(detailsButton);
            item.addButton(orphains);
            item.addButton(editButton);
            item.addButton(deleteButton);

            listPanel.add(item);
            listPanel.add(Box.createVerticalStrut(10));
        }

        listPanel.revalidate();
        listPanel.repaint();
    }

    private void goToPage(Orphanage orphanage) {
        App.lastOrphanage = orphanage;
        App.window.panel.removeAll();
        App.window.panel.add(new OrphanageDetailView(orphanage));
        App.window.revalidate();
        App.window.repaint();
    }

    private void goToPage(YPanel target) {
        App.window.panel.removeAll();
        App.window.panel.add(target);
        App.window.revalidate();
        App.window.repaint();
    }

    private void showDetails(Orphanage orphanage) {
        JLabel detailsLabel = new JLabel(orphanage.getFormattedInfo());
        detailsLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JOptionPane.showMessageDialog(
                this,
                detailsLabel,
                "Détails de " + orphanage.getName(),
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteOrphanage(Orphanage orphanage) {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Voulez-vous vraiment supprimer " + orphanage.getName() + "?",
                "Confirmation", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            App.orphanages.remove(orphanage);
            refreshOrphanageList();
        }
    }

}