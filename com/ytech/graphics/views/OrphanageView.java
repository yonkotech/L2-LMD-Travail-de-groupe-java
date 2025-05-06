package com.ytech.graphics.views;

import com.ytech.common.AppTheme;
import com.ytech.graphics.components.*;
import com.ytech.graphics.layouts.ListItem;
import com.ytech.main.App;
import com.ytech.models.Orphanage;

import javax.swing.*;
import java.awt.*;

public class OrphanageView extends YPanel {
    private JPanel listPanel;

    public OrphanageView() {

        // Configurer le layout principal
        setLayout(new BorderLayout());
        // setPadding(20);

        // Creer le titre
        JLabel titleLabel = new JLabel("Liste d'orphelinats");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 48f));
        titleLabel.setForeground(YComponent.primaryColor);
        add(titleLabel, BorderLayout.NORTH);

        // Panel pour la liste des orphelinats
        listPanel = new YPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0, YComponent.primaryColor));
        add(scrollPane, BorderLayout.CENTER);

        YPanel bottom = new YPanel();
        // Bouton d'ajout
        YButton addButton = new YButton("AJOUTER UN ORPHELINAT", 0); // Type 0 = vert
        addButton.addActionListener(e -> goToPage(new OrphanageSubscriptionView()));

        bottom.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        bottom.add(addButton);

        add(bottom, BorderLayout.SOUTH);

        // Charger la liste
        refreshOrphanageList();
    }

    private void refreshOrphanageList() {
        listPanel.removeAll();

        int i = 0;
        for (Orphanage orphanage : App.orphanages) {
            ListItem item = new ListItem(orphanage.getName(),
                    "Adresse: " + orphanage.getLocation() +
                            " | Capacite: " + orphanage.getCapacity(),
                    i % 2 == 0);
            i++;

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
        detailsLabel.setFont(new Font("Poppins", Font.PLAIN, 14));

        JOptionPane.showMessageDialog(
                this,
                detailsLabel,
                "Details de " + orphanage.getName(),
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