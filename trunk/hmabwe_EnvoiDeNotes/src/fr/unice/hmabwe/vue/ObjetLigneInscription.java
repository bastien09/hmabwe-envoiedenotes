package fr.unice.hmabwe.vue;

import java.awt.*;

import javax.swing.*;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Une ligne d'inscription c-a-d nom du cours, annee, note
 *
 */

public class ObjetLigneInscription{
	protected JLabel nomCours;
	protected JLabel annee;
	protected JLabel note;
	protected JComboBox comboFiliere;
	protected JTextField textAnnee;
	protected JTextField textNote;
	protected JPanel panelLigne, panelBoutonV,panelBoutonN , panelField;
	
	private JButton boutonMoins, boutonPlus, boutonValide;
	private boolean isNewLine;
	
	public ObjetLigneInscription(){
		// TODO Gestion d'ajout de ligne 
		// TODO Ajout d'un bonton a la suite de la ligne pour valider ligne et ajouter une autre ligne
		
		panelLigne = new JPanel();
		
		nomCours = new JLabel("Cours");
		annee = new JLabel("Année");
		note= new JLabel("Note");
		comboFiliere = new JComboBox();
		textAnnee = new JTextField();
		textNote = new JTextField();
		
		
		panelLigne.setLayout(new FlowLayout());
		
		comboFiliere.setMaximumSize(new Dimension(30, 30));
		textAnnee.setPreferredSize(new Dimension(50,25));
		textAnnee.setColumns(4);
		textNote.setMaximumSize(new Dimension(50, 25));
		textNote.setColumns(2);
		
		panelField = new JPanel(new FlowLayout());
		panelField.add(nomCours);
		panelField.add(comboFiliere);
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

}
