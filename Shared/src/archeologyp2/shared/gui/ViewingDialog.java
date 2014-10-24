package archeologyp2.shared.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import archeologyp2.shared.map.Coordinate;
import archeologyp2.shared.map.Map;
import archeologyp2.shared.map.MapEditor;

public class ViewingDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JComboBox<String> comboBox;
	private JLabel charLabel;
	private JTextField charField;
	private JButton confirmButton;
	private JButton cancelButton;
	
	private char symbol;
	private String selection;
	
	private Map<Coordinate> map;
	private Relay relay;
	
	private GridBagConstraints constraints;
	
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
	
	public void addComponent(JComponent component, int column, int row, int width, int height){
		constraints.gridx = column;
		constraints.gridy = row;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		this.add(component, constraints);
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
				relay.fireMyEvent(new CompletionEvent(this));
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

	public void setRelay(Relay relay) {
		this.relay = relay;
	}
}
