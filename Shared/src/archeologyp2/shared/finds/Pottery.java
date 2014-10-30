package archeologyp2.shared.finds;


/**
 * POT FOR THE SHARED FINDS
 * This class handles the pot find.
 * @author Daniel
 * @author Celine
 */
public abstract class Pottery extends Artifact {
	int date;
	
	/**
	 * For public Pottery
	 * @param date
	 */
	public Pottery(int date){
		super(date);
	}

	/**
	 * For public Pottery
	 * @param date
	 * @param row
	 * @param column
	 */
	public Pottery(int date, int row, String column) {
		super(date, row, column);
	}
}
