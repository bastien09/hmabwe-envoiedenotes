/**
 * 
 */
package fr.unice.hmabwe.controleur.bd.dao.jpa;

import javax.persistence.Persistence;
import fr.unice.hmabwe.controleur.bd.Connexion;
import fr.unice.hmabwe.controleur.bd.JpaConnexion;
import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;
import fr.unice.hmabwe.controleur.bd.dao.DaoGenerique;
import fr.unice.hmabwe.modele.Coefficient;
import fr.unice.hmabwe.modele.Cours;
import fr.unice.hmabwe.modele.Enseignant;
import fr.unice.hmabwe.modele.Etudiant;
import fr.unice.hmabwe.modele.Filiere;
import fr.unice.hmabwe.modele.Inscription;

/**
 * @author Paraita Wohler
 * 
 * JpaDaoFabrique est une DaoFabrique spécialisé dans
 * le type de persistance JPA. Elle permet de récupérer
 * les DAO pouvant gérer les différentes entités qu'on va utiliser
 *
 */
public class JpaDaoFabrique extends DaoFabrique {
	
	private JpaConnexion conn = null;
	
	public JpaDaoFabrique() {
		super();
		// TODO ici c'est lié avec l'IHM configuration de la persistance
		this.conn = new JpaConnexion(Persistence.createEntityManagerFactory("persistance").createEntityManager());
	}

	@Override
	public DaoGenerique<Enseignant, Integer> getDaoEnseignant() {
		JpaDaoEnseignant enseignant = new JpaDaoEnseignant();
		enseignant.setEntityManager(conn.getEntityManager());
		return enseignant;
	}

	@Override
	public DaoGenerique<Etudiant, Integer> getDaoEtudiant() {
		JpaDaoEtudiant etudiant = new JpaDaoEtudiant();
		etudiant.setEntityManager(conn.getEntityManager());
		return etudiant;
	}

	@Override
	public DaoGenerique<Cours, Integer> getDaoCours() {
		JpaDaoCours cours = new JpaDaoCours();
		cours.setEntityManager(conn.getEntityManager());
		return cours;
	}

	@Override
	public DaoGenerique<Inscription, Integer> getDaoInscription() {
		JpaDaoInscription inscription = new JpaDaoInscription();
		inscription.setEntityManager(conn.getEntityManager());
		return inscription;
	}

	@Override
	public DaoGenerique<Filiere, Integer> getDaoFilliere() {
		JpaDaoFiliere filliere = new JpaDaoFiliere();
		filliere.setEntityManager(conn.getEntityManager());
		return filliere;
	}

	@Override
	public DaoGenerique<Coefficient, Integer> getDaoCoefficient() {
		JpaDaoCoefficient coefficient = new JpaDaoCoefficient();
		coefficient.setEntityManager(conn.getEntityManager());
		return coefficient;
	}

	@Override
	public Connexion getConnexion() {
		return (Connexion)conn;
	}

}
