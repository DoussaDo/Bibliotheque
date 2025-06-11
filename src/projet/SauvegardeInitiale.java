package projet;

import java.io.*;
import java.util.ArrayList;

public class SauvegardeInitiale {
    public static void main(String[] args) {
        try {
            // 1. On crée quelques livres
            Livre livre1 = new Livre(1, "Le Petit Prince", "Antoine de Saint-Exupéry", 123);
            Livre livre2 = new Livre(2, "L'Étranger", "Albert Camus", 456);
            Livre livre3 = new Livre(3, "Stupeur et Tremblements", "Amélie Notomb", 789);


            // 2. On les enveloppe dans des exemplaires
            Exemplaire ex1 = new Exemplaire(1, livre1);
            Exemplaire ex2 = new Exemplaire(2, livre2);
            Exemplaire ex3 = new Exemplaire(3, livre3);
            Exemplaire ex4 = new Exemplaire(4, livre3); // deuxième exemplaire de livre3


            // 3. On crée un catalogue et on y ajoute les exemplaires
            Catalogue catalogue = new Catalogue();
            catalogue.ajouterExemplaire(ex1);
            catalogue.ajouterExemplaire(ex2);
            catalogue.ajouterExemplaire(ex3);
            catalogue.ajouterExemplaire(ex4); // le 2e exemplaire de livre3

            // 4. On sauvegarde le catalogue
            ObjectOutputStream outCat = new ObjectOutputStream(new FileOutputStream("catalogue.dat"));
            outCat.writeObject(catalogue);
            outCat.close();
            System.out.println("Catalogue sauvegardé dans catalogue.dat");

            // 5. Création d'adhérents
            ArrayList<Adherent> adherents = new ArrayList<>();
            adherents.add(new Adherent(1, "Dounia", "Zouaghi", catalogue));
            adherents.add(new Adherent(2, "Romain", "Nkoumouck", catalogue));
            adherents.add(new Adherent(3, "Imane", "Amane", catalogue));

            ObjectOutputStream outAdh = new ObjectOutputStream(new FileOutputStream("adherents.dat"));
            outAdh.writeObject(adherents);
            outAdh.close();
            System.out.println("Adhérents sauvegardés dans adherents.dat");

            // 6. Création d’un bibliothécaire
            Bibliothecaire biblio = new Bibliothecaire(100, "Jordan", "Fometio", catalogue,new ArrayList<Emprunt>(), adherents);

            ObjectOutputStream outBib = new ObjectOutputStream(new FileOutputStream("biblio.dat"));
            outBib.writeObject(biblio);
            outBib.close();
            System.out.println("Bibliothécaire sauvegardé dans biblio.dat");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
