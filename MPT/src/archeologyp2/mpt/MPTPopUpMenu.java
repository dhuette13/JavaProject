package archeologyp2.mpt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import archeologyp2.shared.gui.PopupMenuParent;

/**
 * THE MPT POP UP MENU
 * 
 * @author Daniel
 * @author Celine
 *
 */

/*
Set Feature
Toggle Excavated
Toggle Heritage
Add Metal Find
Add Pottery Find
Add Charcoal Find
 */
public class MPTPopUpMenu extends PopupMenuParent implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private JMenuItem setFeature;
	private JMenuItem toggleExcavated;
	private JMenuItem toggleHeritage;
	private JMenuItem addMetal;
	private JMenuItem addPottery;
	private JMenuItem addCharcoal;
	
	private SubController subController;
	
	public MPTPopUpMenu(SubController subController){
		this.subController = subController;
		
		// Making pop-up menu items
		setFeature = new JMenuItem("Set Feature");
		toggleExcavated = new JMenuItem("Toggle Excavated");
		toggleHeritage = new JMenuItem("Toggle Heritage");
		addMetal = new JMenuItem("Add Metal Find");
		addPottery = new JMenuItem("Add Pottery Find");
		addCharcoal = new JMenuItem("Add Charcoal Find");
		
		// Adds menu items to the pop-up menu
		this.add(setFeature);
		this.add(toggleExcavated);
		this.add(toggleHeritage);
		this.add(addMetal);
		this.add(addPottery);
		this.add(addCharcoal);
		
		// Waits for what the user clicks
		setFeature.addActionListener(this);
		toggleExcavated.addActionListener(this);
		toggleHeritage.addActionListener(this);
		addMetal.addActionListener(this);
		addPottery.addActionListener(this);
		addCharcoal.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setVisible(false);
		switch(e.getActionCommand()){
		case "Set Feature":
//			AddFeatureDialog featureDialog = new AddFeatureDialog("Add Feature", null);
			break;
		case "Toggle Excavated":
			break;
		case "Toggle Heritage":
			break;
		case "Add Metal Find":
			break;
		case "Add Pottery Find":
			break;
		case "Add Charcoal Find":
			break;
		}
	}
}
