package fr.unice.hmabwe.vue;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.*;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Fenetre permettant d'afficher les statistiques d'un etudiant
 *
 */

public class FenetreStatistiqueEtudiant extends JFrame{
	
	private JPanel panelAnnee, panelMoyenne, panelCours, panel;
	
	private JLabel annee, moyenne;
	
	private JComboBox comboAnnee;
	
	private JScrollPane scrollCours
	;
	//Simple essai pour l'interface
	private ArrayList<ObjetLigneMoyenneEtudiant> ligneMoyenne;
	
	public FenetreStatistiqueEtudiant(){
		//TODO Dans le constructeur ajouter la connexion a la base.
		//TODO Remplacer les moyennes de l'arraylist, par les moyennes recus de la database(ArrayList), ainsi que le nom des cours.
		
		//Simple essai pour l'interface
		ligneMoyenne = new ArrayList<ObjetLigneMoyenneEtudiant>();
		ObjetLigneMoyenneEtudiant obj1 = new ObjetLigneMoyenneEtudiant("Francais", "10");
		ObjetLigneMoyenneEtudiant obj2 = new ObjetLigneMoyenneEtudiant("Anglais", "15");
		ObjetLigneMoyenneEtudiant obj3 = new ObjetLigneMoyenneEtudiant("Math", "15");
		ObjetLigneMoyenneEtudiant obj4 = new ObjetLigneMoyenneEtudiant("Sport", "15");
		ObjetLigneMoyenneEtudiant obj5 = new ObjetLigneMoyenneEtudiant("Latin", "15");
		ObjetLigneMoyenneEtudiant obj6 = new ObjetLigneMoyenneEtudiant("Physique", "15");
		ObjetLigneMoyenneEtudiant obj7 = new ObjetLigneMoyenneEtudiant("Italien", "15");
		ligneMoyenne.add(obj1);
		ligneMoyenne.add(obj2);
		ligneMoyenne.add(obj3);
		ligneMoyenne.add(obj4);
		ligneMoyenne.add(obj5);
		ligneMoyenne.add(obj6);
		ligneMoyenne.add(obj7);
		// Fin essai
		
		panelAnnee = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelMoyenne = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelCours = new JPanel();
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		annee = new JLabel("Année :");
		moyenne = new JLabel("Moyenne :");
		
		comboAnnee = new JComboBox();
		
		panelAnnee.add(annee);
		panelAnnee.add(comboAnnee);
		
		panelMoyenne.add(moyenne);
		
		panelCours.setLayout(new BoxLayout(panelCours, BoxLayout.Y_AXIS));
		scrollCours = new JScrollPane(panelCours);
		scrollCours.setBorder(BorderFactory.createTitledBorder("Cours"));
		for(ObjetLigneMoyenneEtudiant objl : ligneMoyenne){
			panelCours.add(objl.panelMoyenne);
		}
		
		panel.add(panelAnnee);
		panel.add(panelMoyenne);
		panel.add(scrollCours);
		
		this.setTitle("Statistiques Etudiant");
        this.setSize(350, 350);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
		this.setContentPane(panel);
		this.setVisible(true);
		
	}
}
