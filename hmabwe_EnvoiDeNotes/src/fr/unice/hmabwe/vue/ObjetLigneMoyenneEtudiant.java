package fr.unice.hmabwe.vue;

import java.awt.FlowLayout;

import javax.swing.*;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Une ligne de moyenne d'etudiant c-a-d moyenne, cours, note a la suite
 *
 */

public class ObjetLigneMoyenneEtudiant extends JPanel{
	
	public JLabel moyenne, cours, note;
	
	public JPanel panelMoyenne;
	
	public ObjetLigneMoyenneEtudiant(String cours, String note){
		
		panelMoyenne = new JPanel(new FlowLayout(FlowLayout.LEFT));
		moyenne = new JLabel("Moyenne en ");
		this.cours = new JLabel();
		this.cours.setText(cours);
		this.note = new JLabel();
		this.note.setText(note);
		
		panelMoyenne.add(moyenne);
		panelMoyenne.add(this.cours);
		panelMoyenne.add(this.note);
		
	}
	
	
}
