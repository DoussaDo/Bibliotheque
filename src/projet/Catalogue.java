package projet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe Catalogue représente l'ensemble des exemplaires physiques disponibles dans la bibliothèque.
 * Elle sert à stocker, rechercher et afficher les exemplaires.
 * Elle implémente Serializable afin de pouvoir sauvegarder son contenu dans un fichier .dat
 */
public class Catalogue implements Serializable {

    private static final long serialVersionUID = 1L;

    // ATTRIBUTS

    /**
     * Liste de tous les exemplaires (copies physiques) présents dans la bibliothèque.
     * Un même Livre peut avoir plusieurs Exemplaires différents (ex: 3 copies de "Harry Potter").
     */
    private List<Exemplaire> exemplaires;

    // CONSTRUCTEUR

    /**
     * Constructeur par défaut : initialise une liste vide d'exemplaires.
     * Le catalogue sera ensuite rempli par le programme principal ou en chargeant un fichier .dat
     */
    public Catalogue() {
        exemplaires = new ArrayList<>();
    }

    // MÉTHODES PRINCIPALES

    /**
     * Ajoute un exemplaire au catalogue.
     * Cela n'enregistre pas encore dans un fichier : c'est une opération en mémoire.
     * @param exemplaire l’exemplaire à ajouter
     */
    public void ajouterExemplaire(Exemplaire exemplaire) {
        exemplaires.add(exemplaire);
    }

    /**
     * Retourne la liste complète des exemplaires en mémoire.
     * Utile pour d’autres classes qui veulent y accéder comme le bibliothécaire.
     * @return liste des exemplaires
     */
    public List<Exemplaire> getExemplaires() {
        return exemplaires;
    }

    /**
     * Affiche tous les exemplaires dans la console (utilise la méthode toString() de Exemplaire).
     * Si le catalogue est vide, affiche un message clair.
     */
    public void afficherCatalogue() {
        if (exemplaires.isEmpty()) {
            System.out.println("Le catalogue est vide.");
        } else {
            for (Exemplaire ex : exemplaires) {
                System.out.println(ex); 
            }
        }
    }

    /**
     * Recherche tous les exemplaires dont le titre du livre contient le mot-clé donné.
     * @param motCle le mot à chercher dans le titre
     * @return liste d’exemplaires correspondants
     */
    public List<Exemplaire> rechercherParTitre(String motCle) {
        List<Exemplaire> resultats = new ArrayList<>();

        for (Exemplaire ex : exemplaires) {
            String titre = ex.getLivre().getTitre().toLowerCase();
            if (titre.contains(motCle.toLowerCase())) {
                resultats.add(ex);
            }
        }

        return resultats;
    }

    /**
     * Recherche tous les exemplaires dont le nom de l’auteur contient le mot-clé donné.
     * @param motCle le mot à chercher dans l’auteur
     * @return liste d’exemplaires correspondants
     */
    public List<Exemplaire> rechercherParAuteur(String motCle) {
        List<Exemplaire> resultats = new ArrayList<>();

        for (Exemplaire ex : exemplaires) {
            String auteur = ex.getLivre().getAuteur().toLowerCase();
            if (auteur.contains(motCle.toLowerCase())) {
                resultats.add(ex);
            }
        }

        return resultats;
    }
    
    public void afficherExemplairesDisponibles() {
        boolean vide = true;
        for (Exemplaire ex : exemplaires) {
            if (ex.estDisponible()) {
                System.out.println(ex);
                vide = false;
            }
        }
        if (vide) {
            System.out.println("Aucun exemplaire disponible.");
        }
    }

    
    /**
     * Recherche un exemplaire par son identifiant.
     * @param idExemplaire identifiant à rechercher
     * @return l’exemplaire correspondant ou null s’il n’existe pas
     */
    public Exemplaire trouverExemplaireParId(int idExemplaire) {
        for (Exemplaire ex : exemplaires) {
            if (ex.getIdExemplaire() == idExemplaire) {
                return ex;
            }
        }
        return null; // Aucun exemplaire trouvé
    }

}
