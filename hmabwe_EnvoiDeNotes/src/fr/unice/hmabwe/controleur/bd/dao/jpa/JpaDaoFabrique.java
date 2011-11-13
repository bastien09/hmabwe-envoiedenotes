/**
 * 
 */
package fr.unice.hmabwe.controleur.bd.dao.jpa;

import javax.persistence.Persistence;
import fr.unice.hmabwe.controleur.bd.Connexion;
import fr.unice.hmabwe.controleur.bd.JpaConnexion;
import fr.unice.hmabwe.controleur.bd.dao.*;

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
		this.conn = new JpaConnexion(Persistence.createEntityManagerFactory("persistance").createEntityManager());
	}

	@Override
	public DaoEnseignant getDaoEnseignant() {
		JpaDaoEnseignant enseignant = new JpaDaoEnseignant();
		enseignant.setEntityManager(conn.getEntityManager());
		return enseignant;
	}

	@Override
	public DaoEtudiant getDaoEtudiant() {
		JpaDaoEtudiant etudiant = new JpaDaoEtudiant();
		etudiant.setEntityManager(conn.getEntityManager());
		return etudiant;
	}

	@Override
	public DaoCours getDaoCours() {
		JpaDaoCours cours = new JpaDaoCours();
		cours.setEntityManager(conn.getEntityManager());
		return cours;
	}

	@Override
	public DaoInscription getDaoInscription() {
		JpaDaoInscription inscription = new JpaDaoInscription();
		inscription.setEntityManager(conn.getEntityManager());
		return inscription;
	}

	@Override
	public DaoFiliere getDaoFiliere() {
		JpaDaoFiliere filiere = new JpaDaoFiliere();
		filiere.setEntityManager(conn.getEntityManager());
		return filiere;
	}

	@Override
	public DaoCoefficient getDaoCoefficient() {
		JpaDaoCoefficient coefficient = new JpaDaoCoefficient();
		coefficient.setEntityManager(conn.getEntityManager());
		return coefficient;
	}

	@Override
	public Connexion getConnexion() {
		return (Connexion)conn;
	}

}
