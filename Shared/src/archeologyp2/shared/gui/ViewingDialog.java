package archeologyp2.shared.gui;

import java.awt.GridBagConstraints; 
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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

import archeologyp2.shared.map.Coordinate;
import archeologyp2.shared.map.Map;
import archeologyp2.shared.map.MapEditor;
import archeologyp2.shared.map.ViewingOption;

/**
 * VIEWING DIALOG FOR SHARED GUI
 * 
 * This class is a dialog box that asks
 * the user what they'd like to change features
 * to for easier viewing. It allows the user
 * to enter a character for the user's
 * convenience, and see different symbols
 * instead of just hard-to-notice-something's-different
 * alphabet letters.
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class ViewingDialog extends JDialog implements KeyListener {

	private static final long serialVersionUID = 1L;
	
	/* Graphical Components of the Dialog */
	private JComboBox<String> comboBox;
	private JLabel charLabel;
	private JTextField charField;
	private JButton confirmButton;
	private JButton cancelButton;
	private GridBagConstraints constraints;
	private Relay relay;
	
	private char symbol;
	private String selection;
	private Map<Coordinate> map;
	
	private JFrame frame;
	
	/**
	 * For public ViewingDialog
	 * Constructs the dialog's gui
	 * 
	 * @param title
	 * @param map
	 */
	public ViewingDialog(String title, Map<Coordinate> map){
		this.map = map;
		setSize(420, 100);
		setTitle(title);
		setLayout(new GridBagLayout());
		setResizable(false);

		createComboBox();
		createTextField();
		createButton();
		charLabel = new JLabel("Enter a character: ");
		
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(3, 3, 3, 3);
		constraints.anchor = GridBagConstraints.WEST;
		addComponent(charLabel, 0, 0, 1, 1);
		constraints.anchor = GridBagConstraints.CENTER;
		addComponent(charField, 1, 0, 1, 1);
		constraints.anchor = GridBagConstraints.EAST;
		addComponent(comboBox, 2, 0, 1, 1);
		constraints.anchor = GridBagConstraints.WEST;
		addComponent(confirmButton, 2, 1, 1, 1);
		constraints.anchor = GridBagConstraints.EAST;
		addComponent(cancelButton, 2, 1, 1, 1);
	}
	
	/**
	 * For public void addComponent
	 * Adds the specified component to the dialog using constraints
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
	 * For private void createButton
	 * Creates the OK and cancel buttons, and gives them
	 * action listeners
	 * 
	 * OK gets the user's information and changes the viewing accordingly
	 */
	private void createButton() {
		confirmButton = new JButton("OK");
		confirmButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					symbol = charField.getText().charAt(0);
					selection = comboBox.getSelectedItem().toString();
					map.setViewingOption(ViewingOption.userModified);
					MapEditor.changeViewingSymbol(map, selection, symbol);
					MapEditor.updateView(map);
					relay.fireMyEvent(new CompletionEvent(this));
				}
				catch(NullPointerException | StringIndexOutOfBoundsException e){
					JOptionPane.showMessageDialog(frame,
						    "Gee-wizz! Uh oh! Looks like you typed in something wrong. Please try again.",
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
	 * For private void createTextField
	 * Creates the text fields for data entry
	 */
	private void createTextField() {
		charField = new JTextField(5);
		charField.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				charField.setText("");
			}
			public void focusLost(FocusEvent arg0) {
			}
		});
		charField.addKeyListener(this);
	}

	/**
	 * For private void createComboBox
	 * Creates the combo box for selecting the type of symbol to change
	 */
	private void createComboBox() {
		comboBox = new JComboBox<String>();
		comboBox.addItem(MapEditor.naturalToken);
		comboBox.addItem(MapEditor.stoneToken);
		comboBox.addItem(MapEditor.postHoleToken);
		comboBox.addItem(MapEditor.excavatedNaturalToken);
		comboBox.addItem(MapEditor.excavatedStoneToken);
		comboBox.addItem(MapEditor.excavatedPostHoleToken);
		comboBox.addKeyListener(this);
	}

	/**
	 * For public void setRelay
	 * @param relay
	 */
	public void setRelay(Relay relay) {
		this.relay = relay;
	}
	
	public Map<Coordinate> getMap(){
		return map;
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
