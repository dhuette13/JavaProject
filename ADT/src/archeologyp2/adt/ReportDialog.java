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

/**
 * THE REPORT DIALOG FOR THE ADT
 * 
 * Represents a dialog window to display report data such as
 * found items, average date, and standard deviation. Can optionally
 * receive an archaeologist name to display as well.
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class ReportDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	/* Graphical components */
	private JTextArea display;
	private JScrollPane scrollPane;
	private JPanel buttonPanel;
	private JButton confirmButton;
	
	/**
	 * For public ReportDialog
	 * 
	 * Receives a report, average date, and standard deviation to 
	 * display. Creates dialog window with confirm button and text
	 * area to display information.
	 * 
	 * @param report
	 * @param averageDate
	 * @param standardDeviation
	 */
	public ReportDialog(Report report, double averageDate, double standardDeviation){
		setTitle("Finds Report");
		setSize(800, 600);
		setResizable(true);
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
		
		display.setText(report.toString() + "The average date is: " + String.format("%.2f", averageDate) + 
				"\nThe Standard Deviation is: " + String.format("%.2f", standardDeviation));
	}

	/**
	 * For public ReportDialog
	 * 
	 * Receives a name, report, average date, and standard deviation to 
	 * display. Creates dialog window with confirm button and text
	 * area to display information and archaeologist name.
	 * 
	 * @param name
	 * @param report
	 * @param averageDate
	 * @param standardDeviation
	 */
	public ReportDialog(String name, Report report, double averageDate, double standardDeviation) {
		this(report, averageDate, standardDeviation);
		display.setText(name + " has completed his task, here are his finds!\n\n" + report.toString() + 
				"The average date is: " + String.format("%.2f", averageDate) + "\nThe Standard Deviation is: " + String.format("%.2f", standardDeviation));
	}

}
