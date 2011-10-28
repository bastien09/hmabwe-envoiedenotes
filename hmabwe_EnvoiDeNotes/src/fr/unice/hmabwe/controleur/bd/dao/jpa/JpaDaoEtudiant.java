/**
 * 
 */
package fr.unice.hmabwe.controleur.bd.dao.jpa;

import javax.persistence.Embeddable;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.unice.hmabwe.controleur.bd.dao.DaoEtudiant;
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

	private String RequeteEtaitInscrit = "select e from Etudiant as e where " +
			"e.nom = :etuNom and " +
			"e.";

	/**
	 * le code métier va ici
	 */

	//TODO finir la méthode
	public boolean etaitInscrit(String numEtu, String nomCours, int annee) {
		EntityManager e = this.getEntityManager();
		Query q = null;
		e.createQuery(RequeteEtaitInscrit);
		return true;
	}
}
