import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// JPanel sluzy do grupowania komponentow wewnatrz JFrame
// Dla JPanel mozna stosowac layouty jak dla Jrame - czyli mozna np rozkladac JPanel wewnatrz JFrame (wg przyjetego layoutu) a w Jpanel rowniez mozna wykorzystywac layout'y do ustawiania komponentow

public class TextPanel extends JPanel {
	
	private JTextArea textArea;
	
	public TextPanel() {
		textArea = new JTextArea();
		
		setLayout(new BorderLayout());
		
		
		// JScrollPane (pasek boczny i dolny przewijania) - moze byc stosowany dla roznych komponentow nie tylko JTextArea
		add(new JScrollPane(textArea), BorderLayout.CENTER); // textArea zajmie cala dostepna przestrzen panelu
	}
	
	
	
	public void appendText(String text) {
		textArea.append(text);
	}
	
}
