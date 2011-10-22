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
 * La classe Filliere repr�sente une filli�re � laquelle sont associ�s des enseignants(dont un responsable)
 * et des �tudiant. Elle est mod�lis�e par la table hmabwe_filliere.
 * 
 * */

@Entity
@Table (name="hmabwe_filliere")
public class Filliere {
	
	/**Chaque filli�re a un identifiant unique, c'est la cl� primaire(automatique) dans la table associ�e.*/
	@Id
	@GeneratedValue
	private int id;
	
	/**Une filli�re est d�sign�e par son nom.*/
	private String nom;
	
	/**Une filli�re a un responsable.*/
	@ManyToOne
	private Enseignant responsable;
	
	/**Une filli�re peut contenir plusieurs �tudiants.*/
	@OneToMany(mappedBy="filliere")
	private Collection<Etudiant> listeEtudiants;
	
	/**Une filli�re peut avoir plusieurs cours ayant chacun un coefficient.*/
	@OneToMany(mappedBy="filliere")
	private Collection<Coefficient> listeCoeffCours;
	
	/**Constructeur par d�faut*/
	public Filliere(){
		
	}
	
	/**Constructeur associant un nom � une filli�re
	 * @param n nom de la filli�re � cr�er*/
	public Filliere(String n){
		nom=n;
	}
	
	/**Constructeur associant un nom et un responsable � une filli�re
	 * @param n nom de la filli�re � cr�er
	 * @param e responsable de la filli�re � cr�er*/
	public Filliere(String n, Enseignant e){
		nom=n;
		responsable=e;
		responsable.addFilliere(this);
	}
	
	/**Retourne l'id de la filli�re.
	 * @return identifiant de la filli�re*/
	public int getId(){
		return id;
	}

	//Accesseurs pour l'attribut nom

	/**Retourne le nom de la filli�re.
	 * @return nom de la filli�re*/
	public String getNom(){
		return nom;
	}

	/**Modifie le nom de la filli�re si celui pass� en param�tre est diff�rent de null(retourne vrai si
	 * la modification s'est bien pass�e, faux sinon).
	 * @param n nouveau nom � attribuer � la filli�re
	 * @return modification effectu�e ou non*/
	public boolean setNom(String n){
		boolean res = false;
		
		if(n != null && !n.equalsIgnoreCase("")){
			nom=n;
			res=true;
		}
		
		return res;
	}
	
	//Accesseurs pour l'attribut listeEtudiants
	
	/**Associe un nouvel �tudiant � une filli�re si celui pass� en param�tre est diff�rent de null(retourne vrai si
	 * la modification s'est bien pass�e, faux sinon).
	 * @param e nouvel �tudiant � associer � la filli�re
	 * @return modification effectu�e ou non*/
	public boolean addEtudiant(Etudiant e){
		boolean res = false;
		
		if(e != null){
			listeEtudiants.add(e);
			res=true;
		}
		
		return res;
	}
	
	/**Retire l'�tudiant pass�e en param�tre de la liste des �tudiant associ�s � une filli�re
	 * (retourne vrai si la modification s'est bien pass�e, faux sinon).
	 * @param e �tudiant � retirer de la filli�re
	 * @return modification effectu�e ou non*/
	public boolean removeEtudiant(Etudiant e){
		boolean res = false;
		if(e!=null){
			res=listeEtudiants.remove(e);
		}
		return res;
	}
	
	/**Vide la liste des �tudiants associ�s � une filli�re.
	 *@return modification effectu�e ou non*/
	public void removeAllEtudiants(){
		listeEtudiants.clear();
	}
	
//Accesseurs pour l'attribut listeCours
	
	/**Associe un nouveau cours(avec un coefficient) � une filli�re si celui pass� en param�tre est 
	 * diff�rent de null(retourne vrai si la modification s'est bien pass�e, faux sinon).
	 * @param c nouveau cours � associer � la filli�re
	 * @param coeff coefficient du cours � associer � la filli�re
	 * @return modification effectu�e ou non*/
	public boolean addCours(Cours c, Integer coeff){
		boolean res = false;
		
		if(c != null){
			listeCoeffCours.add(new Coefficient(c, this, coeff));
			res=true;
		}
		
		return res;
	}
	
	/**Retire le cours pass� en param�tre de la liste des cours associ�s � une filli�re
	 * (retourne vrai si la modification s'est bien pass�e, faux sinon).
	 * @param c cours � retirer de la filli�re
	 * @return modification effectu�e ou non*/
	public boolean removeCours(Cours c){
		boolean res=false;
		
		if(c!=null){
			for(Coefficient coeff: listeCoeffCours){
				if(coeff.getCours()==c){
					listeCoeffCours.remove(coeff);
					break;
				}
			}
		}
		return res;
	}
	
	/**Vide la liste des cours associ�s � une filli�re.
	 *@return modification effectu�e ou non*/
	public void removeAllCours(){
		listeCoeffCours.clear();
	}
	
	//Accesseurs pour l'attribut responsable

	/**Retourne le responsable de la filli�re.
	 * @return filli�re de l'�tudiant*/
	public Enseignant getResponsable(){
		return responsable;
	}

	/**Modifie le responsable de la filli�re si celui pass� en param�tre est diff�rent de null(retourne vrai si
	 * la modification s'est bien pass�e, faux sinon).
	 * @param num nouveau num�ro � attribuer � l'�tudiant
	 * @return modification effectu�e ou non*/
	public boolean setResponsable(Enseignant e){
		boolean res = false;
		responsable=e;
		if(e!= null){
			e.removeFilliere(this);
			e.addFilliere(this);
			res=true;
		}
		
		return res;
	}

}

//////////////////////////////PROBLEME A REGLER////////////////////////////////////////////////////

//removeCours et removeAllCours : suppression du(des) coefficient(s) dans la base

///////////////////////////////////////////////////////////////////////////////////////////////////

