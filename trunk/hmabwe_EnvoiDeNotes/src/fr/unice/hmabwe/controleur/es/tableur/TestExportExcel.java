package fr.unice.hmabwe.controleur.es.tableur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

import fr.unice.hmabwe.controleur.bd.dao.DaoException;
import fr.unice.hmabwe.controleur.bd.dao.jpa.JpaDaoEtudiant;
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
		
		JpaDaoEtudiant etus = new JpaDaoEtudiant();
		
		HashMap<Etudiant, String> ed = null;
		/*
		try {
			
			/*appeler une méthode qui renverra une HashMap<Etudiant, String> qui devra contenir :
			 * - les étudiants inscrits au cours c l'année a (c et a seront passés en paramètres);
			 * - leurs moyenne pour cette inscription sous forme de String.*/
			
			//ed = /*******/
		/*} catch (DaoException e) {
			e.printStackTrace();
		}*/
		
		eex.createXls(ed, cours, annee);

	}

}