package archeologyp2.adt;

import javax.swing.SwingUtilities;

import archeologyp2.shared.gui.FrameOfFun;

/**
 * ENTRY POINT OF THE ARCHAEOLOGICAL DIG TOOL 
 * @author Daniel
 * @author Celine
 * 
 * This class contains the Main method for the ADT. It runs the 
 * user input menu, which asks the user if they'd like to 
 * survey, which tool they'd like to use if they do survey, 
 * if they'd like to excavate, if they'd like to see the date
 * average (with the bonus of standard deviation) of the finds 
 * they have, change the viewing option, print, or export. 
 *
 */

public class EntryPoint {
	private FrameOfFun frame;

	/**
	 * For public EntryPoint
	 * Initializes the Frame
	 */
	public EntryPoint(){
		frame = new ADTFrameOfFun("ADT");
	}

	/**
	 * For public void go
	 * Shows the frame
	 */
	public void go(){
		frame.setVisible(true);
	}

	/**
	 * For public static void main
	 * This method creates an object, and 
	 * begins the class. 
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				EntryPoint entry = new EntryPoint();
				entry.go();
			}
		});
	}
}
