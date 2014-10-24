package archeologyp2.shared.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import archeologyp2.shared.map.Coordinate;
import archeologyp2.shared.map.Map;
import archeologyp2.shared.map.Utilities;

public class PathDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private Relay relay;

	private JLabel label;
	private JTextField textField;
	private JButton okButton;
	private JButton cancelButton;
	private Map<Coordinate> map;
	private String title;

	public PathDialog(String title, Map<Coordinate> map){
		this.map = map;
		this.title = title;
		
		setTitle(title);
		setSize(380, 100);
		setResizable(false);

		Container pane = getContentPane();
		pane.setLayout(new GridBagLayout());

		label = new JLabel("Enter file to " + title.toLowerCase() + ": ");
		textField = new JTextField(20);
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		
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

	public void setRelay(Relay r){
		relay = r;
	}

	public Map<Coordinate> getMap() {
		return map;
	}
}
