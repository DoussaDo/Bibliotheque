package projet;

import java.io.Serializable;
import java.util.List;

/**
 * La classe Adherent représente un utilisateur inscrit à la bibliothèque.
 * Il peut consulter le catalogue, faire des recherches, et réserver un exemplaire disponible.
 */
public class Adherent extends Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;

    // CONSTRUCTEUR

    /**
     * Crée un nouvel adhérent inscrit.
     * @param id identifiant de l’adhérent
     * @param nom nom de l’adhérent
     * @param prenom prénom de l’adhérent
     * @param catalogue référence vers le catalogue de la bibliothèque
     */
    public Adherent(int id, String nom, String prenom, Catalogue catalogue) {
        super(id, nom, prenom, catalogue);
    }

    
    // MÉTHODES REDÉFINIES


    /**
     * Affiche tous les exemplaires du catalogue, mais uniquement les infos de base :
     * titre et auteur (comme un visiteur).
     */
    @Override
    public void consulterCatalogue() {
        List<Exemplaire> liste = getCatalogue().getExemplaires();

        if (liste.isEmpty()) {
            System.out.println("Catalogue vide.");
        } else {
            System.out.println("Consultation du catalogue (accès Adhérent) :");
            for (Exemplaire ex : liste) {
                Livre livre = ex.getLivre();
                System.out.println("- " + livre.getTitre() + " par " + livre.getAuteur());
            }
        }
    }

    /**
     * Recherche des exemplaires par titre (affichage : titre et auteur).
     * @param motCle mot recherché dans le titre
     */
    @Override
    public void rechercherParTitre(String motCle) {
        List<Exemplaire> resultats = getCatalogue().rechercherParTitre(motCle);

        if (resultats.isEmpty()) {
            System.out.println("Aucun livre trouvé avec ce mot dans le titre.");
        } else {
            System.out.println("Résultats de la recherche (par titre) :");
            for (Exemplaire ex : resultats) {
                Livre livre = ex.getLivre();
                System.out.println("- " + livre.getTitre() + " par " + livre.getAuteur());
            }
        }
    }

    /**
     * Recherche des exemplaires par auteur (affichage : titre et auteur).
     * @param motCle mot recherché dans le nom de l’auteur
     */
    @Override
    public void rechercherParAuteur(String motCle) {
        List<Exemplaire> resultats = getCatalogue().rechercherParAuteur(motCle);

        if (resultats.isEmpty()) {
            System.out.println("Aucun livre trouvé avec ce mot dans le nom de l’auteur.");
        } else {
            System.out.println("Résultats de la recherche (par auteur) :");
            for (Exemplaire ex : resultats) {
                Livre livre = ex.getLivre();
                System.out.println("- " + livre.getTitre() + " par " + livre.getAuteur());
            }
        }
    }


    // MÉTHODE SPÉCIFIQUE À ADHERENT

    /**
     * Réserve un exemplaire s’il est disponible.
     * Change son état à RESERVE si la réservation réussit.
     * @param exemplaire l’exemplaire que l’adhérent veut réserver
     */
    public void reserver(Exemplaire exemplaire) {
        if (exemplaire.estDisponible()) {
            exemplaire.setEtat(EtatLivre.Reserve);
            System.out.println("Réservation confirmée pour : " + exemplaire.getLivre().getTitre());
        } else {
            System.out.println("Impossible de réserver cet exemplaire. Il n’est pas disponible.");
        }
    }

    public void sauthentifier() {
        // TODO : logique d'authentification qui ne sera pas developpée
    }
}

