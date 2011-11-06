package fr.unice.hmabwe.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;
import fr.unice.hmabwe.modele.Cours;
import fr.unice.hmabwe.modele.Enseignant;
import fr.unice.hmabwe.modele.Filiere;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Panel pour la gestion d'un professeur 
 *
 */

public class PanelGestionEnseignant extends JPanel {
	private JSplitPane panel;
	
	private JPanel panelCours, panelFiliere, panelNom, panelPrenom ,panelEmail, panelDroite, panelIdentite, panelBouton1, panelBouton2, panelGauche, panelBoutPlusMoins;
	
	private JScrollPane panscrollEnseig, panscrollFili, panscrollCours;
	private JScrollBar essaiScroll = new JScrollBar();
	public JButton bAjout1, bAjout2, bSuppress1, bSuppress2, bModif1, bModif2, bPlus, bMoins;
	
	private JLabel nom, prenom, email, filiere, cours;
	private JTextField txtNom, txtPrenom, txtEmail;
	private JList listEnseignant, listFiliere, listCours;
	
	
	
	public PanelGestionEnseignant() {
		
		// A effacer plus tard
		Object[] tabEnse = {"Patrick", "Louis", "Yves"};
		Object[] tabCours = {"Math", "Anglais", "Latin"};
		Object[] tabFili = {"L2I", "M1MASS", "Bio"};
		//
		nom = new JLabel("Nom : ");
		prenom = new JLabel("Prenom : ");
		email = new JLabel("E-mail : ");
		
		txtNom = new JTextField();
		txtNom.setMaximumSize(new Dimension(150, 50));
		txtNom.setColumns(20);
		txtPrenom = new JTextField();
		txtPrenom.setMaximumSize(new Dimension(150, 50));
		txtPrenom.setColumns(20);
		txtEmail = new JTextField();
		txtEmail.setMaximumSize(new Dimension(150, 50));
		txtEmail.setColumns(20);
		
		panelNom = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelNom.add(nom);
		panelNom.add(txtNom);
		
		panelPrenom = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelPrenom.add(prenom);
		panelPrenom.add(txtPrenom);
		
		panelEmail = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelEmail.add(email);
		panelEmail.add(txtEmail);
		
		
		panelIdentite = new JPanel();
		panelIdentite.setLayout(new BoxLayout(panelIdentite, BoxLayout.Y_AXIS));
		panelIdentite.add(panelNom);
		panelIdentite.add(panelPrenom);
		panelIdentite.add(panelEmail);
		
		listEnseignant = new JList(tabEnse);
		listEnseignant.setFixedCellWidth(100);
		panscrollEnseig = new JScrollPane(listEnseignant, panscrollEnseig.VERTICAL_SCROLLBAR_AS_NEEDED, panscrollEnseig.HORIZONTAL_SCROLLBAR_NEVER );
		
		panelBoutPlusMoins = new JPanel(new BorderLayout());
		bPlus = new JButton(new ImageIcon(this.getClass().getResource("/resource/plus-circle.png")));
		bMoins = new JButton(new ImageIcon(this.getClass().getResource("/resource/minus-circle.png")));
		panelBoutPlusMoins.add(bPlus, BorderLayout.WEST);
		panelBoutPlusMoins.add(bMoins, BorderLayout.EAST);
		
		panelGauche = new JPanel(new BorderLayout());
		panelGauche.add(panscrollEnseig, BorderLayout.CENTER);
		panelGauche.add(panelBoutPlusMoins, BorderLayout.SOUTH);
		
		panelBouton1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bAjout1 = new JButton(new ImageIcon(this.getClass().getResource("/resource/plus-circle.png")));
		bSuppress1 = new JButton(new ImageIcon(this.getClass().getResource("/resource/minus-circle.png")));
		bModif1 = new JButton(new ImageIcon(this.getClass().getResource("/resource/pencil.png")));
		panelBouton1.add(bAjout1);
		panelBouton1.add(bSuppress1);
		panelBouton1.add(bModif1);
		
	
		listFiliere = new JList(tabFili);
		panelFiliere = new JPanel(new BorderLayout());
		panelFiliere.setBorder(BorderFactory.createTitledBorder("Filière"));
		panscrollFili = new JScrollPane(listFiliere,panscrollCours.VERTICAL_SCROLLBAR_AS_NEEDED, panscrollCours.HORIZONTAL_SCROLLBAR_NEVER );
		panelFiliere.add(panscrollFili, BorderLayout.CENTER);
		panelFiliere.add(panelBouton1, BorderLayout.SOUTH);
		
		panelBouton2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bAjout2 = new JButton(new ImageIcon(this.getClass().getResource("/resource/plus-circle.png")));
		bSuppress2 = new JButton(new ImageIcon(this.getClass().getResource("/resource/minus-circle.png")));
		bModif2 = new JButton(new ImageIcon(this.getClass().getResource("/resource/pencil.png")));
		panelBouton2.add(bAjout2);
		panelBouton2.add(bSuppress2);
		panelBouton2.add(bModif2);
		
		
		listCours = new JList(tabCours);
		
		panscrollCours = new JScrollPane(listCours,panscrollCours.VERTICAL_SCROLLBAR_AS_NEEDED, panscrollCours.HORIZONTAL_SCROLLBAR_NEVER );
		panelCours = new JPanel(new BorderLayout());
		panelCours.setBorder(BorderFactory.createTitledBorder("Cours"));
		
		panelCours.add(panscrollCours, BorderLayout.CENTER);
		panelCours.add(panelBouton2, BorderLayout.SOUTH);
		
		
		
		
		panelDroite = new JPanel(new GridLayout(3, 0));
		panelDroite.add(panelIdentite);
		panelDroite.add(panelFiliere);
		panelDroite.add(panelCours);
		panelDroite.setAlignmentX(50);
		
		panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelGauche, panelDroite);
		
	}
	
	public JSplitPane getPanelPrincipal(){
		return this.panel;
		
	}
	
	//private JTextField txtNom, txtPrenom, txtEmail;
	
	public String getNom(){
		return this.txtNom.getText();
	}
	
	public String getPrenom(){
		return this.txtPrenom.getText();
	}
	
	public String getEmail(){
		return this.txtEmail.getText();
	}
	
	//private JList listEnseignant, listFiliere, listCours;
	
	public Enseignant getEnseignantSelect(){
		return (Enseignant) this.listEnseignant.getSelectedValue();
	}
	
	public Filiere getFiliereSelect(){
		return (Filiere) this.listFiliere.getSelectedValue();
	}
	
	public Cours getCoursSelect(){
		return (Cours) this.listCours.getSelectedValue();
	}
	
}
