package fr.unice.hmabwe.controleur.bd.dao.jpa;

import java.util.Collection;

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
	//TODO code a finir
	

	/**
	 * @see fr.unice.hmabwe.controleur.dao.DaoFiliere#getEtudiantsInscrit()
	 */
	public Collection<Etudiant> getEtudiantsInstcrits(){
	return null;
	}
	

	/**
	 * @see fr.unice.hmabwe.controleur.dao.DaoFiliere#getAllFilieres()
	 */
	public Collection<Filiere> getAllFilieres(){
		return null;
	}
	

	/**
	 * @see fr.unice.hmabwe.controleur.dao.DaoFiliere#getMoyenne()
	 */
	
	public double getMoyenne(){
		return 0;
	}
}
