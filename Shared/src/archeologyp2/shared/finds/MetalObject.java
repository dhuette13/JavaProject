package archeologyp2.shared.finds;


/**
 * METALOBJECT FOR THE SHARED RESOURCES
 * This class handles the metal object finds.
 * 
 * @author Daniel
 * @author Celine
 */
public abstract class MetalObject extends Artifact {
	int date;
	
	/**
	 * For the public MetalObject method
	 * @param date
	 */
	public MetalObject(int date){
		super(date);
	}
	
	/**
	 * For the public MetalObject method
	 * @param date
	 * @param row
	 * @param column
	 */
	public MetalObject(int date, int row, String column) {
		super(date, row, column);
	}

	/**
	 * For public abstract int respondToMetalDetector
	 * Specifies what kinds of metal are in a coordinate
	 * @return int 
	 */
	public abstract int respondToMetalDetector();
}
