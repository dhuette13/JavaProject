package archeologyp2.adt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * THE ADT POP UP MENU
 * 
 * @author Daniel
 * @author Celine
 *
 */

/*
ADT POP-UP TOOL
Scan Map Square with Magnetometer
Scan Map Square with Metal Detector
Dig Map Square
 */
public class ADTPopUpMenu extends JPopupMenu implements ActionListener {
	
	private JMenuItem magnetoMeter;
	private JMenuItem metalDetector;
	private JMenuItem dig;
	
	public ADTPopUpMenu(){
		
		// Making pop-up menu items
		magnetoMeter = new JMenuItem("Scan Square with Magnetometer");
		metalDetector = new JMenuItem("Scan Square with Metal Detector");
		dig = new JMenuItem("Dig Square");
		
		// Adds menu items to the pop-up menu
		this.add(magnetoMeter);
		this.add(metalDetector);
		this.add(dig);
		
		// Waits for what the user clicks
		magnetoMeter.addActionListener(this);
		metalDetector.addActionListener(this);
		dig.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
