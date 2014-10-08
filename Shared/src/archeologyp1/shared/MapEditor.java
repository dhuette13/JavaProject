package archeologyp1.shared;


/**
 * MAPEDITOR FOR THE MAP POPULATION TOOL
 * @author Daniel
 * @author Celine
 * 
 * This class handles editing the map for the Map Population 
 * Tool. It includes changing features and adding finds. 
 *
 */

public class MapEditor {

	/**
	 * Updates the passed map's view
	 *  
	 * @param map
	 */
	public static void updateView(Map<Coordinate> map){
		switch(map.getViewingOption()){
		case natural:
			for(Coordinate coord : map){
				switch(coord.getFeature()){
				case stone:
					if(coord.getExcavated()) map.setMapSymbol(coord.getRow(), coord.getColumn(), Map.defaultStoneSymbol);
					else map.setMapSymbol(coord.getRow(), coord.getColumn(), Map.defaultStoneAlias);
					break;
				case postHole:
					if(coord.getExcavated()) map.setMapSymbol(coord.getRow(), coord.getColumn(), Map.defaultPostHoleSymbol);
					else map.setMapSymbol(coord.getRow(), coord.getColumn(), Map.defaultPostHoleAlias);
					break;
				case dirt:
					if(coord.getExcavated()) map.setMapSymbol(coord.getRow(), coord.getColumn(), Map.defaultDirtSymbol);
					else map.setMapSymbol(coord.getRow(), coord.getColumn(), Map.defaultDirtAlias);
					break;
				}
			}
			break;
		case userModified:
			for(Coordinate coord : map){
				switch(coord.getFeature()){
				case stone:
					if(coord.getExcavated()) map.setMapSymbol(coord.getRow(), coord.getColumn(), map.getStoneSymbol());
					else map.setMapSymbol(coord.getRow(), coord.getColumn(), map.getStoneAlias());
					break;
				case postHole:
					if(coord.getExcavated()) map.setMapSymbol(coord.getRow(), coord.getColumn(), map.getPostHoleSymbol());
					else map.setMapSymbol(coord.getRow(), coord.getColumn(), map.getPostHoleAlias());
					break;
				case dirt:
					if(coord.getExcavated()) map.setMapSymbol(coord.getRow(), coord.getColumn(), map.getDirtSymbol());
					else map.setMapSymbol(coord.getRow(), coord.getColumn(), map.getDirtAlias());
					break;
				}
			}
			break;
		case potCount:
			for(Coordinate coord : map){
				if(coord.getExcavated())
					map.setMapSymbol(coord.getRow(), coord.getColumn(), Integer.toString(coord.getPotCount()).charAt(0));
				else
					map.setMapSymbol(coord.getRow(), coord.getColumn(), ' ');
			}
			break;
		case metalCount:
			for(Coordinate coord : map){
				if(coord.getExcavated())
					map.setMapSymbol(coord.getRow(), coord.getColumn(), Integer.toString(coord.getMetalCount()).charAt(0));
				else
					map.setMapSymbol(coord.getRow(), coord.getColumn(), ' ');
			}
			break;
		case charcoalCount:
			for(Coordinate coord : map){
				if(coord.getExcavated())
					map.setMapSymbol(coord.getRow(), coord.getColumn(), Integer.toString(coord.getCharcoalCount()).charAt(0));
				else
					map.setMapSymbol(coord.getRow(), coord.getColumn(), ' ');
			}
			break;
		case magnetometerResult:
			for(Coordinate coord : map) {
				if(coord.getCharcoalInspected()){
					if(coord.charcoalHidden())
						map.setMapSymbol(coord.getRow(), coord.getColumn(), 'T');
					else
						map.setMapSymbol(coord.getRow(), coord.getColumn(), 'F');
				}
				else
					map.setMapSymbol(coord.getRow(), coord.getColumn(), ' ');
			}
			break;
		case metalDetectorResult:
			for(Coordinate coord : map) {
				if(coord.getMetalInspected()){
					if(coord.metalHidden())
						map.setMapSymbol(coord.getRow(), coord.getColumn(), 'T');
					else
						map.setMapSymbol(coord.getRow(), coord.getColumn(), 'F');
				}
				else
					map.setMapSymbol(coord.getRow(), coord.getColumn(), ' ');
			}
			break;
		}
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
	public static int countNumberOfFinds(Map<Coordinate> map){
		int count = 0;
		for(Coordinate coord : map){
			count = count + coord.getPotCount() + coord.getMetalCount() + coord.getCharcoalCount();
		}
		return count;
	}

}
