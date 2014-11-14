package archeologyp2.adt;

import javax.swing.JOptionPane;

public class DigThread implements Runnable {

	private SubController subController;
	private int row, column;
	
	public DigThread(SubController subController, int row, int column){
		this.subController = subController;
		this.row = row;
		this.column = column;
	}
	@Override
	public void run() {
		try {
			subController.dig(row, column);
			Thread.sleep(10000);
			subController.updateMap();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (HeritageException e) {
			JOptionPane.showMessageDialog(null, "This spot is heritage, you can not dig here!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
