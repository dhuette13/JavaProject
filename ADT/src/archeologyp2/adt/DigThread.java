package archeologyp2.adt;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

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
public class DigThread extends SwingWorker<String, Integer> {

	private SubController subController;
	private ProgressFrame progressFrame;
	private int row, column;
	private String name;
	private double averageDate, standardDeviation;
	private Report foundReport;
	private ReportDialog reportMessage;
	
	/**
	 * Initializes variables
	 * 
	 * @param name
	 * @param subController
	 * @param row
	 * @param column
	 */
	public DigThread(String name, SubController subController, int row, int column, ProgressFrame progressFrame){
		this.name = name;
		this.subController = subController;
		this.progressFrame = progressFrame;
		this.row = row;
		this.column = column;
		foundReport = null;
	}
	
	/**
	 *  This method calls the dig subroutine, sleeps for 10 seconds
	 *  while publishing progress.
	 */
	@Override
	protected String doInBackground() throws Exception {
		try {
			foundReport = subController.dig(row, column);
			for(int i = 0; i < 100; i++){
				Thread.sleep(100);
				publish(i);
			}
			
			averageDate = subController.computeAverageDate(foundReport.getFoundItems());
			standardDeviation = subController.computeStandardDeviation(averageDate, foundReport.getFoundItems());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (HeritageException e) {
			JOptionPane.showMessageDialog(null, "This spot is heritage, you can not dig here!", "Error", JOptionPane.ERROR_MESSAGE);
			return name;
		}
		
		return name;
	}
	
	/**
	 * Handles update to progress bar, thread safe method
	 */
	@Override
	protected void process(List<Integer> publishedVals){
		for(Integer val : publishedVals){
			progressFrame.setProgress(name, val);
		}
	}
	
	/**
	 * Called on completion of thread, updates the map,
	 * updates the progress bar to zero, and displays a report dialog.
	 */
	@Override
	protected void done() {
		progressFrame.setProgress(name, 0);
		if(foundReport != null){
			subController.updateMap();
			reportMessage = new ReportDialog(name, foundReport, averageDate, standardDeviation);
			reportMessage.setVisible(true);
		}
	}
}
