package fr.unice.hmabwe.controleur.bd.dao.jpa;

import fr.unice.hmabwe.controleur.bd.dao.DaoCours;
import fr.unice.hmabwe.modele.Cours;

/**
 * 
 * @author Paraita Wohler
 *
 * JpaDaoCours permet de faire abstraction de la couche persistance JPA
 * tout en rendant ce DAO suffisament générique.
 * Cette classe fourni les méthodes spécifiques au code métier de l'application
 *
 */
public abstract class JpaDaoCours extends JpaDaoGenerique<Cours, Integer> implements
		DaoCours {

	/**
	 * le code métier va ici
	 */
}
