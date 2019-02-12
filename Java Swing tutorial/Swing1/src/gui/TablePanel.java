package gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Person;

public class TablePanel extends JPanel {

	private JTable table;
	private PersonTableModel tableModel;
	
	public TablePanel() {
		
		tableModel = new PersonTableModel();
		table = new JTable(tableModel);
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	public void setData(List<Person> db) {
		tableModel.setData(db);
	}

	// odswieza widok tabeli po dodaniu nowego elementu
	public void refresh() {
		// TODO Auto-generated method stub
		tableModel.fireTableDataChanged();
	}
	
}
