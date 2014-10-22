/**
 * 
 */
package archeologyp2.mpt;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Daniel
 *
 */
public class FindDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	
	private JLabel rowLabel;
	private JLabel columnLabel;
	private JTextField rowTextField;
	private JTextField columnTextField;
	private JButton confirmButton;
	private JComboBox<String> comboBox;
	private JTextField dataTextField;
	private JLabel dataLabel;
	
	private int row;
	private String column;
	private SubController subController;

	public FindDialog(String title, SubController subController){
		this.setTitle(title);
		createTextFields();
		createButton();
		createComboBox();
		this.subController = subController;
		this.setLayout(new GridLayout(4,3));
		this.setSize(280, 120);
		this.setResizable(false);
		rowLabel = new JLabel("row: ");
		columnLabel = new JLabel("col: ");
		dataLabel = new JLabel("data: ");
		
		this.add(rowLabel);
		this.add(rowTextField);
		this.add(comboBox);
		this.add(columnLabel);
		this.add(columnTextField);
		this.add(new JPanel());
		this.add(dataLabel);
		this.add(dataTextField);
		this.add(new JPanel());
		this.add(confirmButton);
	}

	/**
	 * 
	 */
	private void createTextFields() {
		rowTextField = new JTextField(5);
		columnTextField = new JTextField(5);
		dataTextField = new JTextField(8);
	}
	
	/**
	 * 
	 */
	private void createButton() {
		confirmButton = new JButton("OK");
		confirmButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				row = Integer.parseInt(rowTextField.getText());
				column = columnTextField.getText();
			}
		});
	}
	
	private void createComboBox() {
		comboBox = new JComboBox<>();
		comboBox.addItem("Pot");
		comboBox.addItem("Charcoal");
		comboBox.addItem("Metal");
	}
}
