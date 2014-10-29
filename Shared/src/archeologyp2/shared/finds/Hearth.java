package archeologyp2.shared.finds;


/**
 * HEARTH FOR SHARED FINDS
 * @author Daniel
 * @author Celine
 *
 */
public class Hearth extends Charcoal {

	private double length;
	private double width;
	
	/**
	 * For public Hearth
	 * @param date
	 */
	public Hearth(int date) {
		super(date);
	}
	
	/**
	 * For public Hearth
	 * @param date
	 * @param length
	 * @param width
	 */
	public Hearth(int date, double length, double width) {
		super(date);
		this.length = length;
		this.width = width;
	}
	
	/**
	 * For public int getLength
	 * This returns the int length.
	 * @return length
	 */
	public double getLength(){
		return length;
	}
	
	/**
	 * For public int getWidth
	 * This returns the int width. 
	 * @return width
	 */
	public double getWidth(){
		return width;
	}

}
