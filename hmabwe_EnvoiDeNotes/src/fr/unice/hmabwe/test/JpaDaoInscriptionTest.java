package fr.unice.hmabwe.test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import fr.unice.hmabwe.controleur.bd.Connexion;
import fr.unice.hmabwe.controleur.bd.config.ConfigConnexion;
import fr.unice.hmabwe.controleur.bd.config.ConfigConnexion.TypePersistance;
import fr.unice.hmabwe.controleur.bd.dao.DaoCours;
import fr.unice.hmabwe.controleur.bd.dao.DaoException;
import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;
import fr.unice.hmabwe.controleur.bd.dao.DaoInscription;
import fr.unice.hmabwe.modele.Etudiant;

public class JpaDaoInscriptionTest {

	static DaoFabrique df;
	static Connexion conn;
	static DaoCours daoCours;
	static DaoInscription daoInscription;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//TestEnvironment.setup();
		
				ConfigConnexion.setTypePersistance(TypePersistance.JPA);
				df = DaoFabrique.getDaoFabrique();
				conn = df.getConnexion();
				daoCours = df.getDaoCours();
				daoInscription = df.getDaoInscription();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//close the connection when the tests finish
		try{
			conn.fermer();
		}catch(DaoException e){
			e.printStackTrace();
		}
	}

	@Test
	public void testListeInscrit() {
		HashMap<Etudiant, String> list_inscrit = null;
		try{
			conn.beginTransaction();
			list_inscrit = daoInscription.listeInscrit("POO", 2010);
			conn.commitTransaction();
		}catch(DaoException e){
			try{
				conn.rollbackTransaction();
			}catch(DaoException ex){
				ex.printStackTrace();
			}
		}
		assertNotNull(list_inscrit);
	}

	@Ignore
	public void testCreate() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testFindAll() {
		fail("Not yet implemented");
	}

}
