package fr.unice.hmabwe.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

import fr.unice.hmabwe.controleur.bd.dao.DaoCours;
import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Fenetre permettant d'ajouter un cours
 *
 */

public class FenetreAjoutCours extends FenetreCommune{
	
	PanelAjoutCours panelCours;
	
	public FenetreAjoutCours(DaoFabrique df) {
		super("Ajout/Edition de cours", 400, 250, df);
		
		DaoCours daocours = df.getDaoCours();
		
		panelCours = new PanelAjoutCours();
		
		
		
		this.setResizable(false);
		this.container.add(panelCours.getPanelPrincipal(), BorderLayout.NORTH);
		this.setVisible(true);
		
		
		
	}
	

}
