package projet;

import java.io.Serializable;

// La classe Livre représente un livre dans la bibliothèque.
// Elle implémente Serializable pour pouvoir être sauvegardée dans un fichier .dat.
public class Livre implements Serializable {

    // Obligatoire pour assurer la compatibilité lors de la sérialisation
    private static final long serialVersionUID = 1L;

    // ATTRIBUTS DU LIVRE

    private int id;         // Identifiant unique du livre dans le système
    private String titre;   // Titre de l’ouvrage
    private String auteur;  // Nom de l’auteur
    private int ISBN;       // Code ISBN (simplifié en tant qu'entier)
    private EtatLivre etat; // État du livre : DISPONIBLE, EMPRUNTE, RESERVE


    // CONSTRUCTEUR

    // Initialise tous les champs sauf l’état, qui est par défaut DISPONIBLE
    public Livre(int id, String titre, String auteur, int ISBN) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.ISBN = ISBN;
        this.etat = EtatLivre.Disponible;  // Par défaut, tout nouveau livre est disponible
    }


    // MÉTHODES

    // Méthode qui permet de vérifier si un livre peut être emprunté
    public boolean estDisponible() {
        return this.etat == EtatLivre.Disponible;
    }

    // GETTERS

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public int getISBN() {
        return ISBN;
    }

    public EtatLivre getEtat() {
        return etat;
    }

    // SETTERS

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public void setEtat(EtatLivre etat) {
        this.etat = etat;
    }


    // toString

    // Affiche les informations du livre sous forme lisible pour l’utilisateur
    @Override
    public String toString() {
        return "ID: " + id +
               ", Titre: " + titre +
               ", Auteur: " + auteur +
               ", ISBN: " + ISBN +
               ", État: " + etat;
    }
}
