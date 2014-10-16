package archeologyp2.shared.finds;


/**
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class SubmergedPottery extends Pottery {

	private int depth;
	
	/**
	 * 
	 * @param date
	 */
	public SubmergedPottery(int date) {
		super(date);
	}
	
	/**
	 * 
	 * @param date
	 * @param depth
	 */
	public SubmergedPottery(int date, int depth) {
		super(date);
		this.depth = depth;
	}

	/**
	 * 
	 * @return depth
	 */
	public int getDepth(){
		return depth;
	}
}
