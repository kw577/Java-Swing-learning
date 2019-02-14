package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class PrefsDialog extends JDialog {
	
	private JButton okButton;
	private JButton cancelButton;
	private JSpinner portSpinner;
	private SpinnerNumberModel spinnerModel;
	private JTextField userField;
	private JPasswordField passField;
	
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
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		////////////First row /////////////
		
		gc.gridy = 0;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.fill = GridBagConstraints.NONE;
		
		add(new JLabel("User: "), gc);
		
		//nastepnie komponent dodajemy po prawej stronie
		gc.gridx++;
		add(userField, gc);
		
		////////////Next row /////////////
		
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.fill = GridBagConstraints.NONE;
		
		add(new JLabel("Password: "), gc);
		
		//nastepnie komponent dodajemy po prawej stronie
		gc.gridx++;
		add(passField, gc);
		
		
		
		
		////////////Next row /////////////
		
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.fill = GridBagConstraints.NONE;
		
		add(new JLabel("Port: "), gc);
		
		//nastepnie komponent dodajemy po prawej stronie
		gc.gridx++;
		add(portSpinner, gc);
		
		
		
		//////////// Next row /////////////
		gc.gridy++;
		gc.gridx = 0;
		
		add(okButton, gc);
		
		gc.gridx++;
		add(cancelButton, gc);
		
		
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Integer value = (Integer)portSpinner.getValue();
				
				String user = userField.getText();
				char[] password = passField.getPassword();
				
				System.out.println(value + " " + user + " " + new String(password));
				
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
		
		setSize(400,300);
		
		//ustawienie lokalizacji okna dialogowego wzgledem okna z ktorego zostalo wywolane (MainFrame)
		setLocationRelativeTo(parent);
		
		
	}
}
