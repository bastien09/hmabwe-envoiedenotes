package fr.unice.hmabwe.test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;

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
import fr.unice.hmabwe.modele.Cours;
import fr.unice.hmabwe.modele.Etudiant;

public class JpaDaoCoursTest {

	static DaoFabrique df;
	static Connexion conn;
	static DaoCours daoCours;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//TestEnvironment.setup();
		
		ConfigConnexion.setTypePersistance(TypePersistance.JPA);
		df = DaoFabrique.getDaoFabrique();
		conn = df.getConnexion();
		daoCours = df.getDaoCours();
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
	public void testGetMoyenne() {
		double moyenne = 0.0;
		try{
			conn.beginTransaction();
			
			moyenne = daoCours.getMoyenne("POO", 2010);
			
			conn.commitTransaction();
		}catch(DaoException e){
			try{
				conn.rollbackTransaction();
			}catch(DaoException ex){
				ex.printStackTrace();
			}
		}
		assertTrue(moyenne == 15);
	}

	@Test
	public void testGetEtudiantsInstcrits() {
		Collection<Etudiant> etud = null;
		try{
			conn.beginTransaction();
			
			List<Cours> c = daoCours.getCoursByName("POO");
			if(c.size()>0){
				etud = daoCours.getEtudiantsInstcrits(c.get(0));
			}
			
			conn.commitTransaction();
		}catch(DaoException e){
			try{
				conn.rollbackTransaction();
			}catch(DaoException ex){
				ex.printStackTrace();
			}
		}
		assertNotNull(etud);
	}

	@Test
	public void testGetCoursByName() {
		List<Cours> c=null;
		try{
			conn.beginTransaction();
			
			c = daoCours.getCoursByName("POO");
			
			conn.commitTransaction();
		}catch(DaoException e){
			try{
				conn.rollbackTransaction();
			}catch(DaoException ex){
				ex.printStackTrace();
			}
		}
		assertNotNull(c);
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
