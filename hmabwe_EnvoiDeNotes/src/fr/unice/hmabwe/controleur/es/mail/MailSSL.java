package fr.unice.hmabwe.controleur.es.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author iliasse Hassala
 * 
 *
 */
public class MailSSL {

	private String login;
	private String password;
	private String host;
	private int port;

	private String tagNom;
	private String tagPrenom;
	private String tagNote;
	private String tagCours;
	private String tagMoyenne;
	private String tagPrenomEnseignant;
	private String tagNomEnseignant;
	private String tagMailEnseignant;

	/**
	 * @param login  l'identifiant sur le serveur smtp choisit
	 * @param password  le password sur le serveur smtp
	 * @param host  adresse du serveur smtp
	 * @param port port d'envoi, ici le 465 pour l'envoi en SSL 
	 */
	public MailSSL(String login, String password, String host, int port) {
		this.login = login;
		this.password = password;
		this.host = host;
		this.port = port;
	}

	/**
	 * Cette méthode initialise les tags
	 * @param tagNom valeur du tag (nom à mettre à la place du tag)
	 * @param tagPrenom prenom à mettre à la place du tag
	 * @param tagNote note à mettre à la place du tag
	 * @param tagCours cours à mettre à la place du tag
	 * @param tagMoyenne moyenne à mettre à la place du tag
	 * @param tagPrenomEnseignant PrenomEnseignant à mettre à la place du tag
	 * @param tagNomEnseignant NomEnseignant à mettre à la place du tag
	 * @param tagMailEnseignant MailEnseignant à mettre à la place du tag
	 */
	public void setTags(String tagNom, String tagPrenom, String tagNote,
			String tagCours, String tagMoyenne, String tagPrenomEnseignant,
			String tagNomEnseignant, String tagMailEnseignant) {

		this.tagPrenom = tagPrenom;
		this.tagCours = tagCours;
		this.tagMailEnseignant = tagMailEnseignant;
		this.tagMoyenne = tagMoyenne;
		this.tagNom = tagNom;
		this.tagNomEnseignant = tagNomEnseignant;
		this.tagPrenomEnseignant = tagPrenomEnseignant;
		this.tagNote = tagNote;

	}

	/**
	 * @param text on emplace les tags présent dans le texte par leur valeur
	 * @return retourne le texte à envoyer par mail pré-formaté
	 */
	public String replaceBalises(String text) {
		text = text.replace("#{nom}", tagNom);
		text = text.replace("#{prenom}", tagPrenom);
		text = text.replace("#{note}", tagNote);
		text = text.replace("#{moyenne}", tagMoyenne);
		text = text.replace("#{cours}", tagCours);
		text = text.replace("#{prenom_enseignant}", tagPrenomEnseignant);
		text = text.replace("#{nom_enseignant}", tagNomEnseignant);
		text = text.replace("#{email_enseignant}", tagMailEnseignant);

		return text;

	}

	/**
	 * @param from ce qu'on souhaite faire apparaitre dans le champ from
	 * @param to l'adresse du destinataire
	 * @param subject le sujet du mail
	 * @param text le texte à envoyer
	 */
	public void SendMail(String from, String to, String subject, String text) {
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.socketFactory.port", port);
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", port);

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(login, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject(subject);
			
			text = replaceBalises(text);
			message.setText(text);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}