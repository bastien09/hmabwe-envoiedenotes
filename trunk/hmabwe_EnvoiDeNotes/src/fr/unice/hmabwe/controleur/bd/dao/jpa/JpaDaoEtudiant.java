/**
 * 
 */
package fr.unice.hmabwe.controleur.bd.dao.jpa;

import java.util.Collection;
import java.util.List;
import java.util.HashMap;

import javax.persistence.Embeddable;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import fr.unice.hmabwe.controleur.bd.dao.DaoEtudiant;
import fr.unice.hmabwe.modele.Coefficient;
import fr.unice.hmabwe.modele.Cours;
import fr.unice.hmabwe.modele.Etudiant;
import fr.unice.hmabwe.modele.Filiere;
import fr.unice.hmabwe.modele.Inscription;

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

	private final String RequeteEtaitInscrit = "select e from Inscription i " +
			"join i.etudiant e " +
			"join i.cours c " +
			"where i.annee = :annee and e.numEtu = :numEtu and c.nom = :nomCours";
	//TODO chercher comment faire des requete avec jointure
	
	private final String RequeteListeInscrit = "select e from Inscription i " +
	"join i.etudiant e " +
	"join i.cours c " +
	"where i.annee = :annee and c.nom = :nomCours";
	

	/**
	 * le code métier va ici
	 */


	/**
	 * @see fr.unice.hmabwe.controleur.dao.DaoEtudiant#etaitInscrit()
	 */
	public boolean etaitInscrit(String numEtu, String nomCours, int annee) {
		EntityManager em = getEntityManager();
		Query q = em.createQuery(RequeteEtaitInscrit);
		q.setParameter("numEtu", numEtu);
		q.setParameter("nomCours", nomCours);
		q.setParameter("annee", annee);
		List<Etudiant> res = (List<Etudiant>)q.getResultList();
		if (res.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
	

	/**
	 * @see fr.unice.hmabwe.controleur.dao.DaoEtudiant#listeInscrit()
	 */
	//TODO methode à faire
	public HashMap<Etudiant, String> listeInscrit(String nomCours, int annee){
		EntityManager em = getEntityManager();
		Query q = em.createQuery(RequeteListeInscrit);
		q.setParameter("nomCours", nomCours);
		q.setParameter("annee", annee);
		List<Etudiant> res = (List<Etudiant>)q.getResultList();
		System.out.println( "ici : \n\n "+ res + "\n\n");
		
		
		return null;
	}
	
	
	/**
	 * @see fr.unice.hmabwe.controleur.dao.DaoEtudiant#findByNumeroEtudiant()
	 */
	public Etudiant findByNumeroEtudiant(String numEtu) {
		EntityManager em = getEntityManager();
		Query q = em.createQuery("select e from Etudiant e where e.numEtu = :numEtu");
		q.setParameter("numEtu", numEtu);
		List<Etudiant> res = (List<Etudiant>)q.getResultList();
		if (res.isEmpty()) {
			return null;
		}
		else {
			return res.get(0);
		}
	}
	
	
	/**
	 * @see fr.unice.hmabwe.controleur.dao.DaoEtudiant#getMoyenne()
	 */
	//TODO finir la méthode !
	public double getMoyenne(String numEtu) {
		EntityManager em = getEntityManager();
		Query q = em.createQuery("select c from Coefficient c where c.filiere_id = :id");
		Etudiant e = findByNumeroEtudiant(numEtu);
		Collection<Inscription> l_inscr = e.getInscriptions();
		Filiere f = e.getFiliere();
		q.setParameter("id", f.getId());
		double somme = 0;
		List<Coefficient> l_coeffs = q.getResultList();
		for (Coefficient coefficient : l_coeffs) {
		}
		
		
		
		
		return 0;
	}
}
