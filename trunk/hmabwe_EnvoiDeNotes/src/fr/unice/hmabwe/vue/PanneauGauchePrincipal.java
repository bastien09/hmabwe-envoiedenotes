package fr.unice.hmabwe.vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.unice.hmabwe.controleur.bd.Connexion;
import fr.unice.hmabwe.controleur.bd.dao.DaoCours;
import fr.unice.hmabwe.controleur.bd.dao.DaoException;
import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;
import fr.unice.hmabwe.controleur.bd.dao.DaoFiliere;
import fr.unice.hmabwe.modele.Cours;
import fr.unice.hmabwe.modele.Filiere;

/**
 * 
 * @author Bastien Auda
 * 
 * Le JPanel contenant la liste des filières et cours.
 *
 */

public class PanneauGauchePrincipal extends JPanel {

	private DaoFabrique df;
	private Connexion conn;
	private DaoFiliere daoFiliere;
	private DaoCours daoCours;

	private EcouteurGauche l = new EcouteurGauche();

	private JComboBox choix = new JComboBox();
	private JList liste = new JList();

	private JPanel boutons = new JPanel();
	private JButton stats = new JButton(new ImageIcon(this.getClass().getResource("/resource/chart.png")));
	private JButton edit = new JButton(new ImageIcon(this.getClass().getResource("/resource/pencil.png")));
	private JButton remove = new JButton(new ImageIcon(this.getClass().getResource("/resource/minus-circle.png")));
	private JButton add = new JButton(new ImageIcon(this.getClass().getResource("/resource/plus-circle.png")));

	public PanneauGauchePrincipal(DaoFabrique df) {
		this.df = df;
		conn = df.getConnexion();
		daoFiliere = df.getDaoFiliere();
		daoCours = df.getDaoCours();

		this.setLayout(new BorderLayout());

		choix.addItem("Filière");
		choix.addItem("Cours");
		choix.addActionListener(l);

		this.add(choix, BorderLayout.NORTH);
		this.add(new JScrollPane(liste), BorderLayout.CENTER);
		refreshList();


		boutons.add(stats);
		stats.addActionListener(l);
		boutons.add(edit);
		edit.addActionListener(l);
		boutons.add(remove);
		remove.addActionListener(l);
		boutons.add(add);
		add.addActionListener(l);

		this.add(boutons, BorderLayout.SOUTH);

	}

	/**
	 * rafraichit la liste des filières et cours
	 */
	public void refreshList() {
		try {
			conn.beginTransaction();
			if(choix.getSelectedItem().equals("Filière")) {
				//TODO liste.setListData(daoFiliere.getAllFilieres().toArray());
			} else {
				//TODO liste.setListData(daoCours.getAllCours().toArray());
			}
			liste.setSelectedIndex(0);
			conn.commitTransaction();
		} catch (DaoException e) {
			//TODO error message
			e.printStackTrace();
		}
	}

	/*
	 * Le listener des composants du panneau
	 */

	private class EcouteurGauche implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source.equals(choix)) {
				refreshList();
			}
			if(source.equals(add)) {
				if(choix.getSelectedItem().equals("Cours")) {
					//TODO ajout cours
					new FenetreAjoutCours("", 600, 600);
				} else {
					//TODO ajout filiere
					new FenetreAjoutFiliere("", 600, 600);
				}
			}
			if(source.equals(remove)) {
				String objetASupprimer;
				try {
					conn.beginTransaction();
					if(choix.getSelectedItem().equals("Cours")) {
						Cours c = (Cours) liste.getSelectedValue();
						objetASupprimer = "le cours " + c.getNom();
						daoCours.delete(c);
					} else {
						Filiere f = (Filiere) liste.getSelectedValue();
						objetASupprimer = "la filière " + f.getNom();
						daoFiliere.delete(f);
					}
					int reponse = JOptionPane.showConfirmDialog(PanneauGauchePrincipal.this.getParent(), "Êtes vous certain de vouloir supprimer " +
							objetASupprimer + " ?");
					if(reponse == JOptionPane.OK_OPTION) {
						conn.commitTransaction();
					} else {
						conn.rollbackTransaction();
					}
				} catch (Exception ex) {
					// TODO: handle exception
				}
			}
			if(source.equals(edit)) {
				if(choix.getSelectedItem().equals("Cours")) {
					Cours c = (Cours) liste.getSelectedValue();
					//TODO edit cours
					new FenetreAjoutCours("", 600, 600);
				} else {
					Filiere f = (Filiere) liste.getSelectedValue();
					//TODO edit filiere
					new FenetreAjoutFiliere("", 600, 600);
				}
			}
			if(source.equals(stats)) {
				if(choix.getSelectedItem().equals("Cours")) {
					Cours c = (Cours) liste.getSelectedValue();
					//TODO stats cours
				} else {
					Filiere f = (Filiere) liste.getSelectedValue();
					//TODO stats filiere
				}
			}
		}


	}

}