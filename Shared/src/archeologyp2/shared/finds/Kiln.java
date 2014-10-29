package archeologyp2.shared.finds;


/**
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class Kiln extends Charcoal {

	private double radius;
	
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
	public Kiln(int date, double radius){
		super(date);
		this.radius = radius;
	}
	
	/**
	 * 
	 * @return radius
	 */
	public double getRadius(){
		return radius;
	}

}
