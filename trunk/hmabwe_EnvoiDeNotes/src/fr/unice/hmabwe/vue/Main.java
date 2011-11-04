package fr.unice.hmabwe.vue;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Affichage des fenetres pour avoir une vue d'ensemble
 *
 */

public class Main {
	public static void main(String[] args){
		
		FenetreAjoutEleve fenetre = new FenetreAjoutEleve("Ajouter un élève", 450, 400);
		FenetreAjoutFiliere fenetre1 = new FenetreAjoutFiliere("Ajout/Edition d'une filière", 500, 300);
		FenetreAjoutCours fenetre2 = new FenetreAjoutCours("Ajout/Edition de cours", 400, 250);
		FenetreGestionEnseignants fenetre3 = new FenetreGestionEnseignants("Gestion des enseignants", 500, 500);
		FenetreStatistiqueFiliere fenetre4 = new FenetreStatistiqueFiliere(10, 13, 15, 9, 7, 20, 11);
		FenetreStatistiqueEtudiant fenetre5 = new FenetreStatistiqueEtudiant();
	}
}
