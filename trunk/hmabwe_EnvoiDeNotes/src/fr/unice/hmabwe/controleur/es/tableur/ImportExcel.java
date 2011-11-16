package fr.unice.hmabwe.controleur.es.tableur;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import fr.unice.hmabwe.controleur.bd.Connexion;
import fr.unice.hmabwe.controleur.bd.config.ConfigConnexion;
import fr.unice.hmabwe.controleur.bd.config.ConfigConnexion.TypePersistance;
import fr.unice.hmabwe.controleur.bd.dao.DaoCoefficient;
import fr.unice.hmabwe.controleur.bd.dao.DaoCours;
import fr.unice.hmabwe.controleur.bd.dao.DaoEtudiant;
import fr.unice.hmabwe.controleur.bd.dao.DaoException;
import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;
import fr.unice.hmabwe.controleur.bd.dao.DaoFiliere;
import fr.unice.hmabwe.controleur.bd.dao.DaoInscription;
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
			int c_annee = getColonneByString(annee);
			int c_cours = getColonneByString(cours);

			if (c_numEtu == -1 || c_nom == -1 || c_prenom == -1 || c_mail == -1
					|| c_origine == -1 || c_moyenne == -1 || c_filiere == -1
					|| c_annee == -1 || c_cours == -1)
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

			Etudiant e = null;
			Filiere f = null;
			Cours c = null;
			Coefficient coef = null;
			Inscription inscription = null;

			int an = Integer.parseInt(annee[1].getContents());

			ConfigConnexion.setTypePersistance(TypePersistance.JPA);
			DaoFabrique df = DaoFabrique.getDaoFabrique();
			DaoCours daoCours = df.getDaoCours();
			DaoFiliere daoFiliere = df.getDaoFiliere();
			DaoEtudiant daoEtudiant = df.getDaoEtudiant();
			Connexion conn = df.getConnexion();
			conn.beginTransaction();

			for (int i = 1; i < (numsEtu.length >= noms.length ? noms.length
					: numsEtu.length); i++) {

				try {

					/* en premier les cours */
					Collection<Cours> col_cours = daoCours.findAll();
					int existe = -1;
					for (Cours coucours : col_cours) {
						if (coucours.getNom().compareTo(cours[i].getContents()) == 0) {
							existe = i;
						}
					}
					if (existe != -1) {
						System.out
								.println("le cours "
										+ cours[existe].getContents()
										+ " existe deja dans la base de données ! je n'ai pas a le recreer...");
					} else {
						System.out.print("le cours " + cours[i].getContents()
								+ " n'existe pas, il faut le créer:");
						c = new Cours(cours[i].getContents());
						daoCours.create(c);
						System.out.println("ok !");
					}

					/* ensuite les filieres */
					Collection<Filiere> col_filieres = daoFiliere.findAll();
					existe = -1;
					for (Filiere filiere : col_filieres) {
						if (filiere.getNom().compareTo(
								filieres[i].getContents()) == 0) {
							existe = i;
							f = filiere;
						}
					}
					if (existe != -1) {
						System.out
								.println("la filiere "
										+ filieres[existe].getContents()
										+ " existe deja dans la base de données ! je n'ai pas a la recreer...");
					} else {
						System.out.print("la filiere "
								+ filieres[i].getContents()
								+ " n'existe pas, il faut la créer:");
						f = new Filiere(filieres[i].getContents());
						daoFiliere.create(f);
						System.out.println("ok !");
					}

					e = daoEtudiant.findByNumeroEtudiant(numsEtu[i]
							.getContents());
					if (e == null) {
						/* ca veut dire que l'étudiant n'existe pas dans la BDD */
						System.out
								.println(prenoms[i].getContents()
										+ " "
										+ noms[i].getContents()
										+ " n'existe pas dans la base de données ! il faut le créer: ");
						e = new Etudiant(numsEtu[i].getContents(),
								noms[i].getContents(),
								prenoms[i].getContents(),
								mails[i].getContents(),
								origines[i].getContents(), f);
						daoEtudiant.create(e);
						System.out.print("ok !\n");
					} else {
						/*
						 * ca veut dire que l'étudiant existe déjà, il faut
						 * juste le mettre a jour
						 */
						System.out
								.print(noms[i].getContents()
										+ " "
										+ prenoms[i].getContents()
										+ " existe déjà dans la base de données ! il suffit de le mettre à jour: ");
						e.setFiliere(f);
						e.setMail(mails[i].getContents());
						e.setNom(noms[i].getContents());
						e.setPrenom(prenoms[i].getContents());
						daoEtudiant.update(e);
						System.out.print("ok !\n");
					}
						e.addInscription(new Inscription(e, c, an, Double.parseDouble((moyennes[i].getContents()))));
						daoEtudiant.update(e);
					
					
					

				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
				}
			}

			conn.commitTransaction();

		} catch (BiffException e) {
			e.printStackTrace();
		} catch (DaoException e) {
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
