package archeologyp2.mpt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

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
public class MPTPopUpMenu extends JPopupMenu implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private JMenuItem setFeature;
	private JMenuItem toggleExcavated;
	private JMenuItem toggleHeritage;
	private JMenuItem addMetal;
	private JMenuItem addPottery;
	private JMenuItem addCharcoal;
	
	public MPTPopUpMenu(){
		
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
	public void actionPerformed(ActionEvent arg0) {
		
	}	
}
