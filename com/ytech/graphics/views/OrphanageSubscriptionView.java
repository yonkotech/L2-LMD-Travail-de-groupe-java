package com.ytech.graphics.views;

import com.ytech.graphics.components.*;
import com.ytech.main.App;
import com.ytech.models.Orphanage;
import javax.swing.*;
import java.awt.*;

public class OrphanageSubscriptionView extends YPanel {
    private Orphanage orphanage;
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
                capacityField.getText().isBlank());
    }

    private boolean allNumbersValid() {
        try {

            Integer.parseInt(capacityField.getText());

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
                if (orphanage != null) {
                    App.orphanages.set(orphanage.getId(),
                            new Orphanage(

                                    nameField.getText(),
                                    locationField.getText(),
                                    Integer.parseInt(capacityField.getText()),
                                    0,
                                    directorField.getText(),
                                    phoneNumberField.getText(),
                                    emailField.getText(),
                                    orphanage.getCreationDate()));
                } else {
                    App.addOrphanage(nameField.getText(), locationField.getText(),
                            Integer.parseInt(capacityField.getText()), 0,
                            directorField.getText(), phoneNumberField.getText(),
                            emailField.getText());
                }
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
    }

    public OrphanageSubscriptionView() {

        // Configurer le layout principal
        setLayout(new BorderLayout());
        // setPadding(20);

        // Creer le titre
        JLabel titleLabel = new JLabel("EDITER UN ORPHELINAT");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 48f));
        titleLabel.setForeground(YComponent.primaryColor);
        add(titleLabel, BorderLayout.NORTH);

        // Panel pour les contrôles de filtrage
        YPanel filterPanel = new YPanel();
        filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        filterPanel.add(new YCheckbox("Filtrer par capacite"));
        filterPanel.add(new YButton("Rechercher"));

        add(filterPanel, BorderLayout.CENTER);

        // Panel pour la liste des orphelinats
        listPanel = new YPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        implementFields(listPanel);
        listPanel.setPreferredSize(new Dimension(getWidth(), 400));
        JScrollPane scrollPane = new JScrollPane(listPanel);
        add(scrollPane, BorderLayout.CENTER);

        // // Bouton d'ajout
        YPanel bottom = new YPanel();

        YButton addButton = new YButton("ENREGISTRER", 0);
        addButton.addActionListener(e -> save());

        YButton cancelButton = new YButton("ANNULER", -1);
        cancelButton.addActionListener(e -> cancel());

        bottom.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        bottom.add(cancelButton, BorderLayout.WEST);
        bottom.add(addButton, BorderLayout.EAST);

        add(bottom, BorderLayout.SOUTH);

    }

    public OrphanageSubscriptionView(Orphanage orphanage) {
        this();
        this.orphanage = orphanage;

        nameField.setText(orphanage.getName());
        directorField.setText(orphanage.getDirector());
        emailField.setText(orphanage.getEmail());
        locationField.setText(orphanage.getLocation());
        phoneNumberField.setText(orphanage.getPhoneNumber());
        capacityField.setText("" + orphanage.getCapacity());

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