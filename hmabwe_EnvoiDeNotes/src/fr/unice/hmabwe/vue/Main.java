package fr.unice.hmabwe.vue;

import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;
import fr.unice.hmabwe.modele.Etudiant;

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
		Etudiant e = new Etudiant();
		FenetreAjoutEleve fenetre = new FenetreAjoutEleve(df);
		FenetreAjoutFiliere fenetre1 = new FenetreAjoutFiliere(df);
		FenetreAjoutCours fenetre2 = new FenetreAjoutCours(df);
		FenetreGestionEnseignants fenetre3 = new FenetreGestionEnseignants(df);
		//FenetreStatistiqueFiliere fenetre4 = new FenetreStatistiqueFiliere(df);
		FenetreStatistiqueEtudiant fenetre5 = new FenetreStatistiqueEtudiant(df, e);
	}
}
