package com.ytech.models;

import java.time.LocalDate;

public class Orphanage {
    private int id;
    private String name;//
    private String location;//
    private int capacity;//
    private int currentChildren;//
    private String director;//
    private String phoneNumber;//
    private String email;//
    private LocalDate creationDate;
    public static int lastId = 0;

    // Constructeur complet
    public Orphanage(String name, String location, int capacity,
            int currentChildren, String director, String phoneNumber,
            String email, LocalDate creationDate) {
        this.id = lastId;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.currentChildren = currentChildren;
        this.director = director;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.creationDate = creationDate;

        Orphanage.lastId++;

    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentChildren() {
        return currentChildren;
    }

    public String getDirector() {
        return director;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setCurrentChildren(int currentChildren) {
        this.currentChildren = currentChildren;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Méthodes utiles
    public int getAvailableSpaces() {
        return capacity - currentChildren;
    }

    public boolean isFull() {
        return currentChildren >= capacity;
    }

    public String getFormattedInfo() {
        return String.format(
                "<html><b>%s</b><br>"
                        + "Lieu: %s<br>"
                        + "Capacité: %d/%d<br>"
                        + "Directeur: %s<br>"
                        + "Contact: %s | <br>"
                        + "Email: %s</html>",
                name, location, currentChildren, capacity,
                director, phoneNumber, email);
    }

    @Override
    public String toString() {
        return name + " (" + location + ")";
    }
}