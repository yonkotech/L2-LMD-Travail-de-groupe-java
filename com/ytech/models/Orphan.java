package com.ytech.models;

import java.util.Date;
import java.util.UUID;

public class Orphan {
    private int id;
    private int orphanage_id;
    private String firstName;
    private String lastName;
    private int age;
    private Date comingDate;
    private Date lefDate;
    private String motif;
    private boolean avaible = true;

    public static int lastId = 0;

    public boolean isAvaible() {
        return avaible;
    }

    public void setAvaible(boolean avaible) {
        this.avaible = avaible;
    }

    // Constructeur complet
    public Orphan(String firstName, String lastName, int age, Date comingDate, Date lefDate) {
        this.id = Orphan.lastId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.comingDate = comingDate;
        this.lefDate = lefDate;

        Orphan.lastId++;
    }

    public String getFormattedInfo() {
        return String.format(
                "<html><b>%s</b><br>"
                        + "Age: %s ans<br>",

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

    public Date getComingDate() {
        return comingDate;
    }

    public void setComingDate(Date comingDate) {
        this.comingDate = comingDate;
    }

    public Date getLefDate() {
        return lefDate;
    }

    public void setLefDate(Date lefDate) {
        this.lefDate = lefDate;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

}