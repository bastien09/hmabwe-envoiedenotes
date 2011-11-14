package fr.unice.hmabwe.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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
	public Collection<Enseignant> listeEnseign = new ArrayList<Enseignant>();
	public boolean estNouveauCours;
	public EcouteurCours l;
	
	
	/**Constructeur permettant d'ajouter un nouveau cours
	 *@param df Daofabrique
	 */
	public FenetreAjoutCours(DaoFabrique df) {
		super("Ajout/Edition de cours", 400, 250, df);
		estNouveauCours = true;
		daocours = df.getDaoCours();
		daoenseignant = df.getDaoEnseignant();
		panelCours = new PanelAjoutCours(df);
		l = new EcouteurCours(this);
		
		boutonOK.addMouseListener(l);
		boutonAnnuler.addMouseListener(l);
		
		this.setResizable(false);
		this.container.add(panelCours.getPanelPrincipal(), BorderLayout.NORTH);
		this.setVisible(true);
		
		
		
	}
	/**Constructeur permettant de modifier un cours
	 *@param df Daofabrique
	 *@param c Cours à modifiers
	 */
	public FenetreAjoutCours(DaoFabrique df, Cours c){
		super("Ajout/Edition de cours", 400, 250, df);
		estNouveauCours = false;
		daocours = df.getDaoCours();
		daoenseignant = df.getDaoEnseignant();
		panelCours = new PanelAjoutCours(df);
		l = new EcouteurCours(this);
		
		boutonOK.addMouseListener(l);
		boutonAnnuler.addMouseListener(l);
		
		panelCours.txtNom.setText(c.getNom());
		panelCours.tabEnseignant.setSelectedItem(c.getEnseignant());
		
				
		this.setResizable(false);
		this.container.add(panelCours.getPanelPrincipal(), BorderLayout.NORTH);
		this.setVisible(true);
		
		
		
	}
	
	
	private class EcouteurCours implements MouseListener{
		public FenetreAjoutCours fac;
		
		public EcouteurCours(FenetreAjoutCours fac) {
			this.fac = fac;
			// TODO Auto-generated constructor stub
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			Object boutonSelected = arg0.getSource();
			if(boutonSelected.equals(boutonOK)){//Debut bouton OK
				if(estNouveauCours){//Si nouveau Cours
					Cours c = new Cours(panelCours.getNom(), panelCours.getEnseignantChoisi());
					try {
						conn.beginTransaction();
						daocours.create(c);
						conn.commitTransaction();
						fac.setVisible(false);
					} catch (DaoException e) {
						
						try {
							conn.rollbackTransaction();
						} catch (DaoException e1) {
							
							e1.printStackTrace();
						}
						e.printStackTrace();
					}
				}//Fin si nouveau Cours
				
				else{
					if(!estNouveauCours){
						Cours c = new Cours(panelCours.getNom(), panelCours.getEnseignantChoisi());
						try {
							conn.beginTransaction();
							daocours.update(c);
							conn.commitTransaction();
							fac.setVisible(false);
						} catch (DaoException e) {
							
							try {
								conn.rollbackTransaction();
							} catch (DaoException e1) {
								
								e1.printStackTrace();
							}
							e.printStackTrace();
						}
						
					}
				}
				
			}//Fin boutn OK
			else{
				if(boutonSelected.equals(boutonAnnuler)){
					//TODO Fermer la fenetre sans rien faire d'autre
					fac.setVisible(false);
					
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
