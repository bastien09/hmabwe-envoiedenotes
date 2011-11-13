package fr.unice.hmabwe.vue;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.unice.hmabwe.controleur.bd.dao.DaoEtudiant;
import fr.unice.hmabwe.controleur.bd.dao.DaoException;
import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;
import fr.unice.hmabwe.modele.Etudiant;
import fr.unice.hmabwe.modele.Inscription;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Panel pour les statisiques d'un etudiant 
 *
 */

public class PanelStatistiqueEtudiant extends JPanel{
	private DaoFabrique df;
	
	private JPanel panelAnnee, panelMoyenne, panelCours, panel;
	
	private JLabel annee, moyenne, noteMoyenne, changeAnne;
	
	private JComboBox comboAnnee;
	private DefaultComboBoxModel comboModel;
	
	private JScrollPane scrollCours
	;
	//Simple essai pour l'interface
	public ArrayList<ObjetLigneMoyenneEtudiant> ligneMoyenne = new ArrayList<ObjetLigneMoyenneEtudiant>();
	
	public PanelStatistiqueEtudiant(Etudiant e, DaoFabrique df){
		// Methode de remplissage de diverse liste.
		DaoEtudiant de;
		de = df.getDaoEtudiant();
		comboModel = new DefaultComboBoxModel();
		
		Collection<Inscription> listInscription = new ArrayList<Inscription>();
		ArrayList<Integer> listannee = new ArrayList<Integer>();
		
		listInscription = e.getInscriptions();
		for(Inscription insc : listInscription){
			ObjetLigneMoyenneEtudiant o = new ObjetLigneMoyenneEtudiant(insc);
			this.ligneMoyenne.add(o); // Remplissage de la liste de moyenne
			comboModel.addElement(insc.getAnnee()); // Remplissage de la liste Annee
		}
		
		
		panelAnnee = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelMoyenne = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelCours = new JPanel();
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		annee = new JLabel("Année :");
		moyenne = new JLabel("Moyenne :");
		try {
			noteMoyenne = new JLabel(String.valueOf(de.getMoyenne(e.getNumEtu(), 2010)));
		}
		catch(DaoException ex) {
			ex.printStackTrace();
		}
		comboAnnee = new JComboBox(comboModel);
		
		
		
		panelAnnee.add(annee);
		panelAnnee.add(comboAnnee);
		
		panelMoyenne.add(moyenne);
		panelMoyenne.add(noteMoyenne);
		
		panelCours.setLayout(new BoxLayout(panelCours, BoxLayout.Y_AXIS));
		scrollCours = new JScrollPane(panelCours);
		scrollCours.setBorder(BorderFactory.createTitledBorder("Cours"));
		
		
		for(ObjetLigneMoyenneEtudiant objl : ligneMoyenne){
			panelCours.add(objl.panelMoyenne);
		}
		
		panel.add(panelAnnee);
		panel.add(panelMoyenne);
		panel.add(scrollCours);
	}
	
	public JPanel getPanelPrincipal(){
		return this.panel;
	}
	
	public void setMoyenne(int note){
		this.noteMoyenne.setText(String.valueOf(note));
	}
	
	public void setAnnee(int annee){
		this.changeAnne.setText(String.valueOf(annee));
	}
	
}