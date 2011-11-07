package fr.unice.hmabwe.vue;



import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import fr.unice.hmabwe.controleur.bd.dao.DaoEtudiant;
import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;
import fr.unice.hmabwe.modele.Etudiant;

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
    
    
    /**Constructeur permettant d'afficher une fenêtre d'ajout d'un nouvel etudiant
     *@param df DaoFabrique
     */
	public FenetreAjoutEleve(DaoFabrique df) {
	
		super("Ajouter un élève", 450, 400, df);
		
		panelEleve = new PanelAjoutEleve();
	    daoetudiant = df.getDaoEtudiant();
        
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
		
		panelEleve = new PanelAjoutEleve();
	    daoetudiant = df.getDaoEtudiant();
        
	    panelEleve.jtf.setText(e.getNom());
	    panelEleve.jtf2.setText(e.getPrenom());
	    panelEleve.jtf3.setText(e.getMail());
	    panelEleve.jtf4.setText((e.getGroupe()));
	    panelEleve.jtf5.setText(e.getOrigine());
	    
	    this.setResizable(true);
        this.container.add(panelEleve.getPanelPrincipal(), BorderLayout.CENTER);
        this.setVisible(true);
        
	}

}
