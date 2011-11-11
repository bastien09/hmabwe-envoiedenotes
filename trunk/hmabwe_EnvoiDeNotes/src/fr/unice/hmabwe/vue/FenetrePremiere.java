package fr.unice.hmabwe.vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;

/**
 * La fenêtre affichée lors de la première utilisation.
 * @author Bastien Auda
 *
 */
public class FenetrePremiere extends JFrame {
	
	private ActionListener l = new Ecouteur();
	
	private JComboBox choix = new JComboBox();
	private JButton config = new JButton("Configurer");
	private JButton ok = new JButton("valider");
	
	public FenetrePremiere() {
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setSize(600,100);
		this.setTitle("Choix du type de persistance");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		choix.addItem("SGBD relationnel");
		choix.addItem("Autre type de persistance");
		choix.addActionListener(l);
		config.addActionListener(l);
		ok.addActionListener(l);
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		panel.add(new JLabel("Type de persistance : "));
		panel.add(choix);
		panel.add(config);
		
		this.add(panel, BorderLayout.CENTER);
		JPanel bouton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bouton.add(ok);
		this.add(bouton,BorderLayout.SOUTH);
		
		if((new File("hmabwe-properties").exists())) { // si le fichier de configuration exste déjà, alors ce n'est pas le premier lancement
			//TODO récupérer connexion
			
			launch();
			
		} else { // sinon c'est la première connexion
			this.setVisible(true);
		}
	}
	
	/**
	 * Lance le programme.
	 */
	private void launch() {
		//TODO retirer ouverture connexion
		DaoFabrique.setTypeDao(DaoFabrique.TypeFabrique.JPA);

		DaoFabrique df = DaoFabrique.getDaoFabrique();

		new FenetrePrincipale(df);
		
		this.dispose();
	}
	
	public static void main(String[] args) {
		
		new FenetrePremiere();

	}
	
	private class Ecouteur implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source.equals(choix)) {
				if(choix.getSelectedItem().equals("SGBD relationnel")) {
					config.setEnabled(true);
					ok.setEnabled(true);
				} else {
					config.setEnabled(false);
					ok.setEnabled(false);
				}
			}
			if(source.equals(config)) {
				new FenetrePreferences();
			}
			if(source.equals(ok)) {
				if((new File("hmabwe-properties").exists())) {
					launch();
				} else {
					JOptionPane.showMessageDialog(FenetrePremiere.this,"Vous n'avez pas configuré votre connexion.", "Erreur" , JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
	}

}
