/////////// ROWS AND COLUMNS NEED TO BE SET UP!!!!!!!!!!!!
/////////// ROWS AND COLUMNS NEED TO BE SET UP!!!!!!!!!!!!
/////////// ROWS AND COLUMNS NEED TO BE SET UP!!!!!!!!!!!!
/////////// ROWS AND COLUMNS NEED TO BE SET UP!!!!!!!!!!!!
/////////// ROWS AND COLUMNS NEED TO BE SET UP!!!!!!!!!!!!
/////////// ROWS AND COLUMNS NEED TO BE SET UP!!!!!!!!!!!!
/////////// ROWS AND COLUMNS NEED TO BE SET UP!!!!!!!!!!!!
/////////// ROWS AND COLUMNS NEED TO BE SET UP!!!!!!!!!!!!
/////////// ROWS AND COLUMNS NEED TO BE SET UP!!!!!!!!!!!!

package archeologyp2.mpt;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * THE ADD FEATURE DIALOG OF THE MPT GUI
 * 
 * This class contains methods that allow the user to 
 * see a GUI if they clicked on the menu item "Add Feature".
 * It will ask for what coordinate (or row) they want to
 * change, and what feature they want to change
 * what they specified to. 
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class AddFeatureDialog extends JDialog implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	private SubController subController;

	private JComboBox<String> comboBox;
	private JButton confirmButton;
	private JButton cancelButton;
	private boolean singleOrRow;
	private JFrame frame;

	private int row, column;
	/**
	 * For public AddFeatureDialog
	 * 
	 * This contains a GridBagLayout, which makes the user interface
	 * seem more pleasing to the user's eyes. It contains a check box,
	 * three labels, two text fields, two buttons, and a combobox for
	 * the user to specify what feature they want to change their 
	 * specified coordinate (or row) to. It also implements a 
	 * ChangeListener to see whether or not the user has selected they
	 * wanted to change a whole row, which makes typing in a 
	 * column grey out. 
	 * 
	 * @param title
	 * @param subController
	 * @param column 
	 * @param row 
	 */
	public AddFeatureDialog(String title, SubController subController, int row, int column){
		this.subController = subController;
		this.row = row;
		this.column = column;
		
		setTitle(title);
		setSize(280, 100);
		setLayout(new GridBagLayout());
		setResizable(false);
		
		GridBagConstraints constraints = new GridBagConstraints();
		createButtons();
		createComboBox();

		

		// =========== FIRST COLUMN ========= //
		constraints.insets = new Insets(3,3,3,3);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.NONE;

		constraints.gridx = 0;
		constraints.gridy = 0; 
		add(comboBox, constraints);

		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 1;
		constraints.gridy = 0;
		add(confirmButton, constraints);
		
		constraints.gridx = 2;
		constraints.gridy = 0;
		add(cancelButton, constraints);

	}

	/**
	 * 
	 */
	private void createComboBox() {
		String[] choices = {"Dirt", "Stone", "Post Hole"};
		comboBox = new JComboBox<String>(choices);
		comboBox.addKeyListener(this);
	}

	/**
	 * 
	 */
	private void createButtons(){
		confirmButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		
		confirmButton.addActionListener(this);
		confirmButton.addKeyListener(this);
		
		cancelButton.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					dispose();
				}
			}
		});
		
		cancelButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}
	/**
	 * For public void actionPerformed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			int feature = comboBox.getSelectedIndex() + 1;
			singleOrRow = false;
			subController.changeFeature(row, column, feature, singleOrRow);
			subController.updateMap();
		}
		catch(NumberFormatException n){
			JOptionPane.showMessageDialog(frame,
					"Uh oh! Looks like you typed in something wrong. Please try again.",
					"Error",
					JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException n){
			JOptionPane.showMessageDialog(frame,
					"Uh oh! Looks like you typed in something wrong. Please try again.",
					"Error",
					JOptionPane.ERROR_MESSAGE);
		}
		finally{
			dispose();
		}
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
	public void keyReleased(KeyEvent e) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	}
}