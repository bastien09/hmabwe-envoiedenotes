package fr.unice.hmabwe.vue;

import java.awt.*;

import javax.swing.*;

import fr.unice.hmabwe.modele.Cours;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Une ligne d'inscription c-a-d nom du cours, annee, note
 *
 */

public class ObjetLigneInscription{
	private JLabel nomCours;
	private JLabel annee;
	private JLabel note;
	public JComboBox comboCours;
	public JTextField textAnnee;
	public JTextField textNote;
	public JPanel panelLigne;
	private JPanel panelBoutonV, panelBoutonN, panelField;
	
	private JButton boutonMoins, boutonPlus, boutonValide;
	private boolean isNewLine;
	
	public ObjetLigneInscription(){
		
	}
	public ObjetLigneInscription(boolean newline){
		// TODO Gestion d'ajout de ligne 
		// TODO Ajout d'un bonton a la suite de la ligne pour valider ligne et ajouter une autre ligne
		
		panelLigne = new JPanel();
		
		nomCours = new JLabel("Cours");
		annee = new JLabel("Année");
		note= new JLabel("Note");
		comboCours = new JComboBox();
		textAnnee = new JTextField();
		textNote = new JTextField();
		this.isNewLine = newline;
		
		panelLigne.setLayout(new FlowLayout());
		
		comboCours.setMaximumSize(new Dimension(30, 30));
		textAnnee.setPreferredSize(new Dimension(50,25));
		textAnnee.setColumns(4);
		textNote.setMaximumSize(new Dimension(50, 25));
		textNote.setColumns(2);
		
		panelField = new JPanel(new FlowLayout());
		panelField.add(nomCours);
		panelField.add(comboCours);
		panelField.add(annee);
		panelField.add(textAnnee);
		panelField.add(note);
		panelField.add(textNote);
		
		boutonMoins = new JButton((new ImageIcon(this.getClass().getResource("/resource/minus-circle.png"))));
		boutonPlus =  new JButton((new ImageIcon(this.getClass().getResource("/resource/plus.png"))));
		boutonValide =  new JButton((new ImageIcon(this.getClass().getResource("/resource/tick-circle.png"))));
		
		panelBoutonV = new JPanel(new FlowLayout());
		panelBoutonV.add(boutonMoins);
		panelBoutonV.add(boutonValide);
		
		panelBoutonN = new JPanel(new FlowLayout());
		panelBoutonN.add(boutonPlus);
		
		
		
		if(!this.isNewLine){
			panelLigne.add(panelField);
			panelLigne.add(panelBoutonV);
		}
		else{
			panelLigne.add(panelField);
			panelLigne.add(panelBoutonN);
			
		}
		
		
		
	}
	
	public void setIsNew(boolean nouveau){
		this.isNewLine = nouveau;
	}
	
	public Cours getCoursSelected(){
		return (Cours) this.comboCours.getSelectedItem();
	}
	
	public Integer getAnnee(){
		return Integer.parseInt(this.textAnnee.getText());
	}
	
	public Double getMoyenne(){
		return Double.parseDouble(this.textNote.getText());
	}

}
