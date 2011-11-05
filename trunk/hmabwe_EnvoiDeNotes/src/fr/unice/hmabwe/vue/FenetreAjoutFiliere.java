package fr.unice.hmabwe.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;
import fr.unice.hmabwe.controleur.bd.dao.DaoFiliere;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Fenetre permettant d'ajouter une filiere et son prof responsable
 *
 */

public class FenetreAjoutFiliere extends FenetreCommune{
	
	PanelAjoutFiliere panelFiliere;
	
	public FenetreAjoutFiliere(DaoFabrique df) {
		super("Ajout/Edition d'une filière", 500, 300, df);
		panelFiliere = new PanelAjoutFiliere();
		
		DaoFiliere daoFiliere = df.getDaoFiliere();
		
		
		this.setResizable(false);
		this.container.add(panelFiliere.getPanelPrincipal(), BorderLayout.NORTH);
		this.setVisible(true);
		
		
	}
	
	

}
