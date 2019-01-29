import javax.swing.JFrame;
import javax.swing.SwingUtilities;

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
