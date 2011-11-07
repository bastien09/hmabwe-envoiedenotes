package fr.unice.hmabwe.vue;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;
import fr.unice.hmabwe.controleur.bd.dao.DaoFiliere;
import fr.unice.hmabwe.modele.Enseignant;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Panel pour l'ajout d'une filière 
 *
 */

public class PanelAjoutFiliere extends JPanel{
	
	private JLabel labelNom, labelEnseignant;
	
	public JTextField textNom;
	
	public JComboBox tabEnseignant;
	
	private JPanel panel, nomPanel, enseignantPanel;
	
	private DaoFabrique df;
	
	public PanelAjoutFiliere() {
		
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
		
		

	}
	
	public JPanel getPanelPrincipal(){
		return this.panel;
	}
	
	public String getNom(){
		return this.textNom.getText();
	}
	
	public Enseignant getEnseignant(){
		return (Enseignant) this.tabEnseignant.getSelectedItem();
	}
	
	
	
}
