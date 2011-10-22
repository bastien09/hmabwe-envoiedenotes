package fr.unice.hmabwe.modele;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**@author Anthony Biga
 * 
 * La classe Enseignant repr�sente un enseignant dans la table hmabwe_personne
 * (il n'y a pas de table hmabwe_enseignant).
 * 
 * */

@Entity
public class Enseignant extends Personne{
	
	/**Un enseignant peut �tre responsable d'une ou plusieurs filli�res*/
	@OneToMany(mappedBy="responsable")
	private Collection<Filliere> listeFillieres;
	
	/**Un enseignant peut pr�senter un ou plusieurs cours*/
	@OneToMany(mappedBy="enseignant")
	private Collection<Cours> listeCours;
	
	/**Constructeur par d�faut*/
	public Enseignant(){
		
	}

	/**Constructeur associant � un enseignant un nom, un pr�nom et une adresse e-mail
	 * @param n nom de l'enseignant � cr�er
	 * @param pn pr�nom de l'enseignant � cr�er
	 * @param m adresse e-mail de l'enseignant � cr�er*/
	public Enseignant(String n, String pn, String m){
		super(n, pn, m);
	}
	
	//Accesseurs pour l'attribut listeFillieres
	
	/**Retourne toutes les filli�res dont l'enseignant est responsable.
	 * @return filli�res dont l'enseignant est responsable*/
	public Collection<Filliere> getFillieres(){
		return listeFillieres;
	}
	
	/**Associe une nouvelle filli�re � un enseignant si celle pass�e en param�tre est diff�rente de null(retourne vrai si
	 * la modification s'est bien pass�e, faux sinon).
	 * @param f nouvelle filli�re � associer � l'enseignant
	 * @return modification effectu�e ou non*/
	public boolean addFilliere(Filliere f){
		boolean res = false;
		
		if(f != null){
			listeFillieres.add(f);
			f.setResponsable(this);
			res=true;
		}
		
		return res;
	}
	
	/**Retire la filli�re pass�e en param�tre de la liste des filli�res dont l'enseignant est responsable
	 * (retourne vrai si la modification s'est bien pass�e, faux sinon).
	 * @param f filli�re � retirer � l'enseignant
	 * @return modification effectu�e ou non*/
	public boolean removeFilliere(Filliere f){
		boolean res = false;
		if(f!=null){
			f.setResponsable(null);
			res=listeFillieres.remove(f);
		}
		return res;
	}
	
	/**Vide la liste des filli�res dont l'enseignant est responsable.
	 *@return modification effectu�e ou non*/
	public void removeAllFilli�res(){
		for(Filliere f : listeFillieres){
			f.setResponsable(null);
		}
		listeFillieres.clear();
	}
	
	//Accesseurs pour l'attribut listeCours
	
	/**Retourne touts les cours pr�sent�s par l'enseignant.
	 * @return cours pr�sent�s par l'enseignant*/
	public Collection<Cours> getCours(){
		return listeCours;
	}
	
	/**Associe un nouveau cours � un enseignant si celui pass� en param�tre est diff�rent de null(retourne vrai si
	 * la modification s'est bien pass�e, faux sinon).
	 * @param c nouveau cours � associer � l'enseignant
	 * @return modification effectu�e ou non*/
	public boolean addCours(Cours c){
		boolean res = false;
		
		if(c != null){
			listeCours.add(c);
			c.setEnseignant(this);
			res=true;
		}
		
		return res;
	}
	
	/**Retire le cours pass� en param�tre de la liste des cours pr�sent�s par l'enseignant
	 * (retourne vrai si la modification s'est bien pass�e, faux sinon).
	 * @param c cours � retirer � l'enseignant
	 * @return modification effectu�e ou non*/
	public boolean removeCours(Cours c){
		boolean res = false;
		if(c!=null){
			c.setEnseignant(null);
			res=listeCours.remove(c);
		}
		return res;
	}
	
	/**Vide la liste des cours pr�sent�s par l'enseignant.
	 *@return modification effectu�e ou non*/
	public void removeAllCours(){
		for(Cours c : listeCours){
			c.setEnseignant(null);
		}
		listeCours.clear();
	}
}
