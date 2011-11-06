package fr.unice.hmabwe.controleur.bd.dao.jpa;

import java.util.Collection;

import fr.unice.hmabwe.controleur.bd.dao.DaoCours;
import fr.unice.hmabwe.modele.Cours;
import fr.unice.hmabwe.modele.Etudiant;

/**
 * 
 * @author Paraita Wohler
 *
 * JpaDaoCours permet de faire abstraction de la couche persistance JPA
 * tout en rendant ce DAO suffisament générique.
 * Cette classe fourni les méthodes spécifiques au code métier de l'application
 *
 */
public class JpaDaoCours extends JpaDaoGenerique<Cours, Integer> implements
		DaoCours {
	
	/**
	 * @see fr.unice.hmabwe.controleur.dao.DaoCours#getMoyenne()
	 */
	public double getMoyenne(int annee){
		return 0;
	}
	
	/**
	 * @see fr.unice.hmabwe.controleur.dao.DaoCours#getEtudiantsInscrits()
	 */
	public Collection<Etudiant> getEtudiantsInstcrits(){
		return null;
	}
}
