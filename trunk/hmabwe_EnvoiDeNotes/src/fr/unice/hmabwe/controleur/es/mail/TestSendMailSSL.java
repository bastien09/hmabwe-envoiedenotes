package fr.unice.hmabwe.controleur.es.mail;

public class TestSendMailSSL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SendMailSSL mail = new SendMailSSL("login@etu.unice.fr", "password", "smtp.unice.fr", 465);
		mail.SendMail("from@etu.unice.fr", "to@etu.unice.fr", "Subject", "Text");
		
	}

}
