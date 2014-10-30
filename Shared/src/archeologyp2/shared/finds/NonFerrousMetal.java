package archeologyp2.shared.finds;

/**
 * NONFERROUS FOR SHARED FINDS
 * @author Daniel
 * @author Celine
 */
public class NonFerrousMetal extends MetalObject {

	private String type;
	private static boolean goldExists;
	private static int goldRow;
	private static String goldColumn;
	
	/**
	 * For public NonFerrousMetal
	 * @param date
	 */
	public NonFerrousMetal(int date) {
		super(date);
	}
	
	/**
	 * For public NonFerrousMetal
	 * @param date
	 * @param row
	 * @param column
	 */
	public NonFerrousMetal(int date, int row, String column) {
		super(date, row, column);
	}
	
	/**
	 * For public NonFerrousMetal
	 * @param date
	 * @param type
	 */
	public NonFerrousMetal(int date, String type) {
		super(date);
		this.type = type;
	}
	
	/**
	 * For public NonFerrousMetal
	 * @param date
	 * @param type
	 * @param row
	 * @param column
	 */
	public NonFerrousMetal(int date, String type, int row, String column) {
		super(date, row, column);
		this.type = type;
	}
	
	/**
	 * For public String getType
	 * @return type
	 */
	public String getType(){
		return type;
	}

	@Override
	/**
	 * For public int respondToMetalDetector
	 * @return 4
	 */
	public int respondToMetalDetector() {
		return 4;
	}
	
	/**
	 * For public boolean goldExists
	 * This method returns a true or false based on if 
	 * there's a gold on the map already, as there should
	 * only be one gold on the map at all times.
	 * @return goldExists
	 */
	public boolean goldExists(){
		return goldExists;
	}
	
	/**
	 * For public void setGoldExists
	 * @param exists
	 */
	public void setGoldExists(boolean exists){
		goldExists = exists;
	}
	
	/**
	 * For public int getGoldRow
	 * @return goldRow
	 */
	public int getGoldRow(){
		return goldRow;
	}
	
	/**
	 * For public void setGoldRow
	 * @param row
	 */
	public void setGoldRow(int row){
		goldRow = row;
	}
	
	/**
	 * For public String getGoldColumn
	 * @return goldColumn
	 */
	public String getGoldColumn(){
		return goldColumn;
	}
	
	/**
	 * For public void setGoldColumn
	 * @param column
	 */
	public void setGoldColumn(String column){
		goldColumn = column;
	}
	

}
