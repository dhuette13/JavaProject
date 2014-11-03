/**
 * 
 */
package archeologyp2.shared.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import archeologyp2.shared.map.Utilities;

/**
 * @author Celine
 *
 */
public class BaseConverterDialog extends JDialog implements ActionListener, KeyListener, FocusListener {

	private static final long serialVersionUID = 1L;

	private JLabel indexToCol;
	private JLabel colToIndex;

	private JTextField indexArea;
	private JTextField colArea;

	private JButton confirmButton;
	private JButton cancelButton;


	public BaseConverterDialog(){
		setVisible(true);
		setTitle("Base 26 Tool");
		setSize(250, 120);
		setResizable(false);

		Container pane = getContentPane();
		pane.setLayout(new GridBagLayout());

		indexToCol = new JLabel("Index to Column");
		colToIndex = new JLabel("Column to Index");

		indexArea = new JTextField(10);
		colArea = new JTextField(10);
		
		indexArea.addKeyListener(this);
		indexArea.addFocusListener(this);
		colArea.addKeyListener(this);
		colArea.addFocusListener(this);

		confirmButton = new JButton("OK");
		cancelButton = new JButton("Exit");

		confirmButton.addKeyListener(this);
		cancelButton.addKeyListener(new KeyAdapter(){
			@Override
			public void keyTyped(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					dispose();
				}
			}
		});
		
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.gridy = 0;
		add(indexToCol, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		add(indexArea, constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;
		add(confirmButton, constraints);
		confirmButton.addActionListener(this);

		constraints.gridx = 1;
		constraints.gridy = 0;
		add(colToIndex, constraints);

		constraints.gridx = 1;
		constraints.gridy = 1;
		add(colArea, constraints);

		constraints.gridx = 1;
		constraints.gridy = 4;
		add(cancelButton, constraints);
		cancelButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		int index;
		String col;

		if(indexArea.getText().length() > 0) {
			index = Integer.parseInt(indexArea.getText());
			String column = Utilities.indexToColumn(index);
			colArea.setText(column);
		}
		if(colArea.getText().length() > 0){
			col = colArea.getText();
			int i = Utilities.columnToIndex(col);
			indexArea.setText(new Integer(i).toString());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			confirmButton.doClick();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		indexArea.setText("");
		colArea.setText("");
	}

	@Override
	public void focusLost(FocusEvent arg0) {
	}
}

