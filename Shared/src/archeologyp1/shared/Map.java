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
	}
	
	public void updateView(){
		int r,c;
		Coordinate current;
		switch(viewingOption){
		case natural:
			for(r = 0; r < rows; r++){
				for(c = 0; c < columns; c++){
					current = plane[r][c];
					current.setCurrentViewableSymbol();
				}
			}
			break;
		case readable:
			for(r = 0; r < rows; r++){
				for(c = 0; c < columns; c++){
					current = plane[r][c];
					current.setCurrentViewableSymbol();
				}
			}
			break;
		case potCount:
			for(r = 0; r < rows; r++){
				for(c = 0; c < columns; c++){
					current = plane[r][c];
					current.setCurrentViewableSymbol(Integer.toString(current.potCount.size()).charAt(0));
					/* Update the Current viewable symbol based on feature
					 * symbol and excavation status.
					 */
//					current.setCurrentViewableSymbol();
				}
			}
			break;
		case metalCount:
			for(r = 0; r < rows; r++){
				for(c = 0; c < columns; c++){
					current = plane[r][c];
					current.setCurrentViewableSymbol(Integer.toString(current.metalCount.size()).charAt(0));
					/* Update the Current viewable symbol based on feature
					 * symbol and excavation status.
					 */
//					current.setCurrentViewableSymbol();
				}
			}
			break;
		case charcoalCount:
			for(r = 0; r < rows; r++){
				for(c = 0; c < columns; c++){
					current = plane[r][c];
					current.setCurrentViewableSymbol(Integer.toString(current.charcoalCount.size()).charAt(0));
					/* Update the Current viewable symbol based on feature
					 * symbol and excavation status.
					 */
//					current.setCurrentViewableSymbol();
				}
			}
			break;
		case detectorResult:
			for(r = 0; r < rows; r++){
				for(c = 0; c < columns; c++){
					current = plane[r][c];
					if(current.getInspected()){
						if(current.charcoalHidden() || current.metalHidden())
							current.setCurrentViewableSymbol('T');
						else
							current.setCurrentViewableSymbol('F');
					}
					else
						current.setCurrentViewableSymbol(' ');
					
					/* Update the Current viewable symbol based on feature
					 * symbol and excavation status.
					 */
//					current.setCurrentViewableSymbol();
				}
			}
			break;
		default:
			System.out.println("Invalid ViewingOption in Map Class");
		}
	}

	public ViewingOption getViewingOption(){
		return viewingOption;
	}
}
