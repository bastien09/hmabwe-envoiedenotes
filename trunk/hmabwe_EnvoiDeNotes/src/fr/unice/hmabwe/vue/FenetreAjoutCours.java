package fr.unice.hmabwe.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Fenetre permettant d'ajouter un cours
 *
 */

public class FenetreAjoutCours extends FenetreCommune{
	
	private JLabel labelNom;
	private JLabel labelEnseignant;
	
	private JTextField txtNom;
	private JComboBox tabEnseignant;
	
	JPanel panel;
	JPanel panelNom;
	JPanel panelEnseignant;
	
	public FenetreAjoutCours(String nomFenetre, int longueur, int largeur) {
		super(nomFenetre, longueur, largeur);
		
		panel = new JPanel(new GridLayout(2, 0));
		panelNom = new JPanel(new GridLayout(0 ,2));
		panelEnseignant = new JPanel(new GridLayout(0, 2));
		
		labelNom = new JLabel("Nom :");
		labelEnseignant =  new JLabel("Enseignant :");
		
		txtNom = new JTextField();
		txtNom.setMaximumSize(new Dimension(150, 30));
		
		tabEnseignant = new JComboBox();
		
		panelNom.add(labelNom);
		panelNom.add(txtNom);
		
		panelEnseignant.add(labelEnseignant);
		panelEnseignant.add(tabEnseignant);
		
		panel.add(panelNom);
		panel.add(panelEnseignant);
		
		
		this.setResizable(false);
		this.container.add(panel, BorderLayout.NORTH);
		this.setVisible(true);
		
	}
	

}
