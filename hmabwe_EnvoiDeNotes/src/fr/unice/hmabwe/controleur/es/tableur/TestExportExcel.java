package fr.unice.hmabwe.controleur.es.tableur;

import java.util.HashMap;
import java.util.Map.Entry;

import fr.unice.hmabwe.modele.Etudiant;
import fr.unice.hmabwe.modele.Filliere;

public class TestExportExcel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ExportExcel eex = new ExportExcel();
		
		HashMap<Etudiant, String> ed = new HashMap<Etudiant, String>();
		
		Filliere f1 = new Filliere("M1 info");
		Etudiant e1 = new Etudiant("hi911803", "hassala", "iliasse", "hi911803@gmail.com","L3I",f1);
		Etudiant e2 = new Etudiant("av702264", "actis", "virginie", "vactis@polytech.unice.fr","SI3",f1);
		
		ed.put(e1, "12");
		ed.put(e2, "20");
		
		eex.createXls(ed);

	}

}
