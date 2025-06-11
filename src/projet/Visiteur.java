package projet;

import java.util.List;

/**
 * La classe Visiteur est une classe illustrative.
 * Elle représente un utilisateur non inscrit.
 * Elle ne sera pas utilisée pour enregistrer ou interagir avec le système.
 */
public class Visiteur extends Utilisateur {

    /**
     * Constructeur minimal du visiteur.
     * @param id identifiant fictif
     * @param nom nom du visiteur
     * @param prenom prénom du visiteur
     * @param catalogue catalogue visible (même si non exploité ici)
     */
    public Visiteur(int id, String nom, String prenom, Catalogue catalogue) {
        super(id, nom, prenom, catalogue);
    }

    /**
     * Méthode illustrative : en théorie, le visiteur pourrait créer un compte.
     * Mais ici, ce n’est pas implémenté.
     */
    public void creerCompte() {
        System.out.println("La création de compte n’est pas disponible pour le moment.");
    }
    
    // MÉTHODES REDÉFINIES

    /**
     * Affiche tous les exemplaires du catalogue, mais uniquement les infos de base :
     * titre et auteur. Le visiteur ne voit pas les ISBN.
     */
    
    @Override
    public void consulterCatalogue() {
        List<Exemplaire> liste = getCatalogue().getExemplaires();
        if (liste.isEmpty()) {
            System.out.println("Catalogue vide.");
        } else {
            System.out.println("Consultation du catalogue (accès Visiteur) :");
            for (Exemplaire ex : liste) {
                Livre livre = ex.getLivre();
                System.out.println("- " + livre.getTitre() + " par " + livre.getAuteur());
            }
        }
    }

    /**
     * Recherche des exemplaires dont le titre contient un mot-clé.
     * Le résultat affiche uniquement le titre et l’auteur.
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
     * Recherche des exemplaires dont l’auteur contient un mot-clé.
     * Le résultat affiche uniquement le titre et l’auteur.
     * @param motCle mot recherché dans l’auteur
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
}
