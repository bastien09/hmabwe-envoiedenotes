/**
 * 
 */
package fr.unice.hmabwe.controleur.bd.dao.jpa;

import java.util.List;
import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import fr.unice.hmabwe.controleur.bd.dao.DaoEtudiant;
import fr.unice.hmabwe.modele.Cours;
import fr.unice.hmabwe.modele.Etudiant;

/**
 * @author Paraita Wohler
 *
 * JpaDaoEtudiant permet de faire abstraction de la couche persistance JPA
 * tout en rendant ce DAO suffisament générique.
 * Cette classe fourni les méthodes spécifiques au code métier de l'application
 *
 */

public class JpaDaoEtudiant extends JpaDaoGenerique<Etudiant, Integer>
implements DaoEtudiant{

	private String RequeteEtaitInscrit = "select e from Etudiant as e where e.numEtu = :numEtu" +
										 " and e.filiere.listeCoeffCours.cours.nom = :nomCours" +
										 " and e.Inscription.annee = :annee";
	//TODO chercher comment faire des requete avec jointure
	private String RequeteListeInscrit = "SELECT s FROM Etudiant s WHERE s.firstname = :fs";

	/**
	 * le code métier va ici
	 */

	/**
	 * @see fr.unice.hmabwe.controleur.dao.DaoEtudiant#etaitInscrit()
	 */
	public boolean etaitInscrit(String numEtu, String nomCours, int annee) {
		return true;
	}
	

	/**
	 * @see fr.unice.hmabwe.controleur.dao.DaoEtudiant#listeInscrit()
	 */
	//TODO methode à faire
	public HashMap<Etudiant, String> listeInscrit(String nomCours, int annee){
		
		return null;
	}
}
