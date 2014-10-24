package archeologyp2.mpt;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * THE FIND DIALOG FOR THE MPT GUI
 * 
 * This class contains a dialog that has multiple GUI tools
 * implemented, and allows the user to see a user friendly 
 * dialog box if they specified they wanted to add a find 
 * to their map. It contains five labels, four text fields,
 * one check box, one combobox, and two buttons. 
 * 
 * @author Daniel
 * @author Celine
 */
public class FindDialog extends JDialog {

	private static final long serialVersionUID = 1L;


	private JLabel rowLabel;
	private JLabel columnLabel;
	private JTextField rowTextField;
	private JTextField columnTextField;
	private JButton confirmButton;
	private JButton cancelButton;
	private JComboBox<String> comboBox;
	private JTextField dateTextField;
	private JLabel dateLabel;
	private JLabel rowPromptLabel;
	private JCheckBox rowCheckBox;
	private JLabel dataLabel;
	private JTextField dataTextField;

	private GridBagConstraints constraints;

	private int row;
	private String column;
	private int date;
	private boolean entireRow;
	private String data;
	private SubController subController;

	/**
	 * For the public FindDialog
	 * 
	 * This includes a GridBagLayout that allows the GUI dialog
	 * box to seem pleasant to the user's eyes.
	 * 
	 * @param title
	 * @param subController
	 */
	public FindDialog(String title, SubController subController){
		this.subController = subController;
		
		setTitle(title);
		setLayout(new GridBagLayout());
		setSize(360, 220);
		setResizable(false);

		createTextFields();
		createButton();
		createComboBox();
		createCheckBox();

		rowLabel = new JLabel("Row: ");
		columnLabel = new JLabel("Column: ");
		dateLabel = new JLabel("Date: ");
		rowPromptLabel = new JLabel("Change an entire row? ");
		dataLabel = new JLabel("Description: ");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(3, 3, 3, 3);

		dataLabel.setEnabled(false);
		dataTextField.setEnabled(false);

		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.NONE;
		addComponent(rowPromptLabel, 0, 0, 2, 1);
		addComponent(rowCheckBox, 2, 0, 1, 1);
		addComponent(rowLabel, 0, 1, 1, 1);
		addComponent(columnLabel, 0, 2, 1, 1);
		addComponent(dateLabel, 0, 3, 1, 1);
		addComponent(dataLabel, 0, 4, 1, 1);
		
		constraints.anchor = GridBagConstraints.CENTER;
		addComponent(comboBox, 2, 1, 2, 1);
		constraints.anchor = GridBagConstraints.EAST;
		addComponent(rowTextField, 1, 1, 1, 1);
		addComponent(columnTextField, 1, 2, 1, 1);
		addComponent(dateTextField, 1, 3, 1, 1);
		addComponent(dataTextField, 1, 4, 1, 1);
		
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.NONE;
		constraints.weightx = 1;
		addComponent(confirmButton, 2, 5, 1, 1);
		addComponent(cancelButton, 3, 5, 1, 1);
	}

	/**
	 * For the private void createCheckBox
	 * 
	 * This method creates a check box for when the
	 * user wants to specify they want to change
	 * and entire row in their map. It checks to see
	 * if the check box has been clicked, and if so, 
	 * then it grays out the column option so the user
	 * won't get confused. 
	 */
	private void createCheckBox() {
		rowCheckBox = new JCheckBox();
		rowCheckBox.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				if(rowCheckBox.isSelected()){
					columnTextField.setEnabled(false);
					columnLabel.setEnabled(false);
				} else {
					columnTextField.setEnabled(true);
					columnLabel.setEnabled(true);
				}
			}

		});

	}

	/**
	 * For public void addComponent
	 * 
	 * This takes in multiple components from the public FindDialog
	 * and smushes the parameters down so they can all fit in the
	 * constraints and add it to the GridBagLayout
	 * 
	 * @param component
	 * @param column
	 * @param row
	 * @param width
	 * @param height
	 */
	public void addComponent(JComponent component, int column, int row, int width, int height){
		constraints.gridx = column;
		constraints.gridy = row;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		this.add(component, constraints);
	}

	/**
	 * For private void createTextFields
	 * 
	 * This creates the four text fields the user will use
	 * to input their information.
	 */
	private void createTextFields() {
		rowTextField = new JTextField(5);
		columnTextField = new JTextField(5);
		dateTextField = new JTextField(5);
		dataTextField = new JTextField(5);
	}

	/**
	 * For private void createButton
	 * 
	 * This creates exactly two buttons, each with ActionListeners.
	 * The first is the "OK" button, which when clicked will take the
	 * user input, and run it through other called methods. The second
	 * one is the "Cancel" button, which will let the user click out
	 * of the GUI without having to click the red X. 
	 */
	private void createButton() {
		confirmButton = new JButton("OK");
		confirmButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				row = Integer.parseInt(rowTextField.getText());
				column = columnTextField.getText();
				date = Integer.parseInt(dateTextField.getText());
				entireRow = rowCheckBox.isSelected();
				data = dataTextField.getText();
				subController.addFind(row, column, comboBox.getSelectedIndex() + 1, date, data, entireRow);
				dispose();
			}
		});

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}

	/**
	 * For private void createComboBox
	 * 
	 * This creates the combobox, which allows the user to
	 * specify down to the details what kind of find they'd
	 * like to add in, from a decorated pot to a nonferrous metal. 
	 * Depending on what they want, the last label will change to
	 * suit the needs of the user. 
	 * 
	 */
	private void createComboBox() {
		String[] options = {"Decorated Pot", "Submerged Pot", "Storage Pot", "Kiln", "Hearth", "Ferrous", "NonFerrous"};
		comboBox = new JComboBox<>(options);
		comboBox.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				switch(e.getItem().toString()){
				case "Decorated Pot":
					dataLabel.setText("Description: ");
					break;
				case "Submerged Pot":
					dataLabel.setText("Depth: ");
					break;
				case "Storage Pot":
					dataLabel.setText("Volume: ");
					break;
				case "Kiln":
					dataLabel.setText("Radius: ");
					break;
				case "Hearth":
					dataLabel.setText("Length, Width: ");
					break;
				case "Ferrous":
					dataLabel.setText("Strength: ");
					break;
				case "NonFerrous":
					dataLabel.setText("Type: ");
					break;
				}
				dataLabel.setEnabled(true);
				dataTextField.setEnabled(true);
			}
		});
	}
}
