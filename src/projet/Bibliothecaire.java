package projet;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe Bibliothecaire représente un agent de la bibliothèque avec un accès complet.
 * Il peut gérer les livres, créer/modifier les emprunts, et accéder à tous les utilisateurs.
 */
public class Bibliothecaire extends Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;

    // Liste d'emprunts suivis par le bibliothécaire
    private List<Emprunt> emprunts;
    private List<Adherent> adherents;

    /**
     * Constructeur principal.
     * @param id identifiant unique
     * @param nom nom
     * @param prenom prénom
     * @param catalogue catalogue de la bibliothèque
     * @param emprunts liste des emprunts en cours
     * @param adherents liste des adhérents connus
     */
    public Bibliothecaire(int id, String nom, String prenom, Catalogue catalogue,
                          List<Emprunt> emprunts, List<Adherent> adherents) {
        super(id, nom, prenom, catalogue);
        this.emprunts = emprunts;
        this.adherents = adherents;
    }


    // CONSULTATION ET RECHERCHE

    @Override
    public void consulterCatalogue() {
        List<Exemplaire> liste = getCatalogue().getExemplaires();
        if (liste.isEmpty()) {
            System.out.println("Catalogue vide.");
        } else {
            System.out.println("Catalogue complet :");
            for (Exemplaire ex : liste) {
                Livre livre = ex.getLivre();
                System.out.println("- " + livre.getTitre()
                        + " | Auteur : " + livre.getAuteur()
                        + " | ISBN : " + livre.getISBN()
                        + " | État : " + ex.getEtat());
            }
        }
    }

    @Override
    public void rechercherParTitre(String motCle) {
        List<Exemplaire> resultats = getCatalogue().rechercherParTitre(motCle);
        for (Exemplaire ex : resultats) {
            Livre livre = ex.getLivre();
            System.out.println("- " + livre.getTitre()
                    + " | Auteur : " + livre.getAuteur()
                    + " | ISBN : " + livre.getISBN()
                    + " | État : " + ex.getEtat());
        }
    }

    @Override
    public void rechercherParAuteur(String motCle) {
        List<Exemplaire> resultats = getCatalogue().rechercherParAuteur(motCle);
        for (Exemplaire ex : resultats) {
            Livre livre = ex.getLivre();
            System.out.println("- " + livre.getTitre()
                    + " | Auteur : " + livre.getAuteur()
                    + " | ISBN : " + livre.getISBN()
                    + " | État : " + ex.getEtat());
        }
    }

    // GESTION DES LIVRES


    /**
     * Ajoute un nouvel exemplaire d’un livre dans le catalogue.
     * @param livre le livre à ajouter
     * @param titre le titre du livre
     * @param l'auteur du livre 
     * @param l'isbn du livre
     */
    public void ajouterLivre(int idLivre, String titre, String auteur, int isbn) {
        // 1. Création d’un nouveau livre
        Livre nouveau = new Livre(idLivre, titre, auteur, isbn);

        // 2. On crée un nouvel exemplaire du livre
        // On suppose que l’ID de l’exemplaire est simplement le même que le livre ici
        Exemplaire exemplaire = new Exemplaire(idLivre, nouveau);

        // 3. Ajout de l’exemplaire dans le catalogue
        getCatalogue().ajouterExemplaire(exemplaire);

        System.out.println("Livre ajouté avec succès ! ID de l’exemplaire : " + idLivre);
    }


    /**
     * Permet de modifier les informations (titre et auteur) d’un livre existant.
     * La recherche se fait par ISBN. Tous les exemplaires liés à ce livre seront mis à jour.
     *
     * @param isbn          l’ISBN du livre à modifier
     * @param nouveauTitre  le nouveau titre à appliquer
     * @param nouvelAuteur  le nouvel auteur à appliquer
     */
    /**
     * Modifie les infos d’un livre existant basé sur son titre.
     * @param ancienTitre titre du livre à modifier
     * @param nouveauTitre nouveau titre
     * @param nouvelAuteur nouvel auteur
     */
    public void modifierLivre(String ancienTitre, String nouveauTitre, String nouvelAuteur) {
        boolean modifie = false;

        for (Exemplaire ex : getCatalogue().getExemplaires()) {
            Livre livre = ex.getLivre();
            if (livre.getTitre().equalsIgnoreCase(ancienTitre.trim())) {
                livre.setTitre(nouveauTitre);
                livre.setAuteur(nouvelAuteur);
                modifie = true;
            }
        }

        if (modifie) {
            System.out.println("Livre modifié avec succès !");
        } else {
            System.out.println("Aucun livre avec le titre exact \"" + ancienTitre + "\" trouvé dans le catalogue.");
        }
    }



    /**
     * Supprime tous les exemplaires liés à un livre donné, en se basant sur son ISBN.
     * Cela revient à supprimer le livre entièrement du catalogue.
     * @param isbn ISBN du livre à supprimer
     */
    public void supprimerLivre(int isbn) {
        List<Exemplaire> exemplaires = getCatalogue().getExemplaires();
        List<Exemplaire> aSupprimer = new ArrayList<>();

        for (Exemplaire ex : exemplaires) {
            if (ex.getLivre().getISBN() == isbn) {
                aSupprimer.add(ex);
            }
        }

        if (!aSupprimer.isEmpty()) {
            exemplaires.removeAll(aSupprimer);
            System.out.println("Tous les exemplaires du livre ISBN " + isbn + " ont été supprimés.");
        } else {
            System.out.println("Aucun livre trouvé avec cet ISBN.");
        }
    }

    // GESTION DES EMPRUNTS


    /**
     * Crée un nouvel emprunt pour un adhérent donné.
     * @param empruntId identifiant de l’emprunt
     * @param adherent adhérent concerné
     * @param exemplaire exemplaire à emprunter
     */
    public void creerEmprunt(int empruntId, Adherent adherent, Exemplaire exemplaire) {
        if (exemplaire.estDisponible()) {
            LocalDate aujourdhui = LocalDate.now();
            LocalDate retour = aujourdhui.plusWeeks(3); // durée fixe de 3 semaines
            Emprunt emprunt = new Emprunt(empruntId, adherent, exemplaire, aujourdhui, retour);
            emprunts.add(emprunt);
            System.out.println("Emprunt créé pour " + adherent.getNom() + " sur " + exemplaire.getLivre().getTitre());
        } else {
            System.out.println("L’exemplaire n’est pas disponible pour emprunt.");
        }
    }

    /**
     * Prolonge un emprunt existant en ajoutant des jours à la date prévue.
     * @param empruntId identifiant de l’emprunt
     * @param nbJours nombre de jours à prolonger
     */
    public void prolongerEmprunt(int empruntId, int nbJours) {
        for (Emprunt em : emprunts) {
            if (em.getId() == empruntId) {
                if (em.getExemplaire().getEtat() != EtatLivre.Reserve) {
                    em.prolongerRetour(nbJours);
                    System.out.println("Emprunt prolongé jusqu’au " + em.getDateRetourPrevue());
                } else {
                    System.out.println("Impossible de prolonger : l’exemplaire est réservé.");
                }
                return;
            }
        }
        System.out.println("Aucun emprunt trouvé avec l’ID " + empruntId);
    }

    /**
     * Enregistre le retour d’un emprunt et met l’exemplaire en DISPONIBLE.
     * @param empruntId identifiant de l’emprunt à clôturer
     */
    public void enregistrerRetour(int empruntId) {
        for (Emprunt em : emprunts) {
            if (em.getId() == empruntId && em.getStatut() == StatutEmprunt.EN_COURS) {
                em.enregistrerRetour(LocalDate.now());
                System.out.println("Retour enregistré pour l’emprunt ID " + empruntId);
                return;
            }
        }
        System.out.println("Emprunt non trouvé ou déjà retourné.");
    }

    /**
     * Affiche la liste des adhérents disponibles (pour créer un emprunt par exemple).
     */
    public void voirListeAdherents() {
        System.out.println("Liste des adhérents :");
        for (Adherent a : adherents) {
            System.out.println("- " + a.getNom() + " " + a.getPrenom() + " (ID : " + a.getId() + ")");
        }
    }

    public void sauthentifier() {
        // Logique d'authentification à ne pas faire
    }
}
