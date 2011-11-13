package fr.unice.hmabwe.vue;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.unice.hmabwe.controleur.bd.dao.DaoEnseignant;
import fr.unice.hmabwe.controleur.bd.dao.DaoException;
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
	
	public JButton bAjoutEnseignant;
	
	public JComboBox tabEnseignant;
	
	private DefaultComboBoxModel comboModel;
	
	private JPanel panel, nomPanel, enseignantPanel, panelBouton;
	
	private DaoFabrique df;
	
	private DaoEnseignant de;
	
	private Collection<Enseignant> listEns = new ArrayList<Enseignant>();
	
	public PanelAjoutFiliere(DaoFabrique df) {
		this.df = df;
		
		de = df.getDaoEnseignant();
			
		comboModel = new DefaultComboBoxModel();
		labelNom = new JLabel("Nom :");
		labelEnseignant = new JLabel("Enseignant responsable :");
		textNom = new JTextField();
		textNom.setMaximumSize(new Dimension(150, 30));
		textNom.setColumns(25);
		try {
			listEns = de.findAll();
			for(Enseignant e : listEns){
				comboModel.addElement(e);
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tabEnseignant = new JComboBox(comboModel);
		bAjoutEnseignant = new JButton("Ajouter un enseignant");
		panelBouton =  new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		panel = new JPanel();
		nomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		enseignantPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		nomPanel.add(labelNom);
		nomPanel.add(textNom);
		enseignantPanel.add(labelEnseignant);
		enseignantPanel.add(tabEnseignant);
		
		panelBouton.add(bAjoutEnseignant);
		
		panel.add(nomPanel);
		panel.add(enseignantPanel);
		panel.add(panelBouton);
		

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
