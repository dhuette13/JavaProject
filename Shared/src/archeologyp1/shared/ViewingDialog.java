package archeologyp1.shared;

import javax.swing.JComboBox;
import javax.swing.JDialog;

public class ViewingDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JComboBox<String> comboBox;
	
	public ViewingDialog(){
		createComboBox();
	}
	
	private void createComboBox(){
		comboBox = new JComboBox<String>();
		comboBox.addItem("Natural");
		comboBox.addItem("Stone");
		comboBox.addItem("Post Hole");
		comboBox.addItem("Excavated Natural");
		comboBox.addItem("Excavated Stone");
		comboBox.addItem("Excavated Post Hole");
	}

}
