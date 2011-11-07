package fr.unice.hmabwe.controleur.es.tableur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

import fr.unice.hmabwe.controleur.bd.Connexion;
import fr.unice.hmabwe.controleur.bd.dao.DaoEtudiant;
import fr.unice.hmabwe.controleur.bd.dao.DaoException;
import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;
import fr.unice.hmabwe.controleur.bd.dao.DaoInscription;
import fr.unice.hmabwe.controleur.bd.dao.jpa.JpaDaoEtudiant;
import fr.unice.hmabwe.controleur.bd.dao.jpa.JpaDaoInscription;
import fr.unice.hmabwe.modele.Cours;
import fr.unice.hmabwe.modele.Etudiant;
import fr.unice.hmabwe.modele.Filiere;
import fr.unice.hmabwe.modele.Inscription;

public class TestExportExcel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ExportExcel eex = new ExportExcel();
		Cours cours = new Cours("POO");

		int annee = 2010;
		
		DaoFabrique.setTypeDao(DaoFabrique.TypeFabrique.JPA);
		DaoFabrique df = DaoFabrique.getDaoFabrique();
		Connexion conn = df.getConnexion();
		
		DaoEtudiant etus = df.getDaoEtudiant();
		DaoInscription insc = df.getDaoInscription();
		
		try {
			conn.beginTransaction();
			HashMap<Etudiant, String> ed = null;

			ed = insc.listeInscrit(cours.getNom(), annee);
	
			eex.createXls(ed, cours, annee);
			
			conn.commitTransaction();
		}
		catch(DaoException e) {
			e.printStackTrace();
		}

	}

}