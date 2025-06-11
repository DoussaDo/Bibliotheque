package projet;

import java.io.Serializable;

/**
 * Classe abstraite représentant un utilisateur de la bibliothèque.
 * Elle est héritée par Visiteur, Adherent et Bibliothecaire.
 */
public abstract class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;

    // ATTRIBUTS

    private int id;               // Identifiant unique de l'utilisateur
    private String nom;           // Nom de famille
    private String prenom;        // Prénom
    private Catalogue catalogue;  // Catalogue accessible à l'utilisateur

    // CONSTRUCTEUR

    /**
     * Initialise un utilisateur avec ses informations de base et le catalogue.
     * @param id identifiant unique
     * @param nom nom de l’utilisateur
     * @param prenom prénom de l’utilisateur
     * @param catalogue catalogue accessible à cet utilisateur
     */
    public Utilisateur(int id, String nom, String prenom, Catalogue catalogue) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.catalogue = catalogue;
    }

    // GETTERS

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Catalogue getCatalogue() {
        return catalogue;
    }

    // SETTERS

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setCatalogue(Catalogue catalogue) {
        this.catalogue = catalogue;
    }

    // MÉTHODES ABSTRAITES

    /**
     * Méthode que chaque type d’utilisateur doit implémenter.
     * Elle permet d’afficher le catalogue selon ses droits (avec ou sans ISBN, par exemple).
     */
    public abstract void consulterCatalogue();

    /**
     * Recherche un ou plusieurs exemplaires par titre.
     * Le contenu affiché dépendra du type d'utilisateur.
     */
    public abstract void rechercherParTitre(String motCle);

    /**
     * Recherche un ou plusieurs exemplaires par auteur.
     */
    public abstract void rechercherParAuteur(String motCle);


    // MÉTHODE toString

    @Override
    public String toString() {
        return "Utilisateur : " + nom + " " + prenom + " (ID : " + id + ")";
    }
}

