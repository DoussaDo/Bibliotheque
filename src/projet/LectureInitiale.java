package projet;

import java.io.*;//pour pouvoir lire dans les fichiers avec ObjectInputStream, FileInputStream, etc.
import java.util.ArrayList;

public class LectureInitiale {
    public static void main(String[] args) {
    	//Tout est mis dans un try pour attraper les erreurs potentielles lors de la lecture des fichiers.
        try {
            // LECTURE DU CATALOGUE
        	//FileInputStream : ouvre le fichier binaire "catalogue.dat" contenant un objet sérialisé.
        	//ObjectInputStream : permet de lire un objet Java depuis ce fichier.
        	//readObject() : retourne un Object, donc on doit le caster en Catalogue.
        	//getExemplaires().size() : affiche combien d’exemplaires ont été chargés depuis le fichier.
        	
            ObjectInputStream inCat = new ObjectInputStream(new FileInputStream("catalogue.dat"));
            Catalogue catalogue = (Catalogue) inCat.readObject();
            inCat.close();
            System.out.println("Catalogue chargé avec " + catalogue.getExemplaires().size() + " exemplaires.");

            // LECTURE DE LA LISTE D’ADHÉRENTS
            //Ouvre "adherents.dat" et lis une liste d’adhérents (ArrayList<Adherent>).
            //Le cast ((ArrayList<Adherent>)) est nécessaire, car readObject() renvoie Object.
            //L’annotation @SuppressWarnings("unchecked") supprime un avertissement du compilateur sur le cast générique.
            //adherents.size() affiche le nombre d’objets chargés.
            
            ObjectInputStream inAdh = new ObjectInputStream(new FileInputStream("adherents.dat"));
            @SuppressWarnings("unchecked")
			ArrayList<Adherent> adherents = (ArrayList<Adherent>) inAdh.readObject();
            inAdh.close();
            System.out.println(adherents.size() + " adhérent(s) chargé(s).");

            // LECTURE DU BIBLIOTHÉCAIRE
            ObjectInputStream inBib = new ObjectInputStream(new FileInputStream("biblio.dat"));
            Bibliothecaire biblio = (Bibliothecaire) inBib.readObject();
            inBib.close();
            System.out.println("Bibliothécaire chargé : " + biblio.getNom() + " " + biblio.getPrenom());

            //Gestion des erreurs
            //IOException : erreur d'ouverture/lecture/fermeture de fichiers.
            //ClassNotFoundException : si l’objet lu ne correspond à aucune classe connue dans ton code actuel (ex. : nom de classe modifié entre la sauvegarde et la lecture).
        } catch (IOException e) {
            System.err.println("Erreur d'entrée/sortie : " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Classe non trouvée : " + e.getMessage());
        }
    }
}
