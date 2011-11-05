package fr.unice.hmabwe.vue;



import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import fr.unice.hmabwe.controleur.bd.dao.DaoEtudiant;
import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Fenetre permettant d'ajouter un eleve avec sa liste de cours
 *
 */
public class FenetreAjoutEleve extends FenetreCommune{
	
    private PanelAjoutEleve panelEleve;
    
	public FenetreAjoutEleve(DaoFabrique df) {
	
		super("Ajouter un élève", 450, 400, df);
		
		panelEleve = new PanelAjoutEleve();
	    
		DaoEtudiant daoEtudiant = df.getDaoEtudiant();
		
		
        this.setResizable(true);
        this.container.add(panelEleve.getPanelPrincipal(), BorderLayout.CENTER);
        this.setVisible(true);
		// TODO Auto-generated constructor stub
	}
	
    

}
