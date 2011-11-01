package fr.unice.hmabwe.vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;

import fr.unice.hmabwe.controleur.bd.Connexion;

/**
 * 
 * @author Bastien Auda
 * 
 * Le JPanel contenant la 
 *
 */

public class PanneauGauchePrincipal extends JPanel {
	
	private Connexion conn;
	
	private JComboBox choix = new JComboBox();
	private JList liste = new JList();
	
	private JPanel boutons = new JPanel();
	private JButton stats = new JButton(new ImageIcon(this.getClass().getResource("/resource/chart.png")));
	private JButton edit = new JButton(new ImageIcon(this.getClass().getResource("/resource/pencil.png")));
	private JButton remove = new JButton(new ImageIcon(this.getClass().getResource("/resource/minus-circle.png")));
	private JButton add = new JButton(new ImageIcon(this.getClass().getResource("/resource/plus-circle.png")));
	
	public PanneauGauchePrincipal(Connexion conn) {
		
		this.conn = conn;
		
		this.setLayout(new BorderLayout());
		
		choix.addItem("Filière");
		choix.addItem("Cours");
		
		this.add(choix, BorderLayout.NORTH);
		this.add(liste, BorderLayout.CENTER);
		Object[] tab = { 1, 2, 3, 5};
		liste.setListData(tab);
		
		boutons.add(stats);
		boutons.add(edit);
		boutons.add(remove);
		boutons.add(add);
		
		this.add(boutons, BorderLayout.SOUTH);
		
	}
	

}