package fr.unice.hmabwe.vue;



import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

import fr.unice.hmabwe.controleur.bd.dao.DaoEtudiant;
import fr.unice.hmabwe.controleur.bd.dao.DaoException;
import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;
import fr.unice.hmabwe.modele.Etudiant;
import fr.unice.hmabwe.modele.Inscription;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Fenetre permettant d'ajouter un eleve avec sa liste de cours
 *
 */
public class FenetreAjoutEleve extends FenetreCommune{
	
    private PanelAjoutEleve panelEleve;
    
    private DaoEtudiant daoetudiant;
    
    public boolean nouvelEtudiant;
    
    public EcouteurEtudiant l = new EcouteurEtudiant();
    
    /**Constructeur permettant d'afficher une fenêtre d'ajout d'un nouvel etudiant
     *@param df DaoFabrique
     */
	public FenetreAjoutEleve(DaoFabrique df) {
		super("Ajouter un élève", 450, 400, df);
		//C'est un ajout d'etudiant
		this.nouvelEtudiant = true;
		
		
		panelEleve = new PanelAjoutEleve();
	    daoetudiant = df.getDaoEtudiant();
        
	    boutonOK.addMouseListener(l);
	    boutonAnnuler.addMouseListener(l);
	    
	    this.setResizable(true);
        this.container.add(panelEleve.getPanelPrincipal(), BorderLayout.CENTER);
        this.setVisible(true);
		
	}
	
	/**Constructeur permettant d'afficher une fenêtre de modification d'étudiant
	 *@param df DaoFabrique
	 *@param e Etudiant à modifier
	 */
	public FenetreAjoutEleve(DaoFabrique df, Etudiant e){
		
		super("Ajouter un élève", 450, 400, df);
		//C'est une edition d'etudiant;
		this.nouvelEtudiant = false;
		
		panelEleve = new PanelAjoutEleve();
	    daoetudiant = df.getDaoEtudiant();
        
	    //On remplit les champs avec les anciennes valeurs
	    panelEleve.jtf.setText(e.getNom());
	    panelEleve.jtf2.setText(e.getPrenom());
	    panelEleve.jtf3.setText(e.getMail());
	    panelEleve.jtf4.setText((e.getGroupe()));
	    panelEleve.jtf5.setText(e.getOrigine());
	    panelEleve.jtfNumetud.setText(e.getNumEtu());
	    for(Inscription i : e.getInscriptions()){
	    	ObjetLigneInscription ob = new ObjetLigneInscription(); // Un constructeur eut été préférable
	    	ob.textAnnee.setText(String.valueOf(i.getAnnee()));
	    	ob.textNote.setText(String.valueOf(i.getMoyenne()));
	    	ob.comboCours.setSelectedItem(i.getCours());
	    	panelEleve.listeLigne.add(ob);
	    	
	    }
	    
	    boutonOK.addMouseListener(l);
	    boutonAnnuler.addMouseListener(l);
	    this.setResizable(true);
        this.container.add(panelEleve.getPanelPrincipal(), BorderLayout.CENTER);
        this.setVisible(true);
        
	}
	private class EcouteurEtudiant implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			Object boutSelected = e.getSource();
			
			if(boutSelected.equals(boutonOK)){ //Si on click sur le bouton OK
				Etudiant etu = new Etudiant(panelEleve.getNumEtudiant(), panelEleve.getNom(), panelEleve.getPrenom(), panelEleve.getEmail(), panelEleve.getOrigine(),panelEleve.getFiliere() , panelEleve.getOrigine());
				Inscription insc;
				// On recupere toutes les inscriptions de l'etudiants et on les ajoute à etu
				
				for(ObjetLigneInscription objl : panelEleve.listeLigne){
					insc = new Inscription(etu, objl.getCoursSelected(), objl.getAnnee(), objl.getMoyenne());
					etu.addInscription(insc);
				}
				if(nouvelEtudiant){
					try {
						conn.beginTransaction();
						daoetudiant.create(etu);
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
							catch(DaoException eee) {
								eee.printStackTrace();
							}
						}
					}
				} // Fin nouvel etudiant
				
				else{
					if(!nouvelEtudiant){
						try {
							conn.beginTransaction();
							daoetudiant.update(etu);
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
							
								}
								catch(DaoException eee) {
									eee.printStackTrace();
								}
							}
						}
					}
				} // Fin mis a jour etudiant
				
			}
			else{
				if(boutSelected.equals(boutonAnnuler)){
					//Fermer la fenetre
				}
			}
		}
	

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
