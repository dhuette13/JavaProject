package archeologyp2.adt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import archeologyp2.shared.gui.PopupMenuParent;

/**
 * ADT POP-UP MENU
 * Scan Map Square with Magnetometer
 * Scan Map Square with Metal Detector
 * Dig Map Square
 * 
 * Represents a popup menu that the user can access with a right click.
 * Allows the user to dig, and scan the clicked plot.
 * 
 * @author Daniel
 * @author Celine
 * 
 */
public class ADTPopUpMenu extends PopupMenuParent implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private SubController subController;
	
	/* Menu item options */
	private JMenuItem magnetoMeter;
	private JMenuItem metalDetector;
	private JMenuItem dig;

	/* Three collections are used to keep track of digging threads */
	private String[] diggerNames = {"Schilemann", "Carter", "Bingham", "Thompson", "Robinson"};
	private ArrayList<String> availableDiggers = new ArrayList<String>();
	private HashMap<String, Future<String>> diggers;
	ExecutorService executorService = Executors.newFixedThreadPool(5);

	/**
	 * Initializes variables, instantiates menu item components,
	 * adds components to popup menu, and assigns actionlisteners.
	 * 
	 * @param subController
	 */
	public ADTPopUpMenu(SubController subController){
		this.subController = subController;

		// Making pop-up menu items
		magnetoMeter = new JMenuItem("Magnetometer");
		metalDetector = new JMenuItem("Metal Detector");
		dig = new JMenuItem("Dig");

		// Adds menu items to the pop-up menu
		this.add(dig);
		this.add(magnetoMeter);
		this.add(metalDetector);

		// Waits for what the user clicks
		magnetoMeter.addActionListener(this);
		metalDetector.addActionListener(this);
		dig.addActionListener(this);

		diggers = new HashMap<>();
		for(String name : diggerNames){
			diggers.put(name, null);
			availableDiggers.add(name);
		}
	}

	/**
	 * Determines menu item selected, and calls respective subController
	 * subroutine. In the event of a dig action, the program must ensure
	 * that a digger thread is currently available. If it is, then it will
	 * submit a new thread, otherwise it will display an error message to 
	 * the user.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		setVisible(false);
		switch(e.getActionCommand()){
		case "Magnetometer":
			subController.magnetoMeter(row, column);
			break;
		case "Metal Detector":
			subController.metalDetector(row, column);
			break;
		case "Dig":
			/* Check for complete diggers */
			for(String name : diggerNames){
				Future<String> currentDigger = diggers.get(name);
				if(currentDigger == null) continue;
				if(currentDigger.isDone()){
					try {
						String finishedDigger = currentDigger.get();
						availableDiggers.add(finishedDigger);
						diggers.remove(name);
						diggers.put(name, null);
					} catch (InterruptedException | ExecutionException e1) {
						e1.printStackTrace();
					}
				}
			}
			
			/* If all diggers are busy, print message to user */
			if(availableDiggers.size() == 0){
				JOptionPane.showMessageDialog(null, "All archaeologists are busy right now!");
			} 
			/* Otherwise, get a new digger and allocate him to spot */
			else {
				String newDigger = availableDiggers.get(0);
				diggers.put(newDigger, executorService.submit(new DigThread(newDigger, subController, row, column)));
				availableDiggers.remove(0);
			}

			break;
		}
	}
}
