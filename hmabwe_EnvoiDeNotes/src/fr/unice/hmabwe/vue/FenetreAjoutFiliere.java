package fr.unice.hmabwe.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Fenetre permettant d'ajouter une filiere et son prof responsable
 *
 */

public class FenetreAjoutFiliere extends FenetreCommune{
	private JLabel labelNom, labelEnseignant;
	
	private JTextField textNom;
	
	private JComboBox tabEnseignant;
	
	private JPanel panel, nomPanel, enseignantPanel;
	
	public FenetreAjoutFiliere(DaoFabrique df) {
		super("Ajout/Edition d'une filière", 500, 300, df);
		
		labelNom = new JLabel("Nom :");
		labelEnseignant = new JLabel("Enseignant responsable :");
		textNom = new JTextField();
		textNom.setMaximumSize(new Dimension(150, 30));
		textNom.setColumns(25);
		tabEnseignant = new JComboBox();
		
		
		panel = new JPanel();
		nomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		enseignantPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		nomPanel.add(labelNom);
		nomPanel.add(textNom);
		enseignantPanel.add(labelEnseignant);
		enseignantPanel.add(tabEnseignant);
		
		panel.add(nomPanel);
		panel.add(enseignantPanel);
		
		
		this.setResizable(false);
		this.container.add(panel, BorderLayout.NORTH);
		this.setVisible(true);
		
		
	}
	
	

}
