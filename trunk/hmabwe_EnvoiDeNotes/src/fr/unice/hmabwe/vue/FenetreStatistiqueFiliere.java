package fr.unice.hmabwe.vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

import fr.unice.hmabwe.controleur.bd.dao.DaoFabrique;

/**
 * 
 * @author M'RAH Mehdi
 * 
 * Fenetre permettant d'afficher les statistiques d'une filière
 *
 */

public class FenetreStatistiqueFiliere extends JFrame{
	private DaoFabrique df;
	
	private JPanel panel, panelGeneral, panelSpecD, panelmoyenneG, panelmoyenneGr1, panelmoyenneGr2, panelmoyenneG2, panelmoyenneGr12, panelmoyenneGr22, panelEcartType;
	
	private JLabel moyenneG, moyenneGr1, moyenneGr2, ecartType,moyenneG2, moyenneGr12, moyenneGr22, notemoyenneG, notemoyenneGr1, notemoyenneGr2, noteEcartType, notemoyenneG2, notemoyenneGr12, notemoyenneGr22;
	
	private JList listCours;
	
	private JSplitPane panelSpec;
	
	private JScrollPane scrollList;
	
	public FenetreStatistiqueFiliere(int notemoyenneG, int notemoyenneGr1, int notemoyenneGr2, int noteEcartType, int notemoyenneG2, int notemoyenneG12, int notemoyenneG22, DaoFabrique df){
		//TODO Dans le constructeur ajouter la connexion a la base.
		//TODO Remplacer les moyennes dans le constructeur, par les moyennes recus de la database.
		this.df = df;
		
		
		moyenneG = new JLabel("Moyenne General :	");
		moyenneGr1 = new JLabel("Moyenne Groupe 1 :	");
		moyenneGr2 = new JLabel("Moyenne Groupe 2 :	");
		ecartType = new JLabel("Ecart-type :		");
		moyenneG2 = new JLabel("Moyenne General 	:");
		moyenneGr12 = new JLabel("Moyenne Groupe 1 	:");
		moyenneGr22 = new JLabel("Moyenne Groupe 2 	:");
		
		this.notemoyenneG = new JLabel();
		this.notemoyenneG.setText(String.valueOf(notemoyenneG));
		this.notemoyenneGr1 = new JLabel();
		this.notemoyenneGr1.setText(String.valueOf(notemoyenneGr1));
		this.notemoyenneGr2 = new JLabel();
		this.notemoyenneGr2.setText(String.valueOf(notemoyenneGr2));
		this.noteEcartType = new JLabel();
		this.noteEcartType.setText(String.valueOf(noteEcartType));
		this.notemoyenneG2 = new JLabel();
		this.notemoyenneG2.setText(String.valueOf(notemoyenneG2));
		this.notemoyenneGr12 = new JLabel();
		this.notemoyenneGr12.setText(String.valueOf(notemoyenneG12));
		this.notemoyenneGr22 = new JLabel();
		this.notemoyenneGr22.setText(String.valueOf(notemoyenneG22));
		
		panelmoyenneG = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelmoyenneG.add(moyenneG);
		panelmoyenneG.add(this.notemoyenneG);
		
		panelmoyenneGr1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelmoyenneGr1.add(moyenneGr1);
		panelmoyenneGr1.add(this.notemoyenneGr1);
		
		panelmoyenneGr2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelmoyenneGr2.add(moyenneGr2);
		panelmoyenneGr2.add(this.notemoyenneGr2);
		
		panelmoyenneG2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelmoyenneG2.add(moyenneG2);
		panelmoyenneG2.add(this.notemoyenneG2);
		
		panelmoyenneGr12 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelmoyenneGr12.add(moyenneGr12);
		panelmoyenneGr12.add(this.notemoyenneGr12);
		
		panelmoyenneGr22 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelmoyenneGr22.add(moyenneGr22);
		panelmoyenneGr22.add(this.notemoyenneGr22);
		
		panelEcartType = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelEcartType.add(ecartType);
		panelEcartType.add(this.noteEcartType);
		
		panelGeneral = new JPanel(new GridLayout(2,2));
		panelGeneral.add(panelmoyenneG);
		panelGeneral.add(panelmoyenneGr1);
		panelGeneral.add(panelmoyenneGr2);
		panelGeneral.add(panelEcartType);
		
		listCours = new JList();
		listCours.setFixedCellWidth(100);
		scrollList = new JScrollPane(listCours);
		
		
		panelSpecD = new JPanel();
		panelSpecD.setLayout(new BoxLayout(panelSpecD, BoxLayout.Y_AXIS));
		panelSpecD.add(panelmoyenneG2);
		panelSpecD.add(panelmoyenneGr12);
		panelSpecD.add(panelmoyenneGr22);
		
		panelSpec = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollList, panelSpecD);
		panelSpec.setBorder(BorderFactory.createTitledBorder("Cours"));
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(panelGeneral, BorderLayout.NORTH);
		panel.add(panelSpec, BorderLayout.CENTER);
		
		this.setTitle("Statistiques Flières");
        this.setSize(350, 350);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
		this.setContentPane(panel);
		this.setVisible(true);
		
		
		
	}
	

}
