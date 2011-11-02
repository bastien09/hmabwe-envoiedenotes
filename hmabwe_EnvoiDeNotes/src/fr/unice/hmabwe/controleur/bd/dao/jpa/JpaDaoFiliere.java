package fr.unice.hmabwe.controleur.bd.dao.jpa;

import fr.unice.hmabwe.controleur.bd.dao.DaoFiliere;
import fr.unice.hmabwe.modele.Filiere;

/**
 * 
 * @author Paraita Wohler
 *
 * JpaDaoFiliere permet de faire abstraction de la couche persistance JPA
 * tout en rendant ce DAO suffisament générique.
 * Cette classe fourni les méthodes spécifiques au code métier de l'application
 *
 */
public abstract class JpaDaoFiliere extends JpaDaoGenerique<Filiere, Integer>
		implements DaoFiliere {

	/**
	 * le code métier va ici
	 */
}
