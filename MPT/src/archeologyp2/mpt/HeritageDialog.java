package archeologyp2.mpt;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class HeritageDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JLabel rowLabel;
	private JLabel columnLabel;
	private JTextField rowTextField;
	private JTextField columnTextField;
	private JButton confirmButton;
	private JButton cancelButton;
	
	private GridBagConstraints constraints;
	
	private int row;
	private String column;
	private SubController subController;
	
	public HeritageDialog(String title, SubController subController){
		this.setTitle(title);
		this.subController = subController;
		setSize(200, 120);
		setResizable(false);
		setLayout(new GridBagLayout());

		createTextFields();
		createButtons();
		
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(3, 3, 3, 3);
		
		rowLabel = new JLabel("Row: ");
		columnLabel = new JLabel("Column: ");
		constraints.anchor = GridBagConstraints.WEST;
		addComponent(rowLabel, 0, 0, 1, 1);
		addComponent(columnLabel, 0, 1, 1, 1);
		constraints.anchor = GridBagConstraints.EAST;
		addComponent(rowTextField, 1, 0, 1, 1);
		addComponent(columnTextField, 1, 1, 1, 1);
		addComponent(confirmButton, 0, 2, 1, 1);
		addComponent(cancelButton, 1, 2, 1, 1);
	}

	public void addComponent(JComponent component, int column, int row, int width, int height){
		constraints.gridx = column;
		constraints.gridy = row;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		this.add(component, constraints);
	}
	
	private void createTextFields() {
		rowTextField = new JTextField(5);
		columnTextField = new JTextField(5);
	}
	
	private void createButtons() {
		confirmButton = new JButton("OK");
		confirmButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				row = Integer.parseInt(rowTextField.getText());
				column = columnTextField.getText();
				subController.markHeritage(row, column);
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
}
