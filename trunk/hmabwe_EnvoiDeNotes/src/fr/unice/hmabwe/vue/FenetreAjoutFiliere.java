package fr.unice.hmabwe.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import fr.unice.hmabwe.controleur.bd.dao.DaoEnseignant;
import fr.unice.hmabwe.controleur.bd.dao.DaoException;
import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;
import fr.unice.hmabwe.controleur.bd.dao.DaoFiliere;
import fr.unice.hmabwe.modele.Filiere;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Fenetre permettant d'ajouter une filiere et son prof responsable
 *
 */

public class FenetreAjoutFiliere extends FenetreCommune{
	
	public PanelAjoutFiliere panelFiliere;
	
	public DaoFiliere daoFiliere;
	public DaoEnseignant daoEnseignant;
	public EcouteurFiliere l;
	
	/**Constructeur permettant l'ajout d'une nouvelle filière
	 *@param df Daofabrique
	 */
	public FenetreAjoutFiliere(DaoFabrique df) {
		super("Ajout/Edition d'une filière", 500, 300, df);
		
		panelFiliere = new PanelAjoutFiliere();
		
		daoFiliere = df.getDaoFiliere();
		daoEnseignant = df.getDaoEnseignant();
		
		l = new EcouteurFiliere();
		
		boutonOK.addMouseListener(l);
		boutonAnnuler.addMouseListener(l);
		
		
		this.setResizable(false);
		this.container.add(panelFiliere.getPanelPrincipal(), BorderLayout.NORTH);
		this.setVisible(true);
		
		
	}
	
	/**Constructeur permettant de modifier une filiere
	 *@param df Daofabrique
	 *@param f Filière à modifier
	 */
	public FenetreAjoutFiliere(DaoFabrique df, Filiere f){
		
		super("Ajout/Edition d'une filière", 500, 300, df);
		
		panelFiliere = new PanelAjoutFiliere();
		
		daoFiliere = df.getDaoFiliere();
		daoEnseignant = df.getDaoEnseignant();
		
		panelFiliere.textNom.setText(f.getNom());
		panelFiliere.tabEnseignant.setSelectedItem(f.getResponsable());
		
		
		l = new EcouteurFiliere();		
		boutonOK.addMouseListener(l);
		boutonAnnuler.addMouseListener(l);
		
		
		this.setResizable(false);
		this.container.add(panelFiliere.getPanelPrincipal(), BorderLayout.NORTH);
		this.setVisible(true);
		
	}
	
	
	private class EcouteurFiliere implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			
			Object boutSelected = arg0.getSource();
			if(boutSelected.equals(boutonOK)){
				Filiere f = new Filiere(panelFiliere.getNom(), panelFiliere.getEnseignant());
				
				try {
					conn.beginTransaction();
					daoFiliere.create(f);
					conn.commitTransaction();
				} 
				catch (DaoException e) {
					try {
						conn.rollbackTransaction();
					}
					catch(DaoException ee) {
						ee.printStackTrace();
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
				if(boutSelected.equals(boutonAnnuler)){
					//TODO Fermer la fenetre sans rien faire de special
					
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
