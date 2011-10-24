package fr.unice.hmabwe.controleur.es.tableur;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import fr.unice.hmabwe.modele.Etudiant;


public class ExportExcel {
	
	private static String URL_FICHIER = new File("").getAbsolutePath() + File.separator + "moyenneEtu.xls";

    public boolean createXls(HashMap<Etudiant, String> map) 
    {
        //on créé le fichier qu'on va remplir
        
        if(new File(URL_FICHIER).exists()) {
            new File(URL_FICHIER).delete();
        }

        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(new File(URL_FICHIER));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        WritableSheet sheet = workbook.createSheet("moyennes", 0);


        // on créé la ligne d'entete du tableau excel.

        try {
            sheet.addCell(new Label(0,0,"numEtu"));
            sheet.addCell(new Label(1,0,"Nom"));
            sheet.addCell(new Label(2,0,"Prenom"));
            sheet.addCell(new Label(3,0,"mail"));
            sheet.addCell(new Label(4,0,"origine"));
            sheet.addCell(new Label(5,0,"moyenne"));
            sheet.addCell(new Label(6,0,"filiere"));
        } catch (RowsExceededException e) {
            e.printStackTrace();
            return false;
        } catch (WriteException e) {
            e.printStackTrace();
            return false;
        }                       

        int i = 1;
        for (Entry<Etudiant, String> entry : map.entrySet())
        {            
            try {
                sheet.addCell(new Label(0,i,entry.getKey().getNumEtu()));
                sheet.addCell(new Label(1,i,entry.getKey().getNom()));
                sheet.addCell(new Label(2,i,entry.getKey().getPrenom()));
                sheet.addCell(new Label(3,i,entry.getKey().getMail()));
                sheet.addCell(new Label(4,i,entry.getKey().getOrigine()));
                sheet.addCell(new Label(5,i,entry.getValue()));
                sheet.addCell(new Label(6,i,entry.getKey().getFilliere().getNom()));                
                i++;
            } catch (RowsExceededException e) {
                e.printStackTrace();
                return false;
            } catch (WriteException e) {
                e.printStackTrace();
                return false;
            }
        }

        try {
            workbook.write();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try {
            workbook.close();
        } catch (WriteException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }

}
