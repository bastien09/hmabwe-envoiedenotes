package fr.unice.hmabwe.vue;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;

public class FenetreMail extends JFrame {

	private DaoFabrique df;
	
	public FenetreMail(DaoFabrique df) {
		this.df = df;
		
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setSize(600,100);
		this.setTitle("Envoi de notes par email");
	}
	
}
