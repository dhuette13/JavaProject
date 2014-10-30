package archeologyp2.shared.finds;

/**
 * ARTIFACT FOR THE SHARED FINDS
 * This class is the parent class for all finds in a map. 
 * It is comparable based on dates.
 * 
 * @author Daniel
 * @author Celine
 *
 */
public abstract class Artifact implements Comparable <Artifact>{
	
	private int date;
	private int row;
	private String column;
	
	/**
	 * For public Artifact 
	 * @param date
	 */
	public Artifact(int date){
		this.date = date;
	}
	
	public Artifact(int date, int row, String column){
		this.date = date;
		this.row = row;
		this.column = column;
	}

	/**
	 * For public int getDate 
	 * @return the date for the artifact
	 */
	public int getDate(){
		return date;
	}
	
	/**
	 * For public int getRow
	 * @return the row of the artifact
	 */
	public int getRow(){
		return row;
	}
	
	/**
	 * For public int getColumn
	 * @return the column for the artifact
	 */
	public String getColumn(){
		return column;
	}
	
	@Override
	/**
	 * For public int compareTo
	 * All subclasses of Artifact are comparable based on date
	 * @param a 
	 * @return -1
	 * @return 0 
	 * @return 1
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
