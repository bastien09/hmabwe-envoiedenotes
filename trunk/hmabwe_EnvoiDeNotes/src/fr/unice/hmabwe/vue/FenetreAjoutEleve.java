package fr.unice.hmabwe.vue;



import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Fenetre permettant d'ajouter un eleve avec sa liste de cours
 *
 */
public class FenetreAjoutEleve extends FenetreCommune{
	
	private JTextField jtf = new JTextField("");
	private JTextField jtf2 = new JTextField("");
	private JTextField jtf3 = new JTextField("");
	private JTextField jtf4 = new JTextField("");
	
    private JLabel label = new JLabel("Nom :");
    private JLabel label2 = new JLabel("Prénom :");
    private JLabel label3 = new JLabel("e-mail :");
    private JLabel label4 = new JLabel("Groupe :");
    private JLabel label5 = new JLabel("Filière :");
    
    private JScrollPane scrollPane;
    private JPanel panel, panelIdentite, panelNom, panelPrenom, panelGroupe, panelEmail, panelFiliere, top, labels, saisie, lignePanel;
    private ArrayList<ObjetLigneInscription> listeLigne ;
    private JComboBox combo1 = new JComboBox();
    
	public FenetreAjoutEleve(String nomFenetre, int longueur, int largeur) {
	
		super(nomFenetre, longueur, largeur);
		
		panel = new JPanel();
		lignePanel = new JPanel();
		
		ObjetLigneInscription ligne1 = new ObjetLigneInscription();
		ObjetLigneInscription ligne2 = new ObjetLigneInscription();
		ObjetLigneInscription ligne3 = new ObjetLigneInscription();
		ligne1.setIsNew(false);
		ligne2.setIsNew(true);
		listeLigne = new ArrayList<ObjetLigneInscription>();
		listeLigne.add(ligne1);
		listeLigne.add(ligne2);
		listeLigne.add(ligne3);
		
		lignePanel.setLayout(new BoxLayout(lignePanel, BoxLayout.Y_AXIS));
		for(ObjetLigneInscription objl : listeLigne){
			lignePanel.add(objl.panelLigne);
		}
		
		scrollPane = new JScrollPane(lignePanel);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Inscriptions"));
		
	    
	    jtf.setPreferredSize(new Dimension(150, 25));
	    jtf.setColumns(25);
	    jtf2.setPreferredSize(new Dimension(150, 25));
	    jtf2.setColumns(25);
	    jtf3.setPreferredSize(new Dimension(150, 25));
	    jtf3.setColumns(25);
	    jtf4.setPreferredSize(new Dimension(150, 25));
	    jtf4.setColumns(2);
	    
	    panelNom = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    panelNom.add(label);
	    panelNom.add(jtf);
	    
	    panelPrenom = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    panelPrenom.add(label2);
	    panelPrenom.add(jtf2);
	    
	    panelEmail = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    panelEmail.add(label3);
	    panelEmail.add(jtf3);
	    
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
		panelIdentite.add(panelGroupe);
		panelIdentite.add(panelFiliere);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(panelIdentite);
		panel.add(scrollPane);
	    
	    
        this.setResizable(true);
        this.container.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
		// TODO Auto-generated constructor stub
	}
	
    

}
