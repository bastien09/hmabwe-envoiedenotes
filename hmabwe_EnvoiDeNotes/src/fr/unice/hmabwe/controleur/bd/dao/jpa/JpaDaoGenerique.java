/**
 * 
 */
package fr.unice.hmabwe.controleur.bd.dao.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

import fr.unice.hmabwe.controleur.bd.dao.DaoException;
import fr.unice.hmabwe.controleur.bd.dao.DaoGenerique;

/**
 * @author Paraita Wohler
 *
 * permet de factoriser le code nécessaire à un DAO dans le cadre d'une
 * persistance en utilisant JPA
 *
 */
public abstract class JpaDaoGenerique<T, ID extends Serializable> implements DaoGenerique<T, ID> {

	private Class<T> classeEntite;
	private EntityManager em;
	private final String query_findAll = "select e from :entite as e";
	
	public JpaDaoGenerique() {
		classeEntite = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void create(T objet) throws DaoException {
		try {
			if(em != null && em.isOpen())
				em.persist(objet);
			else
				throw new DaoException("probleme interne au DAO", 5);
		}
		catch(EntityExistsException eee) {
			// chainer l'exception avec une nouvelle DaoException
			throw new DaoException("l'entité existe déjà dans la base de données",
					eee);
		}
		/* objet n'est pas une entité */
		catch(IllegalArgumentException iae) {
			throw new DaoException("n'est pas une entité ou est une entité détachée", iae);
		}
		catch(TransactionRequiredException tre) {
			throw new DaoException("transaction nécessaire", tre, 4);
		}
	}

	public void update(T objet) throws DaoException {
		try {
			if(em != null && em.isOpen())
				em.merge(objet);
			else
				throw new DaoException("probleme interne au DAO", 5);
		}
		catch(IllegalArgumentException iae) {
			throw new DaoException("n'est pas une entité ou est une entité détachée", iae);
		}
		catch(TransactionRequiredException tre) {
			throw new DaoException("transaction nécessaire", tre, 4);
		}
	}

	public void delete(T objet) throws DaoException {
		try {
			if(em != null && em.isOpen())
				em.remove(objet);
			else
				throw new DaoException("probleme interne au DAO", 5);
		}
		catch(IllegalArgumentException iae) {
			throw new DaoException("n'est pas une entité ou est une entité détachée", iae);
		}
		catch(TransactionRequiredException tre) {
			throw new DaoException("transaction nécessaire", tre, 4);
		}
	}

	public T findById(ID id) throws DaoException {
		try {
			if(em != null && em.isOpen())
				return em.find(classeEntite, id);
			else
				throw new DaoException("probleme interne au DAO", 5);
		}
		catch(IllegalArgumentException iae) {
			throw new DaoException("n'est pas une entité ou est une entité détachée", iae);
		}
	}

	public Collection<T> findAll() throws DaoException {
		try {
			if(em != null && em.isOpen()) {
				Query q = em.createQuery(query_findAll);
				q.setParameter("entite", classeEntite.getName());
				List<T> res = (List<T>) q.getResultList();
				return res;
			}
			else {
				throw new DaoException("erreur interne  au DAO", 5);
			}
		}
		catch(IllegalArgumentException iae) {
			throw new DaoException("requete invalide (" +
									query_findAll +
									") ou nom de classe invalide (" +
									classeEntite.getName() +
									")", iae);
		}
	}
	
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	protected EntityManager getEntityManager() {
		if(em != null && em.isOpen()) {
			return em;
		}
		else {
			return null;
		}
	}
}
