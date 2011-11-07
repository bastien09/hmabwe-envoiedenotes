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

	private final String  colonnes[] = {"Numéro", "Nom", "Prénom", "e-mail", "Filière", "Groupe" , "Origine"};
	private Object data[][]; // les données de la JTable
	private JTable tableEtudiants = new JTable();


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

		this.add(boutons);
	}

	/**
	 * Mets la liste d'étudiants dans la table.
	 * @param etudiants
	 */
	public void setListeEtudiants(Collection<Etudiant> etudiants) {
		//TODO debug [Ljava.lang.Object; cannot be cast to [[Ljava.lang.Object;
		Collection<Object[]> tmpdata = new ArrayList<Object[]>();
		for(Etudiant e : etudiants) {
			tmpdata.add(etudiantToRow(e));
		}
		data = (Object[][]) tmpdata.toArray();
		this.tableEtudiants = new JTable(data,colonnes);
	}

	/**
	 * Transforme un objet Etudiant en ligne pour la JTable
	 * @param e
	 * @return
	 */
	private Object[] etudiantToRow(Etudiant e) {
		Object row[] = new Object[7];
		row[0] = e.getNumEtu();
		row[1] = e.getNom();
		row[2] = e.getPrenom();
		row[3] = e.getMail();
		Object[] filieres;
		try {
			filieres = daoFiliere.findAll().toArray();

			JComboBox jc = new JComboBox(filieres);
			jc.setSelectedItem(e.getFiliere());
			row[4] = jc;
		} catch (DaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//TODO row[5] = e.getGroupe();
		row[6] = e.getOrigine();
		return row;
	}

	private Etudiant rowToEtudiant(Object[] row) {
		Etudiant e = new Etudiant((String)row[0],(String) row[1],(String) row[2], (String)row[3], (String)row[6]);
		e.setFiliere((Filiere) ((JComboBox)row[4]).getSelectedItem());

		return e;
	}

	private class BoutonsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if(source.equals(mail)) {
				Collection<Etudiant> etudiants = new ArrayList<Etudiant>();
				int selectedRows[] = tableEtudiants.getSelectedRows();
				for(int i : selectedRows) {
					Object row[] = new Object[7];
					for(int j = 0; j < 7; j++) {
						row[j] = tableEtudiants.getValueAt(i, j);
					}
					etudiants.add(rowToEtudiant(row));
				}
				new FenetreMail(df, etudiants, ((FenetrePrincipale) PanneauListeEtudiants.this.getTopLevelAncestor()).getSelectedItem());
			}
			if(source.equals(stats)) {
				//TODO afficher stats
			}
			if(source.equals(edit)) {
				//TODO fenetre edition etudiant
			}
			if(source.equals(remove)) {
				Object row[] = new Object[7];
				for(int j = 0; j < 7; j++) {
					row[j] = tableEtudiants.getValueAt(tableEtudiants.getSelectedRow(), j);
				}
				Etudiant etu = rowToEtudiant(row);
				try {
					conn.beginTransaction();
					daoEtudiant.delete(etu);
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
