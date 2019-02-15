package gui;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener{
	
	private JButton saveButton;
	private JButton refreshButton;
	
	private ToolbarListener textListener;
	
	public Toolbar() {
		
		setBorder(BorderFactory.createEtchedBorder());
		
		saveButton = new JButton("Save");
		refreshButton = new JButton("Refresh");
		
		saveButton.addActionListener(this);
		refreshButton.addActionListener(this);
		
		
		
		// UWAGA - zastosowano tu Flow Layout
		
		//setLayout(new FlowLayout()); // przyciski beda ustawione na srodku panelu
		
		setLayout(new FlowLayout(FlowLayout.LEFT)); // przyciski po lewej stronie panelu
		
		add(saveButton);
		add(refreshButton);
	}
	
	




	public void setToolbarListener(ToolbarListener listener) {
		this.textListener = listener;
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("A button has been clicked");
		
		JButton clicked = (JButton)e.getSource(); // getSource zwraca Object - ale w tym przypadku sa jedynie obiekty typu JButton - zatem stosuje sie rzutowanie
	
		if(clicked == saveButton) {
			//System.out.println("Hello");
			
			//textPanel.appendText("Hello\n");
			
			if(textListener != null) {
				textListener.saveEventOccured();
			}
			
			
		} else if (clicked == refreshButton) {
			//System.out.println("Goodbye");
			
			//textPanel.appendText("Goodbye\n");
			
			if(textListener != null) {
				textListener.refreshEventOccured();
			}
			
		}
	
	}
	
}
