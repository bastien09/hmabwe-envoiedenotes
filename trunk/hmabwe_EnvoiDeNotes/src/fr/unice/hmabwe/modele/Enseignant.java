package fr.unice.hmabwe.modele;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**@author Anthony Biga
 * 
 * La classe Enseignant représente un enseignant dans la table hmabwe_personne
 * (il n'y a pas de table hmabwe_enseignant).
 * 
 * */

@Entity
public class Enseignant extends Personne{
	
	/**Un enseignant peut être responsable d'une ou plusieurs fillières*/
	@OneToMany(mappedBy="responsable")
	private Collection<Filliere> listeFillieres;
	
	/**Un enseignant peut présenter un ou plusieurs cours*/
	@OneToMany(mappedBy="enseignant")
	private Collection<Cours> listeCours;
	
	/**Constructeur par défaut*/
	public Enseignant(){
		
	}

	/**Constructeur associant à un enseignant un nom, un prénom et une adresse e-mail
	 * @param n nom de l'enseignant à créer
	 * @param pn prénom de l'enseignant à créer
	 * @param m adresse e-mail de l'enseignant à créer*/
	public Enseignant(String n, String pn, String m){
		super(n, pn, m);
	}
	
	//Accesseurs pour l'attribut listeFillieres
	
	/**Retourne toutes les fillières dont l'enseignant est responsable.
	 * @return fillières dont l'enseignant est responsable*/
	public Collection<Filliere> getFillieres(){
		return listeFillieres;
	}
	
	/**Associe une nouvelle fillière à un enseignant si celle passée en paramètre est différente de null(retourne vrai si
	 * la modification s'est bien passée, faux sinon).
	 * @param f nouvelle fillière à associer à l'enseignant
	 * @return modification effectuée ou non*/
	public boolean addFilliere(Filliere f){
		boolean res = false;
		
		if(f != null){
			listeFillieres.add(f);
			f.setResponsable(this);
			res=true;
		}
		
		return res;
	}
	
	/**Retire la fillière passée en paramètre de la liste des fillières dont l'enseignant est responsable
	 * (retourne vrai si la modification s'est bien passée, faux sinon).
	 * @param f fillière à retirer à l'enseignant
	 * @return modification effectuée ou non*/
	public boolean removeFilliere(Filliere f){
		boolean res = false;
		if(f!=null){
			f.setResponsable(null);
			res=listeFillieres.remove(f);
		}
		return res;
	}
	
	/**Vide la liste des fillières dont l'enseignant est responsable.
	 *@return modification effectuée ou non*/
	public void removeAllFillières(){
		for(Filliere f : listeFillieres){
			f.setResponsable(null);
		}
		listeFillieres.clear();
	}
	
	//Accesseurs pour l'attribut listeCours
	
	/**Retourne touts les cours présentés par l'enseignant.
	 * @return cours présentés par l'enseignant*/
	public Collection<Cours> getCours(){
		return listeCours;
	}
	
	/**Associe un nouveau cours à un enseignant si celui passé en paramètre est différent de null(retourne vrai si
	 * la modification s'est bien passée, faux sinon).
	 * @param c nouveau cours à associer à l'enseignant
	 * @return modification effectuée ou non*/
	public boolean addCours(Cours c){
		boolean res = false;
		
		if(c != null){
			listeCours.add(c);
			c.setEnseignant(this);
			res=true;
		}
		
		return res;
	}
	
	/**Retire le cours passé en paramètre de la liste des cours présentés par l'enseignant
	 * (retourne vrai si la modification s'est bien passée, faux sinon).
	 * @param c cours à retirer à l'enseignant
	 * @return modification effectuée ou non*/
	public boolean removeCours(Cours c){
		boolean res = false;
		if(c!=null){
			c.setEnseignant(null);
			res=listeCours.remove(c);
		}
		return res;
	}
	
	/**Vide la liste des cours présentés par l'enseignant.
	 *@return modification effectuée ou non*/
	public void removeAllCours(){
		for(Cours c : listeCours){
			c.setEnseignant(null);
		}
		listeCours.clear();
	}
}
