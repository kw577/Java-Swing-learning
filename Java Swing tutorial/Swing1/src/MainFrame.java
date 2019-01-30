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
	private FormPanel formPanel;
	
	public MainFrame() {
		super("Hello World");
		
		// UWAGA  - w tym przykladzie zastosowano BorderLayout   - tu opisano inne dostepne layout'y https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
		setLayout(new BorderLayout());
		
		textPanel = new TextPanel();
		
		toolbar = new Toolbar();
		
		formPanel = new FormPanel();
		
		toolbar.setStringListener(new StringListener() {

			@Override
			public void textEmitted(String text) {
				// TODO Auto-generated method stub
				textPanel.appendText(text);
			}
			
		});
		
		
		formPanel.setFormListener(new FormListener() {
			public void formEventOccured(FormEvent e) {
				String name = e.getName();
				String occupation = e.getOccupation();
				
				textPanel.appendText(name + ": " + occupation + "\n");
			}
		});
		
		
		
		add(formPanel, BorderLayout.WEST);	
		
		add(toolbar, BorderLayout.NORTH);
		
		add(textPanel, BorderLayout.CENTER);  // TextPanel (JPanel) ustawione w centrum i wypelnia cale dostepne miejsce
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 500);
		
	}
	
	
	
}
