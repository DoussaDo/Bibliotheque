package projet;

import java.io.*;
import java.util.*;

public class MainAdherent {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // 1. Chargement du catalogue
            ObjectInputStream inCat = new ObjectInputStream(new FileInputStream("catalogue.dat"));
            Catalogue catalogue = (Catalogue) inCat.readObject();
            inCat.close();

            // 2. Chargement des adhérents
            ObjectInputStream inAdh = new ObjectInputStream(new FileInputStream("adherents.dat"));
            @SuppressWarnings("unchecked")
            ArrayList<Adherent> adherents = (ArrayList<Adherent>) inAdh.readObject();
            inAdh.close();

            // 3. Connexion d’un adhérent (ici on prend le premier)
            Adherent adherent = adherents.get(0); // exemple : Dounia
            System.out.println("Bienvenue " + adherent.getPrenom() + " " + adherent.getNom());

            boolean quitter = false;

            while (!quitter) {
                System.out.println("\n=== MENU ADHÉRENT ===");
                System.out.println("1. Rechercher par titre");
                System.out.println("2. Rechercher par auteur");
                System.out.println("3. Voir les exemplaires disponibles");
                System.out.println("4. Réserver un exemplaire");
                System.out.println("5. Quitter");
                System.out.print("Votre choix : ");
                int choix = Integer.parseInt(scanner.nextLine());

                switch (choix) {
                    case 1:
                        System.out.print("Mot-clé du titre : ");
                        String titre = scanner.nextLine();
                        adherent.rechercherParTitre(titre);
                        break;

                    case 2:
                        System.out.print("Mot-clé de l’auteur : ");
                        String auteur = scanner.nextLine();
                        adherent.rechercherParAuteur(auteur);
                        break;

                    case 3:
                        System.out.println("Exemplaires disponibles :");
                        for (Exemplaire ex : catalogue.getExemplaires()) {
                            if (ex.estDisponible()) {
                                System.out.println("- ID : " + ex.getIdExemplaire() + " | " +
                                        ex.getLivre().getTitre() + " par " + ex.getLivre().getAuteur());
                            }
                        }
                        break;

                    case 4:
                        System.out.print("ID de l’exemplaire à réserver : ");
                        int id = Integer.parseInt(scanner.nextLine());

                        Exemplaire exemplaireAReserver = null;
                        for (Exemplaire ex : catalogue.getExemplaires()) {
                            if (ex.getIdExemplaire() == id) {
                                exemplaireAReserver = ex;
                                break;
                            }
                        }

                        if (exemplaireAReserver != null) {
                            adherent.reserver(exemplaireAReserver);
                        } else {
                            System.out.println("Exemplaire introuvable.");
                        }
                        break;

                    case 5:
                        quitter = true;
                        System.out.println("Déconnexion.");
                        break;

                    default:
                        System.out.println("Choix invalide.");
                        break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors de l’exécution : " + e.getMessage());
        }

        scanner.close();
    }
}
