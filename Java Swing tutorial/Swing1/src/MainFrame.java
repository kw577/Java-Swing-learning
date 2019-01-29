import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;



// https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html   - opis layoutu w Swing'u



public class MainFrame extends JFrame {
	
	
	
	
	private TextPanel textPanel;
	private Toolbar toolbar;
	
	
	public MainFrame() {
		super("Hello World");
		
		// UWAGA  - w tym przykladzie zastosowano BorderLayout   - tu opisano inne dostepne layout'y https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
		setLayout(new BorderLayout());
		
		textPanel = new TextPanel();
		
		toolbar = new Toolbar();
		
		toolbar.setTextPanel(textPanel);
		
		
			
		
		add(toolbar, BorderLayout.NORTH);
		
		add(textPanel, BorderLayout.CENTER);  // TextPanel (JPanel) ustawione w centrum i wypelnia cale dostepne miejsce
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 500);
		
	}
	
	
	
}
