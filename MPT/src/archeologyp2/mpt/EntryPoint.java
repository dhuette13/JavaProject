package archeologyp2.mpt;

import javax.swing.SwingUtilities;

import archeologyp2.shared.gui.FrameOfFun;

/**
 * ENTRY POINT FOR THE MAP POPULATION TOOL
 * @author Daniel
 * @author Celine
 * 
 * This class includes the main method for the MPT, as well
 * as the initial user menu. 
 *
 */

public class EntryPoint {
	
	FrameOfFun frame;
	/**
	 * 
	 * For the public EntryPoint method
	 * This method handles the objects that will be 
	 * used in this class.
	 * 
	 */
	public EntryPoint(){
		frame = new MPTFrameOfFun("MPT");
	}

	/**
	 * For private void go
	 */
	private void go() {
		frame.setVisible(true);
	}
	
	/**
	 * 
	 * For the public static void main method
	 * 
	 * This method starts the entire program. 
	 * 
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				EntryPoint entry = new EntryPoint();
				entry.go();
			}
		});
	}

}
