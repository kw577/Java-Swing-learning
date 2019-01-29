import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel{
	
	private JButton helloButton;
	private JButton goodbyeButton;
	
	
	public Toolbar() {
		helloButton = new JButton("Hello");
		goodbyeButton = new JButton("Goodbye");
		
		
		// UWAGA - zastosowano tu Flow Layout
		
		//setLayout(new FlowLayout()); // przyciski beda ustawione na srodku panelu
		
		setLayout(new FlowLayout(FlowLayout.LEFT)); // przyciski po lewej stronie panelu
		
		add(helloButton);
		add(goodbyeButton);
	}
	
	
	
	
}
