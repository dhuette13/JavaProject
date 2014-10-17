package archeologyp2.shared.gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import archeologyp2.shared.map.Coordinate;
import archeologyp2.shared.map.Map;
import archeologyp2.shared.map.Utilities;

public class PathDialog extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
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
		
		Container pane = getContentPane();
		pane.setLayout(new FlowLayout());
		
		label = new JLabel("Enter file to " + title.toLowerCase());
		textField = new JTextField(25);
		button = new JButton("OK");
		button.addActionListener(this);
		
		pane.add(label);
		pane.add(textField);
		pane.add(button);
		
		paintComponents(getGraphics());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if((e.getSource() == button) && (e.getSource() == textField)){
			switch(title){
			case "Load":
				map = Utilities.load(textField.getText());
				break;
			case "Save":
				Utilities.save(map, textField.getText());
				break;
			case "Export":
				try {
					Utilities.printMap(map, new PrintStream(new File(textField.getText())));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				break;
			}
		}
		dispose();
	}

	public Map<Coordinate> go() {
		
		return map;
	}
}
