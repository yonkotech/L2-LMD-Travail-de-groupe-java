package com.ytech.main;

import java.util.ArrayList;
import java.util.Date;
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
            int phoneNumber, String email) {
        orphanages.add(new Orphanage(name, location, capacity, currentChildren, director, director, email, new Date()));
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
                new Date(120, 0, 15) // 15 janvier 2020
        ));

        orphanages.add(new Orphanage(

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

    public static void initialiseOrphans() {
        orphans.add(new Orphan(

                "Jean",
                "Luc",
                12,
                new Date(2014, 1, 17),
                new Date(2021, 0, 15)));
        orphans.add(new Orphan(

                "Emmanuel",
                "Kant",
                12,
                new Date(2014, 1, 17),
                new Date(2021, 0, 15)));

    }
}