package archeologyp2.shared.finds;


/**
 * SUBMERGED POTTERY FOR SHARED FINDS
 * @author Daniel
 * @author Celine
 */
public class SubmergedPottery extends Pottery {

	private int depth;
	
	/**
	 * For public SubmergedPottery
	 * @param date
	 */
	public SubmergedPottery(int date) {
		super(date);
	}

	/**
	 * For public SubmergedPottery
	 * @param date
	 * @param row
	 * @param column
	 */
	public SubmergedPottery(int date, int row, String column) {
		super(date, row, column);
	}
	
	/**
	 * For public SubmergedPottery
	 * @param date
	 * @param depth
	 */
	public SubmergedPottery(int date, int depth) {
		super(date);
		this.depth = depth;
	}
	
	/**
	 * For public SubmergedPottery
	 * @param date
	 * @param depth
	 * @param row
	 * @param column
	 */
	public SubmergedPottery(int date, int depth, int row, String column) {
		super(date, row, column);
		this.depth = depth;
	}

	/**
	 * For public int getDepth
	 * @return depth
	 */
	public int getDepth(){
		return depth;
	}
}
