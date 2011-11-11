package fr.unice.hmabwe.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.unice.hmabwe.controleur.bd.dao.DaoCours;
import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;
import fr.unice.hmabwe.controleur.bd.dao.DaoFiliere;
import fr.unice.hmabwe.modele.Etudiant;

/**
 * Cette fenêtre permet d'envoyer les note par email aux étudiants.
 * @author Bastien Auda
 *
 */
public class FenetreMail extends JFrame {

	private DaoFabrique df;
	private DaoFiliere daoFiliere;
	private DaoCours daoCours;
	
	private ActionListener l = new BoutonListener();
	
	private JComboBox choixNote;
	
	private JTextField sujet = new JTextField(20);
	private JTextArea textMail = new JTextArea();
	
	private JButton aide = new JButton(new ImageIcon(this.getClass().getResource("/resource/question-frame.png")));
	private JButton annuler = new JButton("Annuler");
	private JButton valider = new JButton("Envoyer");
	
	public FenetreMail(DaoFabrique df, Collection<Etudiant> etudiants, Object filiereCours) {
		this.df = df;
		daoFiliere = df.getDaoFiliere();
		daoCours = df.getDaoCours();
		
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setSize(600,400);
		this.setTitle("Envoi de notes par email");
		
		Collection<Object> choixFiliereCours = new ArrayList<Object>();
		//TODO choixFiliereCours.addAll(daoFiliere.getAllFilieres());
		//choixFiliereCours.addAll(daoCours.getAllCours());	
		
		choixNote = new JComboBox(choixFiliereCours.toArray());
		choixNote.setSelectedItem(filiereCours);
		
		JPanel top = new JPanel();
		
		top.add(new JLabel("Filière/Cours :"));
		top.add(choixNote);
		top.add(new JLabel("Sujet :"));
		top.add(sujet);
		top.add(aide);		
		aide.addActionListener(l);
		aide.setBorderPainted(false);
		this.add(top,BorderLayout.NORTH);
		
		textMail.setBorder(BorderFactory.createTitledBorder("Texte personnalisé du mail :"));
		this.add(textMail,BorderLayout.CENTER);
		
		JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		south.add(annuler);
		annuler.addActionListener(l);
		south.add(valider);
		valider.addActionListener(l);
		
		this.add(south, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	private class BoutonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			
			if(source.equals(annuler)) {
				FenetreMail.this.dispose();
			}
			if(source.equals(aide)) {
				String tagDispo = "<html><p>Les emplacements spéciaux utilisables sont :" +
						"<ul>" +
						"<li>#nom : Le nom de l'étudiant</li>" +
						"<li>#prenom : Le prénom de l'étudiant</li>" +
						"<li>#note : La note de l'étudiant pour le cours/ la filière selectionné(e)</li>" +
						"<li>#moyenne : La moyenne de la classe pour le cours/ la filière selectionné(e)</li>" +
						"<li>#cours : Le noms du cours/de la filière sélectionné(e)</li>" +
						"<li>#prenom_enseignant, #nom_enseignant et #email_enseignant : Les coordonnées de l'enseignant responsable du cours/de la filière selectionné(e)</li>" +
						"</ul>" +
						"</p></html>";
				JLabel textAide = new JLabel(tagDispo);
				JFrame aide = new JFrame();
				aide.add(textAide);
				aide.setTitle("Aide redaction de mail");
				aide.setLocationRelativeTo(null);
				aide.setSize(400,300);
				aide.setVisible(true);
			}
			if(source.equals(valider)) {
				//TODO envoyer mail
			}
			
		}
		
		
		
	}
	
}
