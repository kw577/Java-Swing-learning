import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;



// https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html   - opis layoutu w Swing'u



public class MainFrame extends JFrame {
	
	
	private JTextArea textArea;
	private JButton btn;
	
	
	public MainFrame() {
		super("Hello World");
		
		// UWAGA  - w tym przykladzie zastosowano BorderLayout   - tu opisano inne dostepne layout'y https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
		setLayout(new BorderLayout());
		
		textArea = new JTextArea();
		btn = new JButton("Click me!");
		
		
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Metoda uruchamiana po wcisieciu przycisku
				
				textArea.append("Hello\n");
			}
			
		});
		
		
		add(textArea, BorderLayout.CENTER);  // ustawione w centrum i wypelnia cale dostepne miejsce
		add(btn, BorderLayout.SOUTH);
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 500);
		
	}
	
	
	
}
