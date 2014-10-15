package archeologyp1.shared;

/**
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class Kiln extends Charcoal {

	private int radius;
	
	/**
	 * 
	 * @param date
	 */
	public Kiln(int date) {
		super(date);
	}
	
	/**
	 * 
	 * @param date
	 * @param radius
	 */
	public Kiln(int date, int radius){
		super(date);
		this.radius = radius;
	}
	
	/**
	 * 
	 * @return radius
	 */
	public int getRadius(){
		return radius;
	}

}
