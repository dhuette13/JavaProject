/**
 * 
 */
package archeologyp2.mpt;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import archeologyp2.shared.map.Utilities;

/**
 * @author Celine
 *
 */
public class Extra extends JDialog implements ActionListener, KeyListener {

	private JLabel indexToCol;
	private JLabel colToIndex;
	private JLabel row1;
	private JLabel row2;
	
	private JTextField indexArea;
	private JTextField colArea;
	private JTextField rowArea1;
	private JTextField rowArea2;
	
	private JButton oButton;
	private JButton cButton;
	
	private JFrame frame;
	
	public Extra(){
		setVisible(true);
		setTitle("Base 26 Tool");
		setSize(250, 250);
		setResizable(false);
		
		Container pane = getContentPane();
		pane.setLayout(new GridBagLayout());
		
		indexToCol = new JLabel("Index to Column");
		colToIndex = new JLabel("Column to Index");
		row1 = new JLabel("Row");
		row2 = new JLabel("Row");
		
		indexArea = new JTextField(10);
		colArea = new JTextField(10);
		rowArea1 = new JTextField(10);
		rowArea2 = new JTextField(10);
		
		oButton = new JButton("OK");
		cButton = new JButton("Exit");
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		add(indexToCol, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		add(indexArea, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		add(row1, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		add(rowArea1, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		add(oButton, constraints);
		oButton.addActionListener(this);
		
		constraints.gridx = 1;
		constraints.gridy = 0;
		add(colToIndex, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 1;
		add(colArea, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 2; 
		add(row2, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 3;
		add(rowArea2, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 4;
		add(cButton, constraints);
		cButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try{
			int index;
			String col;
			
			if(((CharSequence)indexArea).length()>0)
			{
				index = Integer.parseInt(indexArea.getText());
				Utilities.indexToColumn(index);
			}
			if(((CharSequence)colArea).length()>0){
				col = colArea.getText();
				Utilities.columnToIndex(col);
			}
			if(((CharSequence) rowArea1).length()>0){
				rowArea2 = rowArea1;
			}
			if(((CharSequence) rowArea2).length()>0){
				rowArea1 = rowArea2;
			}
		}
		finally{
			dispose();
		}
		
	}
	
}

