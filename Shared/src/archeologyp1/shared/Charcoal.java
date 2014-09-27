package archeologyp1.shared;

/**
 * 
 * CHARCOAL FOR THE SHARED RESOURCES
 * @author Daniel
 * @author Celine
 * 
 * This class handles the Charcoal find.
 *
 */
public class Charcoal {
	int date;
	
	/**
	 * 
	 * For the public Charcoal method
	 * @param date
	 * 
	 */
	public Charcoal(int date){
		this.date = date;
	}
	
	/**
	 * 
	 * For the public int getDate method
	 * @return the date of charcoal find
	 * 
	 */
	public int getDate(){
		return date;
	}
}
