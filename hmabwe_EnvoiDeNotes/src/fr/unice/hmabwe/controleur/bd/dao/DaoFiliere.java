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
	 * cette methode renvoie tout les etudiants inscrient à cette fillière
	 * 
	 */
	public Collection<Etudiant> getEtudiantsInscrits(Filiere filiere);
	
	/**
	 * cette methode renvoie la moyenne d'une filière donnée
	 * 
	 * @param nomFiliere le nom de la filière dont on veut la moyenne
	 * @return la moyenne de la filière donnée
	 */
	public double getMoyenne(Filiere filiere);
	

}
