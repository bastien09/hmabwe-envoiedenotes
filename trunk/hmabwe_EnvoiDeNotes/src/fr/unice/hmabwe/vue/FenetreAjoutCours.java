package fr.unice.hmabwe.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;

import javax.swing.*;

import fr.unice.hmabwe.controleur.bd.dao.DaoCours;
import fr.unice.hmabwe.controleur.bd.dao.DaoEnseignant;
import fr.unice.hmabwe.controleur.bd.dao.DaoException;
import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;
import fr.unice.hmabwe.modele.Cours;
import fr.unice.hmabwe.modele.Enseignant;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Fenetre permettant d'ajouter un cours
 *
 */

public class FenetreAjoutCours extends FenetreCommune{
	
	public PanelAjoutCours panelCours;
	public DaoCours daocours;
	public DaoEnseignant daoenseignant;
	public Collection<Enseignant> listeEnseign;
	public FenetreAjoutCours(DaoFabrique df) {
		super("Ajout/Edition de cours", 400, 250, df);
		
		daocours = df.getDaoCours();
		daoenseignant = df.getDaoEnseignant();
		
		try {
			listeEnseign = daoenseignant.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		panelCours = new PanelAjoutCours();
		
		//Remplissage de la comboBox Enseignant
		panelCours.getComboEnseignant().addItem(listeEnseign);
		
		
		this.setResizable(false);
		this.container.add(panelCours.getPanelPrincipal(), BorderLayout.NORTH);
		this.setVisible(true);
		
		
		
	}
	
	private class EcouteurCours implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			Object boutonSelected = arg0.getSource();
			if(boutonSelected.equals(boutonOK)){
				Cours c = new Cours(panelCours.getNom(), panelCours.getEnseignantChoisi());
				try {
					conn.beginTransaction();
					daocours.create(c);
					conn.commitTransaction();
				} catch (DaoException e) {
					
					try {
						conn.rollbackTransaction();
					} catch (DaoException e1) {
						
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
				finally {
					if(conn.estOuverte()) {
						try {
							conn.fermer();
							//TODO Faire disparaitre la fenetre a la fin de la transaction
						}
						catch(DaoException e) {
							e.printStackTrace();
						}
					}
				}
				
			}
			else{
				if(boutonSelected.equals(boutonAnnuler)){
					//TODO Fermer la fenetre sans rien faire d'autre
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
