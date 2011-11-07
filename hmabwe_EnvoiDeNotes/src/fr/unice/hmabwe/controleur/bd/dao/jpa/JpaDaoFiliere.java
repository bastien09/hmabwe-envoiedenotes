package fr.unice.hmabwe.controleur.bd.dao.jpa;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import fr.unice.hmabwe.controleur.bd.dao.DaoFiliere;
import fr.unice.hmabwe.modele.Coefficient;
import fr.unice.hmabwe.modele.Etudiant;
import fr.unice.hmabwe.modele.Filiere;
import fr.unice.hmabwe.modele.Inscription;

/**
 * 
 * @author Paraita Wohler
 *
 * JpaDaoFiliere permet de faire abstraction de la couche persistance JPA
 * tout en rendant ce DAO suffisament générique.
 * Cette classe fourni les méthodes spécifiques au code métier de l'application
 *
 */
public class JpaDaoFiliere extends JpaDaoGenerique<Filiere, Integer>
		implements DaoFiliere {
	
	


	/**
	 * @see fr.unice.hmabwe.controleur.dao.DaoFiliere#getEtudiantsInscrit()
	 */
	public Collection<Etudiant> getEtudiantsInscrits(Filiere filiere){
		return filiere.getListEtudiants();
	}
	

	/**
	 * @see fr.unice.hmabwe.controleur.dao.DaoFiliere#getMoyenne()
	 */
	
	
	public double getMoyenne(Filiere filiere) {
		Collection<Etudiant> listeEtudiant = getEtudiantsInscrits(filiere);
		double somme = 0;
		for (Etudiant etudiant : listeEtudiant) {
			HashMap<Integer, Integer> coeffs = new HashMap<Integer, Integer>();
			EntityManager em = getEntityManager();
			Query q = em.createQuery("select c from Coefficient c where c.filiere = :id");
			Collection<Inscription> l_inscr = etudiant.getInscriptions();
			q.setParameter("id", filiere.getId());
			double somme_notes = 0;
			int somme_coef = 0;
			List<Coefficient> l_coeffs = q.getResultList();
			for (Coefficient coefficient : l_coeffs) {
				coeffs.put(coefficient.getCours().getId(), coefficient.getCoefficient());
			}
			for (Inscription inscription : l_inscr) {
				somme_notes += inscription.getMoyenne() * coeffs.get(inscription.getCours().getId());
				somme_coef += coeffs.get(inscription.getCours().getId());
			}
			somme += somme_notes / somme_coef;
		}
		
		return somme / listeEtudiant.size();
		
	}
	

}
