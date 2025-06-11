package projet;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe principale permettant à un bibliothécaire de gérer les livres du catalogue :
 * affichage, ajout, modification, suppression, et sauvegarde.
 */
public class MainBibliothecaireLivres {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            // 1. Chargement du catalogue sauvegardé
            ObjectInputStream inCat = new ObjectInputStream(new FileInputStream("catalogue.dat"));
            Catalogue catalogue = (Catalogue) inCat.readObject();
            inCat.close();

            // 2. Création d’un bibliothécaire fictif (test uniquement)
            Bibliothecaire biblio = new Bibliothecaire(
                    999, "Bibli", "Thecaire", catalogue,
                    new ArrayList<>(), new ArrayList<>());

            System.out.println("=== MENU GESTION DES LIVRES ===");

            boolean quitter = false;
            while (!quitter) {
                // Menu 
                System.out.println("\n1. Afficher le catalogue");
                System.out.println("2. Ajouter un livre");
                System.out.println("3. Modifier un livre");
                System.out.println("4. Supprimer un exemplaire");
                System.out.println("5. Sauvegarder et quitter");
                System.out.print("Votre choix : ");
                int choix = Integer.parseInt(scanner.nextLine());

                switch (choix) {
                    case 1:
                        // Affichage simple
                        biblio.consulterCatalogue();
                        break;

                    case 2:
                        // Ajout d’un livre et exemplaire
                        System.out.print("ID Livre : ");
                        int idLivre = Integer.parseInt(scanner.nextLine());

                        System.out.print("Titre : ");
                        String titre = scanner.nextLine();

                        System.out.print("Auteur : ");
                        String auteur = scanner.nextLine();

                        System.out.print("ISBN : ");
                        int isbn = Integer.parseInt(scanner.nextLine());

                        biblio.ajouterLivre(idLivre, titre, auteur, isbn);
                        break;

                    case 3:
                        System.out.print("Titre du livre à modifier : ");
                        String ancienTitre = scanner.nextLine();

                        System.out.print("Nouveau titre : ");
                        String nouveauTitre = scanner.nextLine();

                        System.out.print("Nouvel auteur : ");
                        String nouvelAuteur = scanner.nextLine();

                        biblio.modifierLivre(ancienTitre, nouveauTitre, nouvelAuteur);
                        break;


                    case 4:
                        System.out.print("ISBN du livre à supprimer entièrement : ");
                        int isbnSup = Integer.parseInt(scanner.nextLine());
                        biblio.supprimerLivre(isbnSup);
                        break;


                    case 5:
                        // Sauvegarde du catalogue
                        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("catalogue.dat"));
                        out.writeObject(catalogue);
                        out.close();
                        System.out.println("Catalogue sauvegardé. À bientôt !");
                        quitter = true;
                        break;

                    default:
                        System.out.println("Choix invalide. Réessayez.");
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur : " + e.getMessage());
        }

        scanner.close();
    }
}
