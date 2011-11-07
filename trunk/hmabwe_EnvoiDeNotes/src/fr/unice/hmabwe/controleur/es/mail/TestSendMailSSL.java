package fr.unice.hmabwe.controleur.es.mail;

import java.util.ArrayList;
import java.util.Collection;

import fr.unice.hmabwe.modele.Cours;
import fr.unice.hmabwe.modele.Enseignant;
import fr.unice.hmabwe.modele.Etudiant;

public class TestSendMailSSL {

	public static void main(String[] args) {
		
		MailSSL mail = new MailSSL("from@etu.unice.fr", "password",
				"smtp.unice.fr", 465);
		
		

		Etudiant e1 = new Etudiant("20702049", "Biga", "Anthony",
				"biga.anthony@etu.unice.fr");
		Etudiant e2 = new Etudiant("20911803", "Hassala", "iliasse",
				"hassala.iliasse@etu.unice.fr");

		Enseignant enseignant = new Enseignant("Grin", "Richard",
				"grin@unice.fr");

		Cours c1 = new Cours("JPA", enseignant);

		Collection<Etudiant> to = new ArrayList<Etudiant>();
		to.add(e1);
		to.add(e2);

		mail.SendMail("from@etu.unice.fr", to, "Subject",
				"<-- Text + Tag --> : salut #nom #prenom", c1, enseignant, 2011);

	}

}
