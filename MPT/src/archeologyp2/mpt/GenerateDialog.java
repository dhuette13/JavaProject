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

import archeologyp2.shared.gui.CompletionEvent;
import archeologyp2.shared.gui.Relay;
import archeologyp2.shared.map.Coordinate;
import archeologyp2.shared.map.Map;
import archeologyp2.shared.map.Utilities;

/**
 * THE GENERATE DIALOG OF THE MPT GUI
 * 
 * This class brings up a dialog box that asks the user for
 * a width and a height, and generates a blank map based off of
 * the input given. It extends JDialog. 
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class GenerateDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel widthLabel;
	private JLabel heightLabel;
	private JTextField widthTextField;
	private JTextField heightTextField;
	private JButton confirmButton;
	private JButton cancelButton;
	
	private GridBagConstraints constraints;
	
	private int width;
	private int height;
	private Map<Coordinate> map;

	private Relay relay;
	
	/**
	 * For public GenerateDialog
	 * 
	 * This creates the dialog box by the GridBagLayout, and has 
	 * two labels, before running it through the addComponent method
	 * to make sure the constraints are all sorted out. 
	 * 
	 * @param title
	 */
	public GenerateDialog(String title){
		this.setTitle(title);
		setSize(200, 120);
		setResizable(false);
		setLayout(new GridBagLayout());

		createTextFields();
		createButtons();
		
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(3, 3, 3, 3);
		
		widthLabel = new JLabel("Width: ");
		heightLabel = new JLabel("Height: ");
		addComponent(widthLabel, 0, 0, 1, 1);
		addComponent(widthTextField, 1, 0, 1, 1);
		addComponent(heightLabel, 0, 1, 1, 1);
		addComponent(heightTextField, 1, 1, 1, 1);
		addComponent(confirmButton, 0, 2, 1, 1);
		addComponent(cancelButton, 1, 2, 1, 1);
	}

	/**
	 * For public void addComponent
	 * 
	 * This takes the information from public GenerateDialog and 
	 * puts them into a constraints object to make handling the add
	 * component into the GridBagLayout quick and efficient. 
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
	 * For the private void createTextFields
	 * 
	 * This creates the width and height text fields for
	 * the user to type their input in. 
	 */
	private void createTextFields() {
		widthTextField = new JTextField(5);
		heightTextField = new JTextField(5);
	}
	
	/**
	 * For private void createButtons
	 * 
	 * This creates exactly two buttons, each with ActionListeners.
	 * The first is the "OK" button, which when clicked will take the
	 * user input, and run it through other called methods. The second
	 * one is the "Cancel" button, which will let the user click out
	 * of the GUI without having to click the red X. 
	 */
	private void createButtons() {
		confirmButton = new JButton("OK");
		confirmButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				width = Integer.parseInt(widthTextField.getText());
				height = Integer.parseInt(heightTextField.getText());
				map = Utilities.generateMap(height, width);
				relay.fireMyEvent(new CompletionEvent(this));
				dispose();
			}
		});
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}
	
	/**
	 * For public Map<Coordinate> getMap()
	 * 
	 * @return map
	 */
	public Map<Coordinate> getMap(){
		return map;
	}

	/**
	 * For public void setRelay
	 * 
	 * @param relay
	 */
	public void setRelay(Relay relay) {
		this.relay = relay;
	}
}
