package projet;

import java.io.Serializable;

//La classe Exemplaire représente une copie physique d'un livre.
//Chaque exemplaire a un numéro unique et peut avoir un état différent.
//Exemple : deux exemplaires d'un même livre, l'un emprunté, l'autre disponible.
public class Exemplaire implements Serializable {

    private static final long serialVersionUID = 1L;

    private int IdExemplaire;  		// Identifiant unique de l’exemplaire
    private Livre livre;           // Référence au livre de cet exemplaire
    private EtatLivre etat;        // État de l’exemplaire (DISPONIBLE, EMPRUNTE, etc.)

    // Constructeur : initialise un exemplaire disponible
    public Exemplaire(int IdExemplaire, Livre livre) {
        this.IdExemplaire = IdExemplaire;
        this.livre = livre;
        this.etat = EtatLivre.Disponible;
    }

    // Indique si l'exemplaire peut être emprunté
    public boolean estDisponible() {
        return this.etat == EtatLivre.Disponible;
    }

    // Getters
    public int getIdExemplaire() {
        return this.IdExemplaire;
    }

    public Livre getLivre() {
        return livre;
    }

    public EtatLivre getEtat() {
        return etat;
    }

    // Setters
    public void setEtat(EtatLivre etat) {
        this.etat = etat;
    }

    // Affichage complet d'un exemplaire (utile pour le bibliothécaire)
    @Override
    public String toString() {
        return "Exemplaire n°" + this.IdExemplaire +
               " | Livre : " + livre.getTitre() +
               " | Auteur : " + livre.getAuteur() +
               " | ISBN : " + livre.getISBN() +
               " | État : " + etat;
    }
}
