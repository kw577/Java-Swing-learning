import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel{

	private JLabel nameLabel;
	private JLabel occupationLabel;
	private JTextField nameField;
	private JTextField occupationField;
	private JButton okBtn;
	private FormListener formListener;
	private JComboBox empCombo;
	
	private JList ageList;
	private JCheckBox citizenCheck;
	private JTextField taxField;
	private JLabel taxLabel;
	
	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private ButtonGroup genderGroup;
	
	
	
	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		
		
		nameLabel = new JLabel("Name: ");
		occupationLabel = new JLabel("Occupation: ");
		
		nameField = new JTextField(10);
		occupationField = new JTextField(10);
		ageList = new JList();
		empCombo = new JComboBox();
		
		citizenCheck = new JCheckBox();
		taxField = new JTextField(10);
		taxLabel = new JLabel("Tax ID: ");
		
		okBtn = new JButton("OK");
		
		// skrot klawiszowy do potwierdzenia formularza:   alt + o
		okBtn.setMnemonic(KeyEvent.VK_O);
		
		nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
		nameLabel.setLabelFor(nameField);
		
		maleRadio = new JRadioButton("male");
		femaleRadio = new JRadioButton("female");
		genderGroup = new ButtonGroup();
		
		//wykrywanie ktory przycisk grupy zostal wybrany
		maleRadio.setActionCommand("male");
		femaleRadio.setActionCommand("female");
		
		
		maleRadio.setSelected(true); // domyslnie zaznaczony przcisk grupy przyciskow
		
		
		// tworzenie grupy przyciskow
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);
		
		citizenCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				boolean isTicked = citizenCheck.isSelected();
				taxLabel.setEnabled(isTicked);
				taxField.setEnabled(isTicked);
				
			}
			
		});
		
		
		
		// Set up tax ID
		taxField.setEnabled(false);
		taxLabel.setEnabled(false);
		
		DefaultListModel ageModel = new DefaultListModel();
		
		//// TEST
		ageModel.addElement(new AgeCategory(0, "Under 18"));
		ageModel.addElement(new AgeCategory(1, "18 to 65"));
		ageModel.addElement(new AgeCategory(2, "65 or over"));
				
		///////////
		
		
		// Set up combo box
		DefaultComboBoxModel empModel = new DefaultComboBoxModel();
		empModel.addElement("employed");
		empModel.addElement("self-employed");
		empModel.addElement("unemployed");
		empCombo.setModel(empModel);
		empCombo.setSelectedIndex(0); // domyslnie wybrana opcja
		empCombo.setEditable(true); // dzieki temu uzytkownik moze dopisac inna opcje (poza dostepnymi w liscie ComboBox)
		
		//////////////////
		
		
		
		
		ageList.setModel(ageModel);
		
		ageList.setPreferredSize(new Dimension(110, 70));
		ageList.setBorder(BorderFactory.createEtchedBorder());
		ageList.setSelectedIndex(1); // domyslnie wybrane pole listy
		
		
		
		okBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = nameField.getText();
				String occupation = occupationField.getText();
				AgeCategory ageCat = (AgeCategory) ageList.getSelectedValue();
				String empCat = (String)empCombo.getSelectedItem();
				String taxId = taxField.getText();
				boolean usCitizen = citizenCheck.isSelected();
				
				
				//wykrywanie ktory przycisk grupy zostal wybrany
				String gender = genderGroup.getSelection().getActionCommand();
				
				
				
				System.out.println("ageCat:" + ageCat + "  id: " + ageCat.getId());
				System.out.println("empCat:" + empCat);
				
				
				FormEvent ev = new FormEvent(this, name, occupation, ageCat.getId(), 
						empCat, taxId, usCitizen, gender);
				
				
				if(formListener != null) {
					formListener.formEventOccured(ev);
				}
			}
			
		});
		
		
		
		
		setPreferredSize(dim);
		
		//Obramowanie formularza
		Border innerBorder = BorderFactory.createTitledBorder("Add person");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		
		//Jedno obramowanie wewatrz drugiego
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		
		layoutComponents();
	}
	
	
	
	// metoda dodana w celu zwiêkszenia czytelnoœci konstruktora FormPanel
	public void layoutComponents() {
		
		
		//UWAGA - zastosowano GridBagLayout   - rozmieszcza wg siatki kolumn i wierszy gdzie punkt (0,0) to lewy orny rog
		setLayout(new GridBagLayout());	
		
		GridBagConstraints gc = new GridBagConstraints();
		
		

		
		
		
		////////////////////////// First row /////////////////////////////////
		
		gc.weightx = 1; // szerokosc kolumny
		gc.weighty = 0.1; // wysokosc wiersza
		
		
		gc.gridx = 0; // numer kolumny ukladu liczac od lewego gornego rogu
		gc.gridy = 0; // numer wiersza ukladu liczac od lewego gornego rogu

		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END; // wyrownanie do prawej (prawej krawedzi komorki ukladu GridBagLayout)
		gc.insets = new Insets(0, 0, 0, 5); // padding (odstep wokol zawartosci komorki)
		add(nameLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START; // wyrownanie do lewej (lewej krawedzi komorki ukladu GridBagLayout)
		gc.insets = new Insets(0, 0, 0, 0);
		add(nameField, gc);
		
		
		
		////////////////////////// Second row /////////////////////////////////
		
		gc.weightx = 1; // szerokosc kolumny
		gc.weighty = 0.1; // wysokosc wiersza
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_END; // wyrownanie do prawej (prawej krawedzi komorki ukladu GridBagLayout)
		gc.insets = new Insets(0, 0, 0, 5); // padding (odstep wokol zawartosci komorki)
		add(occupationLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START; // wyrownanie do lewej (lewej krawedzi komorki ukladu GridBagLayout)
		add(occupationField, gc);
		
		
		
		////////////////////////// Third row /////////////////////////////////
		
		gc.weightx = 1; // szerokosc kolumny
		gc.weighty = 0.2; // wysokosc wiersza
		
		
		// opis pola wyboru
		gc.gridx = 0;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.FIRST_LINE_END; // wyrownanie do prawej (prawej krawedzi komorki ukladu GridBagLayout)
		gc.insets = new Insets(0, 0, 0, 5); // padding (odstep wokol zawartosci komorki)
		add(new JLabel("Age: "), gc);
		
		
				
		gc.gridx = 1;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // wyrownanie do lewego gornego rogu komorki siatki 
		gc.insets = new Insets(0, 0, 0, 0);
		add(ageList, gc);
		
		
		
		////////////////////////// Next row /////////////////////////////////
		
		gc.weightx = 1; // szerokosc kolumny
		gc.weighty = 0.2; // wysokosc wiersza
		
		gc.gridy++;  // nastepny wiersz
		
		
		// opis pola wyboru
		gc.gridx = 0;
	
		gc.anchor = GridBagConstraints.FIRST_LINE_END; // wyrownanie do prawej (prawej krawedzi komorki ukladu GridBagLayout)
		gc.insets = new Insets(0, 0, 0, 5); // padding (odstep wokol zawartosci komorki)
		add(new JLabel("Employment: "), gc);
		
		
		
		
		
		gc.gridx = 1;
		
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // wyrownanie do lewego gornego rogu komorki siatki 
		gc.insets = new Insets(0, 0, 0, 0);
		add(empCombo, gc);

		
		///
		
		////////////////////////// Next row /////////////////////////////////
		
		gc.weightx = 1; // szerokosc kolumny
		gc.weighty = 0.2; // wysokosc wiersza
		
		gc.gridy++;  // nastepny wiersz
		
		
		// opis pola wyboru
		gc.gridx = 0;
	
		gc.anchor = GridBagConstraints.FIRST_LINE_END; // wyrownanie do prawej (prawej krawedzi komorki ukladu GridBagLayout)
		gc.insets = new Insets(0, 0, 0, 5); // padding (odstep wokol zawartosci komorki)
		add(new JLabel("US Citizen: "), gc);
		
		
		
		
		
		gc.gridx = 1;
		
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // wyrownanie do lewego gornego rogu komorki siatki 
		gc.insets = new Insets(0, 0, 0, 0);
		add(citizenCheck, gc);

		
		///
		
		
		
		////////////////////////// Next row /////////////////////////////////
		
		gc.weightx = 1; // szerokosc kolumny
		gc.weighty = 0.2; // wysokosc wiersza
		
		gc.gridy++;  // nastepny wiersz
		
		
		// opis pola wyboru
		gc.gridx = 0;
	
		gc.anchor = GridBagConstraints.FIRST_LINE_END; // wyrownanie do prawej (prawej krawedzi komorki ukladu GridBagLayout)
		gc.insets = new Insets(0, 0, 0, 5); // padding (odstep wokol zawartosci komorki)
		add(taxLabel, gc);
		
		
		
		
		
		gc.gridx = 1;
		
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // wyrownanie do lewego gornego rogu komorki siatki 
		gc.insets = new Insets(0, 0, 0, 0);
		add(taxField, gc);

		
		///
		
		
		
		////////////////////////// Next row /////////////////////////////////
		
		gc.weightx = 1; // szerokosc kolumny
		gc.weighty = 0.05; // wysokosc wiersza
		
		gc.gridy++;  // nastepny wiersz
		
		
		
		gc.gridx = 0;
	
		gc.anchor = GridBagConstraints.LINE_END; // wyrownanie do prawej (prawej krawedzi komorki ukladu GridBagLayout)
		gc.insets = new Insets(0, 0, 0, 5); // padding (odstep wokol zawartosci komorki)
		add(new JLabel("Gender: "), gc);
		
		
		
		
		
		gc.gridx = 1;
		
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // wyrownanie do lewego gornego rogu komorki siatki 
		gc.insets = new Insets(0, 0, 0, 0);
		add(maleRadio, gc);

		
		///
		
	////////////////////////// Next row /////////////////////////////////
		
		gc.weightx = 1; // szerokosc kolumny
		gc.weighty = 0.2; // wysokosc wiersza
		
		gc.gridy++;  // nastepny wiersz
		
		
		
		gc.gridx = 1;
	
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // wyrownanie do prawej (prawej krawedzi komorki ukladu GridBagLayout)
		gc.insets = new Insets(0, 0, 0, 0); // padding (odstep wokol zawartosci komorki)
		add(femaleRadio, gc);
		
		
		
		
		
	
		
		///
		
		////////////////////////// Przycisk submit /////////////////////////////////
		
		gc.weightx = 1; // szerokosc kolumny
		gc.weighty = 2; // wysokosc wiersza
		
		gc.gridx = 1;
		gc.gridy++; // nastepny wiersz
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // wyrownanie do lewego gornego rogu komorki siatki 
		gc.insets = new Insets(0, 0, 0, 0);
		add(okBtn, gc);
	
		
		///
		
		
		
	}
	
	
	
	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
	
	class AgeCategory {
		
		private int id;
		private String text;
		
		public AgeCategory(int id, String text) {
			
			this.id = id;
			this.text = text;
			
			
		}
		
		public String toString() {
			return text;
		}
		
		public int getId() {
			return id;
		}
	}
	
}
