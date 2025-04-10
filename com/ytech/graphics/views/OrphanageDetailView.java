package com.ytech.graphics.views;

import com.ytech.graphics.components.*;
import com.ytech.graphics.layouts.ListItem;
import com.ytech.main.App;
import com.ytech.models.Orphanage;
import com.ytech.models.Orphan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrphanageDetailView extends YPanel {
    private List<Orphan> orphans = new ArrayList<>();
    private JPanel listPanel;
    private Orphanage orphanage;

    public OrphanageDetailView(Orphanage orphanage) {

        this.orphanage = orphanage;

        // Configurer le layout principal
        setLayout(new BorderLayout());
        // setPadding(20);

        // Créer le titre
        JLabel titleLabel = new JLabel(orphanage.getName());
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

        YPanel bottom = new YPanel();
        // Bouton d'ajout
        YButton addButton = new YButton("AJOUTER UN ORPHELIN", 0); // Type 0 = vert
        addButton.addActionListener(e -> goToPage(new OrphanSubscriptionView(this.orphanage)));
        YButton backButton = new YButton("RETOUR", 1);
        backButton.addActionListener(e -> goToPage(new OrphanageView()));

        bottom.add(backButton, BorderLayout.WEST);
        bottom.add(addButton, BorderLayout.EAST);
        add(bottom, BorderLayout.SOUTH);

        // Charger la liste
        refreshOrphanageList();
    }

    private void refreshOrphanageList() {
        listPanel.removeAll();

        for (Orphan orphan : App.orphans) {
            if (orphan.getOrphanage_id() == orphanage.getId()) {
                ListItem item = new ListItem(orphan.fullName(),
                        "Age: " + orphan.getAge() +
                                (orphan.isAvaible() ? " | Disponible" : " | " + orphan.getMotif()),
                        orphans.indexOf(orphan) % 2 == 0);

                // Boutons d'action
                YButton detailsButton = new YButton("Détails");
                detailsButton.addActionListener(e -> showDetails(orphan));

                YButton editButton = new YButton("Modifier", 1);
                editButton.addActionListener(e -> goToPage(new OrphanSubscriptionView(orphanage, orphan)));
                YButton adopButton = new YButton("Adopte", 0);
                adopButton.addActionListener(e -> adopt(orphan));
                YButton emanciperButton = new YButton("Emaciper", 2);
                emanciperButton.addActionListener(e -> emanciper(orphan));

                YButton deleteButton = new YButton("Supprimer", -1); // Type -1 = rouge
                deleteButton.addActionListener(e -> deleteOrphanage(orphan));

                item.addButton(detailsButton);
                if (orphan.isAvaible()) {
                    item.addButton(editButton);
                    item.addButton(emanciperButton);
                    item.addButton(adopButton);
                    item.addButton(deleteButton);
                }

                listPanel.add(item);
                listPanel.add(Box.createVerticalStrut(10));
            }
        }

        listPanel.revalidate();
        listPanel.repaint();
    }

    private void emanciper(Orphan orphan) {
        orphan.setAvaible(false);
        orphan.setMotif("S'est emancipe");
        orphan.setLeftDate(LocalDate.now());
        refreshOrphanageList();
    }

    private void adopt(Orphan orphan) {
        orphan.setAvaible(false);
        orphan.setMotif("A ete adopte");
        orphan.setLeftDate(LocalDate.now());
        refreshOrphanageList();
    }

    private void showDetails(Orphan orphan) {
        JLabel detailsLabel = new JLabel(orphan.getFormattedInfo());
        detailsLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JOptionPane.showMessageDialog(
                this,
                detailsLabel,
                "Détails de " + orphan.fullName(),
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteOrphanage(Orphan orphan) {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Voulez-vous vraiment supprimer " + orphan.fullName() + "?",
                "Confirmation", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            App.orphans.remove(orphan);
            refreshOrphanageList();
        }
    }

    private void goToPage(YPanel target) {
        App.window.panel.removeAll();
        App.window.panel.add(target);
        App.window.revalidate();
        App.window.repaint();
    }

}