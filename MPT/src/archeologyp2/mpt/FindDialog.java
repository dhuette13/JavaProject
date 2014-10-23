/**
 * 
 */
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
	private SubController subController;

	public FindDialog(String title, SubController subController){
		this.setTitle(title);
		createTextFields();
		createButton();
		createComboBox();
		createCheckBox();
		this.subController = subController;
		setLayout(new GridBagLayout());
		setSize(360, 220);
		setResizable(false);

		rowLabel = new JLabel("row: ");
		columnLabel = new JLabel("col: ");
		dateLabel = new JLabel("date: ");
		rowPromptLabel = new JLabel("Change an entire row? ");
		dataLabel = new JLabel("description: ");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(3, 3, 3, 3);

		constraints.anchor = GridBagConstraints.EAST;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		addComponent(rowPromptLabel, 0, 0, 2, 1);
		addComponent(rowCheckBox, 2, 0, 1, 1);
		addComponent(rowLabel, 0, 1, 1, 1);
		addComponent(rowTextField, 1, 1, 1, 1);
		addComponent(comboBox, 2, 1, 2, 1);
		addComponent(columnLabel, 0, 2, 1, 1);
		addComponent(columnTextField, 1, 2, 1, 1);
		addComponent(dateLabel, 0, 3, 1, 1);
		addComponent(dateTextField, 1, 3, 1, 1);
		addComponent(dataLabel, 0, 4, 1, 1);
		addComponent(dataTextField, 1, 4, 1, 1);
		constraints.anchor = GridBagConstraints.EAST;
		constraints.fill = GridBagConstraints.NONE;
		constraints.weightx = 1;
		addComponent(confirmButton, 2, 5, 1, 1);
		addComponent(cancelButton, 3, 5, 1, 1);
	}

	/**
	 * 
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

	public void addComponent(JComponent component, int column, int row, int width, int height){
		constraints.gridx = column;
		constraints.gridy = row;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		this.add(component, constraints);
	}

	/**
	 * 
	 */
	private void createTextFields() {
		rowTextField = new JTextField(5);
		columnTextField = new JTextField(5);
		dateTextField = new JTextField(5);
		dataTextField = new JTextField(5);
	}

	/**
	 * 
	 */
	private void createButton() {
		confirmButton = new JButton("OK");
		confirmButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rowCheckBox.isSelected()){
					row = Integer.parseInt(rowTextField.getText());
					date = Integer.parseInt(dateTextField.getText());
					subController.addFind(row, "A", comboBox.getSelectedIndex() + 1, date, true);
				} else {
					row = Integer.parseInt(rowTextField.getText());
					column = columnTextField.getText();
					date = Integer.parseInt(dateTextField.getText());
					subController.addFind(row, column, comboBox.getSelectedIndex() + 1, date, false);
				}
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

	private void createComboBox() {
		String[] options = {"Decorated Pot", "Submerged Pot", "Storage Pot", "Kiln", "Hearth", "Ferrous", "NonFerrous"};
		comboBox = new JComboBox<>(options);
		comboBox.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				switch(e.getItem().toString()){
				case "Decorated Pot":
					break;
				case "Submerged Pot":
					break;
				case "Storage Pot":
					break;
				case "Kiln":
					break;
				case "Hearth":
					break;
				case "Ferrous":
					break;
				case "NonFerrous":
					break;
				}
			}
			
		});
	}
}
