package com.ytech.models;

import java.time.LocalDate;
import com.ytech.main.App;

public class Orphan {
    private int id;
    private int orphanage_id;
    private String firstName;
    private String lastName;
    private int age;
    private LocalDate comingDate;
    private LocalDate leftDate;
    private String motif;
    private boolean avaible = true;
    private boolean sexe = true;

    public boolean isSexe() {
        return sexe;
    }

    public void setSexe(boolean sexe) {
        this.sexe = sexe;
    }

    public static int lastId = 0;

    public boolean isAvaible() {
        return avaible;
    }

    public void setAvaible(boolean avaible) {
        this.avaible = avaible;
    }

    // Constructeur complet
    public Orphan(String firstName, String lastName, int age, LocalDate comingDate, int orphanage_id, boolean sexe) {
        this.id = Orphan.lastId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.comingDate = comingDate;
        this.orphanage_id = orphanage_id;
        this.sexe = sexe;
        App.orphanages.get(orphanage_id).setCurrentChildren(App.orphanages.get(orphanage_id).getCurrentChildren() + 1);
        Orphan.lastId++;
    }

    public String getFormattedInfo() {
        return String.format(
                "<html><b>%s</b><br>" + (sexe ? "Homme" : "Femme") + "<br>" +
                        "Date d'arrive : " + App.formatDate(comingDate) + "<br>"
                        + "Age: %s ans<br>"
                        + (isAvaible() ? "Peut etre adopte" : getMotif() + ": le " + App.formatDate(leftDate)),

                fullName(), age);
    }

    public String fullName() {
        return firstName + " " + lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrphanage_id() {
        return orphanage_id;
    }

    public void setOrphanage_id(int orphanage_id) {
        this.orphanage_id = orphanage_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getComingDate() {
        return comingDate;
    }

    public void setComingDate(LocalDate comingDate) {
        this.comingDate = comingDate;
    }

    public LocalDate getLeftDate() {
        return leftDate;
    }

    public void setLeftDate(LocalDate leftDate) {
        this.leftDate = leftDate;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

}