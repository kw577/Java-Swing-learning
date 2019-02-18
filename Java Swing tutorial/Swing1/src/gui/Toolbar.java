package gui;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class Toolbar extends JToolBar implements ActionListener{
	
	private JButton saveButton;
	private JButton refreshButton;
	
	private ToolbarListener textListener;
	
	public Toolbar() {
		
		// po odznaczeniu 2 ponizszych opcji pasek narzedzi jest "dragable" - mozna go "przeciagnac" do osobnego okna
		//setBorder(BorderFactory.createEtchedBorder());
		//setFloatable(false);
		
		
		saveButton = new JButton();
		saveButton.setIcon(createIcon("/images/Save16.gif"));
		saveButton.setToolTipText("Save"); // po najechaniu kursorem na przycisk wyswietli sie ten komunikat
		
		refreshButton = new JButton();
		
		saveButton.addActionListener(this);
		refreshButton.addActionListener(this);
		refreshButton.setIcon(createIcon("/images/Refresh16.gif"));
		refreshButton.setToolTipText("Refresh");
		
		// UWAGA - zastosowano tu Flow Layout
		
		//setLayout(new FlowLayout()); // przyciski beda ustawione na srodku panelu
		
		setLayout(new FlowLayout(FlowLayout.LEFT)); // przyciski po lewej stronie panelu
		
		add(saveButton);
		//addSeparator();//odstep miedzy przyciksami
		add(refreshButton);
	}
	
	
	private ImageIcon createIcon(String path) {
			
		URL url = getClass().getResource(path);
		
		if(url == null) {
			System.err.println("Unable to load image: " + path);
		}
		
		
		ImageIcon icon = new ImageIcon(url);
		return icon;
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
