package archeologyp2.mpt;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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
public class FindDialog extends JDialog implements KeyListener {

	private static final long serialVersionUID = 1L;


	private JButton confirmButton;
	private JButton cancelButton;
	private JComboBox<String> comboBox;
	private JTextField dateTextField;
	private JLabel dateLabel;
	private JLabel dataLabel;
	private JTextField dataTextField;
	private JFrame frame;

	private GridBagConstraints constraints;

	private int row, column;
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
	public FindDialog(String title, SubController subController, int row, int column){
		this.subController = subController;
		this.row = row;
		this.column = column;
		
		setTitle(title);
		setLayout(new GridBagLayout());
		setSize(340, 160);
		setResizable(false);
		addKeyListener(this);

		createTextFields();
		createButton();
		createComboBox();

		dateLabel = new JLabel("Date: ");
		dataLabel = new JLabel("Description: ");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);

		dataLabel.setEnabled(false);
		dataTextField.setEnabled(false);

		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.NONE;
		addComponent(dateLabel, 0, 0, 1, 1);
		addComponent(dataLabel, 1, 0, 1, 1);
		
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.anchor = GridBagConstraints.EAST;
		addComponent(dateTextField, 0, 1, 1, 1);
		addComponent(dataTextField, 1, 1, 1, 1);
		
		constraints.anchor = GridBagConstraints.EAST;
		addComponent(comboBox, 0, 2, 1, 1);
		
		constraints.fill = GridBagConstraints.NONE;
		constraints.weightx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		addComponent(confirmButton, 2, 2, 1, 1);
		constraints.anchor = GridBagConstraints.EAST;
		addComponent(cancelButton, 2, 2, 1, 1);
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
	public void addComponent(JComponent component, int row, int column, int width, int height){
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
		dateTextField = new JTextField(5);
		dataTextField = new JTextField(5);
		
		dateTextField.addKeyListener(this);
		dataTextField.addKeyListener(this);
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
				try{
					date = Integer.parseInt(dateTextField.getText());
					data = dataTextField.getText();
					subController.addFind(row, column, comboBox.getSelectedIndex() + 1, date, data, entireRow);
				}
				catch(NumberFormatException n){
					JOptionPane.showMessageDialog(frame,
						    "Uh oh! Looks like you typed in something wrong. Please try again.",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
				finally{
					dispose();
				}
			}
		});
		confirmButton.addKeyListener(this);

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelButton.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					dispose();
				}
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
		comboBox.addKeyListener(this);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			confirmButton.doClick();
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
