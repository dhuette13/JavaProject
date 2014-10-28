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

import javax.swing.JButton;
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
public class PathDialog extends JDialog implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;


	/* Graphical Components of the Dialog */
	private JLabel label;
	private JTextField textField;
	private JButton okButton;
	private JButton cancelButton;
	private Relay relay;
	
	private Map<Coordinate> map;
	private String title;

	/**
	 * Constructs the gui of the Path Dialog
	 * 
	 * @param title
	 * @param map
	 */
	public PathDialog(String title, Map<Coordinate> map){
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

	/**
	 * Performs three actions:
	 * 1) Load
	 * 2) Save
	 * 3) Export
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
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
		dispose();
	}

	/**
	 * Sets the dialog's relay for triggering completion event
	 * 
	 * @param relay
	 */
	public void setRelay(Relay relay){
		this.relay = relay;
	}

	/**
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
