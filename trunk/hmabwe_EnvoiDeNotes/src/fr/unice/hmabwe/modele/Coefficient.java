package fr.unice.hmabwe.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**@author Anthony Biga
 * 
 * La classe Coefficient représente l'association d'un cours à une fillière avec un certain coefficient.
 * Elle est modélisée par la table hmabwe_coefficient.
 * 
 * */

@Entity
@Table (name="hmabwe_coefficient")
public class Coefficient {
	
	/**Chaque instance de la classe Coefficient a un identifiant unique, c'est la clé primaire(automatique)
	 * dans la table associée.*/
	@Id
	@GeneratedValue
	private int id;
	
	/**Un coefficient est associé à un cours*/
	@ManyToOne
	private Cours cours;
	
	/**Un coefficient est associé à une fillière*/
	@ManyToOne
	private Filliere filliere;
	
	/**Coefficient pour un cours dans une fillière*/
	private Integer coefficient;
	
	/**Constructeur par défaut*/
	public Coefficient(){
	}
	
	/**Constructeur associant au coefficient un cours et une fillère*/
	public Coefficient(Cours c, Filliere f, Integer coeff){
		cours=c;
		filliere=f;
		coefficient=coeff;
	}
	
	/**Retourne le cours associé à une instance de la classe Coefficient
	 * @return id de l'instance de Coefficient*/
	public int getId() {
		return id;
	}

	/**Retourne le cours associé à une instance de la classe Coefficient
	 * @return cours de l'instance de Coefficient*/
	public Cours getCours() {
		return cours;
	}

	/**Retourne la fillière associée à une instance de la classe Coefficient
	 * @return fillière de l'instance de Coefficient*/
	public Filliere getFilliere() {
		return filliere;
	}
	
	/**Retourne le cours associé à une instance de la classe Coefficient
	 * @return coefficient de l'instance de Coefficient*/
	public Integer getCoefficient() {
		return coefficient;
	}

}
