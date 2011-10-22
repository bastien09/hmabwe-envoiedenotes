package fr.unice.hmabwe.modele;

/**@author Anthony Biga
 * 
 * La classe Personne repr�sente la table hmabwe_personne. 
 * La strat�gie choisie pour le mapping objet/relationnel �tant une seule table par
 * arborescence d'h�ritage, les classes h�ritant de Personne ne sont pas repr�sent�es par des tables diff�rentes
 * dans la base de donn�es de l'application EnvoieDeNotes.
 * 
 * */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hmabwe_personne")
public class Personne {
	
	/**Identifiant d'une personne (cl�e primaire de la table hmabwe_personne g�n�r�e automatiquement)*/
	@Id
	@GeneratedValue
	protected int id;
	
	/**nom d'une personne*/
	protected String nom;
	
	/**pr�nom d'une personne*/
	protected String prenom;
	
	/**adresse e-mail d'une personne. Dans le cadre de l'application EnvoieDe Notes chaque personne
	 * doit obligatoirement avoir une adresse e-mail.*/
	protected String mail;

	/**Constructeur par d�faut*/
public Personne(){
	
}

/**Constructeur associant � une personne un nom, un pr�nom et une adresse e-mail
 * @param n nom de la personne � cr�er
 * @param pn pr�nom de la personne � cr�er
 * @param m adresse e-mail de la personne � cr�er*/
public Personne(String n, String pn, String m){
	nom=n;
	prenom=pn;
	mail=m;
}

/**Retourne l'id de la personne.
 * @return identifiant de la personne*/
public int getId(){
	return id;
}

//Accesseurs pour l'attribut nom

/**Retourne le nom de la personne.
 * @return nom de la personne*/
public String getNom(){
	return nom;
}

/**Modifie le nom de la personne si celui pass� en param�tre est diff�rent de null(retourne vrai si
 * la modification s'est bien pass�e, faux sinon).
 * @param n nouveau nom � attribuer � la personne
 * @return modification effectu�e ou non*/
public boolean setNom(String n){
	boolean res = false;
	
	if(n != null && !n.equalsIgnoreCase("")){
		nom=n;
		res=true;
	}
	
	return res;
}

//Accesseurs pour l'attribut prenom

/**Retourne le pr�nom de la personne.
 * @return pr�nom de la personne*/
public String getPrenom(){
	return prenom;
}

/**Modifie le pr�nom de la personne si celui pass� en param�tre est diff�rent de null(retourne vrai si
 * la modification s'est bien pass�e, faux sinon).
 * @param pn nouveau pr�nom � attribuer � la personne
 * @return modification effectu�e ou non*/
public boolean setPrenom(String pn){
	boolean res = false;
	
	if(pn != null && !pn.equalsIgnoreCase("")){
		nom=pn;
		res=true;
	}
	
	return res;
}

//Accesseurs pour l'attribut mail

/**Retourne l'adresse e-mail de la personne.
 * @return adresse e-mail de la personne*/
public String getMail(){
	return mail;
}

/**Modifie l'adresse e-mail de la personne si celle pass�e en param�tre est diff�rente de null(retourne vrai si
 * la modification s'est bien pass�e, faux sinon).
 * @param m nouvelle adresse e-mail � attribuer � la personne
 * @return modification effectu�e ou non*/
public boolean setMail(String m){
	boolean res = false;
	
	if(m != null && !m.equalsIgnoreCase("")){
		mail=m;
		res=true;
	}
	
	return res;
}

}
