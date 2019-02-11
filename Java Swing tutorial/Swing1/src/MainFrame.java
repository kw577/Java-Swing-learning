import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;



// https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html   - opis layoutu w Swing'u



public class MainFrame extends JFrame {
	
	
	
	
	private TextPanel textPanel;
	private Toolbar toolbar;
	private FormPanel formPanel;
	
	public MainFrame() {
		super("Hello World");
		
		// UWAGA  - w tym przykladzie zastosowano BorderLayout   - tu opisano inne dostepne layout'y https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
		setLayout(new BorderLayout());
		
		textPanel = new TextPanel();
		
		toolbar = new Toolbar();
		
		formPanel = new FormPanel();
		
		
		setJMenuBar(createMenuBar());
		
		
		toolbar.setStringListener(new StringListener() {

			@Override
			public void textEmitted(String text) {
				// TODO Auto-generated method stub
				textPanel.appendText(text);
			}
			
		});
		
		
		formPanel.setFormListener(new FormListener() {
			public void formEventOccured(FormEvent e) {
				String name = e.getName();
				String occupation = e.getOccupation();
				int ageCat = e.getAgeCategory();
				String empCat = e.getEmpCat();
				
				textPanel.appendText(name + ": " + occupation + " : " + ageCat + " : " + empCat + "\n");
				System.out.println(e.getGender());
			}
		});
		
		
		
		add(formPanel, BorderLayout.WEST);	
		
		add(toolbar, BorderLayout.NORTH);
		
		add(textPanel, BorderLayout.CENTER);  // TextPanel (JPanel) ustawione w centrum i wypelnia cale dostepne miejsce
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 500);
		
	}
	
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		//przyciski na pasku menu
		JMenu fileMenu = new JMenu("File");
		JMenu windowMenu = new JMenu("Window");
		
		
		
		// po rozwinieciu przycisku "File" na pasku menu pokazuja sie funkcje
		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		
		// po rozwinieciu przycisku "Window" na pasku menu pokazuja sie funkcje
		JMenu showMenu = new JMenu("Show"); // lista rozwijana funkcji
		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
		
		showFormItem.setSelected(true); // wartosc domyslna
		
		showMenu.add(showFormItem);
		windowMenu.add(showMenu);
		
		showFormItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)e.getSource();
				
				// ukrywa formularz po odznaczeniu opcji w pasku menu 
				formPanel.setVisible(menuItem.isSelected());
				
			}
			
		});
		
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		
		
		
		
		
		return menuBar;
	}
	
}
