package archeologyp2.adt;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 * THE SCANDIALOG GUI
 * 
 * This class makes the Scan dialog, which the user uses if they 
 * wish to use the tools (magnetometer, metal detector) to figure out
 * if there are any metal objects or charcoal in a coordinate of
 * their choice.
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class ScanDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private SubController subController;

	private JComboBox<String> comboBox;
	private JLabel rowLabel;
	private JLabel colLabel;
	private JLabel rowPromptLabel;
	private JTextField rowText;
	private JTextField colText;
	private JButton oButton;
	private JButton cButton;
	private boolean singleOrRow;

	//For Try-Catch: "Exception in thread "AWT-EventQueue-0" java.lang.NumberFormatException"
	/**
	 * For public ScanDialog
	 * 
	 * This method sets the dialog up so that the user will be able to
	 * specify what tool they want to use (magnetometer, metal detector)
	 * and what coordinate they want to scan with said tool. It contains
	 * two labels, two text areas, two buttons, and a combobox that shows
	 * the user their tool options. 
	 * 
	 * @param title
	 * @param subController
	 */
	public ScanDialog(String title, SubController subController){
		this.setTitle(title);
		this.subController = subController;
		setSize(340,180);
		setVisible(true);
		setLayout(new GridBagLayout());
		setResizable(false);

		rowLabel = new JLabel("Row: ");
		rowText = new JTextField(5);
		colLabel = new JLabel("Column: ");
		colText = new JTextField(5);
		oButton = new JButton("OK");
		cButton = new JButton("Cancel");

		String[] choices = {"Magnetometer", "Metal Detector"};
		comboBox = new JComboBox<String>(choices);

		GridBagConstraints constraints = new GridBagConstraints();

		// =========== FIRST COLUMN ========= //
		constraints.insets = new Insets(3,3,3,3);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.NONE;

		constraints.gridx = 0;
		constraints.gridy = 2;
		add(rowLabel, constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		add(colLabel, constraints);

		// ========== SECOND COLUMN ========== //
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridx = 0;
		constraints.gridy = 0;

		constraints.gridx = 1;
		constraints.gridy = 2;
		add(rowText, constraints);

		constraints.gridx = 1;
		constraints.gridy = 3;
		add(colText, constraints);

		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 1;
		constraints.gridy = 4;
		add(oButton, constraints);
		oButton.addActionListener(this);

		// ============= THIRD COLUMN =========== //
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 2;
		constraints.gridy = 0;
		add(comboBox, constraints);

		constraints.gridx = 2;
		constraints.gridy = 2; 
		add(comboBox, constraints);
		
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 2;
		constraints.gridy = 4;
		add(cButton, constraints);
		cButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * 
	 */
	@Override
	/**
	 * For the public void actionPerformed
	 * 
	 * This will activate when the user clicks the
	 * "OK" button. Once the user clicks the
	 * "OK" button, based on what tool they'd selected, 
	 * this passes information to the specified tool.
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		int feature = comboBox.getSelectedIndex();
		int row = Integer.parseInt(rowText.getText());
		String col = colText.getText();
		switch(feature)
		{
		case 0: //Magnetometer
			subController.magnetoMeter(row, col);
			break;
		case 1: //Metal Detector
			subController.metalDetector(row, col);
			break;
		default:
			break;
		}
		dispose();
	}
}