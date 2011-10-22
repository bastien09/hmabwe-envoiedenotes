package fr.unice.hmabwe.modele;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**@author Anthony Biga
 * 
 * La classe Etudiant repr�sente un �tudiant dans la table hmabwe_personne
 * (il n'y a pas de table hmabwe_�tudiant).
 * 
 * */

@Entity
public class Etudiant extends Personne{
	
	/**En plus de son id, chaque �tudiant dispose d'un num�ro unique qui l'identifie au sein de l'universit�*/
	private String numEtu;
	
	/**Un �tudiant est associ� � une filli�re.*/
	@ManyToOne
	private Filliere filliere;
	
	/**Un �tudiant est inscrit � un ou plusieurs cours.*/
	@OneToMany(mappedBy="etudiant")
	private Collection<Inscription> listeInscriptions;
	
	/**Constructeur par d�faut*/
	public Etudiant(){
		
	}

	/**Constructeur associant � un �tudiant un nom, un pr�nom et une adresse e-mail
	 * @param num num�ro de l'�tudiant � cr�er
	 * @param n nom de l'�tudiant � cr�er
	 * @param pn pr�nom de �tudiant � cr�er
	 * @param m adresse e-mail de �tudiant � cr�er*/
	public Etudiant(String num, String n, String pn, String m){
		super(n, pn, m);
		numEtu=num;
		listeInscriptions=new ArrayList<Inscription>();
	}
	
	/**Constructeur associant � un �tudiant un nom, un pr�nom, une adresse e-mail et une filli�re
	 * @param num num�ro de l'�tudiant � cr�er
	 * @param n nom de l'�tudiant � cr�er
	 * @param pn pr�nom de �tudiant � cr�er
	 * @param m adresse e-mail de �tudiant � cr�er
	 * @param f filli�re de l'�tudiant � cr�er*/
	public Etudiant(String num, String n, String pn, String m, Filliere f){
		super(n, pn, m);
		numEtu=num;
		filliere=f;
		filliere.addEtudiant(this);
		listeInscriptions=new ArrayList<Inscription>();
	}
	
	//Accesseurs pour l'attribut numEtu

	/**Retourne le num�ro de l'�tudiant.
	 * @return num�ro de l'�tudiant*/
	public String getNumEtu(){
		return numEtu;
	}

	/**Modifie le num�ro de l'�tudiant si celui pass� en param�tre est diff�rent de null(retourne vrai si
	 * la modification s'est bien pass�e, faux sinon).
	 * @param num nouveau num�ro � attribuer � l'�tudiant
	 * @return modification effectu�e ou non*/
	public boolean setNumEtu(String num){
		boolean res = false;
		
		if(num != null && !num.equalsIgnoreCase("")){
			numEtu=num;
			res=true;
		}
		
		return res;
	}
	
	//Accesseurs pour l'attribut filliere

	/**Retourne la filli�re de l'�tudiant.
	 * @return filli�re de l'�tudiant*/
	public Filliere getFilliere(){
		return filliere;
	}

	/**Modifie le num�ro de l'�tudiant si celui pass� en param�tre est diff�rent de null(retourne vrai si
	 * la modification s'est bien pass�e, faux sinon).
	 * @param num nouveau num�ro � attribuer � l'�tudiant
	 * @return modification effectu�e ou non*/
	public boolean setFilliere(Filliere f){
		boolean res = false;
		
		if(f != null){
			filliere.removeEtudiant(this);
			filliere=f;
			filliere.addEtudiant(this);
			res=true;
		}
		
		return res;
	}
	
	//Accesseurs pour l'attribut listeInscriptions
	
	/**Retourne toutes les inscriptions de l'�tudiant.*/
	public Collection<Inscription> getInscriptions(){
		return listeInscriptions;
	}
	
	/**Associe une nouvelle inscription � un �tudiant si celle pass�e en param�tre est diff�rente de null(retourne vrai si
	 * la modification s'est bien pass�e, faux sinon).
	 * @param i nouvelle inscription � associer � l'�tudiant
	 * @return modification effectu�e ou non*/
	public boolean addInscription(Inscription i){
		boolean res = false;
		
		if(i != null){
			listeInscriptions.add(i);
			res=true;
		}
		
		return res;
	}
	
	/**Retire l'inscription pass�e en param�tre de la liste des inscriptions associ�es � un �tudiant
	 * (retourne vrai si la modification s'est bien pass�e, faux sinon).
	 * @param i inscription � retirer � l'�tudiant
	 * @return modification effectu�e ou non*/
	public boolean removeInscription(Inscription i){
		boolean res=false;
		if(i!=null){
			res=listeInscriptions.remove(i);
		}
		return res;
	}
	
	/**Vide la liste des inscriptions associ�es � un �tudiant.
	 *@return modification effectu�e ou non*/
	public void removeAllInscriptions(){
		listeInscriptions.clear();
	}
}

//////////////////////////////PROBLEME A REGLER////////////////////////////////////////////////////

//removeInscription et removeAllInscriptions : suppression de(s) l'(les)inscription(s) dans la base

///////////////////////////////////////////////////////////////////////////////////////////////////
