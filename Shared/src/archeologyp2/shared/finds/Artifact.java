package archeologyp2.shared.finds;

/**
 * Parent class for all finds in a map.
 * Is comparable based on dates.
 * 
 * @author dan
 *
 */
public abstract class Artifact implements Comparable <Artifact>{
	
	private int date;
	
	public Artifact(int date){
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
	
	@Override
	/**
	 * All subclasses of Artifact are comparable based on date
	 * 
	 * @param a 
	 */
	public int compareTo(Artifact a) {
		if(this.date < a.date){
			return -1;
		}else if(this.date == a.date){
			return 0;
		}
		// TODO Auto-generated method stub
		return 1;
	}
}
