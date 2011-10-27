/**
 * 
 */
package fr.unice.hmabwe.controleur.bd.dao;

import fr.unice.hmabwe.controleur.bd.Connexion;
import fr.unice.hmabwe.modele.*;

/**
 * @author Paraita Wohler
 * 
 * <b>ATTENTION</b> cette classe est en cours d'écrire,
 * elle ne fonctionne surement pas encore !
 * 
 * Cette classe permet de tester le code des DAO
 * en particulier pour se faire une idée de comment
 * l'application doit utiliser les DAO et les objets métier
 * (les objets métier sont des entités en fait mais chut ne
 * le dites à personne !)
 *
 */
public class TestDao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// TODO tester les DAO
		
		/* je renseigne la fabrique quel type de persistance on va utiliser */
		DaoFabrique.setTypeDao(DaoFabrique.TypeFabrique.JPA);
		
		/* je récupère une fabrique spécifique à JPA */
		DaoFabrique df = DaoFabrique.getDaoFabrique();
		
		/* je récupère la connexion qui va me permettre:
		 * - d'ouvrir/fermer une connexion
		 * - débuter/commiter une transaction
		 * - de savoir l'état de la connexion/transaction
		 */
		Connexion conn = df.getConnexion();
		
		/* je récupère quelques DAO */
		DaoGenerique<Enseignant, Integer> daoEnseignant = df.getDaoEnseignant();
		//DaoGenerique<Cours, Integer> daoCours = df.getDaoCours();
		
		/* je crée quelques objets */
		Enseignant ens1 = new Enseignant("Grin", "Mr", "grin@unice.fr");
		//Cours cours1 = new Cours("Persistance des objets", ens1);
		
		/* on rend persistant ces objets */
		try {
			conn.beginTransaction();
			daoEnseignant.create(ens1);
			//daoCours.create(cours1);
			conn.commitTransaction();
		}
		catch(DaoException e) {
			try {
				conn.rollbackTransaction();
			}
			catch(DaoException ee) {
				ee.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			if(conn.estOuverte()) {
				try {
					conn.fermer();
				}
				catch(DaoException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
