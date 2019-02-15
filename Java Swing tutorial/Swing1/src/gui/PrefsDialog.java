package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

public class PrefsDialog extends JDialog {
	
	private JButton okButton;
	private JButton cancelButton;
	private JSpinner portSpinner;
	private SpinnerNumberModel spinnerModel;
	private JTextField userField;
	private JPasswordField passField;
	private PrefsListener prefsListener;
	
	public PrefsDialog(JFrame parent) {
		super(parent, "Preferences", false);
		//setVisible - ustawione w MainFrame
		
		
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		
		spinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1); //3306 - wartosc poczatkowa na spinnerze
		portSpinner = new JSpinner(spinnerModel);
		
		userField = new JTextField(10);
		passField = new JPasswordField(10);
		
		// gdy wpisujemy tekst do pola z haslem - znaki sa niewidoczne i zastepowane znakiem *
		passField.setEchoChar('*');
		
		// ustawieni zawartosci okna
		layoutControls();
		
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Integer port = (Integer)portSpinner.getValue();
				
				String user = userField.getText();
				char[] password = passField.getPassword();
				
				//System.out.println(port + " " + user + " " + new String(password));
				
				
				if(prefsListener != null) {
					
					//pole typu JPasswordField zraca wartosc zahashowana dlatego stosuje sie zapis new String(password) aby otrzymac haslo
					prefsListener.preferencesSet(user, new String(password), port);
				}
				
				
				
				//ukrycie okna
				setVisible(false);
				
			}
			
		});
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//ukrycie okna
				setVisible(false);
				
			}
			
		});
		
		setSize(300,230);
		
		//ustawienie lokalizacji okna dialogowego wzgledem okna z ktorego zostalo wywolane (MainFrame)
		setLocationRelativeTo(parent);
		
		
	}

	private void layoutControls() {
		
		JPanel controlsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		
		int space = 15;
		Border titleBorder = BorderFactory.createTitledBorder("Database Preferences");
		Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
		
		controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
		//buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		
		controlsPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		Insets rightPadding = new Insets(0, 0, 0, 15);
		Insets noPadding = new Insets(0, 0, 0, 0);
		
		////////////First row /////////////
		
		gc.gridy = 0;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.EAST; // wyrownianie do prawej strony
		gc.insets = rightPadding;
		controlsPanel.add(new JLabel("User: "), gc);
		
		//nastepnie komponent dodajemy po prawej stronie
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST; // wyrownianie do lewej strony
		gc.insets = noPadding;
		controlsPanel.add(userField, gc);
		
		////////////Next row /////////////
		
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.EAST; // wyrownianie do prawej strony
		gc.insets = rightPadding;
		controlsPanel.add(new JLabel("Password: "), gc);
		
		//nastepnie komponent dodajemy po prawej stronie
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST; // wyrownianie do lewej strony
		gc.insets = noPadding;
		controlsPanel.add(passField, gc);
		
		
		
		
		////////////Next row /////////////
		
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.EAST; // wyrownianie do prawej strony
		gc.insets = rightPadding;
		controlsPanel.add(new JLabel("Port: "), gc);
		
		//nastepnie komponent dodajemy po prawej stronie
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST; // wyrownianie do lewej strony
		gc.insets = noPadding;
		controlsPanel.add(portSpinner, gc);
		
		
		
		//////////// Buttons Panel /////////////
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.add(okButton);
		buttonsPanel.add(cancelButton);
		
		//ustalenie rozmiaru przyciskow
		Dimension btnSize = cancelButton.getPreferredSize();
		okButton.setPreferredSize(btnSize);
		
		
		
		//Dodaanie paneli do okna 
		setLayout(new BorderLayout());
		add(controlsPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
	}

	public void setDefaults(String user, String password, int port) {
		userField.setText(user);
		passField.setText(password);
		portSpinner.setValue(port);
		
	}
	
	public void setPrefsListener(PrefsListener prefsListener) {
		
		this.prefsListener = prefsListener;
		
	}
}
