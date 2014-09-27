package archeologyp1.shared;

/**
 * MAP IN THE SHARED RESOURCES
 * @author Daniel
 * @author Celine
 * 
 * This class contains map features that are shared by
 * both the ADT and the MPT. The main method
 * in this class is the viewing options method,
 * which allows the user to switch their view between
 * multiple choices.
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

	/**
	 * 
	 * For the public int getNumRows method
	 * @return the number of rows
	 * 
	 */
	public int getNumRows(){
		return rows;
	}

	/**
	 * 
	 * For the public int getNumColumns method
	 * @return the number of columns
	 * 
	 */
	public int getNumColumns(){
		return columns;
	}

	/**
	 * 
	 * For the public void setViewingOption method
	 * @param option
	 * 
	 */
	public void setViewingOption(ViewingOption option){
		viewingOption = option;
	}

	/**
	 * 
	 * For the public void updateView method
	 * This method toggles with the user's view of the map.
	 * 
	 */
	public void updateView(){
		updateView(null, '\0', false);
	}

	/**
	 * 
	 * For the public void updateView method
	 * @param feature
	 * @param symbol
	 * @param alias
	 * 
	 * This method toggles with the user's view of the map. 
	 * It switches based on whether the user wants to view
	 * the map as the natural version, the modified version
	 * they'd worked on, the counts for the three types, or
	 * the results of the tools if they'd opted to use those
	 * for the ADT.
	 * 
	 */
	public void updateView(Feature feature, char symbol, boolean alias){
		int r,c;
		Coordinate current;
		switch(viewingOption){
		case natural:
			for(r = 0; r < rows; r++){
				for(c = 0; c < columns; c++){
					current = plane[r][c];
					switch(current.getFeature()){
					case stone:
						if(current.getExcavated()) current.setCurrentViewableSymbol(Coordinate.defaultStoneSymbol);
						else current.setCurrentViewableSymbol(Coordinate.defaultStoneAlias);
						break;
					case postHole:
						if(current.getExcavated()) current.setCurrentViewableSymbol(Coordinate.defaultPostHoleSymbol);
						else current.setCurrentViewableSymbol(Coordinate.defaultPostHoleAlias);
						break;
					case dirt:
						if(current.getExcavated()) current.setCurrentViewableSymbol(Coordinate.defaultDirtSymbol);
						else current.setCurrentViewableSymbol(Coordinate.defaultDirtAlias);
						break;
					}
				}
			}
			break;
		case userModified:
			for(r = 0; r < rows; r++){
				for(c = 0; c < columns; c++){
					current = plane[r][c];
					if(current.getFeature() == feature){
						if(alias){
							current.setFeatureAlias(symbol);
						} else {
							current.setFeatureSymbol(symbol);
						}
					}
					current.updateCurrentViewableSymbol();
				}
			}
			break;
		case potCount:
			for(r = 0; r < rows; r++){
				for(c = 0; c < columns; c++){
					current = plane[r][c];
					if(current.getExcavated())
						current.setCurrentViewableSymbol(Integer.toString(current.potCount.size()).charAt(0));
					else
						current.setCurrentViewableSymbol(' ');
				}
			}
			break;
		case metalCount:
			for(r = 0; r < rows; r++){
				for(c = 0; c < columns; c++){
					current = plane[r][c];
					if(current.getExcavated())
						current.setCurrentViewableSymbol(Integer.toString(current.metalCount.size()).charAt(0));
					else
						current.setCurrentViewableSymbol(' ');
				}
			}
			break;
		case charcoalCount:
			for(r = 0; r < rows; r++){
				for(c = 0; c < columns; c++){
					current = plane[r][c];
					if(current.getExcavated())
						current.setCurrentViewableSymbol(Integer.toString(current.charcoalCount.size()).charAt(0));
					else 
						current.setCurrentViewableSymbol(' ');
				}
			}
			break;
		case magnetometerResult:
			for(r = 0; r < rows; r++){
				for(c = 0; c < columns; c++){
					current = plane[r][c];
					if(current.getCharcoalInspected()){
						if(current.charcoalHidden())
							current.setCurrentViewableSymbol('T');
						else
							current.setCurrentViewableSymbol('F');
					}
					else
						current.setCurrentViewableSymbol(' ');
				}
			}
			break;
		case metalDetectorResult:
			for(r = 0; r < rows; r++){
				for(c = 0; c < columns; c++){
					current = plane[r][c];
					if(current.getMetalInspected()){
						if(current.metalHidden())
							current.setCurrentViewableSymbol('T');
						else
							current.setCurrentViewableSymbol('F');
					}
					else
						current.setCurrentViewableSymbol(' ');
				}
			}
			break;
		default:
			System.out.println("Invalid ViewingOption in Map Class");
		}
	}

	/**
	 * 
	 * For the public ViewingOption getViewingOption method
	 * @return the viewing option the user specifies
	 * 
	 */
	public ViewingOption getViewingOption(){
		return viewingOption;
	}
	
	/**
	 * 
	 * For the public int countNumberOfFinds method
	 * @return the number of finds
	 * 
	 * This method goes through the map and counts the amount of
	 * finds, regardless of type of find.
	 * 
	 */
	public int countNumberOfFinds(){
		Coordinate current;
		int count = 0;
		for(int r = 0; r < getNumRows(); r++){
			for(int c = 0; c < getNumColumns(); c++){
				current = plane[r][c];
				count = count + current.potCount.size() + current.metalCount.size() + current.charcoalCount.size();
			}
		}
		return count;
	}
}
