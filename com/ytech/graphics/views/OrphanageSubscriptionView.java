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

public class OrphanageSubscriptionView extends YPanel {
    private List<Orphan> orphans = new ArrayList<>();
    private JPanel listPanel;

    private YTextField nameField = new YTextField("Nom de l'orphelinat");
    private YTextField directorField = new YTextField("Nom du responsable");
    private YTextField emailField = new YTextField("Email de l'orphelinat");
    private YTextField locationField = new YTextField("Adresse de l'orphelinat");
    private YTextField phoneNumberField = new YTextField("Numero mobile de l'orphelinat");
    private YTextField capacityField = new YTextField("Capacite maximale de l'orphelinat");
    private YTextField currentChildren = new YTextField("Nombre d'enfants de l'orphelinat");

    private boolean allFieldsFilled() {
        return !(nameField.getText().isBlank() ||
                directorField.getText().isBlank() ||
                emailField.getText().isBlank() ||
                locationField.getText().isBlank() ||
                phoneNumberField.getText().isBlank() ||
                capacityField.getText().isBlank() ||
                currentChildren.getText().isBlank());
    }

    private boolean allNumbersValid() {
        try {

            Integer.parseInt(phoneNumberField.getText());
            Integer.parseInt(capacityField.getText());
            Integer.parseInt(currentChildren.getText());

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void showDialog(String title, String message) {
        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JOptionPane.showMessageDialog(
                this,
                messageLabel,
                title,
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void save() {

        if (!allFieldsFilled()) {
            showDialog("Champs non remplis", "Veuillez remplir tous les champs");
        } else {
            if (!allNumbersValid()) {
                showDialog("Valeurs non valides", "Veuillez remplir les champs avec des valeurs convenables");
            } else {
                App.addOrphanage(nameField.getText(), locationField.getText(),
                        Integer.parseInt(capacityField.getText()), Integer.parseInt(currentChildren.getText()),
                        directorField.getText(), Integer.parseInt(phoneNumberField.getText()),
                        emailField.getText());
                goToPage(new OrphanageView());
            }
        }

    }

    private void implementFields(JPanel listPanel) {
        listPanel.add(nameField);
        listPanel.add(directorField);
        listPanel.add(emailField);
        listPanel.add(locationField);
        listPanel.add(phoneNumberField);
        listPanel.add(capacityField);
        listPanel.add(currentChildren);

    }

    public OrphanageSubscriptionView() {

        // Configurer le layout principal
        setLayout(new BorderLayout());
        // setPadding(20);

        // Créer le titre
        JLabel titleLabel = new JLabel("AJOUTER UN ORPHELINAT");
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
        implementFields(listPanel);
        JScrollPane scrollPane = new JScrollPane(listPanel);
        add(scrollPane, BorderLayout.CENTER);

        // // Bouton d'ajout
        YPanel bottom = new YPanel();

        YButton addButton = new YButton("ENREGISTRER", 0);
        addButton.addActionListener(e -> save());

        YButton cancelButton = new YButton("ANNULER", -1);
        cancelButton.addActionListener(e -> cancel());

        bottom.add(cancelButton, BorderLayout.WEST);
        bottom.add(addButton, BorderLayout.EAST);

        add(bottom, BorderLayout.SOUTH);

    }

    private void cancel() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Voulez-vous vraiment annuler ?",
                "Confirmation", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            goToPage(new OrphanageView());
        }
    }

    private void goToPage(YPanel target) {
        App.window.panel.removeAll();
        App.window.panel.add(target);
        App.window.revalidate();
        App.window.repaint();
    }

}