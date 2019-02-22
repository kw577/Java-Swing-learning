package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class ProgressDialog extends JDialog{

	
	private JButton cancelButton;
	private JProgressBar progressBar;
	
	public ProgressDialog(Window parent) {
		super(parent, "Messages Downloading...", ModalityType.APPLICATION_MODAL);
		
		
		cancelButton = new JButton("Cancel");
		progressBar = new JProgressBar();
		
		//progressBar.setIndeterminate(true);//inny typ paska stanu
		
		setLayout(new FlowLayout());
		
		Dimension size = cancelButton.getPreferredSize();
		size.width = 400;
		progressBar.setPreferredSize(size);
		
		add(progressBar);
		add(cancelButton);
		
		pack(); // minimalny rozmiar okna tak aby pomiescilo wszystkie komponenty
		
		//setSize(400, 200);
		
		setLocationRelativeTo(parent);
		
		
	}
	
	public void setMaximum(int value) {
		
		progressBar.setMaximum(value);
		
	}
	
	public void setValue(int value) {
		progressBar.setValue(value);
	}

	@Override
	public void setVisible(final boolean visible) {
				
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				//System.out.println("Showing modal dialog");	
				
				if(visible == false) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
				else {
					progressBar.setValue(0); // zerowanie paska stanu przed jego uruchomieniem
				}
				
				ProgressDialog.super.setVisible(visible);	
				//System.out.println("Finished showing modal dialog");
			}
			
		});
	}


	
	
}
