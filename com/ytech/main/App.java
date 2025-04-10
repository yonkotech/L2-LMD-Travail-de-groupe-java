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
        addOrphanage("La Maison des Anges", "Muzu 18/15, Limete, Kinshasa", 14, 0,
                "Madame C’est Prévue Lusila Matendele", "+243822171689", "info@lamaison-desanges.org");
        addOrphanage("Orphelinat Dieu Mon Refuge",
                "Avenue Bosango numéro 2, Quartier Siforco, Commune de Masina, Kinshasa", 10, 0, "Non spécifié",
                "+243899836610", "info@orphelinatdieumonrefuge.org");
        addOrphanage("Orphelinat Nid d’Espoir", "3, Avenue Kivambi, Quartier Mikala 1, Commune de la N’sele, Kinshasa",
                10, 0, "Riziki Kivambi", "+243856141443", "info@reseaufce.org");
        addOrphanage("Orphelinat Jésus est Mon Papa", "Avenue Marais n°16, Quartier Kauka, Commune de Kalamu, Kinshasa",
                10, 0, "Non spécifié", "+243829275825", "orphelinatjemp@gmail.com");
        addOrphanage("Orphelinat Mama Koko", "Kimbondo, Kinshasa", 800, 0, "Padre Hugo Rios Diaz", "Non spécifié",
                "Non spécifié");
        addOrphanage("Orphelinat Jésus le Bon Berger",
                "Avenue Ndombe n°27, Quartier Mitendi, Commune de Mont Ngafula, Kinshasa", 10, 0, "Dieu Merci Sombo",
                "+243891658885", "Non spécifié");
        addOrphanage("Shegeland ONG", "Kinshasa", 10, 10, "Léopold Tanganagba", "Non spécifié", "Non spécifié");
        addOrphanage("Tukulowo Tuhenda", "Avenue Kapombo, n° 3, Quartier Manenga, Commune de Ngaliema, Kinshasa", 25,
                0, "Mme Cécile Ntuite", "+243894456443", "Non spécifié");
        addOrphanage("La Voix de Dieu", "Avenue Kembedila, n° 36, Quartier Mpunda, Commune de Ngaliema, Kinshasa", 15,
                0, "Mme Patience Malata", "+243892311231", "Non spécifié");
        addOrphanage("Les Cris des Orphelins",
                "Avenue Matadi, n° 22, Quartier Djelo-Binza, Commune de Ngaliema, Kinshasa", 28, 0, "Madame Nene",
                "+243903422267", "Non spécifié");
        addOrphanage("CEMAPAR", "Avenue Boma, n° 72, Quartier Sanat, Commune de Selembao, Kinshasa", 92, 0,
                "M. José Ikoma", "+243898513087", "Non spécifié");
        addOrphanage("Les Damans",
                "Route Matadi non loin d’Envraque, Quartier Matadi Kibala, Commune de Mont Ngafula, Kinshasa", 38, 0,
                "Pasteur Muzee Kapepula", "+243896634873", "Non spécifié");
        addOrphanage("La Compassion", "Avenue Mitendi, n° 28, Quartier Mitendi, Commune de Mont Ngafula, Kinshasa", 32,
                0, "Madame Hélène Umba", "+243815135455", "Non spécifié");
        addOrphanage("Crèche Gan Eden",
                "Avenue Masikita, n° 14 bis, Quartier Ngombakikusa/UPNC, Commune de Ngaliema, Kinshasa", 50, 0,
                "Madame Arlelle", "+243821704996", "Non spécifié");
        addOrphanage("Notre Dame Denesse",
                "Avenue Essence, n° 21, Quartier Binza-Delvaux, Arrêt Bahumbu, Commune de Ngaliema, Kinshasa", 46, 0,
                "Sœur Mitoni", "+243816900539", "Non spécifié");
        addOrphanage("La Compassion Bumbu", "Avenue Zongo, Quartier Mbaki, Commune de Bumbu, Kinshasa", 54, 0,
                "Monsieur Franck Puba", "+243895315947", "Non spécifié");
        addOrphanage("SM Favor", "Avenue Kombe, n° 6, Quartier Mfinda, Commune de Ngaliema, Kinshasa", 50, 0,
                "Monsieur Peter", "+243892977835", "Non spécifié");
        addOrphanage("Œuvre Humanitaire Emmanuel",
                "Avenue Tshiakwiva, n°26, Quartier Masangambila, Commune de Mont Ngafula, Kinshasa", 30, 0,
                "Non spécifié", "+243815028448", "Non spécifié");
        addOrphanage("Congo Espoir", "Avenue Mbanza-boma, n°58, Quartier Manenga, Commune de Ngaliema, Kinshasa", 9, 0,
                "Madame Nasi", "+243999908404", "Non spécifié");

    }

    public static String formatDate(LocalDate date) {
        return new String(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    public static void addOrphan(String firstName, String lastName, int age, int orphanage_id, boolean sexe) {
        orphans.add(new Orphan(

                firstName,
                lastName,
                age,
                LocalDate.now(),
                orphanage_id, sexe));
    }

    public static void initialiseOrphans() {
        // Orphelinat 1
        addOrphan("Jean", "Mulumba", 10, 0, true);
        addOrphan("Aline", "Kibwila", 8, 0, false);
        addOrphan("Pierre", "Kasongo", 9, 0, true);
        addOrphan("Claudine", "Mwamba", 7, 0, false);
        addOrphan("Blaise", "Ilunga", 6, 0, true);

        // Orphelinat 2
        addOrphan("Michel", "Kabasele", 11, 1, true);
        addOrphan("Lucie", "Ngoy", 8, 1, false);
        addOrphan("Sylvain", "Tshibangu", 9, 1, true);
        addOrphan("Josiane", "Mayele", 7, 1, false);
        addOrphan("Armand", "Kipala", 6, 1, true);

        // Orphelinat 3
        addOrphan("David", "Mbaya", 12, 2, true);
        addOrphan("Patricia", "Kibondo", 9, 2, false);
        addOrphan("Gérard", "Musaka", 10, 2, true);
        addOrphan("Francine", "Katembo", 8, 2, false);
        addOrphan("Eddy", "Makiese", 7, 2, true);

        // Orphelinat 4
        addOrphan("Roger", "Mutombo", 11, 3, true);
        addOrphan("Monique", "Nkongolo", 8, 3, false);
        addOrphan("Fabrice", "Kilembe", 9, 3, true);
        addOrphan("Alice", "Kalenga", 7, 3, false);
        addOrphan("Jean-Claude", "Kabongo", 6, 3, true);

        // Orphelinat 5
        addOrphan("Eric", "Kankonde", 10, 4, true);
        addOrphan("Rosalie", "Kabaseya", 8, 4, false);
        addOrphan("Albert", "Kakudji", 9, 4, true);
        addOrphan("Jeannette", "Mundele", 7, 4, false);
        addOrphan("Thierry", "Tshimanga", 6, 4, true);

    }
}