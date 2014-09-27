package archeologyp1.shared;

/**
 * 
 * METALOBJECT FOR THE SHARED RESOURCES
 * @author Daniel
 * @author Celine
 * 
 * This class handles the metal object finds.
 *
 */
public class MetalObject {
	int date;
	
	/**
	 * 
	 * For the public MetalObject method
	 * @param date
	 * 
	 */
	public MetalObject(int date){
		this.date = date;
	}
	
	/**
	 * 
	 * For the public int getDate method
	 * @return the date of the metal object find
	 * 
	 */
	public int getDate(){
		return date;
	}
}
