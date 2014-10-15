package archeologyp1.shared;

/**
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class DecoratedPottery extends Pottery {
	
	private String description;

	/**
	 * 
	 * @param date
	 */
	public DecoratedPottery(int date) {
		super(date);
	}
	
	/**
	 * 
	 * @param date
	 * @param description
	 */
	public DecoratedPottery(int date, String description) {
		super(date);
		this.description = description;
	}
	
	/**
	 * 
	 * @return description
	 */
	public String getDescription(){
		return description;
	}
}
