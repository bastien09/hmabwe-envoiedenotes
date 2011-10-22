package fr.unice.hmabwe.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**@author Anthony Biga
 * 
 * La classe Inscription repr�sente l'inscription d'un �tudiant � un cours.
 * Elle est mod�lis�e par la table hmabwe_inscription.
 * 
 * */

@Entity
@Table (name="hmabwe_inscription")
public class Inscription {
	
	/**Chaque inscription a un identifiant unique g�n�r� automatiquement.*/
	@Id
	@GeneratedValue
	int id;
	
	/**Une inscription concerne un �tudiant.*/
	@ManyToOne
	Etudiant etudiant;
	
	/**Une inscription concerne un cours.*/
	@ManyToOne
	Cours cours;
	
	/**Une inscription a lieu une ann�e donn�e.*/
	Integer annee;
	
	/**Dans le cadre de l'application EnvoieDe Notes, un �tudiant n'a qu'une seule note par cours, sa moyenne*/
	Double moyenne;
	
	/**Constructeur par d�faut*/
	public Inscription(){
		
	}
	
	/**Constructeur associant un �tudiant, un cours et une ann�e � une inscription
	 * @param e �tudiant de l'inscription � cr�er
	 * @param a ann�e de l'inscription � cr�er*/
	public Inscription(Etudiant e, Cours c, Integer a){
		etudiant=e;
		cours=c;
		cours.addInscription(this);
		annee=a;
	}
	
	/**Retourne l'id de l'inscription.
	 * @return �tudiant associ� � l'inscription*/
	public int getId(){
		return id;
	}
	
	/**Retourne l'�tudiant associ� � l'inscription.
	 * @return �tudiant associ� � l'inscription*/
	public Etudiant getEtudiant(){
		return etudiant;
	}
	
	/**Retourne l'ann�e associ� � l'inscription.
	 * @return ann�e associ� � l'inscription*/
	public Integer getAnnee(){
		return annee;
	}
	
	
	//Accesseurs pour l'attribut moyenne

	/**Retourne la moyenne de l'�tudiant associ� � l'inscription.
	 * @return moyenne de l'�tudiant associ� � l'inscription*/
	public Double getMoyenne(){
		return moyenne;
	}	

	/**Modifie la moyenne de l'�tudiant associ� � l'inscription.
	 * @param n nouvelle moyenne � attribuer � l'�tudiant
	 */
	public void setMoyenne(Double m){
		moyenne=m;
	}
}
