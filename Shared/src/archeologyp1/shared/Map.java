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
	 * @param rows
	 * @param columns
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
		int r,c;
		Coordinate current;
		for(r = 0; r < rows; r++){
			for(c = 0; c < columns; c++){
				current = plane[r][c];
				if(current.getInspected()){
					
				} 
				else{
					plane[r][c].setCurrentViewableSymbol(' ');
				}
			}
		}
	}
	
	public ViewingOption getViewingOption(){
		return viewingOption;
	}
}
