package fr.unice.hmabwe.controleur.es.mail;

public class TestSendMailSSL {

	public static void main(String[] args) {

		MailSSL mail = new MailSSL("login@etu.unice.fr", "password",
				"smtp.unice.fr", 465);

		// on envoi 3 mails contenant le texte passé en paramètre avec les tags,
		// à l'adresse situé dans le champ "to" avec le "subject" choisit. On
		// peut paramétré l'envoi avec un e.getMail() dans le champ "to"

		for (int i = 0; i < 3; i++) {

			// méthode qui remplace les tags par leur valeur.On passe ici en dur
			// le nom et le prénom qui devront remplacer les tags présents dans
			// le texte.
			mail.setTags("Anthony", "Biga", "", "", "", "", "", "");

			mail.SendMail("from@etu.unice.fr", "to@etu.unice.fr", "Subject",
					"salut #{nom} #{prenom}");
		}

	}

}
