package fr.unice.hmabwe.modele;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**@author Anthony Biga
 * 
 * La classe Cours repr�sente un cours auquel sont associ�s un enseignant et des �tudiant.
 * Elle est mod�lis�e par la table hmabwe_cours.
 * 
 * */

@Entity
@Table (name="hmabwe_cours")
public class Cours {
	/**Chaque cours a un identifiant unique, c'est la cl� primaire(automatique) dans la table associ�e.*/
	@Id
	@GeneratedValue
	private int id;
	
	/**Un cours est d�sign� par son nom.*/
	private String nom;
	
	/**Un cours est pr�sent� par un enseignant.*/
	@ManyToOne
	private Enseignant enseignant;
	
	/**Un cours peut avoir une ou plusieurs inscriptions*/
	@OneToMany(mappedBy="cours")
	private Collection<Inscription> listeInscriptions;
	
	/**Un cours peut avoir plusieurs filli�res ayant chacune un coefficient.*/
	@OneToMany(mappedBy="cours")
	private Collection<Coefficient> listeCoeffFillieres;
	
	/**Constructeur par d�faut*/
	public Cours(){
		
	}
	
	/**Constructeur associant un nom � un cours
	 * @param n nom du cours � cr�er*/
	public Cours(String n){
		nom=n;
	}
	
	/**Constructeur associant un nom et un enseignant � un cours
	 * @param n nom de la filli�re � cr�er
	 * @param e enseignant du cours � cr�er*/
	public Cours(String n, Enseignant e){
		nom=n;
		enseignant=e;
		enseignant.addCours(this);
	}
	
	/**Retourne l'id du cours.
	 * @return identifiant du cours*/
	public int getId(){
		return id;
	}

	//Accesseurs pour l'attribut nom

	/**Retourne le nom du cours.
	 * @return nom de la filli�re*/
	public String getNom(){
		return nom;
	}

	/**Modifie le nom du cours si celui pass� en param�tre est diff�rent de null(retourne vrai si
	 * la modification s'est bien pass�e, faux sinon).
	 * @param n nouveau nom � attribuer au cours
	 * @return modification effectu�e ou non*/
	public boolean setNom(String n){
		boolean res = false;
		
		if(n != null && !n.equalsIgnoreCase("")){
			nom=n;
			res=true;
		}
		
		return res;
	}
	
	//Accesseurs pour l'attribut enseignant

	/**Retourne l'enseignant qui pr�sente le cours.
	 * @return enseignant qui pr�sente le cours*/
	public Enseignant getEnseignant(){
		return enseignant;
	}

	/**Modifie l'enseignant qui pr�sente le cours si celui pass� en param�tre est diff�rent de null(retourne vrai si
	 * la modification s'est bien pass�e, faux sinon).
	 * @param e nouvel enseignant qui pr�sente le cours
	 * @return modification effectu�e ou non*/
	public boolean setEnseignant(Enseignant e){
		boolean res = false;
		
		enseignant=e;
		
		if(e != null){
			e.addCours(this);
			res=true;
		}
		
		return res;
	}
	
//Accesseurs pour l'attribut listeInscription
	
	/**Retourne toutes les inscriptions au cours.
	 * @return inscriptions au cours*/
	public Collection<Inscription> getInscriptions(){
		return listeInscriptions;
	}
	
	/**Associe une nouvelle inscription � un cours si celle pass�e en param�tre est diff�rente de null(retourne vrai si
	 * la modification s'est bien pass�e, faux sinon).
	 * @param i nouvelle inscription � associer au cours
	 * @return modification effectu�e ou non*/
	public boolean addInscription(Inscription i){
		boolean res = false;
		
		if(i != null){
			listeInscriptions.add(i);
			res=true;
		}
		
		return res;
	}
	
	/**Retire l'inscription pass�e en param�tre de la liste des inscriptions au cours
	 * (retourne vrai si la modification s'est bien pass�e, faux sinon).
	 * @param i inscription � retirer au cours
	 * @return modification effectu�e ou non*/
	public boolean removeInscriptions(Inscription i){
		boolean res = false;
		
		if(i!=null){
			res=listeInscriptions.remove(i);
		}
		return res;
	}
	
	/**Vide la liste des inscriptions au cours.
	 *@return modification effectu�e ou non*/
	public void removeAllInscriptions(){
		listeInscriptions.clear();
	}
	
//Accesseurs pour l'attribut listeCoeffFillieres
	
	/**Associe une nouvelle filli�re(avec un coefficient) � un cours si cellle pass�e en param�tre est 
	 * diff�rente de null(retourne vrai si la modification s'est bien pass�e, faux sinon).
	 * @param f nouvelle filli�re � associer au cours
	 * @param coeff coefficient du cours � associer � la filli�re
	 * @return modification effectu�e ou non*/
	public boolean addFilliere(Filliere f, Integer coeff){
		boolean res = false;
		
		if(f != null){
			listeCoeffFillieres.add(new Coefficient(this, f, coeff));
			res=true;
		}
		
		return res;
	}
	
	/**Retire la filli�re pass�e en param�tre de la liste des filli�res associ�es � un cours
	 * (retourne vrai si la modification s'est bien pass�e, faux sinon).
	 * @param f filli�re � retirer du cours
	 * @return modification effectu�e ou non*/
	public boolean removeFilliere(Filliere f){
		boolean res=false;
		
		if(f!=null){
			for(Coefficient coeff: listeCoeffFillieres){
				if(coeff.getFilliere()==f){
					listeCoeffFillieres.remove(coeff);
					break;
				}
			}
		}
		return res;
	}
	
	/**Vide la liste des filli�res associ�es � un cours.
	 *@return modification effectu�e ou non*/
	public void removeAllFillieres(){
		listeCoeffFillieres.clear();
	}

}

//////////////////////////////PROBLEME A REGLER////////////////////////////////////////////////////

//removeInscription et removeAllInscriptions : suppression de(s) l'(les)inscription(s) dans la base

//removeFilliere et removeAllFillieres : suppression du(des) coefficient(s) dans la base

///////////////////////////////////////////////////////////////////////////////////////////////////