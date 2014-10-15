package archeologyp1.shared;

/**
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class Hearth extends Charcoal {

	private int length;
	private int width;
	
	/**
	 * 
	 * @param date
	 */
	public Hearth(int date) {
		super(date);
	}
	
	/**
	 * 
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
	 * 
	 * @return length
	 */
	public int getLength(){
		return length;
	}
	
	/**
	 * 
	 * @return width
	 */
	public int getWidth(){
		return width;
	}

}
