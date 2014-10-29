package archeologyp2.shared.finds;


/**
 * KILN FOR SHARED FINDS
 * @author Daniel
 * @author Celine
 */
public class Kiln extends Charcoal {

	private double radius;
	
	/**
	 * For public Kiln
	 * @param date
	 */
	public Kiln(int date) {
		super(date);
	}
	
	/**
	 * For public Kiln
	 * @param date
	 * @param radius
	 */
	public Kiln(int date, double radius){
		super(date);
		this.radius = radius;
	}
	
	/**
	 * For public int getRadius
	 * This returns the int radius.
	 * @return radius
	 */
	public double getRadius(){
		return radius;
	}

}
