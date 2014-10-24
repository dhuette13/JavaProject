package archeologyp2.mpt;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class AddFeatureDialog extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private SubController subController;
	
	private JComboBox<String> comboBox;
	private JCheckBox checkBox;
	private JLabel rowLabel;
	private JLabel colLabel;
	private JLabel rowPromptLabel;
	private JTextField rowText;
	private JTextField colText;
	private JButton oButton;
	private JButton cButton;
	private String title;
	
	//For Try-Catch: "Exception in thread "AWT-EventQueue-0" java.lang.NumberFormatException"
	public AddFeatureDialog(String title){
		this.setTitle(title);
		this.title = title;
		
		setBounds(50,50,300,200);
		setVisible(true);
		this.setResizable(false);

		Container pane = getContentPane();
		pane.setLayout(new GridBagLayout());
		
		checkBox = new JCheckBox();
		rowPromptLabel = new JLabel("Change an entire row? ");
		rowLabel = new JLabel("Row: ");
		rowText = new JTextField(10);
		colLabel = new JLabel("Column: ");
		colText = new JTextField(10);
		oButton = new JButton("OK");
		cButton = new JButton("Cancel");
		
		String[] choices = {"Dirt", "Stone", "Post Hole"};
		comboBox = new JComboBox<String>(choices);
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		// =========== FIRST COLUMN ========= //
		constraints.anchor = GridBagConstraints.EAST;
		constraints.weightx = 0;
		constraints.weighty = 0;
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		add(rowLabel, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		add(colLabel, constraints);
		
		// ========== SECOND COLUMN ========== //
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridx = 1;
		constraints.gridy = 0;
		add(rowPromptLabel, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		add(rowText, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 3;
		add(colText, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 4;
		add(oButton, constraints);
		oButton.addActionListener(this);
		
		
		// ============= THIRD COLUMN =========== //
		constraints.gridx = 2;
		constraints.gridy = 0;
		add(checkBox, constraints);
		checkBox.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				if(checkBox.isSelected()){
					colText.setEnabled(false);
					colLabel.setEnabled(false);
				} else {
					colText.setEnabled(true);
					colLabel.setEnabled(true);
				}
			}

		});
		
		constraints.gridx = 2;
		constraints.gridy = 2; 
		add(comboBox, constraints);
		
		constraints.gridx = 2;
		constraints.gridy = 4;
		add(cButton, constraints);
		cButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
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
			subController.updateMap();
		}
		catch(java.lang.NumberFormatException n) {
			System.out.println("Your input was wrong. Please try again."); //placeholder for exception
		}
		dispose();
		
	}
}
