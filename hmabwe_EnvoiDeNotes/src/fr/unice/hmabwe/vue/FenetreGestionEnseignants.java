package fr.unice.hmabwe.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

import fr.unice.hmabwe.controleur.bd.dao.DaoEnseignant;
import fr.unice.hmabwe.controleur.bd.dao.DaoException;
import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;
import fr.unice.hmabwe.modele.Enseignant;
import fr.unice.hmabwe.modele.Filiere;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Fenetre permettant de gérer les enseignants
 *
 */

public class FenetreGestionEnseignants extends FenetreCommune{
	
	public PanelGestionEnseignant panelEnseignant;
	
	public DaoEnseignant daoEnseignant;
	
	public EcouteurEnseignant l;
	
	public FenetreGestionEnseignants(DaoFabrique df) {
		super("Gestion des enseignants", 500, 500, df);
		
		daoEnseignant = df.getDaoEnseignant();
		
		panelEnseignant = new PanelGestionEnseignant();
		
		l = new EcouteurEnseignant();
		
		boutonOK.addMouseListener(l);
		boutonAnnuler.addMouseListener(l);
		panelEnseignant.bAjout1.addMouseListener(l);
		panelEnseignant.bAjout2.addMouseListener(l);
		panelEnseignant.bModif1.addMouseListener(l);
		panelEnseignant.bModif2.addMouseListener(l);
		panelEnseignant.bMoins.addMouseListener(l);
		panelEnseignant.bPlus.addMouseListener(l);
		panelEnseignant.bSuppress1.addMouseListener(l);
		panelEnseignant.bSuppress2.addMouseListener(l);
		
		this.setResizable(false);
		this.container.add(panelEnseignant.getPanelPrincipal(), BorderLayout.CENTER);
		this.setVisible(true);
		
		
		
		
		// TODO Auto-generated constructor stub
	}
	private class EcouteurEnseignant implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			Object boutSelected = arg0.getSource();
			if(boutSelected.equals(panelEnseignant.bAjout1)){//Si on veut ajouter une nouvelle filière
				new FenetreAjoutFiliere(df);
			}
			else{
				if(boutSelected.equals(panelEnseignant.bAjout2)){//Si on veut ajouter un nouveau cours
					new FenetreAjoutCours(df);
				}
				else{
					if(boutSelected.equals(boutonOK)){//Si on clique sur le boutonOK
						Enseignant e = new Enseignant(panelEnseignant.getNom(), panelEnseignant.getPrenom(), panelEnseignant.getEmail());
						
						e.addFiliere(panelEnseignant.getFiliereSelect());
						e.addCours(panelEnseignant.getCoursSelect());
						
						
						try {
							conn.beginTransaction();
							daoEnseignant.create(e);
							conn.commitTransaction();
							
						}
						catch (DaoException e1) {
							try {
								conn.rollbackTransaction();
							} catch (DaoException e2) {
								
								e2.printStackTrace();
							}
							e1.printStackTrace();
						}
						finally {
							if(conn.estOuverte()) {
								try {
									conn.fermer();
									//TODO Faire disparaitre la fenetre a la fin de la transaction
								}
								catch(DaoException e3) {
									e3.printStackTrace();
								}
							}
						}
						
					}
					else{
						if(boutSelected.equals(boutonAnnuler)){
							//TODO Fermer la fenetre
						}
						
						else{
							if(boutSelected.equals(panelEnseignant.bMoins)){
								//TODO methode de suppression...
							}
						}
					}
				}
			}
				
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			
			
		}
		
	}
}
