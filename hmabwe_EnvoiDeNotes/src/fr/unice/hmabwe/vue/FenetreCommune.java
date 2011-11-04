package fr.unice.hmabwe.vue;

import java.awt.*;

import javax.swing.*;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Fenetre Fenetre mere
 *
 */

public class FenetreCommune extends JFrame{
	protected JPanel container = new JPanel();

    private JButton boutonOK = new JButton("Ok");
    private JButton boutonAnnuler = new JButton("Annuler");
    
    public FenetreCommune(String nomFenetre, int longueur, int largeur){
        this.setTitle(nomFenetre);
        this.setSize(longueur, largeur);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        
        container.setLayout(new BorderLayout());
        
        
        JPanel downButton = new JPanel();
        
        downButton.add(boutonOK, BorderLayout.EAST);
        downButton.add(boutonAnnuler,BorderLayout.EAST);
        
        
        container.add(downButton, BorderLayout.SOUTH);
        
        this.setContentPane(container);
        
        
        
    }
}
