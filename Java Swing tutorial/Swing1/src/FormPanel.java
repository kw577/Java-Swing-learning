import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel{

	private JLabel nameLabel;
	private JLabel occupationLabel;
	private JTextField nameField;
	private JTextField occupationField;
	private JButton okBtn;
	private FormListener formListener;
	
	private JList ageList;
	
	
	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		
		
		nameLabel = new JLabel("Name: ");
		occupationLabel = new JLabel("Occupation: ");
		
		nameField = new JTextField(10);
		occupationField = new JTextField(10);
		ageList = new JList();
		
		DefaultListModel ageModel = new DefaultListModel();
		
		//// TEST
		ageModel.addElement("Under 18");
		ageModel.addElement("18 to 65");
		ageModel.addElement("65 or over");
				
		///////////
		
		ageList.setModel(ageModel);
		
		ageList.setPreferredSize(new Dimension(110, 70));
		ageList.setBorder(BorderFactory.createEtchedBorder());
		ageList.setSelectedIndex(1); // domyslnie wybrane pole listy
		
		
		okBtn = new JButton("OK");
		
		okBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = nameField.getText();
				String occupation = occupationField.getText();
				String ageCat = (String) ageList.getSelectedValue();
				
				System.out.println("ageCat:" + ageCat);
				
				
				FormEvent ev = new FormEvent(this, name, occupation);
				
				
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
		
		gc.gridx = 1;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // wyrownanie do lewego gornego rogu komorki siatki 
		gc.insets = new Insets(0, 0, 0, 0);
		add(ageList, gc);
		
		
		
		
		
		
		
		////////////////////////// Fourth row /////////////////////////////////
		
		gc.weightx = 1; // szerokosc kolumny
		gc.weighty = 2; // wysokosc wiersza
		
		gc.gridx = 1;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // wyrownanie do lewego gornego rogu komorki siatki 
		gc.insets = new Insets(0, 0, 0, 0);
		add(okBtn, gc);
		
		
		
		
		///
	}
	
	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
	
	
	
}
