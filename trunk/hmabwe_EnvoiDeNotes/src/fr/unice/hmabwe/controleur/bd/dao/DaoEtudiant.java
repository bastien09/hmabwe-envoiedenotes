/**
 * 
 */
package fr.unice.hmabwe.controleur.bd.dao;

import java.util.HashMap;

import fr.unice.hmabwe.modele.Cours;
import fr.unice.hmabwe.modele.Etudiant;

/**
 * @author Engilberge swan
 *
 */
public interface DaoEtudiant extends DaoGenerique<Etudiant, Integer> {
	
	/**
	 * cette methode permet de savoir si un etudiant et bien inscrit
	 * à un cour, une année donnée
	 * 
	 * @param numEtu le numero de l'étudiant dont on veut verifier l'inscription
	 * @param nomCours le nom du cour où l'n veut savoir si l'etudiant est inscrit
	 * @param annee l'année ou l'etudiant est inscrit.
	 */
	
	public boolean etaitInscrit(String numEtu, String nomCours, int annee);
	
	/**
	 * cette methode renvoie la liste des étudiants inscrit à un cour et une année donnée
	 * 
	 * @param nomCours le nom du cours où on veut la liste des inscrit
	 * @param annee l'année a laquelle on veut les inscrit
	 * 
	 */
	
	public HashMap<Etudiant, String> listeInscrit(String nomCours, int annee);

	/**
	 * Cherche l'étudiant dont on donne le numéro d'étudiant et le retourne
	 * 
	 * @param numEtu numéro d'étudiant. <b>Attention</b> le numéro d'étudiant
	 * 		  n'est pas l'identifiant de l'étudiant au sein de l'université !
	 * @return l'étudiant cherché, ou <code>null</code> s'il n'existe pas
	 */
	public Etudiant findByNumeroEtudiant(String numEtu);
	
	/**
	 * calcule la moyenne d'un étudiant suivant son numéro d'étudiant
	 * 
	 * @param numEtu le numéro d'étudiant
	 * @return la moyenne de l'étudiant
	 * 
	 */
	public double getMoyenne(String numEtu);
	
}
