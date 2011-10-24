package fr.unice.hmabwe.controleur.es.tableur;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import fr.unice.hmabwe.modele.Etudiant;
import fr.unice.hmabwe.modele.Filliere;

public class ImportExcel {
	
	private String inputFile;
	private String numEtu;
	private String nom;
	private String prenom;
	private String mail;
	private String origine;
	private String moyenne;
	private String filiere;
    
    public ImportExcel(String inputFile, String numEtu, String nom, String prenom, String mail, String origine, String moyenne, String filiere) {
        
    	this.inputFile = inputFile;
    	this.numEtu = numEtu;
    	this.nom = nom;
    	this.prenom = prenom;
    	this.mail = mail;
    	this.origine = origine;
    	this.moyenne = moyenne;
    	this.filiere = filiere;
    }
    
    public HashMap<Etudiant, String> lectureListEtudiants()  {
        
        File inputWorkbook = new File(inputFile);
        
        if(!inputWorkbook.exists()) {         
            return null;
        }                
        
        Workbook w;
        
        HashMap<Etudiant, String> moyennesEtudiants = new HashMap<Etudiant,String>();
        
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
            System.out.println(c_numEtu + c_nom + c_prenom + c_mail + c_moyenne + c_filiere);
            if(c_numEtu == -1 || c_nom == -1 || c_prenom == -1 || c_mail == -1 || c_moyenne == -1 || c_filiere == -1)
                return null;
            
            Cell[] numsEtu = sheet.getColumn(c_numEtu);
            Cell[] noms = sheet.getColumn(c_nom);
            Cell[] prenoms = sheet.getColumn(c_prenom);
            Cell[] mails = sheet.getColumn(c_mail);
            Cell[] origines = sheet.getColumn(c_origine);
            Cell[] moyennes = sheet.getColumn(c_moyenne);
            Cell[] filieres = sheet.getColumn(c_filiere);
            
            
            Etudiant e;
            Filliere f;
            
            for(int i=1; i<(numsEtu.length >= noms.length ? noms.length : numsEtu.length); i++) {
                try {
                	String moyenne = moyennes[i].getContents();
                	f = new Filliere(filieres[i].getContents());
                	e = new Etudiant(numsEtu[i].getContents(), noms[i].getContents(), prenoms[i].getContents(), mails[i].getContents(),origines[i].getContents(), f);
                	moyennesEtudiants.put(e, moyenne);
                } catch(NumberFormatException nfe) {
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
        } catch(NumberFormatException e) {
            num = -1;
            // le numéro est sous forme de lettre
        }
        if(num != -1) return num;
        
        String conv[] = { "a" ,"b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
        
        //System.out.println("getColonne");
        
        for(int i=0; i<26; i++) {
            if(conv[i].equalsIgnoreCase(c)) {
                return i;
            }
        }
        
        int cpt = 25;
        for(int i=0; i<26; i++) {
            for(int j=0; j<26; j++) {
                ++cpt;
                String s = conv[i]+conv[j];
                if(s.equalsIgnoreCase(c)) {
                    return cpt;
                }
            }
        }        
        return -1;
    }    
	

}
