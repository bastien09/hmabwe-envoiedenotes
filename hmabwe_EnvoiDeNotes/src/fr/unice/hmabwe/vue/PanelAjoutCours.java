package fr.unice.hmabwe.vue;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;
import fr.unice.hmabwe.modele.Enseignant;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Panel pour l'ajout d'un Cours 
 *
 */

public class PanelAjoutCours extends JPanel{
	
	private JLabel labelNom;
	private JLabel labelEnseignant;
	
	public JTextField txtNom;
	public JComboBox tabEnseignant;
	
	private JPanel panel;
	private JPanel panelNom;
	private JPanel panelEnseignant;
	
	public PanelAjoutCours() {
		
		panel = new JPanel();
		panelNom = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelEnseignant = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		
		labelNom = new JLabel("Nom :");
		labelEnseignant =  new JLabel("Enseignant :");
		
		txtNom = new JTextField();
		txtNom.setMaximumSize(new Dimension(150, 30));
		txtNom.setColumns(25);
		
		tabEnseignant = new JComboBox();
		
		panelNom.add(labelNom);
		panelNom.add(txtNom);
		
		panelEnseignant.add(labelEnseignant);
		panelEnseignant.add(tabEnseignant);
		
		panel.add(panelNom);
		panel.add(panelEnseignant);
		
	}
	
	public JPanel getPanelPrincipal(){
		return this.panel;
	}
	
	public String getNom(){
		return this.txtNom.getText();
	}
	
	public Enseignant getEnseignantChoisi(){
		return (Enseignant) this.tabEnseignant.getSelectedItem();
		//TODO A faire
	}
	
	public JComboBox getComboEnseignant(){
		return this.tabEnseignant;
	}
}
