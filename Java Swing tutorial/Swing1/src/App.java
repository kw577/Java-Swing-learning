import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class App {

	public static void main(String[] args) {
		
		//otwarcie w nowym watku - opcjonalne
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				
			}
			
		});
		
		
		JFrame frame = new JFrame("Hello World");
		frame.setVisible(true);
		
		//Zamkniecie okna aplikacji powoduje tez zakonczenie procesu aplikacji
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		frame.setSize(600, 500);
		
		
	}

}
