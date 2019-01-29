import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener{
	
	private JButton helloButton;
	private JButton goodbyeButton;
	
	private TextPanel textPanel;
	
	public Toolbar() {
		helloButton = new JButton("Hello");
		goodbyeButton = new JButton("Goodbye");
		
		helloButton.addActionListener(this);
		goodbyeButton.addActionListener(this);
		
		
		
		// UWAGA - zastosowano tu Flow Layout
		
		//setLayout(new FlowLayout()); // przyciski beda ustawione na srodku panelu
		
		setLayout(new FlowLayout(FlowLayout.LEFT)); // przyciski po lewej stronie panelu
		
		add(helloButton);
		add(goodbyeButton);
	}
	
	




	public void setTextPanel(TextPanel textPanel) {
		this.textPanel = textPanel;
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("A button has been clicked");
		
		JButton clicked = (JButton)e.getSource(); // getSource zwraca Object - ale w tym przypadku sa jedynie obiekty typu JButton - zatem stosuje sie rzutowanie
	
		if(clicked == helloButton) {
			//System.out.println("Hello");
			
			textPanel.appendText("Hello\n");
			
		} else if (clicked == goodbyeButton) {
			//System.out.println("Goodbye");
			
			textPanel.appendText("Goodbye\n");
		}
	
	}
	
}
