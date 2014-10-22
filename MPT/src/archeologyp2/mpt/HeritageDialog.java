package archeologyp2.mpt;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
	
	private int row;
	private String column;
	private SubController subController;
	
	public HeritageDialog(String title, SubController subController){
		this.setTitle(title);
		this.subController = subController;
		createTextFields();
		createButton();
		rowLabel = new JLabel("row: ");
		columnLabel = new JLabel("col: ");
		this.setSize(180, 100);
		this.setLayout(new GridLayout(3, 2));
		this.add(rowLabel);
		this.add(rowTextField);
		this.add(columnLabel);
		this.add(columnTextField);
		this.add(confirmButton);
		this.setResizable(false);
	}

	private void createTextFields() {
		rowTextField = new JTextField(5);
		columnTextField = new JTextField(5);
	}
	
	private void createButton() {
		confirmButton = new JButton("OK");
		confirmButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				row = Integer.parseInt(rowTextField.getText());
				column = columnTextField.getText();
				subController.markHeritage(row, column);
				subController.updateMap();
				dispose();
			}
		});
	}
}
