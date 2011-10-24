package fr.unice.hmabwe.controleur.es.tableur;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

import fr.unice.hmabwe.modele.Etudiant;

public class TestImportExcel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ImportExcel ie = new ImportExcel("/home/amazigh/Desktop/moyenneEtu.xls", "a", "b", "c", "d", "e", "f","g");
		
		HashMap<Etudiant, String> ed = ie.lectureListEtudiants();
		
		for (Entry<Etudiant, String> entry : ed.entrySet())
		{
		    System.out.println(entry.getKey().getNom()+ " " + entry.getKey().getPrenom() + "/" + entry.getValue());
		}




	}

}
