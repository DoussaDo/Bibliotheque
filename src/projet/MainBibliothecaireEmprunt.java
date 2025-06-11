package projet;

import java.io.*;
import java.util.*;

public class MainBibliothecaireEmprunt {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            // === CHARGEMENT DES DONNÉES ===

            // 1. Lecture du catalogue
            ObjectInputStream inCat = new ObjectInputStream(new FileInputStream("catalogue.dat"));
            Catalogue catalogue = (Catalogue) inCat.readObject();
            inCat.close();

            // 2. Lecture de la liste des adhérents
            ObjectInputStream inAdh = new ObjectInputStream(new FileInputStream("adherents.dat"));
            @SuppressWarnings("unchecked")
            ArrayList<Adherent> adherents = (ArrayList<Adherent>) inAdh.readObject();
            inAdh.close();

            // 3. Liste vide des emprunts pour cette session
            List<Emprunt> emprunts = new ArrayList<>();

            // 4. Création du bibliothécaire (avec catalogue, adhérents, emprunts)
            Bibliothecaire biblio = new Bibliothecaire(999, "Bibli", "Thecaire", catalogue, emprunts, adherents);

            // === MENU EMPRUNTS ===
            boolean quitter = false;

            while (!quitter) {
                System.out.println("\n=== MENU GESTION DES EMPRUNTS ===");
                System.out.println("1. Créer un emprunt");
                System.out.println("2. Prolonger un emprunt");
                System.out.println("3. Enregistrer un retour");
                System.out.println("4. Afficher tous les emprunts");
                System.out.println("5. Voir la liste des adhérents");
                System.out.println("6. Quitter");
                System.out.print("Votre choix : ");
                int choix = Integer.parseInt(scanner.nextLine());

                switch (choix) {
                    case 1:
                        // --- Créer un nouvel emprunt ---
                        System.out.print("ID de l’adhérent : ");
                        int idAdh = Integer.parseInt(scanner.nextLine());

                        // On cherche l'adhérent dans la liste
                        Adherent adherent = null;
                        for (Adherent a : adherents) {
                            if (a.getId() == idAdh) {
                                adherent = a;
                                break;
                            }
                        }

                        if (adherent == null) {
                            System.out.println("Adhérent introuvable.");
                            break;
                        }

                        System.out.println("\nExemplaires disponibles :");
                        catalogue.afficherExemplairesDisponibles(); 

                        System.out.print("\nID de l’exemplaire à emprunter : ");
                        int idExemplaire = Integer.parseInt(scanner.nextLine());
                        Exemplaire exemplaire = catalogue.trouverExemplaireParId(idExemplaire);


                        if (exemplaire == null || !exemplaire.estDisponible()) {
                            System.out.println("Exemplaire indisponible ou inexistant.");
                            break;
                        }

                        // Création de l’emprunt via la méthode du bibliothécaire
                        int nouvelIdEmprunt = emprunts.size() + 1;
                        biblio.creerEmprunt(nouvelIdEmprunt, adherent, exemplaire);
                        break;

                    case 2:
                        // --- Prolonger un emprunt ---
                        System.out.print("ID de l’emprunt à prolonger : ");
                        int idEmp = Integer.parseInt(scanner.nextLine());

                        System.out.print("Nombre de jours à ajouter : ");
                        int jours = Integer.parseInt(scanner.nextLine());

                        biblio.prolongerEmprunt(idEmp, jours);
                        break;

                    case 3:
                        // --- Enregistrer un retour ---
                        System.out.print("ID de l’emprunt retourné : ");
                        int idRetour = Integer.parseInt(scanner.nextLine());

                        biblio.enregistrerRetour(idRetour);
                        break;

                    case 4:
                        // --- Afficher les emprunts ---
                        if (emprunts.isEmpty()) {
                            System.out.println("Aucun emprunt enregistré.");
                        } else {
                            for (Emprunt emp : emprunts) {
                                System.out.println(emp);
                            }
                        }
                        break;

                    case 5:
                        // --- Voir les adhérents ---
                        biblio.voirListeAdherents();
                        break;

                    case 6:
                        // --- Quitter ---
                        System.out.println("Fermeture du programme.");
                        quitter = true;
                        break;

                    default:
                        System.out.println("Choix invalide.");
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur : " + e.getMessage());
        }

        scanner.close();
    }
}
