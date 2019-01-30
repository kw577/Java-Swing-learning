import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel{

	private JLabel nameLabel;
	private JLabel occupationLabel;
	private JTextField nameField;
	private JTextField occupationField;
	private JButton okBtn;
	
	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		
		
		nameLabel = new JLabel("Name: ");
		occupationLabel = new JLabel("Occupation: ");
		
		nameField = new JTextField(10);
		occupationField = new JTextField(10);
		
		okBtn = new JButton("OK");
		
		
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
		
		
		gc.gridx = 0;
		gc.gridy = 0;

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
		gc.weighty = 2; // wysokosc wiersza
		
		gc.gridx = 1;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // wyrownanie do lewego gornego rogu komorki siatki 
		gc.insets = new Insets(0, 0, 0, 0);
		add(okBtn, gc);
		
		
		
		
		///
	}
	
}
