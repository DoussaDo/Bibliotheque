package projet;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * La classe Emprunt représente un lien entre un Adhérent et un Exemplaire.
 * Elle contient les informations liées à l'emprunt : dates, état, etc.
 */
public class Emprunt implements Serializable {

    private static final long serialVersionUID = 1L;


    // ATTRIBUTS


    private int id;                        // Identifiant unique de l’emprunt
    private Adherent emprunteur;          // L’adhérent qui a emprunté
    private Exemplaire exemplaire;        // L’exemplaire emprunté
    private LocalDate dateEmprunt;        // Date à laquelle l’emprunt a été effectué
    private LocalDate dateRetourPrevue;   // Date limite pour rendre le livre
    private LocalDate dateRetourEffective;// Date réelle de retour (null si pas encore rendu)
    private StatutEmprunt statut;         // EN_COURS, RETOURNE, EN_RETARD


    // CONSTRUCTEUR


    /**
     * Crée un emprunt actif (statut EN_COURS).
     * @param id identifiant unique
     * @param emprunteur adhérent concerné
     * @param exemplaire exemplaire emprunté
     * @param dateEmprunt date de début
     * @param dateRetourPrevue date limite de retour
     */
    public Emprunt(int id, Adherent emprunteur, Exemplaire exemplaire,
                   LocalDate dateEmprunt, LocalDate dateRetourPrevue) {
        this.id = id;
        this.emprunteur = emprunteur;
        this.exemplaire = exemplaire;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourEffective = null; // pas encore retourné
        this.statut = StatutEmprunt.EN_COURS;

        this.exemplaire.setEtat(EtatLivre.Emprunte); //on met à jour l’état de l’exemplaire
    }

    // MÉTHODES MÉTIER

    /**
     * Enregistre le retour du livre.
     * Met à jour la date effective et le statut.
     * Remet l’exemplaire à l’état DISPONIBLE.
     */
    public void enregistrerRetour(LocalDate dateRetour) {
        this.dateRetourEffective = dateRetour;

        if (dateRetour.isAfter(dateRetourPrevue)) {
            this.statut = StatutEmprunt.EN_RETARD;
        } else {
            this.statut = StatutEmprunt.RETOURNE;
        }

        // On remet l’exemplaire disponible
        this.exemplaire.setEtat(EtatLivre.Disponible);
    }


    // GETTERS

    public int getId() {
        return id;
    }

    public Adherent getEmprunteur() {
        return emprunteur;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public LocalDate getDateRetourEffective() {
        return dateRetourEffective;
    }

    public StatutEmprunt getStatut() {
        return statut;
    }


    // SETTER de prolongation
    /**
     * Prolonge la date prévue de retour de l’emprunt en ajoutant un nombre de jours.
     * Exemple : prolonger de 7 jours ajoutera 7 jours à la date de retour actuelle.
     * @param nbJours nombre de jours à ajouter
     */

    public void prolongerRetour(int nbJours) {
        this.dateRetourPrevue = this.dateRetourPrevue.plusDays(nbJours);
    }

    // toString

    @Override
    public String toString() {
        return "Emprunt #" + id + " | " +
               "Adhérent : " + emprunteur.getNom() + " " + emprunteur.getPrenom() + " | " +
               "Livre : " + exemplaire.getLivre().getTitre() + " | " +
               "Date Emprunt : " + dateEmprunt + " | " +
               "Retour prévu : " + dateRetourPrevue + " | " +
               "Statut : " + statut;
    }
}
