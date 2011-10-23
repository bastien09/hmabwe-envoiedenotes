/**
 * 
 */
package fr.unice.hmabwe.controleur.bd.dao;

import fr.unice.hmabwe.modele.*;
import fr.unice.hmabwe.controleur.bd.dao.jpa.JpaDaoFabrique;

/**
 * @author Paraita Wohler
 * 
 * TODO commentire présentation de la classe
 *
 */
public abstract class DaoFabrique {

	public enum TypeFabrique { JDBC, JPA };
	
	private static TypeFabrique typeFabrique = null;
	
	public abstract DaoGenerique<Enseignant, Integer> getDaoEnseignant();
	
	public abstract DaoGenerique<Etudiant, Integer> getDaoEtudiant();
	
	public abstract DaoGenerique<Cours, Integer> getDaoCours();
	
	public abstract DaoGenerique<Inscription, Integer> getDaoInscription();
	
	public abstract DaoGenerique<Filliere, Integer> getDaoFilliere();
	
	public abstract DaoGenerique<Coefficient, Integer> getDaoCoefficient();
	
	public static void setTypeDao(TypeFabrique tf) {
		typeFabrique = tf;
	}
	
	public static DaoFabrique getDaoFabrique() {
		switch(typeFabrique) {
			case JDBC:
				//return new JdbcDaoFabrique();
			case JPA:
				return new JpaDaoFabrique();
			default:
				return null; // oups...
		}
	}
}
