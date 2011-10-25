package fr.unice.hmabwe.controleur.bd.dao.jpa;

import fr.unice.hmabwe.controleur.bd.dao.DaoGenerique;
import fr.unice.hmabwe.modele.Filiere;

/**
 * 
 * @author Paraita Wohler
 *
 * JpaDaoFilliere permet de faire abstraction de la couche persistance JPA
 * tout en rendant ce DAO suffisament générique.
 * Cette classe fourni les méthodes spécifiques au code métier de l'application
 *
 */
public class JpaDaoFilliere extends JpaDaoGenerique<Filiere, Integer>
		implements DaoGenerique<Filiere, Integer> {

	/**
	 * le code métier va ici
	 */
}
