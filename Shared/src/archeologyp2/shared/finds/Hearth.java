package archeologyp2.shared.finds;


/**
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class Hearth extends Charcoal {

	private double length;
	private double width;
	
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
	public Hearth(int date, double length, double width) {
		super(date);
		this.length = length;
		this.width = width;
	}
	
	/**
	 * 
	 * @return length
	 */
	public double getLength(){
		return length;
	}
	
	/**
	 * 
	 * @return width
	 */
	public double getWidth(){
		return width;
	}

}
