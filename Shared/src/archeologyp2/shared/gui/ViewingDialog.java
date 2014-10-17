package archeologyp2.shared.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;

import archeologyp2.shared.map.Coordinate;
import archeologyp2.shared.map.Map;
import archeologyp2.shared.map.MapEditor;

public class ViewingDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JComboBox<String> comboBox;
	private JTextField charField;
	private JButton confirmButton;
	private char symbol;
	private String selection;
	private Map<Coordinate> map;
	
	public ViewingDialog(String title, Map<Coordinate> map){
		this.map = map;
		createComboBox();
		createTextField();
		createButton();
		this.setTitle(title);
		this.setLayout(new FlowLayout());
		this.add(comboBox);
		this.add(charField);
		this.add(confirmButton);
		this.setSize(280, 100);
	}

	private void createButton() {
		confirmButton = new JButton("OK");
		confirmButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				symbol = charField.getText().charAt(0);
				selection = comboBox.getSelectedItem().toString();
				MapEditor.changeViewingSymbol(map, selection, symbol);
				MapEditor.updateView(map);
				dispose();
			}
		});
	}

	private void createTextField() {
		charField = new JTextField("Enter a character");
	}

	private void createComboBox() {
		comboBox = new JComboBox<String>();
		comboBox.addItem(MapEditor.naturalToken);
		comboBox.addItem(MapEditor.stoneToken);
		comboBox.addItem(MapEditor.postHoleToken);
		comboBox.addItem(MapEditor.excavatedNaturalToken);
		comboBox.addItem(MapEditor.excavatedStoneToken);
		comboBox.addItem(MapEditor.excavatedPostHoleToken);
	}
}
