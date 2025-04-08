package com.ytech.graphics.views;

import com.ytech.graphics.components.*;
import com.ytech.graphics.layouts.ListItem;
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
        // Initialiser quelques données de test
        initializeSampleData();

        // Configurer le layout principal
        setLayout(new BorderLayout());
        // setPadding(20);

        // Créer le titre
        JLabel titleLabel = new JLabel("Gestion des Orphelinats");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 24f));
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
        YButton addButton = new YButton("+ Ajouter un orphelinat", 0); // Type 0 = vert
        addButton.addActionListener(this::addOrphanage);
        add(addButton, BorderLayout.SOUTH);

        // Charger la liste
        refreshOrphanageList();
    }

    private void initializeSampleData() {
        orphanages.add(new Orphanage(
                "OP-001",
                "Orphelinat Saint-Louis",
                "Paris",
                120,
                115,
                "Marie Dupont",
                "01 23 45 67 89",
                "contact@saint-louis.org",
                new Date(120, 0, 15) // 15 janvier 2020
        ));

        orphanages.add(new Orphanage(
                "OP-002",
                "Maison de l'Espoir",
                "Lyon",
                80,
                72,
                "Jean Martin",
                "04 56 78 90 12",
                "info@maison-espoir.org",
                new Date(118, 5, 10) // 10 juin 2018
        ));
    }

    private void refreshOrphanageList() {
        listPanel.removeAll();

        for (Orphanage orphanage : orphanages) {
            ListItem item = new ListItem(orphanage.getName(),
                    "Lieu: " + orphanage.getLocation() +
                            " | Capacité: " + orphanage.getCapacity(),
                    orphanages.indexOf(orphanage) % 2 == 0);

            // Boutons d'action
            YButton detailsButton = new YButton("Détails");
            detailsButton.addActionListener(e -> showDetails(orphanage));

            YButton editButton = new YButton("Modifier");
            editButton.addActionListener(e -> editOrphanage(orphanage));

            YButton deleteButton = new YButton("Supprimer", -1); // Type -1 = rouge
            deleteButton.addActionListener(e -> deleteOrphanage(orphanage));

            item.addButton(detailsButton);
            item.addButton(editButton);
            item.addButton(deleteButton);

            listPanel.add(item);
            listPanel.add(Box.createVerticalStrut(10));
        }

        listPanel.revalidate();
        listPanel.repaint();
    }

    private void addOrphanage(ActionEvent e) {
        // Ici vous implémenteriez la logique d'ajout
        JOptionPane.showMessageDialog(this, "Fonctionnalité d'ajout à implémenter");
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

    private void editOrphanage(Orphanage orphanage) {
        // Logique de modification
        JOptionPane.showMessageDialog(this, "Modification de " + orphanage.getName());
    }

    private void deleteOrphanage(Orphanage orphanage) {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Voulez-vous vraiment supprimer " + orphanage.getName() + "?",
                "Confirmation", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            orphanages.remove(orphanage);
            refreshOrphanageList();
        }
    }

    private void showAddOrphanageDialog() {
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 5));

        JTextField nameField = new JTextField();
        JTextField locationField = new JTextField();
        JSpinner capacitySpinner = new JSpinner(new SpinnerNumberModel(50, 10, 500, 1));

        formPanel.add(new JLabel("Nom:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Lieu:"));
        formPanel.add(locationField);
        formPanel.add(new JLabel("Capacité:"));
        formPanel.add(capacitySpinner);

        int result = JOptionPane.showConfirmDialog(
                this,
                formPanel,
                "Ajouter un nouvel orphelinat",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            Orphanage newOrphanage = new Orphanage(
                    nameField.getText(),
                    locationField.getText(),
                    (Integer) capacitySpinner.getValue());
            orphanages.add(newOrphanage);
            refreshOrphanageList();
        }
    }

}