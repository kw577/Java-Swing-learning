package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.EmploymentCategory;
import model.Person;

public class TablePanel extends JPanel {

	private JTable table;
	private PersonTableModel tableModel;
	
	// okno pojawia sie po kliknieciu PPM na tabeli
	private JPopupMenu popup;
	private PersonTableListener personTableListener;
	
	public TablePanel() {
		
		tableModel = new PersonTableModel();
		table = new JTable(tableModel);
		popup = new JPopupMenu();
		
		table.setDefaultRenderer(EmploymentCategory.class, new EmploymentCategoryRenderer());
		table.setDefaultEditor(EmploymentCategory.class, new EmploymentCategoryEditor());
		table.setRowHeight(25);
		
		JMenuItem removeItem = new JMenuItem("Delete row");
		popup.add(removeItem);
		
		
		// mozna tez zasotsowac MouseListener - ale MouseAdapter jest mniej rozbudowany
		table.addMouseListener(new MouseAdapter() {
			
			// eclipse: source -> override implemented methods
			@Override
			public void mousePressed(MouseEvent e) {
				
				//sprawdzenie nad ktorym wierszem tabeli uzytkownik wybral przawym przyciskiem myszy
				int row = table.rowAtPoint(e.getPoint());
				//System.out.println(row);
				
				// wybranie wiersza o numerach:   od numeru = row do mumeru = row (pojedynczy wiersz)
				table.getSelectionModel().setSelectionInterval(row, row);
				
				if(e.getButton() == MouseEvent.BUTTON3) { // BUTTON3 - oznacza prawy przycisk myszy
					// e.getX(), e.getY() - wspolrzedne punktu w ktorym ma sie pojawic menu
					popup.show(table, e.getX(), e.getY());
				}
				
			}
			
		});
		
		removeItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				System.out.println(row);
				
				if(personTableListener != null) {
					personTableListener.rowDeleted(row);
					tableModel.fireTableRowsDeleted(row, row);
				}
				
			}
			
		});
		
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

	public void setPersonTableListener(PersonTableListener listener) {
		// TODO Auto-generated method stub
		this.personTableListener = listener;
	}
	
}
