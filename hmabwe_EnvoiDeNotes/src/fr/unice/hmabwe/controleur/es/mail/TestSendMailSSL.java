package fr.unice.hmabwe.controleur.es.mail;

import java.util.ArrayList;
import java.util.Collection;

import fr.unice.hmabwe.modele.Cours;
import fr.unice.hmabwe.modele.Enseignant;
import fr.unice.hmabwe.modele.Etudiant;

public class TestSendMailSSL {

	public static void main(String[] args) {
		
		MailSSL mail = new MailSSL("hi911803@etu.unice.fr", "g=0.83462",
				"smtp.unice.fr", 465);
		
		

		Etudiant e1 = new Etudiant("20702049", "Hassala", "Eli",
				"eli.h87@gmail.com");
		Etudiant e2 = new Etudiant("20911803", "Hassala", "iliasse",
				"hassala.iliasse@etu.unice.fr");
		
		Etudiant e3 = new Etudiant("20987654","Ait-tahar","pierre","pierre.aittahar@gmail.com");

		Enseignant enseignant = new Enseignant("Grin", "Richard",
				"grin@unice.fr");

		Cours c1 = new Cours("JPA", enseignant);

		Collection<Etudiant> to = new ArrayList<Etudiant>();
		to.add(e1);
		to.add(e2);
		to.add(e3);

		mail.SendMail("hi911803@etu.unice.fr", to, "Subject",
				"<-- Text + Tag --> : salut #nom #prenom", c1, enseignant, 2011);

	}

}
