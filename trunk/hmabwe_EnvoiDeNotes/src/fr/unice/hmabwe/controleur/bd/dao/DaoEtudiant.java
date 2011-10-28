package fr.unice.hmabwe.controleur.bd.dao;

import java.util.HashMap;

import fr.unice.hmabwe.modele.Cours;
import fr.unice.hmabwe.modele.Etudiant;

/**
 * 
 * @author Engilberge Swan
 */

public interface DaoEtudiant extends DaoGenerique<Etudiant, Integer> {
	
	//methode metier de etudiant
	
	public boolean etaitInscrit(String numEtu, String nomCours, int annee);
	
	public HashMap<Etudiant, String> listeInscrit(Cours cours, Integer annee);

}
