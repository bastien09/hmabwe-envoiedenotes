package fr.unice.hmabwe.vue;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.*;

import fr.unice.hmabwe.controleur.bd.Connexion;
import fr.unice.hmabwe.controleur.bd.dao.DaoException;
import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Fenetre permettant d'afficher les statistiques d'un etudiant
 *
 */

public class FenetreStatistiqueEtudiant extends JFrame{
	private Connexion conn;
	
	private DaoFabrique df;
	
	private PanelStatistiqueEtudiant panelStatEtu;
	
	public FenetreStatistiqueEtudiant(DaoFabrique df){
		//TODO Dans le constructeur ajouter la connexion a la base.
		//TODO Remplacer les moyennes de l'arraylist, par les moyennes recus de la database(ArrayList), ainsi que le nom des cours.
		panelStatEtu = new PanelStatistiqueEtudiant();
		this.df = df;
		this.conn = df.getConnexion();
		
		
		//TODO a voir avec Bastien !Est-ce que c'est Fenetre Principal qui revoi l'etudiant ?
		
		
		
		
		
		this.setTitle("Statistiques Etudiant");
        this.setSize(350, 350);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
		this.setContentPane(panelStatEtu.getPanelPrincipal());
		this.setVisible(true);
		
		
		
	}
	
}
