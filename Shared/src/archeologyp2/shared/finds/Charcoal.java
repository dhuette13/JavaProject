package archeologyp2.shared.finds;


/**
 * 
 * CHARCOAL FOR THE SHARED RESOURCES
 * @author Daniel
 * @author Celine
 * 
 * This class handles the Charcoal find.
 *
 */
public abstract class Charcoal extends Artifact {
	int date;
	
	/**
	 * 
	 * For the public Charcoal method
	 * @param date
	 * 
	 */
	public Charcoal(int date){
		super(date);
	}

	/**
	 * For the public Charcoal method
	 * @param date
	 * @param row
	 * @param column
	 */
	public Charcoal(int date, int row, String column) {
		super(date, row, column);
	}
}
