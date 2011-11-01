package fr.unice.hmabwe.vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.unice.hmabwe.controleur.bd.Connexion;

public class PanneauListeEtudiants extends JPanel {
	
	Connexion conn;
	
	String  colonnes[] = {"Numéro", "Nom", "Prénom", "e-mail", "Filière", "Groupe" , "Origine"};
	private JTable tableEtudiants;
	
	
	private JPanel boutons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JButton mail = new JButton(new ImageIcon(this.getClass().getResource("/resource/mail--arrow.png")));
	private JButton stats = new JButton(new ImageIcon(this.getClass().getResource("/resource/chart.png")));
	private JButton edit = new JButton(new ImageIcon(this.getClass().getResource("/resource/pencil.png")));
	private JButton remove = new JButton(new ImageIcon(this.getClass().getResource("/resource/minus-circle.png")));
	private JButton add = new JButton(new ImageIcon(this.getClass().getResource("/resource/plus-circle.png")));

	public PanneauListeEtudiants(Connexion conn) {
		this.conn = conn;
		
		this.setLayout(new BorderLayout());
		
		
		this.add(new JScrollPane(tableEtudiants), BorderLayout.CENTER);
		
		boutons.add(mail);
		boutons.add(stats);
		boutons.add(edit);
		boutons.add(remove);
		boutons.add(add);
		
		this.add(boutons);
	}
	
}
