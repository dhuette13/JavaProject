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
	 * @param depth
	 */
	public SubmergedPottery(int date, int depth) {
		super(date);
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
