package fr.unice.hmabwe.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.*;

import fr.unice.hmabwe.controleur.bd.dao.DaoEnseignant;
import fr.unice.hmabwe.controleur.bd.dao.DaoException;
import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;
import fr.unice.hmabwe.controleur.bd.dao.DaoFiliere;
import fr.unice.hmabwe.modele.Enseignant;
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
	public Collection<Enseignant> listEns = new ArrayList<Enseignant>();
	public boolean estNouvelleFiliere;
	
	/**Constructeur permettant l'ajout d'une nouvelle filière
	 *@param df Daofabrique
	 */
	public FenetreAjoutFiliere(DaoFabrique df) {
		super("Ajout/Edition d'une filière", 500, 300, df);
		estNouvelleFiliere = true;
		
		panelFiliere = new PanelAjoutFiliere(df);
		
		daoFiliere = df.getDaoFiliere();
		daoEnseignant = df.getDaoEnseignant();
			
		l = new EcouteurFiliere(this);
		boutonOK.addMouseListener(l);
		boutonAnnuler.addMouseListener(l);
		panelFiliere.bAjoutEnseignant.addMouseListener(l);
		
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
		estNouvelleFiliere = false;
		
		panelFiliere = new PanelAjoutFiliere(df);
		
		daoFiliere = df.getDaoFiliere();
		daoEnseignant = df.getDaoEnseignant();
		
		panelFiliere.textNom.setText(f.getNom());
		panelFiliere.tabEnseignant.setSelectedItem(f.getResponsable());
		
		
		l = new EcouteurFiliere(this);		
		boutonOK.addMouseListener(l);
		boutonAnnuler.addMouseListener(l);
		panelFiliere.bAjoutEnseignant.addMouseListener(l);
		
		
		
		this.setResizable(false);
		this.container.add(panelFiliere.getPanelPrincipal(), BorderLayout.NORTH);
		this.setVisible(true);
		
	}
	
	
	private class EcouteurFiliere implements MouseListener{
		public FenetreAjoutFiliere faf;
		
		public EcouteurFiliere(FenetreAjoutFiliere faf){
			this.faf = faf;
		}
		@Override
		public void mouseClicked(MouseEvent arg0) {
			
			Object boutSelected = arg0.getSource();
			if(boutSelected.equals(boutonOK)){//Debut bouton OK
				if(estNouvelleFiliere){//Si nouvelle Filiere
					Filiere f = new Filiere(panelFiliere.getNom(), panelFiliere.getEnseignant());
					
					try {
						conn.beginTransaction();
						daoFiliere.create(f);
						conn.commitTransaction();
						this.faf.setVisible(false);
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
					
				
				}//Fin nouvelle FIliere
				else{
					if(!estNouvelleFiliere){
						Filiere f = new Filiere(panelFiliere.getNom(), panelFiliere.getEnseignant());
						
						try {
							conn.beginTransaction();
							daoFiliere.update(f);
							conn.commitTransaction();
							this.faf.setVisible(false);
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
						
					}
				}
			}//Fin Bouton OK
			else{
				if(boutSelected.equals(panelFiliere.bAjoutEnseignant)){
					new FenetreGestionEnseignants(df);
					
				}
				else{
					if(boutSelected.equals(boutonAnnuler)){
						this.faf.setVisible(false);
						//TODO Ne rien faire et fermer la fenetre
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
