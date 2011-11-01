package fr.unice.hmabwe.vue;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;

import fr.unice.hmabwe.controleur.bd.Connexion;
import fr.unice.hmabwe.controleur.bd.config.ConfigConnection;
import fr.unice.hmabwe.controleur.bd.dao.DaoException;
import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;

/**
 * 
 * @author Bastien Auda
 * 
 * La fenêtre principale dans laquelle nous auront les filières, les cours et les étudiants.
 *
 */
public class FenetrePrincipale extends JFrame {
	private Connexion conn;
	
	// La barre des menus
	private JMenuBar menu = new JMenuBar();
	
	// Les menus
	private JMenu fichier = new JMenu("Fichier");
	private JMenu edition = new JMenu("Édition");
	private JMenu presentation = new JMenu("Présentation");
	private JMenu aide = new JMenu("Aide");
	
	// Les items du menu fichier
	private JMenu ajouter = new JMenu("Ajouter");
	private JMenuItem addEtudiant = new JMenuItem("un étudiant");
	private JMenuItem addEnseignant = new JMenuItem("un enseignant");
	private JMenuItem addCours = new JMenuItem("un cours");
	private JMenuItem addFiliere = new JMenuItem("une filière");
	
	private JMenuItem importFromXls = new JMenuItem("Importer depuis Excel", new ImageIcon(this.getClass().getResource("/resource/import.png")));
	private JMenuItem exportToXls = new JMenuItem("Exporter vers Excel", new ImageIcon(this.getClass().getResource("/resource/export.png")));
	
	private JMenuItem email = new JMenuItem("Envoyer les notes par e-mail", new ImageIcon(this.getClass().getResource("/resource/mail--arrow.png")));
	
	private JMenuItem quitter = new JMenuItem("Quitter", new ImageIcon(this.getClass().getResource("/resource/cross-button.png")));
	
	// Les items du menu edition
	private JMenuItem copier = new JMenuItem("Copier", new ImageIcon(this.getClass().getResource("/resource/document-copy.png")));
	private JMenuItem coller = new JMenuItem("Coller", new ImageIcon(this.getClass().getResource("/resource/clipboard-paste-document-text.png")));
	private JMenuItem selectAll = new JMenuItem("Tout sélectionner");
	
	// Les items du menu d'aide
	private JMenuItem about = new JMenuItem("À propos", new ImageIcon(this.getClass().getResource("/resource/information-frame.png")));
	private JMenuItem web = new JMenuItem("Site web du projet", new ImageIcon(this.getClass().getResource("/resource/question-frame.png")));
	
	// les composants de la fenêtre
	private PanneauGauchePrincipal panneauGauche = new PanneauGauchePrincipal(conn);
	private PanneauListeEtudiants panneauDroite = new PanneauListeEtudiants(conn);
	private JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panneauGauche, panneauDroite);
	
		

	public FenetrePrincipale(Connexion conn) {
		this.conn = conn;
		
		this.setTitle("Envoi de note - hmabwe");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		addMenuBar();
		
		this.add(splitPane);
		
		this.setVisible(true);
	}
	
	/**
	 * Ajoute une barre des menus à la fenêtre.
	 */
	public void addMenuBar() {
				
		ajouter.add(addEtudiant);
		ajouter.add(addEnseignant);
		ajouter.add(addCours);
		ajouter.add(addFiliere);
		
		ajouter.setIcon(new ImageIcon(this.getClass().getResource("/resource/plus.png")));
		fichier.add(ajouter);
		fichier.addSeparator();
		fichier.add(importFromXls);
		fichier.add(exportToXls);
		fichier.add(email);
		fichier.addSeparator();
		fichier.add(quitter);
		
		edition.add(copier);
		edition.add(coller);
		edition.add(selectAll);
		
		aide.add(about);
		aide.add(web);
		
		menu.add(fichier);
		menu.add(edition);
		//menu.add(presentation);
		menu.add(aide);
		
		this.setJMenuBar(menu);
		
		this.addWindowListener(new FenetrePrincipaleListener());
	}
	
	public static void main(String[] args) {
		
		DaoFabrique.setTypeDao(DaoFabrique.TypeFabrique.JPA);

		DaoFabrique df = DaoFabrique.getDaoFabrique();

		Connexion conn = df.getConnexion();

		new FenetrePrincipale(conn);
		
	}
	
	private class FenetrePrincipaleListener implements WindowListener {

		@Override
		public void windowActivated(WindowEvent e) {
						
		}

		@Override
		public void windowClosed(WindowEvent e) {
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			
			try {
				conn.fermer();
			} catch (DaoException e1) {
				e1.printStackTrace();
				System.out.println("La connexion n'a pas été fermée correctement, des données ont pu être corrompue.");
			}
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			
		}
		
	}
	
}
