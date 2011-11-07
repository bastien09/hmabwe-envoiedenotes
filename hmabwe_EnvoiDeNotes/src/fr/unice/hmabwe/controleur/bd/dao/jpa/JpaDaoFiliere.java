package fr.unice.hmabwe.controleur.bd.dao.jpa;

import java.util.Collection;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import fr.unice.hmabwe.controleur.bd.dao.DaoFiliere;
import fr.unice.hmabwe.modele.Etudiant;
import fr.unice.hmabwe.modele.Filiere;

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
	 * le code métier va ici
	 */
	

	/**
	 * @see fr.unice.hmabwe.controleur.dao.DaoFiliere#getEtudiantsInscrit()
	 */
	public Collection<Etudiant> getEtudiantsInstcrits(){
	return null;
	}
	

	/**
	 * @see fr.unice.hmabwe.controleur.dao.DaoFiliere#getMoyenne()
	 */
	
	//TODO la moyenne d'une filiere dépend de la moyenne de chaque étudiant de cette filiere
	public double getMoyenne(String nomFiliere) {
		return 0;
	}
}
