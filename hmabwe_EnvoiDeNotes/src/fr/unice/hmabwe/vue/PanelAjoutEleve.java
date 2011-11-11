package fr.unice.hmabwe.vue;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;
import fr.unice.hmabwe.modele.Filiere;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Panel pour l'ajout d'un élève 
 *
 */

public class PanelAjoutEleve extends JPanel{
	
	public JTextField jtf, jtf2, jtf3, jtf4, jtf5, jtfNumetud ;
	
    private JLabel label, label2, label3, label4, label5, label6, labelNumetud;
    
    public JScrollPane scrollPane;
    
    public JPanel panel, panelIdentite, panelNom, panelPrenom, panelGroupe, panelEmail, panelFiliere, panelOrigine, top, labels, saisie, lignePanel, panelNumetud;
    
    public ArrayList<ObjetLigneInscription> listeLigne = new ArrayList<ObjetLigneInscription>();
    
    private JComboBox combo1 = new JComboBox();
    
	public PanelAjoutEleve() {
	
		
		
		
		panel = new JPanel();
		lignePanel = new JPanel();
		
		ObjetLigneInscription o1 = new ObjetLigneInscription(true);
		ObjetLigneInscription o2 = new ObjetLigneInscription(false);
		
		listeLigne.add(o1);
		listeLigne.add(o2);
		lignePanel.setLayout(new BoxLayout(lignePanel, BoxLayout.Y_AXIS));
		for(ObjetLigneInscription objl : listeLigne){
			lignePanel.add(objl.panelLigne);
		}
		
		scrollPane = new JScrollPane(lignePanel);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Inscriptions"));
		
		jtf = new JTextField("");
		jtf2 = new JTextField("");
		jtf3 = new JTextField("");
		jtf4 = new JTextField("");
		jtf5 = new JTextField("");
		jtfNumetud = new JTextField("");
	    
	    jtf.setPreferredSize(new Dimension(150, 25));
	    jtf.setColumns(25);
	    jtf2.setPreferredSize(new Dimension(150, 25));
	    jtf2.setColumns(25);
	    jtf3.setPreferredSize(new Dimension(150, 25));
	    jtf3.setColumns(25);
	    jtfNumetud.setPreferredSize(new Dimension(250, 25));
	    jtfNumetud.setColumns(25);
	    jtf5.setPreferredSize(new Dimension(150, 25));
	    jtf5.setColumns(25);
	    jtf4.setPreferredSize(new Dimension(150, 25));
	    jtf4.setColumns(2);
	    
	    label = new JLabel("Nom :");
	    label2 = new JLabel("Prénom :");
	    label3 = new JLabel("e-mail :");
	    label4 = new JLabel("Groupe :");
	    label5 = new JLabel("Filière :");
	    label6 = new JLabel("Origine :");
	    labelNumetud = new JLabel("Numero etudiant :");
	    
	    panelNom = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    panelNom.add(label);
	    panelNom.add(jtf);
	    
	    panelPrenom = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    panelPrenom.add(label2);
	    panelPrenom.add(jtf2);
	    
	    panelEmail = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    panelEmail.add(label3);
	    panelEmail.add(jtf3);
	    
	    panelOrigine = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    panelOrigine.add(label6);
	    panelOrigine.add(jtf5);
	    
	    panelNumetud = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    panelNumetud.add(labelNumetud);
	    panelNumetud.add(jtfNumetud);
	    
	    panelGroupe = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    panelGroupe.add(label4);
	    panelGroupe.add(jtf4);
	    
	    panelFiliere = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    panelFiliere.add(label5);
	    panelFiliere.add(combo1);
	    
		panelIdentite = new JPanel();
		panelIdentite.setLayout(new BoxLayout(panelIdentite, BoxLayout.Y_AXIS));
		panelIdentite.add(panelNom);
		panelIdentite.add(panelPrenom);
		panelIdentite.add(panelEmail);
		panelIdentite.add(panelOrigine);
		panelIdentite.add(panelNumetud);
		panelIdentite.add(panelGroupe);
		panelIdentite.add(panelFiliere);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(panelIdentite);
		panel.add(scrollPane);
	}
	
	public JPanel getPanelPrincipal(){
		return this.panel;
	}
	
	
	public String getNom(){
		return this.jtf.getText();
	}
	
	public String getPrenom(){
		return this.jtf2.getText();
	}
	
	public String getEmail(){
		return this.jtf3.getText();
	}
	
	public String getOrigine(){
		return this.jtf5.getText();
	}
	
	public int getGroupe(){
		return Integer.parseInt(this.jtf4.getText());
	}
	
	public Filiere getFiliere(){
		return (Filiere) this.combo1.getSelectedItem();
	}
	
	public String getNumEtudiant(){
		return this.jtfNumetud.getText();
	}
}
