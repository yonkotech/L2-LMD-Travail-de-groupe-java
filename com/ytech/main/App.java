package com.ytech.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.ytech.graphics.Window;
import com.ytech.models.Orphan;
import com.ytech.models.Orphanage;

public class App {
    public static Window window;
    public static Orphanage lastOrphanage;
    public static List<Orphanage> orphanages = new ArrayList<>();
    public static List<Orphan> orphans = new ArrayList<>();

    public static void main(String[] args) {

        initializeOrphanages();
        initialiseOrphans();
        window = new Window(800, 680);
    }

    public static void addOrphanage(String name, String location, int capacity, int currentChildren, String director,
            String phoneNumber, String email) {
        orphanages.add(
                new Orphanage(name, location, capacity, currentChildren, director, director, email, LocalDate.now()));
    }

    public static void initializeOrphanages() {
        orphanages.add(new Orphanage(

                "Orphelinat Saint-Louis",
                "Paris",
                120,
                115,
                "Marie Dupont",
                "01 23 45 67 89",
                "contact@saint-louis.org",
                LocalDate.now()));

        orphanages.add(new Orphanage(

                "Maison de l'Espoir",
                "Lyon",
                80,
                72,
                "Jean Martin",
                "04 56 78 90 12",
                "info@maison-espoir.org",
                LocalDate.now()));
    }

    public static String formatDate(LocalDate date) {
        return new String(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    public static void addOrphan(String firstName, String lastName, int age, int orphanage_id) {
        orphans.add(new Orphan(

                firstName,
                lastName,
                age,
                LocalDate.now(),
                orphanage_id));
    }

    public static void initialiseOrphans() {
        orphans.add(new Orphan(

                "Jean",
                "Luc",
                12,
                LocalDate.now(),
                0));
        orphans.add(new Orphan(

                "Emmanuel",
                "Kant",
                12,
                LocalDate.now(),
                0));

    }
}