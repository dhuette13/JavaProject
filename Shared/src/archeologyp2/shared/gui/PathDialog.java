package archeologyp2.shared.gui;

import java.awt.Container;
import java.awt.FlowLayout;
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
	private JButton button;
	private Map<Coordinate> map;
	private String title;

	public PathDialog(String title, Map<Coordinate> map){
		this.setTitle(title);
		this.map = map;
		this.title = title;
		setBounds(200,200,500,100);
		this.setResizable(false);

		Container pane = getContentPane();
		pane.setLayout(new FlowLayout());

		label = new JLabel("Enter file to " + title.toLowerCase());
		textField = new JTextField(25);
		button = new JButton("OK");
		button.addActionListener(this);

		pane.add(label);
		pane.add(textField);
		pane.add(button);

//		paintComponents(getGraphics());
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
