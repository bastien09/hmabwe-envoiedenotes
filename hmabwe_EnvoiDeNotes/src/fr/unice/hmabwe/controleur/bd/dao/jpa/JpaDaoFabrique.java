/**
 * 
 */
package fr.unice.hmabwe.controleur.bd.dao.jpa;

import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;
import fr.unice.hmabwe.controleur.bd.dao.DaoGenerique;
import fr.unice.hmabwe.modele.Coefficient;
import fr.unice.hmabwe.modele.Cours;
import fr.unice.hmabwe.modele.Enseignant;
import fr.unice.hmabwe.modele.Etudiant;
import fr.unice.hmabwe.modele.Filliere;
import fr.unice.hmabwe.modele.Inscription;

/**
 * @author Paraita Wohler
 * 
 * TODO commentaire présentation de la classe
 *
 */
public class JpaDaoFabrique extends DaoFabrique {

	@Override
	public DaoGenerique<Enseignant, Integer> getDaoEnseignant() {
		return new JpaDaoEnseignant();
	}

	@Override
	public DaoGenerique<Etudiant, Integer> getDaoEtudiant() {
		return new JpaDaoEtudiant();
	}

	@Override
	public DaoGenerique<Cours, Integer> getDaoCours() {
		return new JpaDaoCours();
	}

	@Override
	public DaoGenerique<Inscription, Integer> getDaoInscription() {
		return new JpaDaoInscription();
	}

	@Override
	public DaoGenerique<Filliere, Integer> getDaoFilliere() {
		return new JpaDaoFilliere();
	}

	@Override
	public DaoGenerique<Coefficient, Integer> getDaoCoefficient() {
		return new JpaDaoCoefficient();
	}

}
