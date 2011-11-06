/**
 * 
 */
package fr.unice.hmabwe.controleur.bd.dao;

import java.util.Collection;

import fr.unice.hmabwe.modele.Cours;
import fr.unice.hmabwe.modele.Etudiant;

/**
 * @author Engilberge swan
 *
 * DaoEtudiant est une interface non parametrée qui sera implementé 
 * par les daoetudiant 
 *
 */


public interface DaoCours extends DaoGenerique<Cours, Integer> {
	
	/**
	 * renvoie la moyenne de tout les etudiants
	 * qui on suivit ce cours une année donnée
	 * 
	 * @param annee l'année a laquelle on cherche les moyennes
	 */
	
	public double getMoyenne(int annee); 
	
	/**
	 * renvoie tout les etudiant inscrit à ce cour
	 * toutes années confondue
	 */
	
	public Collection<Etudiant> getEtudiantsInstcrits();

}
