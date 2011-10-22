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
 * La classe Filliere représente une fillière à laquelle sont associés des enseignants(dont un responsable)
 * et des étudiant. Elle est modélisée par la table hmabwe_filliere.
 * 
 * */

@Entity
@Table (name="hmabwe_filliere")
public class Filliere {
	
	/**Chaque fillière a un identifiant unique, c'est la clé primaire(automatique) dans la table associée.*/
	@Id
	@GeneratedValue
	private int id;
	
	/**Une fillière est désignée par son nom.*/
	private String nom;
	
	/**Une fillière a un responsable.*/
	@ManyToOne
	private Enseignant responsable;
	
	/**Une fillière peut contenir plusieurs étudiants.*/
	@OneToMany(mappedBy="filliere")
	private Collection<Etudiant> listeEtudiants;
	
	/**Une fillière peut avoir plusieurs cours ayant chacun un coefficient.*/
	@OneToMany(mappedBy="filliere")
	private Collection<Coefficient> listeCoeffCours;
	
	/**Constructeur par défaut*/
	public Filliere(){
		
	}
	
	/**Constructeur associant un nom à une fillière
	 * @param n nom de la fillière à créer*/
	public Filliere(String n){
		nom=n;
	}
	
	/**Constructeur associant un nom et un responsable à une fillière
	 * @param n nom de la fillière à créer
	 * @param e responsable de la fillière à créer*/
	public Filliere(String n, Enseignant e){
		nom=n;
		responsable=e;
		responsable.addFilliere(this);
	}
	
	/**Retourne l'id de la fillière.
	 * @return identifiant de la fillière*/
	public int getId(){
		return id;
	}

	//Accesseurs pour l'attribut nom

	/**Retourne le nom de la fillière.
	 * @return nom de la fillière*/
	public String getNom(){
		return nom;
	}

	/**Modifie le nom de la fillière si celui passé en paramètre est différent de null(retourne vrai si
	 * la modification s'est bien passée, faux sinon).
	 * @param n nouveau nom à attribuer à la fillière
	 * @return modification effectuée ou non*/
	public boolean setNom(String n){
		boolean res = false;
		
		if(n != null && !n.equalsIgnoreCase("")){
			nom=n;
			res=true;
		}
		
		return res;
	}
	
	//Accesseurs pour l'attribut listeEtudiants
	
	/**Associe un nouvel étudiant à une fillière si celui passé en paramètre est différent de null
	 * (retourne vrai si la modification s'est bien passée, faux sinon).
	 * @param e nouvel étudiant à associer à la filli�re
	 * @return modification effectuée ou non*/
	public boolean addEtudiant(Etudiant e){
		boolean res = false;
		
		if(e != null){
			listeEtudiants.add(e);
			res=true;
		}
		
		return res;
	}
	
	/**Retire l'étudiant passé en paramétre de la liste des étudiant associés à une fillière
	 * (retourne vrai si la modification s'est bien passée, faux sinon).
	 * @param e étudiant à retirer de la fillière
	 * @return modification effectuée ou non*/
	public boolean removeEtudiant(Etudiant e){
		boolean res = false;
		if(e!=null){
			res=listeEtudiants.remove(e);
		}
		return res;
	}
	
	/**Vide la liste des étudiants associés à une fillière.*/
	public void removeAllEtudiants(){
		listeEtudiants.clear();
	}
	
//Accesseurs pour l'attribut listeCours
	
	/**Associe un nouveau cours(avec un coefficient) à une fillière si celui passé en paramètre est 
	 * différent de null(retourne vrai si la modification s'est bien passée, faux sinon).
	 * @param c nouveau cours à associer à la fillière
	 * @param coeff coefficient du cours à associer à la fillière
	 * @return modification effectuée ou non*/
	public boolean addCours(Cours c, Integer coeff){
		boolean res = false;
		
		if(c != null){
			listeCoeffCours.add(new Coefficient(c, this, coeff));
			res=true;
		}
		
		return res;
	}
	
	/**Retire le cours passé en paramètre de la liste des cours associés à une fillière
	 * (retourne vrai si la modification s'est bien passée, faux sinon).
	 * @param c cours à retirer de la fillière
	 * @return modification effectuée ou non*/
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
	
	/**Vide la liste des cours associés à une fillière.*/
	public void removeAllCours(){
		listeCoeffCours.clear();
	}
	
	//Accesseurs pour l'attribut responsable

	/**Retourne le responsable de la fillière.
	 * @return responsable de la fillière*/
	public Enseignant getResponsable(){
		return responsable;
	}

	/**Modifie le responsable de la fillière si celui passé en paramètre est différent de null(retourne vrai si
	 * la modification s'est bien passée, faux sinon).
	 * @param num nouveau numéro à attribuer à l'étudiant
	 * @return modification effectuée ou non*/
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

