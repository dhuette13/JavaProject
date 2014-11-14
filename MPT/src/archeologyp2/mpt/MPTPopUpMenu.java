package archeologyp2.mpt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import archeologyp2.shared.gui.PopupMenuParent;

/**
 * THE MPT POP UP MENU
 * Set Feature
 * Toggle Excavated
 * Toggle Heritage
 * Add Metal Find
 * Add Pottery Find
 * Add Charcoal Find
 * 
 * Represents a popup menu that the user can access with a right click.
 * Allows the user to modify the clicked plot.
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class MPTPopUpMenu extends PopupMenuParent implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private JMenuItem setFeature;
	private JMenuItem addFind;
	private JMenuItem toggleExcavated;
	private JMenuItem toggleHeritage;
	
	private SubController subController;
	
	/**
	 * Initializes variables, instantiates menu item components,
	 * adds components to popup menu, and assigns actionlisteners.
	 * 
	 * @param subController
	 */
	public MPTPopUpMenu(SubController subController){
		this.subController = subController;
		
		// Making pop-up menu items
		setFeature = new JMenuItem("Set Feature");
		addFind = new JMenuItem("Add Find");
		toggleExcavated = new JMenuItem("Toggle Excavated");
		toggleHeritage = new JMenuItem("Toggle Heritage");
		
		// Adds menu items to the pop-up menu
		this.add(setFeature);
		this.add(toggleExcavated);
		this.add(toggleHeritage);
		this.add(addFind);
		
		// Waits for what the user clicks
		setFeature.addActionListener(this);
		toggleExcavated.addActionListener(this);
		toggleHeritage.addActionListener(this);
		addFind.addActionListener(this);
	}

	/**
	 * Determines the menu item selected, and calls the appropriate subController
	 * method or dialog box.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		setVisible(false);
		switch(e.getActionCommand()){
		case "Set Feature":
			AddFeatureDialog featureDialog = new AddFeatureDialog("Add Feature", subController, row, column);
			featureDialog.setVisible(true);
			break;
		case "Toggle Excavated":
			subController.toggleExcavated(row, column);
			break;
		case "Toggle Heritage":
			subController.toggleHeritage(row, column);
			break;
		case "Add Find":
			FindDialog dialog = new FindDialog("Add Finds", subController, row, column);
			dialog.setVisible(true);
			break;
		}
	}
}
