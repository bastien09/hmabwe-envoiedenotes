package fr.unice.hmabwe.controleur.bd.dao;

import java.util.Collection;

import fr.unice.hmabwe.modele.Etudiant;
import fr.unice.hmabwe.modele.Filiere;

/**
 * 
 * @author Engilberge Swan
 */

public interface DaoFiliere extends DaoGenerique<Filiere, Integer> {
	
	/**
	 * cette methode renvoie toute les filières
	 * 
	 */
	public Collection<Filiere> getAllFilieres();
	
	/**
	 * cette methode renvoie tout les etudiants inscrient à cette fillière
	 * 
	 */
	
	public Collection<Etudiant> getEtudiantsInstcrits();
	
	/**
	 * cette methode renvoie la moyenne de cette filière
	 * 
	 */
	
	public double getMoyenne();
	

}
