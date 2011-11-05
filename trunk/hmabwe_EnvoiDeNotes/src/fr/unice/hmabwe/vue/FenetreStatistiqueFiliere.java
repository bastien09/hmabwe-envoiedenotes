package fr.unice.hmabwe.vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

import fr.unice.hmabwe.controleur.bd.Connexion;
import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Fenetre permettant d'afficher les statistiques d'une filière
 *
 */

public class FenetreStatistiqueFiliere extends JFrame{
	private PanelStatistiqueFiliere panelStatFili;
	
	private DaoFabrique df;
	
	private Connexion conn;
	
	public FenetreStatistiqueFiliere(int notemoyenneG, int notemoyenneGr1, int notemoyenneGr2, int noteEcartType, int notemoyenneG2, int notemoyenneG12, int notemoyenneG22, DaoFabrique df){
		//TODO Dans le constructeur ajouter la connexion a la base.
		//TODO Remplacer les moyennes dans le constructeur, par les moyennes recus de la database.
		this.df = df;
		
		this.conn = df.getConnexion();
		
		panelStatFili = new PanelStatistiqueFiliere();
				
		this.setTitle("Statistiques Flières");
        this.setSize(350, 350);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
		this.setContentPane(panelStatFili.getPanelPrincipal());
		this.setVisible(true);
		
		
		
	}
	

}
