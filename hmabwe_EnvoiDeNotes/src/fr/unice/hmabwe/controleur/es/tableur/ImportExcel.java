package fr.unice.hmabwe.controleur.es.tableur;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import fr.unice.hmabwe.controleur.bd.dao.DaoException;
import fr.unice.hmabwe.controleur.bd.dao.jpa.JpaDaoCoefficient;
import fr.unice.hmabwe.controleur.bd.dao.jpa.JpaDaoCours;
import fr.unice.hmabwe.controleur.bd.dao.jpa.JpaDaoEtudiant;
import fr.unice.hmabwe.controleur.bd.dao.jpa.JpaDaoFiliere;
import fr.unice.hmabwe.controleur.bd.dao.jpa.JpaDaoInscription;
import fr.unice.hmabwe.modele.Coefficient;
import fr.unice.hmabwe.modele.Cours;
import fr.unice.hmabwe.modele.Etudiant;
import fr.unice.hmabwe.modele.Filiere;
import fr.unice.hmabwe.modele.Inscription;

public class ImportExcel {

	private String inputFile;
	private String numEtu;
	private String nom;
	private String prenom;
	private String mail;
	private String origine;
	private String moyenne;
	private String filiere;
	private String annee;
	private String cours;

	public ImportExcel(String inputFile, String numEtu, String nom,
			String prenom, String mail, String origine, String moyenne,
			String filiere, String annee, String cours) {

		this.inputFile = inputFile;
		this.numEtu = numEtu;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.origine = origine;
		this.moyenne = moyenne;
		this.filiere = filiere;
		this.annee = annee;
		this.cours = cours;
	}

	public HashMap<Etudiant, Double> lectureListEtudiants() {

		File inputWorkbook = new File(inputFile);

		if (!inputWorkbook.exists()) {
			return null;
		}

		Workbook w;

		HashMap<Etudiant, Double> moyennesEtudiants = new HashMap<Etudiant, Double>();

		try {
			try {
				w = Workbook.getWorkbook(inputWorkbook);
			} catch (IOException e) {
				return null;
			}

			// Get the first sheet
			Sheet sheet = w.getSheet(0);

			int c_numEtu = getColonneByString(numEtu);
			int c_nom = getColonneByString(nom);
			int c_prenom = getColonneByString(prenom);
			int c_mail = getColonneByString(mail);
			int c_origine = getColonneByString(origine);
			int c_moyenne = getColonneByString(moyenne);
			int c_filiere = getColonneByString(filiere);
			int c_annee = getColonneByString("annee");
			int c_cours = getColonneByString("cours");
			System.out.println(c_numEtu + c_nom + c_prenom + c_mail + c_moyenne
					+ c_filiere);
			if (c_numEtu == -1 || c_nom == -1 || c_prenom == -1 || c_mail == -1
					|| c_filiere == -1 || c_annee == -1 || c_cours == -1)
				return null;

			Cell[] numsEtu = sheet.getColumn(c_numEtu);
			Cell[] noms = sheet.getColumn(c_nom);
			Cell[] prenoms = sheet.getColumn(c_prenom);
			Cell[] mails = sheet.getColumn(c_mail);
			Cell[] origines = sheet.getColumn(c_origine);
			Cell[] moyennes = sheet.getColumn(c_moyenne);
			Cell[] filieres = sheet.getColumn(c_filiere);
			Cell[] annee = sheet.getColumn(c_annee);
			Cell[] cours = sheet.getColumn(c_cours);

			Etudiant e;
			Filiere f;
			Cours c;
			Coefficient coef;
			Inscription inscription;
			int an = Integer.parseInt(annee[0].getContents());

			for (int i = 1; i < (numsEtu.length >= noms.length ? noms.length
					: numsEtu.length); i++) {
				try {
					// String moyenne = moyennes[i].getContents();
					Double moyenne = (double) Integer.parseInt(moyennes[i]
							.getContents());
					f = new Filiere(filieres[i].getContents());
					e = new Etudiant(numsEtu[i].getContents(),
							noms[i].getContents(), prenoms[i].getContents(),
							mails[i].getContents(), origines[i].getContents(),
							f);
					f.addEtudiant(e);
					c = new Cours(cours[i].getContents());
					coef = new Coefficient(c, f, 1);
					if (c_moyenne == -1) {
						inscription = new Inscription(e, c,
								Integer.parseInt(annee[i].getContents()));
					} else {
						inscription = new Inscription(e, c,
								Integer.parseInt(annee[i].getContents()),
								moyenne);
					}
					
					JpaDaoEtudiant jpaEtudiant = new JpaDaoEtudiant();
					JpaDaoCours jpaCours = new JpaDaoCours();
					JpaDaoFiliere jpaFiliere = new JpaDaoFiliere();
					JpaDaoCoefficient jpaCoef = new JpaDaoCoefficient();
					JpaDaoInscription jpaInscr = new JpaDaoInscription();
					
					boolean testCoef=false;
					boolean testEtu=false;
					
					try{
						jpaEtudiant.create(e);
					}
					catch(DaoException daexep){
						try {
							jpaEtudiant.update(e);
							testEtu =true;
						} catch (DaoException e1) {
							e1.printStackTrace();
						}
					}
					
					try{
						jpaCours.create(c);
						
					}
					catch(DaoException daexep){
						testCoef = true;
					}
					
					try{
						jpaFiliere.create(f);
						testCoef = false;
						
					}
					catch(DaoException daexep){
						testCoef = true;
					}
					
					if(testCoef == false){
						try{
							jpaCoef.create(coef);
							
						}
						catch(DaoException daexep){
							daexep.printStackTrace();
						}
					}
					
					if(testEtu == false){
						try{
							jpaInscr.create(inscription);
							
						}
						catch(DaoException daexep){
							daexep.printStackTrace();
						}
					}
					else {
						if(jpaEtudiant.etaitInscrit(numEtu, cours.toString(),an)){
							try {
								jpaInscr.update(inscription);
							} catch (DaoException e1) {
								e1.printStackTrace();
							}
						}
						else{
							try {
								jpaInscr.create(inscription);
							} catch (DaoException e1) {
								e1.printStackTrace();
							}
						}
						
					}
					
					moyennesEtudiants.put(e, moyenne);
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
				}
			}
		} catch (BiffException e) {
			e.printStackTrace();
		}
		return moyennesEtudiants;
	}

	private int getColonneByString(String c) {

		int num = -1;

		// le numéro de la colonne est déjà un numéro
		try {
			num = Integer.parseInt(c);
		} catch (NumberFormatException e) {
			num = -1;
			// le numéro est sous forme de lettre
		}
		if (num != -1)
			return num;

		String conv[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
				"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
				"w", "x", "y", "z" };

		// System.out.println("getColonne");

		for (int i = 0; i < 26; i++) {
			if (conv[i].equalsIgnoreCase(c)) {
				return i;
			}
		}

		int cpt = 25;
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < 26; j++) {
				++cpt;
				String s = conv[i] + conv[j];
				if (s.equalsIgnoreCase(c)) {
					return cpt;
				}
			}
		}
		return -1;
	}

}
