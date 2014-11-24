package archeologyp2.adt;

/**
 * PROGRESS FRAME FOR THE ADT
 * 
 * @author Daniel
 * @author Celine
 */

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class ProgressFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JLabel[] labels;
	private HashMap<String, JProgressBar> progressBars;
	
	/**
	 * For public ProgressFrame
	 * @param names
	 */
	public ProgressFrame(String[] names){
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(20, 5, 20, 5);
		setLayout(layout);
		setSize(300, 400);
		setTitle("Digger Status");
		labels = new JLabel[names.length];
		progressBars = new HashMap<>();
		
		JProgressBar temp;
		for(int i = 0; i < names.length; i++){
			labels[i] = new JLabel(names[i]);
			temp = new JProgressBar();
			temp.setStringPainted(true);
			temp.setForeground(Color.ORANGE);
			
			progressBars.put(names[i], temp);
			
			constraints.anchor = GridBagConstraints.WEST;
			constraints.gridx = 0;
			constraints.gridy = i;
			add(labels[i], constraints);
			
			constraints.gridx = 1;
			constraints.gridy = i;
			constraints.anchor = GridBagConstraints.EAST;
			add(progressBars.get(names[i]), constraints);
		}
		
		setResizable(false);
	}
	
	/**
	 * For public void setProgress
	 * @param name
	 * @param value
	 */
	public void setProgress(String name, int value){
		progressBars.get(name).setValue(value);
	}
}
