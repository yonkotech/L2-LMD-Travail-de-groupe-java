package com.ytech.graphics.views;

import com.ytech.graphics.components.*;
import com.ytech.main.App;
import com.ytech.models.Orphanage;
import com.ytech.models.Orphan;

import javax.swing.*;
import java.awt.*;

public class OrphanSubscriptionView extends YPanel {
    private Orphanage orphanage;
    private Orphan orphan;
    private JPanel listPanel;

    private YTextField firstNameField = new YTextField("Nom de l'orphelin");
    private YTextField lastNameField = new YTextField("Post de l'orphelin");
    private YTextField ageField = new YTextField("Age de l'orphelin");
    private YRadioButton sexeM = new YRadioButton("Homme");
    private YRadioButton sexeF = new YRadioButton("Femme");

    private boolean allFieldsFilled() {
        return !(firstNameField.getText().isBlank() ||
                lastNameField.getText().isBlank() ||
                ageField.getText().isBlank() || !(sexeF.isSelected() || sexeM.isSelected()));
    }

    private boolean allNumbersValid() {
        try {

            Integer.parseInt(ageField.getText());

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
                if (orphanage.getCurrentChildren() >= orphanage.getCapacity()) {
                    showDialog("Orphelinat complet",
                            "L'orphelinat " + orphanage.getName() + " a atteint le nombre maximal d'enfants");

                } else {
                    if (this.orphan != null) {
                        App.orphans.set(App.orphans.indexOf(this.orphan), new Orphan(
                                firstNameField.getText(), lastNameField.getText(),
                                Integer.parseInt(ageField.getText()),
                                this.orphan.getComingDate(),
                                orphanage.getId(), sexeM.isSelected()));

                    } else {

                        App.addOrphan(firstNameField.getText(), lastNameField.getText(),
                                Integer.parseInt(ageField.getText()),
                                orphanage.getId(), sexeM.isSelected());

                    }

                }

                goToPage(new OrphanageDetailView(orphanage));
            }
        }

    }

    private void implementFields(JPanel listPanel) {
        listPanel.add(firstNameField);
        listPanel.add(lastNameField);
        listPanel.add(ageField);

        YPanel sexe = new YPanel();

        ButtonGroup bg = new ButtonGroup();
        bg.add(sexeM);
        bg.add(sexeF);
        sexe.add(sexeM, BorderLayout.WEST);
        sexe.add(sexeF, BorderLayout.EAST);

        listPanel.add(sexe);
        listPanel.add(new YPanel());
    }

    public OrphanSubscriptionView(Orphanage orphanage) {

        this.orphanage = orphanage;

        // Configurer le layout principal
        setLayout(new BorderLayout());
        // setPadding(20);

        // Créer le titre
        JLabel titleLabel = new JLabel("AJOUTER UN ORPHELIN");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 48f));
        titleLabel.setForeground(YComponent.primaryColor);
        add(titleLabel, BorderLayout.NORTH);

        // Panel pour les champs d'orphelins
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

    public OrphanSubscriptionView(Orphanage orphanage, Orphan orphan) {

        this(orphanage);
        this.orphan = orphan;

        this.firstNameField.setText(orphan.getFirstName());
        this.lastNameField.setText(orphan.getLastName());
        this.ageField.setText("" + orphan.getAge());

        sexeM.setSelected(orphan.isSexe());
        sexeF.setSelected(!orphan.isSexe());

    }

    private void cancel() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Voulez-vous vraiment annuler ?",
                "Confirmation", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            goToPage(new OrphanageDetailView(orphanage));
        }
    }

    private void goToPage(YPanel target) {
        App.window.panel.removeAll();
        App.window.panel.add(target);
        App.window.revalidate();
        App.window.repaint();
    }

}