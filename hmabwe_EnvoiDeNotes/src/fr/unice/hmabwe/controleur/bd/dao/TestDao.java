/**
 * 
 */
package fr.unice.hmabwe.controleur.bd.dao;

import java.util.Collection;

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

	public static void remplissage() {

		/* je renseigne la classe DaoFabrique qu'on va utiliser le
		 * type de persistance JPA
		 */
		DaoFabrique.setTypeDao(DaoFabrique.TypeFabrique.JPA);

		/* je demande une fabrique */
		DaoFabrique df = DaoFabrique.getDaoFabrique();

		/* je récupère la connexion qui va me permettre:
		 * - d'ouvrir/fermer une connexion
		 * - débuter/commiter une transaction
		 * - de savoir l'état de la connexion/transaction
		 */
		Connexion conn = df.getConnexion();

		/* je récupère quelques DAO */
		DaoEnseignant daoEnseignant = df.getDaoEnseignant();
		DaoEtudiant daoEtudiant = df.getDaoEtudiant();
		DaoCours daoCours = df.getDaoCours();
		DaoCoefficient daoCoefficient = df.getDaoCoefficient();
		DaoFiliere daoFiliere = df.getDaoFiliere();
		DaoInscription daoInscription = df.getDaoInscription();

		/* je crée quelques objets métier */
		Enseignant ens1 = new Enseignant("Grin", "Richard", "grin@unice.fr");
		Cours cours1 = new Cours("POO", ens1);
		Filiere filiere1 = new Filiere("M1 Info", ens1);
		Coefficient coef1 = new Coefficient(cours1, filiere1, 2);
		Etudiant etu1 = new Etudiant("wp803469", "Wohler", "Paraita", "wp803469@etu.unice.fr", "L3I", filiere1);
		Etudiant etu2 = new Etudiant("hi1234", "Hassala", "Iliasse", "hassala.iliasse@etu.unice.fr", "L3I", filiere1);
		Etudiant etu3 = new Etudiant("ba1234", "Auda", "Bastien", "bastien26990@yahoo.fr", "L3I", filiere1);
		Etudiant etu4 = new Etudiant("mm1234", "M'rah", "Mehdi", "m-rah-mehdi@etu.unice.fr", "L3I", filiere1);
		Etudiant etu5 = new Etudiant("es1234", "Engilberge", "Swan", "engilberge-swan@etu.unice.fr", "L3I", filiere1);
		Etudiant etu6 = new Etudiant("nc1234", "Nuon", "Channdarong", "nuonchanndarong@gmail.com", "???", filiere1);
		Inscription inscr1 = new Inscription(etu1, cours1, 2010);

		/* on rend persistant ces objets */
		try {
			conn.beginTransaction();
			daoEnseignant.create(ens1);
			daoCours.create(cours1);
			daoFiliere.create(filiere1);
			daoCoefficient.create(coef1);
			daoEtudiant.create(etu1);
			daoEtudiant.create(etu2);
			daoEtudiant.create(etu3);
			daoEtudiant.create(etu4);
			daoEtudiant.create(etu5);
			daoEtudiant.create(etu6);
			daoInscription.create(inscr1);
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
		System.out.println("############################### Données crées dans la BDD !");
	}

	public static void recupererDonnees() {
		
		DaoFabrique.setTypeDao(DaoFabrique.TypeFabrique.JPA);
		DaoFabrique df = DaoFabrique.getDaoFabrique();
		Connexion conn = df.getConnexion();
		DaoEtudiant daoEtudiant = df.getDaoEtudiant();
		DaoEnseignant daoEnseignant = df.getDaoEnseignant();
		Etudiant etu1 = null;

		try {
			conn.beginTransaction();
			
			
			System.out.println("Tout les étudiants de la BDD:");
			Collection<Etudiant> listeEtu = daoEtudiant.findAll();
			for (Etudiant etudiant : listeEtu) {
				System.out.println("#######################################################################");
				System.out.println("#################################### " + etudiant.getNom() + " " + etudiant.getPrenom());
				System.out.println("#################################### mail: " + etudiant.getMail());
				System.out.println("#######################################################################");
			}
			
			System.out.println("Tout les enseignants de la BDD:");
			
			Collection<Enseignant> listeEnseign = daoEnseignant.findAll();
			for (Enseignant enseignant : listeEnseign) {
				System.out.println("#######################################################################");
				System.out.println("#################################### " + enseignant.getNom() + " " + enseignant.getPrenom());
				System.out.println("#################################### mail: " + enseignant.getMail());
				System.out.println("#######################################################################");
			}
			
			
			System.out.println("Est ce que 'wp803469' à suivi POO en 2010 ? " + daoEtudiant.etaitInscrit("wp803469", "POO", 2010));
			
			String matricule = "wp803469";
			etu1 = daoEtudiant.findByNumeroEtudiant(matricule);
			if (etu1 == null) {
				System.out.println("L'étudiant de matricule " + matricule + " n'est pas dans la base de données !");
			}
			else {
				System.out.println("L'étudiant de matricule " + matricule + " s'apelle " + etu1.getPrenom() + " " + etu1.getNom());
			}
			
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


	public static void main(String[] args) {

		// TODO tester les DAO
		//remplissage();
		recupererDonnees();


	}
}
