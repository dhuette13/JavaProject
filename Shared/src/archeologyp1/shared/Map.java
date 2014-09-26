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
		updateView(null, '\0', false);
	}

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

	public ViewingOption getViewingOption(){
		return viewingOption;
	}
	
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
