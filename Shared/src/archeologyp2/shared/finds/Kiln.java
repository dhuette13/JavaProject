package archeologyp2.shared.finds;


/**
 * KILN FOR SHARED FINDS
 * @author Daniel
 * @author Celine
 */
public class Kiln extends Charcoal {

	private int radius;
	
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
	public Kiln(int date, int radius){
		super(date);
		this.radius = radius;
	}
	
	/**
	 * For public int getRadius
	 * This returns the int radius.
	 * @return radius
	 */
	public int getRadius(){
		return radius;
	}

}
