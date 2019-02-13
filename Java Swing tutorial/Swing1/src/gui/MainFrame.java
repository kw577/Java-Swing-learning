package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import controller.Controller;



// https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html   - opis layoutu w Swing'u



public class MainFrame extends JFrame {
	
	
	
	
	private TextPanel textPanel;
	private Toolbar toolbar;
	private FormPanel formPanel;
	private JFileChooser fileChooser;
	private Controller controller;
	private TablePanel tablePanel;
	private PrefsDialog prefsDialog;
	
	public MainFrame() {	
		super("Hello World");
		
		// UWAGA  - w tym przykladzie zastosowano BorderLayout   - tu opisano inne dostepne layout'y https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
		setLayout(new BorderLayout());
		
		textPanel = new TextPanel();
		
		toolbar = new Toolbar();
		
		formPanel = new FormPanel();
		
		tablePanel = new TablePanel();
		
		prefsDialog = new PrefsDialog(this);
		
		fileChooser = new JFileChooser();
		
		controller = new Controller();
		
		tablePanel.setData(controller.getPeople());
		
		tablePanel.setPersonTableListener(new PersonTableListener() {
			public void rowDeleted(int row) {
				System.out.println("Deleted row: " + row);
				controller.removePerson(row);
			}
		});
		
		//w celu ookreslenia jakie typy pliku beda obslugiwane (rozszerzenia pliku)
		//jesli chcemy miec wiecej filtrow plikow nalezy stworzyc dla nich osobne klasy i dodac w kolejnej linii analogicznie jak dla filtra ponizej
		fileChooser.addChoosableFileFilter(new PersonFileFilter());
		
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
				
				/*
				//TEST
				String name = e.getName();
				String occupation = e.getOccupation();
				int ageCat = e.getAgeCategory();
				String empCat = e.getEmpCat();
				
				textPanel.appendText(name + ": " + occupation + " : " + ageCat + " : " + empCat + "\n");
				System.out.println(e.getGender());
				*/
				
				
				controller.addPerson(e);
				tablePanel.refresh();
			}
		});
		
		
		
		add(formPanel, BorderLayout.WEST);	
		
		add(toolbar, BorderLayout.NORTH);
		
		add(tablePanel, BorderLayout.CENTER);  // TextPanel (JPanel) ustawione w centrum i wypelnia cale dostepne miejsce
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(600, 500));
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
		JMenuItem prefsItem = new JMenuItem("Preferences...");
		JMenu showMenu = new JMenu("Show"); // lista rozwijana funkcji
		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
		
		showFormItem.setSelected(true); // wartosc domyslna
		
		showMenu.add(showFormItem);
		windowMenu.add(showMenu);
		windowMenu.add(prefsItem);
		
		
		prefsItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				prefsDialog.setVisible(true);
			}
			
		});
		
		
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
		
		// mnemonics sa uzywane z przyciskiem Alt
		// skrot klawiszowy do menu File to:   Alt + F
		fileMenu.setMnemonic(KeyEvent.VK_F);
		
		// skrot klawiszowy do menu Exit (wyjscie z programu) to:   Alt + X
		exitItem.setMnemonic(KeyEvent.VK_X);
		
		
		// ctrl + x    - zamkniecie programu
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		
		// ctrl + i    - wczytywanie danych z pliku	
		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		
		
		importDataItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// otwiera okno wyszukiwania pliku po kliknieciu opcji "import" w menu glownym i pobiera plik
				if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.refresh();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						
						// jesli wystapi blad przy otwarciu pliku pojawi sie okno z komunikatem
						JOptionPane.showMessageDialog(MainFrame.this, "Could not load data from file", "Error", JOptionPane.ERROR_MESSAGE);
						
					}
					System.out.println(fileChooser.getSelectedFile()); // wydruk w konsoli nazwy pobranego pliku
				}
				
			}
			
		});
		
		
		exportDataItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// otwiera okno wyszukiwania pliku po kliknieciu opcji "import" w menu glownym i pobiera plik
				if(fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					System.out.println(fileChooser.getSelectedFile()); // wydruk w konsoli nazwy pobranego pliku
				
					try {
						controller.saveToFile(fileChooser.getSelectedFile());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						
						// jesli wystapi blad przy otwarciu pliku pojawi sie okno z komunikatem
						JOptionPane.showMessageDialog(MainFrame.this, 
								"Could not save data to file", "Error", 
								JOptionPane.ERROR_MESSAGE);
						
					}
				
				}
				
			}
			
		});
		
		exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				/* TEST
				
				String text = JOptionPane.showInputDialog(MainFrame.this,
						"Enter your user name.",
						"Enter User Name", JOptionPane.OK_OPTION|JOptionPane.INFORMATION_MESSAGE);//JOptionPane.INFORMATION_MESSAGE - zmiana wygladu ikonki w oknie, 
				//inne ikonki w oknaach: JOptionPane.WARNING_MESSAGE, JOptionPane.QUESTION_MESSAGE
				
				
				System.out.println("\n" + text);
				*/
				
				//okno z potwierdzeniem ze chcemy zakonczyc program
				int action = JOptionPane.showConfirmDialog(MainFrame.this, "Do you really want to exit the application?", 
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				
				if(action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
					
			}
			
		});
		
		return menuBar;
	}
	
}
