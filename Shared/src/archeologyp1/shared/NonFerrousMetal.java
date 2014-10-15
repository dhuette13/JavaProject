package archeologyp1.shared;

/**
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class NonFerrousMetal extends MetalObject {

	private String type;
	
	/**
	 * 
	 * @param date
	 */
	public NonFerrousMetal(int date) {
		super(date);
	}
	
	/**
	 * 
	 * @param date
	 * @param type
	 */
	public NonFerrousMetal(int date, String type) {
		super(date);
		this.type = type;
	}
	
	/**
	 * 
	 * @return type
	 */
	public String getType(){
		return type;
	}
	

}
