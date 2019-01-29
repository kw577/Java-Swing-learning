import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;



// https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html   - opis layoutu w Swing'u



public class MainFrame extends JFrame {
	
	
	
	private JButton btn;
	private TextPanel textPanel;
	
	public MainFrame() {
		super("Hello World");
		
		// UWAGA  - w tym przykladzie zastosowano BorderLayout   - tu opisano inne dostepne layout'y https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
		setLayout(new BorderLayout());
		
		textPanel = new TextPanel();
		btn = new JButton("Click me!");
		
		
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Metoda uruchamiana po wcisieciu przycisku
				
				textPanel.appendText("Hello\n");
			}
			
		});
		
		
		add(textPanel, BorderLayout.CENTER);  // TextPanel (JPanel) ustawione w centrum i wypelnia cale dostepne miejsce
		add(btn, BorderLayout.SOUTH);
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 500);
		
	}
	
	
	
}
