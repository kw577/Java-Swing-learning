package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.EmploymentCategory;
import model.Person;

public class PersonTableModel extends AbstractTableModel {

	private List<Person> db;
	
	//nazwy kolumn tabeli
	private String[] colNames = {"ID", "Name", "Occupation", "Age Category", "Emplyment Category", "US Citizen", "Tax ID"};
	
	public PersonTableModel() {
		//this.db = db;
	}
	
	
	
	
	
	
	// zwraca checkbox'y w okreslonej kolumnie tabeli
	@Override
	public Class<?> getColumnClass(int col) {
		
		switch(col) {
		case 0:
			return Integer.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return EmploymentCategory.class;
		case 5:
			return Boolean.class;
		case 6:
			return String.class;
		default:
			return null;
		}
	}







	public void setData(List<Person> db) {
		this.db = db;
	}

	
	// metoda odpowiada za wyswietlanie nazw kolumn tabeli
	// PPM -> source -> override implemented methods
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colNames[column];
	}


	
	//gdy metoda zwraca true - wtedy komorka tabeli bedzie edytowalna - tu przyjmuje kolumne name (id=1) a takze kolumna o id=5
	@Override
	public boolean isCellEditable(int row, int col) {
		
		switch(col) {
		case 1:
			return true;
		case 4:
			return true;
		case 5:
			return true;
		default:
			return false;
		}
	}


	
	// metoda updateuje zawartosc tabeli (dla komorek edytowwalnych - metoda isCellEditable())
	@Override
	public void setValueAt(Object value, int row, int col) {

		// jesli nie ma polaczenia z baza danych - zakonczenie metody
		if(db == null) return;
		Person person = db.get(row);
		
		switch(col) {
		case 1:
			person.setName((String)value);
			break;
		case 4:
			person.setEmpCat((EmploymentCategory)value);
			break;
		case 5:
			person.setUsCitizen((Boolean)value);
			break;
		default:
			return;
		}
		
	}


	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public Object getValueAt(int row, int col) {
		Person person = db.get(row);
		
		switch(col) {
		case 0:
			return person.getId();
		case 1:
			return person.getName();
		case 2:
			return person.getOccupation();
		case 3:
			return person.getAgeCategory();
		case 4:
			return person.getEmpCat();
		case 5:
			return person.isUsCitizen();
		case 6:
			return person.getTaxId();
		}
		
		return null;
	}

}
