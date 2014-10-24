package archeologyp2.shared.finds;

/**
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class NonFerrousMetal extends MetalObject {

	private String type;
	private static boolean goldExists;
	private static int goldRow;
	private static String goldColumn;
	
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

	@Override
	public int respondToMetalDetector() {
		return 4;
	}
	
	/**
	 * 
	 * @return goldExists
	 */
	public boolean goldExists(){
		return goldExists;
	}
	
	/**
	 * 
	 * @param exists
	 */
	public void setGoldExists(boolean exists){
		goldExists = exists;
	}
	
	/**
	 * 
	 * @return goldRow
	 */
	public int getGoldRow(){
		return goldRow;
	}
	
	/**
	 * 
	 * @param row
	 */
	public void setGoldRow(int row){
		goldRow = row;
	}
	
	/**
	 * 
	 * @return goldColumn
	 */
	public String getGoldColumn(){
		return goldColumn;
	}
	
	/**
	 * 
	 * @param column
	 */
	public void setGoldColumn(String column){
		goldColumn = column;
	}
	

}
