/**
 * 
 */
package fr.unice.hmabwe.controleur.bd.dao;

import java.util.HashMap;
import java.util.List;

import fr.unice.hmabwe.modele.Etudiant;
import fr.unice.hmabwe.modele.Inscription;

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
	

	public Inscription inscriptionEtu(String numEtu, String nomCours, int annee);
	
	/**
	 * Cherche l'étudiant dont on donne le numéro d'étudiant et le retourne
	 * 
	 * @param numEtu numéro d'étudiant. <b>Attention</b> le numéro d'étudiant
	 * 		  n'est pas l'identifiant de l'étudiant au sein de l'université !
	 * @return l'étudiant cherché, ou <code>null</code> s'il n'existe pas
	 */
	public Etudiant findByNumeroEtudiant(String numEtu);
	
	public List<Etudiant> getEtudiantByName(String nom);
	
	public List<Etudiant> getEtudiantByName(String nom, String prenom);
	
	/**
	 * calcule la moyenne d'un étudiant suivant son numéro d'étudiant
	 * 
	 * @param numEtu le numéro d'étudiant
	 * @return la moyenne de l'étudiant
	 * 
	 */
	public double getMoyenne(String numEtu);
	
	
	/**
	 * calcule la moyenne d'un étudiant suivant son numéro d'étudiant pour une annee donnee
	 * 
	 * @param numEtu le numéro d'étudiant
	 * @param annee l'annee en question
	 * @return la moyenne de l'étudiant
	 * 
	 */
	public double getMoyenne(String numEtu, int annee);
}
