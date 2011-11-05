package fr.unice.hmabwe.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import fr.unice.hmabwe.controleur.bd.dao.DaoEnseignant;
import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Fenetre permettant de gérer les enseignants
 *
 */

public class FenetreGestionEnseignants extends FenetreCommune{
	
	PanelGestionEnseignant panelEnseignant;
	
	public FenetreGestionEnseignants(DaoFabrique df) {
		super("Gestion des enseignants", 500, 500, df);
		
		DaoEnseignant daoEnseignant = df.getDaoEnseignant();
		
		panelEnseignant = new PanelGestionEnseignant();
		
		this.setResizable(false);
		this.container.add(panelEnseignant.getPanelPrincipal(), BorderLayout.CENTER);
		this.setVisible(true);
		
		
		
		
		// TODO Auto-generated constructor stub
	}

}
