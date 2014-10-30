package archeologyp2.shared.finds;


/**
 * DECORATEDPOTTERY FOR THE SHARED FINDS
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class DecoratedPottery extends Pottery {
	
	private String description;

	/**
	 * For public DecoratedPottery
	 * @param date
	 */
	public DecoratedPottery(int date) {
		super(date);
	}
	
	/**
	 * For public DecoratedPottery
	 * @param date
	 * @param row
	 * @param column
	 */
	public DecoratedPottery(int date, int row, String column) {
		super(date, row, column);
	}
	
	/**
	 * For public DecoratedPottery 
	 * @param date
	 * @param description
	 */
	public DecoratedPottery(int date, String description) {
		super(date);
		this.description = description;
	}
	
	/**
	 * For public DecoratedPottery 
	 * @param date
	 * @param description
	 * @param row
	 * @param column
	 */
	public DecoratedPottery(int date, String description, int row, String column) {
		super(date, row, column);
		this.description = description;
	}
	
	/**
	 * For public String getDescription
	 * @return description
	 */
	public String getDescription(){
		return description;
	}
}
