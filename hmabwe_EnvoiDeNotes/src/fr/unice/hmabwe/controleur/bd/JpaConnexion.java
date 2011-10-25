/**
 * 
 */
package fr.unice.hmabwe.controleur.bd;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;


import fr.unice.hmabwe.controleur.bd.dao.DaoException;

/**
 * @author Paraita Wohler
 * 
 * Implémentation d'une Connexion pour le type de persistance JPA
 * 
 * TODO on fait quoi avec les exceptions pas contrôlées ?
 *
 */
public class JpaConnexion implements Connexion {

	private EntityManager em = null;
	private EntityTransaction tx = null;
	
	public JpaConnexion(EntityManager em) {
		this.em = em;
		this.tx = em.getTransaction();
	}
	
	
	/**
	 * Ouvrir une connexion en JPA en fait c'est juste demander un nouveau EntityManager
	 * 
	 * @see fr.unice.hmabwe.controleur.bd.Connexion#ouvrir()
	 * 
	 */
	@Override
	public void ouvrir() throws DaoException {
		// TODO ici c'est lié avec l'IHM configuration de la persistance
		this.em = Persistence.createEntityManagerFactory("mapersistance").createEntityManager();
	}

	/**
	 * @see fr.unice.hmabwe.controleur.bd.Connexion#fermer()
	 */
	@Override
	public void fermer() throws DaoException {
		tx = null;
		if(em != null) {
			em.close();
			em = null;
		}
	}

	/**
	 * @see fr.unice.hmabwe.controleur.bd.Connexion#estOuverte()
	 */
	@Override
	public boolean estOuverte() {
		return em.isOpen();
	}

	/**
	 * @see fr.unice.hmabwe.controleur.bd.Connexion#transactionEnCours()
	 */
	@Override
	public boolean transactionEnCours() {
		return tx.isActive();
	}

	/**
	 * @see fr.unice.hmabwe.controleur.bd.Connexion#beginTransaction()
	 */
	@Override
	public void beginTransaction() throws DaoException {
		if(tx.isActive()) {
			throw new DaoException("la transaction est déjà active !");
		}
		else {
			tx.begin();
		}
	}

	/**
	 * @see fr.unice.hmabwe.controleur.bd.Connexion#commitTransaction()
	 */
	@Override
	public void commitTransaction() throws DaoException {
		try {
			tx.commit();
		}
		catch(RollbackException e) {
			throw new DaoException("erreur au commit", e);
		}

	}

	/**
	 * @see fr.unice.hmabwe.controleur.bd.Connexion#rollbackTransaction()
	 */
	@Override
	public void rollbackTransaction() throws DaoException {
		try {
			tx.rollback();
		}
		catch(PersistenceException e) {
			throw new DaoException("le rollback a échoué", e);
		}

	}
	
	public EntityManager getEntityManager() {
		return em;
	}

}
