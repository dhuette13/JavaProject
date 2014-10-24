package archeologyp2.adt;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
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
	private JTextField rowText;
	private JTextField colText;
	private JButton oButton;
	private JButton cButton;

	//For Try-Catch: "Exception in thread "AWT-EventQueue-0" java.lang.NumberFormatException"
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
		subController.updateMap();
		dispose();
	}
}