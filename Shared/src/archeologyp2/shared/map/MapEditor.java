package archeologyp2.shared.map;



/**
 * MAPEDITOR FOR THE MAP POPULATION TOOL
 * 
 * This class handles editing the map for the Map Population 
 * Tool. It includes changing features and adding finds. 
 * 
 * @author Daniel
 * @author Celine
 *
 */

public class MapEditor {

	public static final String naturalToken = "Natural";
	public static final String stoneToken = "Stone";
	public static final String postHoleToken = "Post Hole";
	public static final String excavatedNaturalToken = "Excavated Natural";
	public static final String excavatedStoneToken = "Excavated Stone";
	public static final String excavatedPostHoleToken = "Excavated Post Hole";

	/**
	 * For public static void changeViewingSymbol
	 * 
	 * This method handles the logic of changing the viewing
	 * symbol for the user. It takes in a map object, a token, and 
	 * a symbol, and uses the set functions of map to set the
	 * feature the user wanted to change to that character symbol.
	 * 
	 * @param map
	 * @param token
	 * @param symbol
	 */
	public static void changeViewingSymbol(Map<Coordinate> map, String token, char symbol){
		map.setViewingOption(ViewingOption.userModified);
		switch(token){
		case naturalToken:
			map.setDirtAlias(symbol);
			break;
		case stoneToken:
			map.setStoneAlias(symbol);
			break;
		case postHoleToken:
			map.setPostHoleAlias(symbol);
			break;
		case excavatedNaturalToken:
			map.setDirtSymbol(symbol);
			break;
		case excavatedStoneToken:
			map.setStoneSymbol(symbol);
			break;
		case excavatedPostHoleToken:
			map.setPostHoleSymbol(symbol);
			break;
		default:
			System.out.println("Invalid Token");
		}
	}
	
	/**
	 * For public static void updateView
	 * This method updates the passed map's view.
	 * We call it to make sure the user can always see
	 * the current version of their working map. 
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
	 * This method goes through the map and counts the amount of
	 * finds, regardless of type of find.
	 * @return the number of finds
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
