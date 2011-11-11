package fr.unice.hmabwe.vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.unice.hmabwe.controleur.bd.Connexion;
import fr.unice.hmabwe.controleur.bd.dao.DaoEtudiant;
import fr.unice.hmabwe.controleur.bd.dao.DaoException;
import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;
import fr.unice.hmabwe.controleur.bd.dao.DaoFiliere;
import fr.unice.hmabwe.modele.Etudiant;
import fr.unice.hmabwe.modele.Filiere;

/**
 * 
 * Classe contenant le JPanel qui liste les étudiants.
 * @author Bastien Auda
 * 
 * 
 */
public class PanneauListeEtudiants extends JPanel {

	private DaoFabrique df;
	private Connexion conn;
	private DaoFiliere daoFiliere;
	private DaoEtudiant daoEtudiant;

	private ActionListener l = new BoutonsListener();

	private EtudiantTableModel tableModel = new EtudiantTableModel();
	private JTable tableEtudiants = new JTable(tableModel);


	private JPanel boutons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JButton mail = new JButton(new ImageIcon(this.getClass().getResource("/resource/mail--arrow.png")));
	private JButton stats = new JButton(new ImageIcon(this.getClass().getResource("/resource/chart.png")));
	private JButton edit = new JButton(new ImageIcon(this.getClass().getResource("/resource/pencil.png")));
	private JButton remove = new JButton(new ImageIcon(this.getClass().getResource("/resource/minus-circle.png")));
	private JButton add = new JButton(new ImageIcon(this.getClass().getResource("/resource/plus-circle.png")));

	public PanneauListeEtudiants(DaoFabrique df) {
		this.df = df;
		this.conn = df.getConnexion();
		this.daoFiliere = df.getDaoFiliere();
		this.daoEtudiant = df.getDaoEtudiant();

		this.setLayout(new BorderLayout());

		this.add(new JScrollPane(tableEtudiants), BorderLayout.CENTER);

		boutons.add(mail);
		mail.addActionListener(l);
		boutons.add(stats);
		stats.addActionListener(l);
		boutons.add(edit);
		edit.addActionListener(l);
		boutons.add(remove);
		remove.addActionListener(l);
		boutons.add(add);
		add.addActionListener(l);

		this.add(boutons,BorderLayout.SOUTH);
	}

	/**
	 * Mets la liste d'étudiants dans la table.
	 * @param etudiants
	 */
	public void setListeEtudiants(Collection<Etudiant> etudiants) {
		this.tableModel.setEtudiants(etudiants);
		
	}

	/**
	 * Ouvre la fenêtre d'envoie d'email personnalisé aux étudiants sélectionnés.
	 */
	public void sendMail() {
//		Collection<Etudiant> etudiants = new ArrayList<Etudiant>();
//		int selectedRows[] = tableEtudiants.getSelectedRows();
//		for(int i : selectedRows) {
//			Object row[] = new Object[7];
//			for(int j = 0; j < 7; j++) {
//				row[j] = tableEtudiants.getValueAt(i, j);
//			}
//			etudiants.add(rowToEtudiant(row));
//		}
//		new FenetreMail(df, etudiants, ((FenetrePrincipale) PanneauListeEtudiants.this.getTopLevelAncestor()).getSelectedItem());
	}
	
	/**
	 * Selectionne tout les étudiants de la table
	 */
	public void selectAllEtudiants() {
		tableEtudiants.selectAll();
	}

	private class BoutonsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if(source.equals(mail)) {
				sendMail();
			}
			if(source.equals(stats)) {
				new FenetreAjoutEleve(df);
			}
			if(source.equals(edit)) {
				//TODO 				new FenetreAjoutEleve(df, )
			}
			if(source.equals(remove)) {
				Object row[] = new Object[7];
				for(int j = 0; j < 7; j++) {
					row[j] = tableEtudiants.getValueAt(tableEtudiants.getSelectedRow(), j);
				}
				//TODO Etudiant etu = rowToEtudiant(row);
				try {
					conn.beginTransaction();
					// TODO daoEtudiant.delete(etu);
					int reponse = JOptionPane.showConfirmDialog(PanneauListeEtudiants.this, "Êtes vous certain de vouloir supprimer cet étudiant ?");
					if(reponse == JOptionPane.OK_OPTION) {
						conn.commitTransaction();
					} else {
						conn.rollbackTransaction();
					}
				} catch (Exception ex) {
					// TODO: handle exception
				}

			}
			if(source.equals(add)) {
				//TODO add row http://www.siteduzero.com/tutoriel-3-71859-les-tableaux-les-vrais.html#ss_part_5
			}

		}

		//TODO class JTable listener pour éditer des étudiants à la volée

	}

}
