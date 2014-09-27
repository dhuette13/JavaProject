package archeologyp1.shared;

/**
 * 
 * POT FOR THE SHARED RESOURCES
 * @author Daniel
 * @author Celine
 * 
 * This class handles the pot find.
 *
 */
public class Pot {
	int date;
	
	/**
	 * 
	 * For the public Pot method
	 * @param date
	 * 
	 */
	public Pot(int date){
		this.date = date;
	}
	
	/**
	 * 
	 * For the public int getDate method
	 * @return the date for the pot finds
	 * 
	 */
	public int getDate(){
		return date;
	}
}
