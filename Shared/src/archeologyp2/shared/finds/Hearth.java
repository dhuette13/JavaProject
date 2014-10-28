package archeologyp2.shared.finds;


/**
 * HEARTH FOR SHARED FINDS
 * @author Daniel
 * @author Celine
 *
 */
public class Hearth extends Charcoal {

	private int length;
	private int width;
	
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
	public Hearth(int date, int length, int width) {
		super(date);
		this.length = length;
		this.width = width;
	}
	
	/**
	 * For public int getLength
	 * This returns the int length.
	 * @return length
	 */
	public int getLength(){
		return length;
	}
	
	/**
	 * For public int getWidth
	 * This returns the int width. 
	 * @return width
	 */
	public int getWidth(){
		return width;
	}

}
