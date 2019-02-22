package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class ProgressDialog extends JDialog{

	
	private JButton cancelButton;
	private JProgressBar progressBar;
	private ProgressDialogListener listener;
	
	
	public ProgressDialog(Window parent, String title) {
		super(parent, title, ModalityType.APPLICATION_MODAL);
		
		
		cancelButton = new JButton("Cancel");
		progressBar = new JProgressBar();
		
		//ops na pasku postepu
		progressBar.setStringPainted(true);
		progressBar.setString("Retrieving messages...");
		
		progressBar.setMaximum(10);
		//progressBar.setIndeterminate(true);//inny typ paska stanu
		
		setLayout(new FlowLayout());
		
		Dimension size = cancelButton.getPreferredSize();
		size.width = 400;
		progressBar.setPreferredSize(size);
		
		add(progressBar);
		add(cancelButton);
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(listener != null) {
					
					//przerwanie ladowania wiadomosci po nacisnieciu przycisku cancel
					listener.progressDialogCancelled();
				}
			}
			
		});
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				if(listener != null) {
					
					//przerwanie ladowania wiadomosci po nacisnieciu przycisku cancel
					listener.progressDialogCancelled();
				}
			}
			
		});
		
		pack(); // minimalny rozmiar okna tak aby pomiescilo wszystkie komponenty
		
		//setSize(400, 200);
		
		setLocationRelativeTo(parent);
		
		
	}
	
	
	public void setListener(ProgressDialogListener listener) {
		this.listener = listener;
	}
	
	
	public void setMaximum(int value) {
		
		progressBar.setMaximum(value);
		
	}
	
	public void setValue(int value) {
		
		int progress = 100*value/progressBar.getMaximum();
		
		progressBar.setString(String.format("%d%% complete", progress));
		
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
