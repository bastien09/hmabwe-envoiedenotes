package fr.unice.hmabwe.vue;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;

import fr.unice.hmabwe.modele.Etudiant;

public class EtudiantTableModel extends AbstractTableModel {

	private ArrayList<Etudiant> etudiants;

	public EtudiantTableModel(Collection<Etudiant> etudiants) {
		this.etudiants = new ArrayList<Etudiant>(etudiants);
	}
	
	public EtudiantTableModel() {
		this(new ArrayList<Etudiant>());
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public int getRowCount() {
		return etudiants.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Etudiant e = etudiants.get(row);
		switch (col) {
		case 0:
			return e.getNumEtu();
		case 1:
			return e.getNom();
		case 2:
			return e.getPrenom();
		case 3: 
			return e.getMail();
		case 4:
			return null; //TODO combobox
		case 5:
			return e.getGroupe();
		case 6:
			return e.getOrigine();

		default:
			return null;
		}
	}

	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "Numéro";
		case 1:
			return "Nom";
		case 2:
			return "Prénom";
		case 3: 
			return "e-mail";
		case 4:
			return "Filière";
		case 5:
			return "Groupe";
		case 6:
			return "Origine";
		default:
			return null;
		}
	}

	public Class getColumnClass(int col){
		if(col == 4) {
			return JComboBox.class;
		} else {
			return String.class;
		}
	}

	public boolean isCellEditable(int row, int column) {
		if (column == 4) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void setEtudiants(Collection<Etudiant> etudiants) {
		this.etudiants = new ArrayList<Etudiant>(etudiants);
		this.fireTableDataChanged();
	}

}
