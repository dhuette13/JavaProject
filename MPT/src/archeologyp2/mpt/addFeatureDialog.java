package archeologyp2.mpt;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import archeologyp2.shared.map.Coordinate;
import archeologyp2.shared.map.Map;

/**
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class AddFeatureDialog extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private SubController subController;
	private Map<Coordinate> map;
	
	private JComboBox<String> comboBox;
	private JLabel rowLabel;
	private JLabel colLabel;
	private JTextField rowText;
	private JTextField colText;
	private JButton button;
	private String title;
	
	//For Try-Catch: "Exception in thread "AWT-EventQueue-0" java.lang.NumberFormatException"
	public AddFeatureDialog(String title, Map<Coordinate> map){
		this.setTitle(title);
		this.map = map;
		this.title = title;
		
		setBounds(50,50,200,120);
		setVisible(true);
		this.setResizable(false);

		Container pane = getContentPane();
		pane.setLayout(new GridLayout(3,3));

		rowLabel = new JLabel("Row: ");
		rowText = new JTextField(5);
		colLabel = new JLabel("Column: ");
		colText = new JTextField(5);
		button = new JButton("OK");
		button.addActionListener(this);

		String[] choices = {"Dirt", "Stone", "Post Hole"};
		comboBox = new JComboBox<String>(choices);

		pane.add(rowLabel);
		pane.add(rowText);
		pane.add(colLabel);
		pane.add(colText);
		pane.add(comboBox);
		pane.add(button);
		
		paintComponents(getGraphics());
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try{
			int feature;
			feature=comboBox.getSelectedIndex();
			feature++;
			int row = Integer.parseInt(rowText.getText());
			String col = colText.getText();
			subController.changeFeature(row, col, feature, false);
		}
		catch(java.lang.NumberFormatException n) {
			System.out.println("Your input was wrong. Please try again."); //placeholder for exception
		}
		dispose();
		
	}
}
