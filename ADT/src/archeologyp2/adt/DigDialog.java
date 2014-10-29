package archeologyp2.adt;

import java.awt.GridBagConstraints; 
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * 
 * DIG DIALOG TOOL TO HANDLE DIGGING
 * 
 * This class contains the methods DigDialog, addComponent,
 * createButtons, and addTextFields. It uses a JDialog and 
 * implements a GridBagLayout for a more user-friendly interface.
 * This class sets up the dialog box that the user will
 * be able to use to specify what coordinate they want to dig.
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class DigDialog extends JDialog implements KeyListener {

	private static final long serialVersionUID = 1L;
	
	private JLabel rowLabel;
	private JLabel columnLabel;
	private JTextField rowTextField;
	private JTextField columnTextField;
	private JButton confirmButton;
	private JButton cancelButton;
	
	private GridBagConstraints constraints;
	
	private JFrame frame;
	
	private int row;
	private String column;
	private SubController subController;
	
	/**
	 * For the public Dialog method
	 * 
	 * This method takes in a title and a subController. It uses
	 * a GridBagLayout and adds two labels, two text fields, and two
	 * buttons. 
	 * 
	 * @param title
	 * @param subController
	 */
	public DigDialog(String title, SubController subController){
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

	/**
	 * For the public void addComponent method
	 * 
	 * This method takes in parameters passed by the above method
	 * (public DigDialog) and smushes all the information 
	 * being passed to it until it can fit it correctly into
	 * constraints, to use in the add() to the frame. 
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
	 * For the private void createTextFields method
	 * 
	 * This method merely creates the text fields that the user will be 
	 * typing their input to. 
	 * 
	 */
	private void createTextFields() {
		rowTextField = new JTextField(5);
		columnTextField = new JTextField(5);
		
		rowTextField.addKeyListener(this);
		columnTextField.addKeyListener(this);
	}
	
	/**
	 * For the private void createButtons method
	 * 
	 * This method creates the buttons "OK" and 
	 * "Cancel", which use ActionListeners to see what the
	 * user would like the program to do. If the user
	 * selects "Cancel", the dialog box closes. If the
	 * user selects "OK", the program takes the
	 * information from the text fields and passes them
	 * to the SubController method dig to work its magic.
	 * 
	 */
	private void createButtons() {
		confirmButton = new JButton("OK");
		confirmButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					row = Integer.parseInt(rowTextField.getText());
					column = columnTextField.getText();
					subController.dig(row, column);
					subController.updateMap();
				} catch (HeritageException e) {
					System.out.println("You cannot dig here!");
				}
				catch(IndexOutOfBoundsException i){
					JOptionPane.showMessageDialog(frame,
						    "Uh oh! Looks like the input you gave are out of range. Please try again.",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
				catch(NumberFormatException n){
					JOptionPane.showMessageDialog(frame,
						    "Uh oh! Looks like something went wrong. Please try again.",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
				dispose();
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

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			confirmButton.doClick();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
