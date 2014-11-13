package archeologyp2.adt;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ReportDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JTextArea display;
	private JScrollPane scrollPane;
	private JPanel buttonPanel;
	private JButton confirmButton;
	
	public ReportDialog(Report report, double averageDate, double standardDeviation){
		setTitle("Finds Report");
		setSize(800, 600);
		display = new JTextArea();
		display.setFont(new Font("Courier New", 0, 15));
		scrollPane = new JScrollPane(display);
		buttonPanel = new JPanel();
		confirmButton = new JButton("OK");
		confirmButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		buttonPanel.add(confirmButton, BorderLayout.EAST);
		
		add(scrollPane, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
		display.setText(report.toString() + "The average date is: " + averageDate + "\nThe Standard Deviation is: " + standardDeviation);
	}

}
