package fr.unice.hmabwe.modele;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**@author Anthony Biga
 * 
 * La classe Etudiant représente un étudiant dans la table hmabwe_personne
 * (il n'y a pas de table hmabwe_etudiant).
 * 
 * */

@Entity
@DiscriminatorValue("etudiant")
public class Etudiant extends Personne{
	
	/**En plus de son id, chaque étudiant dispose d'un numéro unique qui l'identifie au sein de l'université*/
	private String numEtu;
	
	/**Un étudiant a une fillière d'origine, elle est de type String car elle n'est pas forcemment 
	 * dans la base de données de l'application.*/
	private String origine;
	
	/**Un étudiant est associé à une fillière.*/
	@ManyToOne
	private Filliere filliere;
	
	/**Un étudiant est inscrit à un ou plusieurs cours.*/
	@OneToMany(mappedBy="etudiant")
	private Collection<Inscription> listeInscriptions;
	
	/**Constructeur par défaut*/
	public Etudiant(){
		
	}

	/**Constructeur associant à un étudiant un nom, un prénom et une adresse e-mail
	 * @param num numéro de l'étudiant à créer
	 * @param n nom de l'étudiant à créer
	 * @param pn prénom de étudiant à créer
	 * @param m adresse e-mail de l'étudiant à créer*/
	public Etudiant(String num, String n, String pn, String m){
		super(n, pn, m);
		numEtu=num;
		listeInscriptions=new ArrayList<Inscription>();
	}
	
	/**Constructeur associant à un étudiant un nom, un prénom, une adresse e-mail et une origine
	 * @param num numéro de l'étudiant à créer
	 * @param n nom de l'étudiant à créer
	 * @param pn prénom de étudiant à créer
	 * @param m adresse e-mail de l'étudiant à créer
	 * @param o origine de l'étudiant à créer*/
	public Etudiant(String num, String n, String pn, String m, String o){
		this(num, n, pn, m);
		origine=o;
		listeInscriptions=new ArrayList<Inscription>();
	}
	
	/**Constructeur associant à un étudiant un nom, un prénom, une adresse e-mail, une origine 
	 * et une fillière
	 * @param num numéro de l'étudiant à créer
	 * @param n nom de l'étudiant à créer
	 * @param pn prénom de l'étudiant à créer
	 * @param m adresse e-mail de l'étudiant à créer
	 * @param o origine de l'étudiant à créer
	 * @param f fillière de l'étudiant à créer*/
	public Etudiant(String num, String n, String pn, String m, String o, Filliere f){
		this(num, n, pn, m, o);
		filliere=f;
		//filliere.addEtudiant(this);
		listeInscriptions=new ArrayList<Inscription>();
	}
	
	//Accesseurs pour l'attribut numEtu

	/**Retourne le numéro de l'étudiant.
	 * @return numéro de l'étudiant*/
	public String getNumEtu(){
		return numEtu;
	}

	/**Modifie le numéro de l'étudiant si celui passé en paramètre est différent de null(retourne vrai si
	 * la modification s'est bien passée, faux sinon).
	 * @param num nouveau numéro à attribuer à l'étudiant
	 * @return modification effectuée ou non*/
	public boolean setNumEtu(String num){
		boolean res = false;
		
		if(num != null && !num.equalsIgnoreCase("")){
			numEtu=num;
			res=true;
		}
		
		return res;
	}
	
	
	//Accesseurs pour l'attribut origine

	/**Retourne l'origine de l'étudiant.
	 * @return origine de l'étudiant*/
	public String getOrigine(){
		return origine;
	}

	/**Modifie l'origine de l'étudiant si celle passée en paramètre est différente de null(retourne vrai si
	 * la modification s'est bien passée, faux sinon).
	 * @param origine nouvelle origine à attribuer à l'étudiant
	 * @return modification effectuée ou non*/
	public boolean setOrigine(String o){
		boolean res = false;
		
		if(o != null && !o.equalsIgnoreCase("")){
			origine=o;
			res=true;
		}
		
		return res;
	}
	
	//Accesseurs pour l'attribut filliere

	/**Retourne la fillière de l'étudiant.
	 * @return fillière de l'étudiant*/
	public Filliere getFilliere(){
		return filliere;
	}

	/**Modifie le numéro de l'étudiant si celui passé en paramètre est différent de null(retourne vrai si
	 * la modification s'est bien passée, faux sinon).
	 * @param f nouvelle fillière à attribuer à l'étudiant
	 * @return modification effectuée ou non*/
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
	
	/**Retourne toutes les inscriptions de l'étudiant.
	 * @return liste des inscriptions de l'étudiant*/
	public Collection<Inscription> getInscriptions(){
		return listeInscriptions;
	}
	
	/**Associe une nouvelle inscription à un étudiant si celle passée en paramètre est différente de null
	 * (retourne vrai si la modification s'est bien passée, faux sinon).
	 * @param i nouvelle inscription à associer à l'étudiant
	 * @return modification effectuée ou non*/
	public boolean addInscription(Inscription i){
		boolean res = false;
		
		if(i != null){
			listeInscriptions.add(i);
			res=true;
		}
		
		return res;
	}
	
	/**Retire l'inscription passée en paramètre de la liste des inscriptions associées à un étudiant
	 * (retourne vrai si la modification s'est bien passée, faux sinon).
	 * @param i inscription à retirer à l'étudiant
	 * @return modification effectuée ou non*/
	public boolean removeInscription(Inscription i){
		boolean res=false;
		if(i!=null){
			res=listeInscriptions.remove(i);
		}
		return res;
	}
	
	/**Vide la liste des inscriptions associées à un étudiant.*/
	public void removeAllInscriptions(){
		listeInscriptions.clear();
	}
}

//////////////////////////////PROBLEME A REGLER////////////////////////////////////////////////////

//removeInscription et removeAllInscriptions : suppression de(s) l'(les)inscription(s) dans la base

///////////////////////////////////////////////////////////////////////////////////////////////////
