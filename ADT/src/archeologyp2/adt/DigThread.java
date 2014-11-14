package archeologyp2.adt;

import java.util.concurrent.Callable;

import javax.swing.JOptionPane;

/**
 * Represents an independent thread of execution for digging
 * a plot on the map. Each task is intended to take 10 seconds
 * to complete. Each thread is given a name, SubController to 
 * access the dig subroutine, and a row and column to dig. When complete
 * the thread will display a report of all the items found in the digged
 * plot, and will display the thread's name.
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class DigThread implements Callable<String> {

	private SubController subController;
	private int row, column;
	private String name;
	private double averageDate, standardDeviation;
	
	/**
	 * Initializes variables
	 * 
	 * @param name
	 * @param subController
	 * @param row
	 * @param column
	 */
	public DigThread(String name, SubController subController, int row, int column){
		this.name = name;
		this.subController = subController;
		this.row = row;
		this.column = column;
	}
	
	/**
	 *  This method calls the dig subroutine, sleeps for 10 seconds,
	 *  updates the map, and displays a report for found artifacts.
	 */
	@Override
	public String call() throws Exception {
		try {
			Report foundReport = subController.dig(row, column);
			Thread.sleep(10000);
			subController.updateMap();
			
			averageDate = subController.computeAverageDate(foundReport.getFoundItems());
			standardDeviation = subController.computeStandardDeviation(averageDate, foundReport.getFoundItems());
			
			ReportDialog reportMessage = new ReportDialog(name, foundReport, averageDate, standardDeviation);
			reportMessage.setVisible(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (HeritageException e) {
			JOptionPane.showMessageDialog(null, "This spot is heritage, you can not dig here!", "Error", JOptionPane.ERROR_MESSAGE);
			return name;
		}
		
		return name;
	}
}
