package archeologyp2.adt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JMenuItem;

import archeologyp2.shared.gui.PopupMenuParent;

/**
 * ADT POP-UP MENU
 * Scan Map Square with Magnetometer
 * Scan Map Square with Metal Detector
 * Dig Map Square
 * 
 * @author Daniel
 * @author Celine
 * 
 */
public class ADTPopUpMenu extends PopupMenuParent implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private JMenuItem magnetoMeter;
	private JMenuItem metalDetector;
	private JMenuItem dig;
	
	private SubController subController;
	
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
	}
	public void setRowAndColumn(int row, int column){
		this.row = row;
		this.column = column;
	}

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
			ExecutorService executorService = Executors.newFixedThreadPool(5);
			executorService.execute(new DigThread(subController, row, column));
			break;
		}
	}
}
