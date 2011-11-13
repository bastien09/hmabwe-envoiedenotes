package fr.unice.hmabwe.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import fr.unice.hmabwe.controleur.bd.Connexion;
import fr.unice.hmabwe.controleur.bd.config.ConfigConnexion;
import fr.unice.hmabwe.controleur.bd.config.ConfigConnexion.TypePersistance;
import fr.unice.hmabwe.controleur.bd.dao.DaoCours;
import fr.unice.hmabwe.controleur.bd.dao.DaoEtudiant;
import fr.unice.hmabwe.controleur.bd.dao.DaoException;
import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;
import fr.unice.hmabwe.modele.Etudiant;
import fr.unice.hmabwe.modele.Inscription;

public class JpaDaoEtudiantTest {

	static DaoFabrique df;
	static Connexion conn;
	static DaoCours daoCours;
	static DaoEtudiant daoEtudiant;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//TestEnvironment.setup();
		
				ConfigConnexion.setTypePersistance(TypePersistance.JPA);
				df = DaoFabrique.getDaoFabrique();
				conn = df.getConnexion();
				daoCours = df.getDaoCours();
				daoEtudiant = df.getDaoEtudiant();
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
	public void testEtaitInscrit() {
		boolean isInscrit=false;
		try{
			conn.beginTransaction();
			isInscrit = daoEtudiant.etaitInscrit("nc1234", "POO", 2010);
			conn.commitTransaction();
		}catch(DaoException e){
			try{
				conn.rollbackTransaction();
			}catch(DaoException ex){
				ex.printStackTrace();
			}
		}
		assertTrue(isInscrit);
	}

	@Test
	public void testInscriptionEtu() {
		Inscription ins = null;
		try{
			conn.beginTransaction();
			ins = daoEtudiant.inscriptionEtu("nc1234", "POO", 2010);
			conn.commitTransaction();
		}catch(DaoException e){
			try{
				conn.rollbackTransaction();
			}catch(DaoException ex){
				ex.printStackTrace();
			}
		}
		assertNotNull(ins);
	}

	@Test
	public void testFindByNumeroEtudiant() {
		Etudiant etud = null;
		try{
			conn.beginTransaction();
			etud = daoEtudiant.findByNumeroEtudiant("nc1234");
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
	public void testGetEtudiantByNameString() {
		List<Etudiant> etud = null;
		try{
			conn.beginTransaction();
			etud = daoEtudiant.getEtudiantByName("Nuon");
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
	public void testGetEtudiantByNameStringString() {
		List<Etudiant> etud = null;
		try{
			conn.beginTransaction();
			etud = daoEtudiant.getEtudiantByName("Nuon", "Channdarong");
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

	@Ignore
	public void testGetMoyenneString() {
		double moyenne = 0;
		try{
			conn.beginTransaction();
			moyenne = daoEtudiant.getMoyenne("nc1234", 2010);
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
	public void testGetMoyenneStringInt() {
		double moyenne = 0;
		try{
			conn.beginTransaction();
			moyenne = daoEtudiant.getMoyenne("nc1234", 2010);
			conn.commitTransaction();
		}catch(DaoException e){
			try{
				conn.rollbackTransaction();
			}catch(DaoException ex){
				ex.printStackTrace();
			}
		}
		
		//System.out.println(moyenne);
		//assertTrue(moyenne == 12.5);
		fail("Don't know how to calculate");
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
