
import javax.swing.SwingUtilities;

import gui.MainFrame;

public class App {

	public static void main(String[] args) {
		
		//otwarcie w nowym watku - opcjonalne
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				
				new MainFrame();
								
			}
			
		});
			
	}

}
