package fr.unice.hmabwe.vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.unice.hmabwe.controleur.bd.Connexion;
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
		
		this.setLayout(new BorderLayout());
		
		this.add(new JScrollPane(tableEtudiants), BorderLayout.CENTER);
		
		boutons.add(mail);
		boutons.add(stats);
		boutons.add(edit);
		boutons.add(remove);
		boutons.add(add);
		
		this.add(boutons);
	}
	
	/**
	 * Mets la liste d'étudiants dans la table.
	 * @param etudiants
	 */
	public void setListeEtudiants(Collection<Etudiant> etudiants) {
		Collection<Object[]> tmpdata = new ArrayList<Object[]>();
		Object[] filieres = daoFiliere.getAllFilieres().toArray();
		for(Etudiant e : etudiants) {
			Object row[] = new Object[7];
			row[0] = e.getNumEtu();
			row[1] = e.getNom();
			row[2] = e.getPrenom();
			row[3] = e.getMail();
			JComboBox jc = new JComboBox(filieres);
			jc.setSelectedItem(e.getFiliere());
			row[4] = jc;
			//TODO row[5] = e.getGroupe();
			row[6] = e.getOrigine();
			tmpdata.add(row);
		}
		data = (Object[][]) tmpdata.toArray();
		this.tableEtudiants = new JTable(data,colonnes);
	}
	
}
