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
	 * @param row
	 * @param column
	 */
	public Kiln(int date, int row, String column) {
		super(date, row, column);
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
	 * For public Kiln
	 * @param date
	 * @param radius
	 * @param row
	 * @param column
	 */
	public Kiln(int date, double radius, int row, String column){
		super(date, row, column);
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
