package fr.unice.hmabwe.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**@author Anthony Biga
 * 
 * La classe Coefficient repr�sente l'association d'un cours � une filli�re avec un certain coefficient.
 * Elle est mod�lis�e par la table hmabwe_coefficient.
 * 
 * */

@Entity
@Table (name="hmabwe_coefficient")
public class Coefficient {
	
	/**Chaque instance de la classe Coefficient � un identifiant unique, c'est la cl� primaire(automatique)
	 * dans la table associ�e.*/
	@Id
	@GeneratedValue
	private int id;
	
	/**Un coefficient est associ� � un cours*/
	@ManyToOne
	private Cours cours;
	
	/**Un coefficient est associ� � une filli�re*/
	@ManyToOne
	private Filliere filliere;
	
	/**Coefficient pour un cours dans une filli�re*/
	private Integer coefficient;
	
	/**Constructeur par d�faut*/
	public Coefficient(){
	}
	
	/**Constructeur associant au coefficient un cours et une fill�re*/
	public Coefficient(Cours c, Filliere f, Integer coeff){
		cours=c;
		filliere=f;
		coefficient=coeff;
	}
	
	/**Retourne le cours associ� � une instance de la classe Coefficient*/
	public int getId() {
		return id;
	}

	/**Retourne le cours associ� � une instance de la classe Coefficient*/
	public Cours getCours() {
		return cours;
	}

	/**Retourne la filli�re associ�e � une instance de la classe Coefficient*/
	public Filliere getFilliere() {
		return filliere;
	}
	
	/**Retourne le cours associ� � une instance de la classe Coefficient*/
	public Integer getCoefficient() {
		return coefficient;
	}

}
