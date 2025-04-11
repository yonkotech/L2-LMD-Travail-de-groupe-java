package com.ytech.graphics.views;

import com.ytech.graphics.components.*;
import com.ytech.graphics.layouts.ListItem;
import com.ytech.main.App;
import com.ytech.models.Orphanage;
import com.ytech.models.Orphan;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class OrphanageDetailView extends YPanel {
    private JPanel listPanel;
    private Orphanage orphanage;

    public OrphanageDetailView(Orphanage orphanage) {

        this.orphanage = orphanage;

        // Configurer le layout principal
        setLayout(new BorderLayout());
        // setPadding(20);

        // Creer le titre
        JLabel titleLabel = new JLabel(orphanage.getName());
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
        YButton addButton = new YButton("AJOUTER UN ORPHELIN", 0); // Type 0 = vert
        addButton.addActionListener(e -> addOrphan());
        YButton backButton = new YButton("RETOUR", 1);
        backButton.addActionListener(e -> goToPage(new OrphanageView()));

        bottom.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        bottom.add(backButton, BorderLayout.WEST);
        bottom.add(addButton, BorderLayout.EAST);
        add(bottom, BorderLayout.SOUTH);

        // Charger la liste
        refreshOrphanageList();
    }

    private void addOrphan() {
        if (this.orphanage.getCurrentChildren() >= this.orphanage.getCapacity()) {
            JLabel detailsLabel = new JLabel("L'orhpelinat est sature");
            detailsLabel.setFont(new Font("Arial", Font.PLAIN, 14));

            JOptionPane.showMessageDialog(
                    this,
                    detailsLabel,
                    "Saturation",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            goToPage(new OrphanSubscriptionView(this.orphanage));
        }
    }

    private void refreshOrphanageList() {
        listPanel.removeAll();

        int i = 0;
        for (Orphan orphan : App.orphans) {
            if (orphan.getOrphanage_id() == orphanage.getId()) {
                ListItem item = new ListItem(orphan.fullName(),
                        "Age: " + orphan.getAge() +
                                (orphan.isAvaible() ? " | Disponible" : " | " + orphan.getMotif()),
                        i % 2 == 0);
                i++;

                // Boutons d'action
                YButton detailsButton = new YButton("Details");
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
                item.addButton(editButton);
                if (orphan.isAvaible()) {
                    item.addButton(emanciperButton);
                    item.addButton(adopButton);
                }
                item.addButton(deleteButton);

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
                "Details de " + orphan.fullName(),
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