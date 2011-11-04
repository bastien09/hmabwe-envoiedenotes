package fr.unice.hmabwe.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Fenetre permettant d'ajouter une filiere et son prof responsable
 *
 */

public class FenetreAjoutFiliere extends FenetreCommune{
	protected JLabel labelNom;
	protected JLabel labelEnseignant;
	protected JTextField textNom;
	protected JComboBox tabEnseignant;
	
	protected JPanel panel;
	protected JPanel nomPanel;
	protected JPanel enseignantPanel;
	
	public FenetreAjoutFiliere(String nomFenetre, int longueur, int largeur) {
		super(nomFenetre, longueur, largeur);
		
		labelNom = new JLabel("Nom :");
		labelEnseignant = new JLabel("Enseignant responsable :");
		textNom = new JTextField();
		textNom.setMaximumSize(new Dimension(150, 30));
		tabEnseignant = new JComboBox();
		
		panel = new JPanel(new GridLayout(2, 0));
		nomPanel = new JPanel(new GridLayout(0, 2));
		enseignantPanel = new JPanel(new GridLayout(0, 2));
		
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
