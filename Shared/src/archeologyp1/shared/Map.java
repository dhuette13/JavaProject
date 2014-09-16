package archeologyp1.shared;

/**
 * 
 * @author Daniel
 *
 */
public class Map {
	
	public Coordinate[][] plane;
	private int rows, columns;
	private ViewingOption viewingOption;
	
	/**
	 * 
	 * @param width
	 * @param height
	 */
	public Map(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
		viewingOption = ViewingOption.natural;
		plane = new Coordinate[rows][columns];
	}
	
	public int getNumRows(){
		return rows;
	}
	
	public int getNumColumns(){
		return columns;
	}
	
	public void setViewingOption(ViewingOption option){
		viewingOption = option;
	}
	
	public ViewingOption getViewingOption(){
		return viewingOption;
	}
}
