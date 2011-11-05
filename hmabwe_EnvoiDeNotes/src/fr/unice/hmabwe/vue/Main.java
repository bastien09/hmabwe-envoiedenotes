package fr.unice.hmabwe.vue;

import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Affichage des fenetres pour avoir une vue d'ensemble
 *
 */

public class Main {
	public static void main(String[] args){
		DaoFabrique.setTypeDao(DaoFabrique.TypeFabrique.JPA);

		/* je demande une fabrique */
		DaoFabrique df = DaoFabrique.getDaoFabrique();
		
		FenetreAjoutEleve fenetre = new FenetreAjoutEleve(df);
		FenetreAjoutFiliere fenetre1 = new FenetreAjoutFiliere(df);
		FenetreAjoutCours fenetre2 = new FenetreAjoutCours(df);
		FenetreGestionEnseignants fenetre3 = new FenetreGestionEnseignants(df);
		FenetreStatistiqueFiliere fenetre4 = new FenetreStatistiqueFiliere(10, 13, 15, 9, 7, 20, 11, df);
		FenetreStatistiqueEtudiant fenetre5 = new FenetreStatistiqueEtudiant(df);
	}
}
