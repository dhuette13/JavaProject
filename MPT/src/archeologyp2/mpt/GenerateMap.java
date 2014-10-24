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

import archeologyp2.shared.map.Coordinate;
import archeologyp2.shared.map.Map;
import archeologyp2.shared.map.Utilities;

/**
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class GenerateMap extends JDialog {
	
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
	private SubController subController;
	
	public GenerateMap(String title, Map<Coordinate> map){
		this.setTitle(title);
		this.map = map;
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

	public void addComponent(JComponent component, int column, int row, int width, int height){
		constraints.gridx = column;
		constraints.gridy = row;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		this.add(component, constraints);
	}
	
	private void createTextFields() {
		widthTextField = new JTextField(5);
		heightTextField = new JTextField(5);
	}
	
	private void createButtons() {
		confirmButton = new JButton("OK");
		confirmButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				width = Integer.parseInt(widthTextField.getText());
				height = Integer.parseInt(heightTextField.getText());
				map = Utilities.generateMap(width, height);
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
