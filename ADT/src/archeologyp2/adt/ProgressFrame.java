package archeologyp2.adt;

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
	
	public ProgressFrame(String[] names){
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(20, 5, 20, 5);
		setLayout(layout);
		setSize(300, 400);
		setTitle("Digger Status");
		labels = new JLabel[names.length];
		progressBars = new HashMap<>();
		
		for(int i = 0; i < names.length; i++){
			labels[i] = new JLabel(names[i]);
			progressBars.put(names[i], new JProgressBar());
			
			constraints.anchor = GridBagConstraints.WEST;
			constraints.gridx = 0;
			constraints.gridy = i;
			add(labels[i], constraints);
			
			constraints.gridx = 1;
			constraints.gridy = i;
			constraints.anchor = GridBagConstraints.EAST;
			add(progressBars.get(names[i]), constraints);
		}
		
		setResizable(true);
	}
	
	public void setProgress(String name, int value){
		progressBars.get(name).setValue(value);
	}
}
