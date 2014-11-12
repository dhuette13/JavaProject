//=========================================================================
// CLASS TO EVENTUALLY DELETE
// CLASS TO EVENTUALLY DELETE
// CLASS TO EVENTUALLY DELETE
// CLASS TO EVENTUALLY DELETE
// CLASS TO EVENTUALLY DELETE
// CLASS TO EVENTUALLY DELETE
// CLASS TO EVENTUALLY DELETE
// CLASS TO EVENTUALLY DELETE
// CLASS TO EVENTUALLY DELETE
// CLASS TO EVENTUALLY DELETE
//=========================================================================


package archeologyp2.shared.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import archeologyp2.shared.map.Coordinate;
import archeologyp2.shared.map.Map;
import archeologyp2.shared.map.Utilities;

/**
 * PATH DIALOG FOR SHARED GUI
 * 
 * This is the dialog box that the program
 * implements for loading, saving, and 
 * exporting, and should look the same for those
 * three besides a change of title.
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class PathDialog extends JDialog implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;


	/* Graphical Components of the Dialog */
	private JLabel label;
	private JTextField textField;
	private JButton okButton;
	private JButton cancelButton;
	private Relay relay;
	private JFrame frame;

	private Map<Coordinate> map;
	private String title;

	/**
	 * For public PathDialog
	 * This constructs the gui of the Path Dialog
	 * by using the GridBagLayout. It looks pleasing and 
	 * simple to the viewers' eyes, and hopefully is self-
	 * explanatory. It contains a label, a text field, and
	 * two buttons (one for "OK" and one for "Cancel").
	 * 
	 * @param title
	 * @param map
	 */
	public PathDialog(String title, Map<Coordinate> map){
		try{
			this.map = map;
			this.title = title;

			setTitle(title);
			setSize(380, 100);
			setResizable(false);
			addKeyListener(this);

			Container pane = getContentPane();
			pane.setLayout(new GridBagLayout());

			label = new JLabel("Enter file to " + title.toLowerCase() + ": ");
			textField = new JTextField(20);
			textField.addKeyListener(this);
			okButton = new JButton("OK");
			okButton.addKeyListener(this);
			cancelButton = new JButton("Cancel");
			cancelButton.addKeyListener(new KeyAdapter(){
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER){
						cancelButton.doClick();
					}
				}
			});

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.insets = new Insets(3, 3, 3, 3);

			constraints.anchor = GridBagConstraints.CENTER;
			constraints.gridx = 0;
			constraints.gridy = 0;
			add(label, constraints);


			constraints.gridx = 1;
			constraints.gridy = 0;
			add(textField, constraints);

			constraints.anchor = GridBagConstraints.CENTER;
			constraints.gridx = 1;
			constraints.gridy = 1;
			add(okButton, constraints);

			constraints.anchor = GridBagConstraints.EAST;
			constraints.gridx = 1;
			constraints.gridy = 1;
			add(cancelButton, constraints);
			cancelButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});

			okButton.addActionListener(this);
		}
		catch(NullPointerException n){
			JOptionPane.showMessageDialog(frame,
					"This path cannot be done. Please try again.",
					"Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * For public void actionPerformed
	 * 
	 * Performs three actions:
	 * 1) Load
	 * 2) Save
	 * 3) Export
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			switch(title){
			case "Load":
				map = Utilities.load(textField.getText());
				relay.fireMyEvent(new CompletionEvent(this));
				break;
			case "Save":
				Utilities.save(map, textField.getText());
				break;
			case "Export":
				Utilities.exportMap(map, textField.getText());
				break;
			default:
				System.out.println("Not a valid path dialog");
			}
		} catch(NullPointerException n){
			JOptionPane.showMessageDialog(frame, "No file available to save or export!", "Error", JOptionPane.ERROR_MESSAGE);
		} catch(FileNotFoundException ex) {
			JOptionPane.showMessageDialog(frame,
					"Uh oh! Looks like you typed in something wrong. Please try again.",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(frame, "Invalid file. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
		} finally {
			dispose();
		}
	}

	/**
	 * For public void setRelay
	 * Sets the dialog's relay for triggering completion event
	 * 
	 * @param relay
	 */
	public void setRelay(Relay relay){
		this.relay = relay;
	}

	/**
	 * For public Map<Coordinate> getMap
	 * Retrieves the loaded map
	 * 
	 * @return map
	 */
	public Map<Coordinate> getMap() {
		return map;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			okButton.doClick();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
