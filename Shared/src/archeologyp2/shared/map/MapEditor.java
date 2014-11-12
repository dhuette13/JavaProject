package archeologyp2.shared.map;

import archeologyp2.shared.gui.TileComponent;



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
//	public static void changeViewingSymbol(Map<Coordinate> map, String token, char symbol){
//		map.setViewingOption(ViewingOption.userModified);
//		switch(token){
//		case naturalToken:
//			map.setDirtAlias(symbol);
//			break;
//		case stoneToken:
//			map.setStoneAlias(symbol);
//			break;
//		case postHoleToken:
//			map.setPostHoleAlias(symbol);
//			break;
//		case excavatedNaturalToken:
//			map.setDirtSymbol(symbol);
//			break;
//		case excavatedStoneToken:
//			map.setStoneSymbol(symbol);
//			break;
//		case excavatedPostHoleToken:
//			map.setPostHoleSymbol(symbol);
//			break;
//		default:
//			System.out.println("Invalid Token");
//		}
//	}
	
	/**
	 * For public static void updateView
	 * This method updates the passed map's view.
	 * We call it to make sure the user can always see
	 * the current version of their working map. 
	 *  
	 * @param map
	 */
	public static void updateView(final Map<Coordinate> map){
		switch(map.getViewingOption()){
		case natural:
			for(Coordinate coord : map){
				switch(coord.getFeature()){
				case stone:
					if(coord.getExcavated()) map.setMapImage(coord.getRow(), coord.getColumn(), Map.stoneImage);
					else map.setMapImage(coord.getRow(), coord.getColumn(), Map.deadGrassImage);
					break;
				case postHole:
					if(coord.getExcavated()) map.setMapImage(coord.getRow(), coord.getColumn(), Map.pitImage);
					else map.setMapImage(coord.getRow(), coord.getColumn(), Map.chlorophyllImage);
					break;
				case dirt:
					if(coord.getExcavated()) map.setMapImage(coord.getRow(), coord.getColumn(), Map.dirtImage);
					else map.setMapImage(coord.getRow(), coord.getColumn(), Map.naturalImage);
					break;
				}
			}
			break;
//		case userModified:
//			for(Coordinate coord : map){
//				switch(coord.getFeature()){
//				case stone:
//					if(coord.getExcavated()) map.setMapImage(coord.getRow(), coord.getColumn(), map.getStoneSymbol());
//					else map.setMapImage(coord.getRow(), coord.getColumn(), map.getStoneAlias());
//					break;
//				case postHole:
//					if(coord.getExcavated()) map.setMapImage(coord.getRow(), coord.getColumn(), map.getPostHoleSymbol());
//					else map.setMapImage(coord.getRow(), coord.getColumn(), map.getPostHoleAlias());
//					break;
//				case dirt:
//					if(coord.getExcavated()) map.setMapImage(coord.getRow(), coord.getColumn(), map.getDirtSymbol());
//					else map.setMapImage(coord.getRow(), coord.getColumn(), map.getDirtAlias());
//					break;
//				}
//			}
//			break;
		case potCount:
			for(Coordinate coord : map){
				if(coord.getExcavated())
					map.setMapImage(coord.getRow(), coord.getColumn(), setCount(coord.getPotCount()));
				else
					map.setMapImage(coord.getRow(), coord.getColumn(), Map.unknownImage);
			}
			break;
		case metalCount:
			for(Coordinate coord : map){
				if(coord.getExcavated())
					map.setMapImage(coord.getRow(), coord.getColumn(), setCount(coord.getMetalCount()));
				else
					map.setMapImage(coord.getRow(), coord.getColumn(), Map.unknownImage);
			}
			break;
		case charcoalCount:
			for(Coordinate coord : map){
				if(coord.getExcavated())
					map.setMapImage(coord.getRow(), coord.getColumn(), setCount(coord.getCharcoalCount()));
				else
					map.setMapImage(coord.getRow(), coord.getColumn(), Map.unknownImage);
			}
			break;
		case magnetometerResult:
			for(Coordinate coord : map) {
				if(coord.getCharcoalInspected()){
					if(coord.charcoalHidden())
						map.setMapImage(coord.getRow(), coord.getColumn(), Map.trueImage);
					else
						map.setMapImage(coord.getRow(), coord.getColumn(), Map.falseImage);
				}
				else
					map.setMapImage(coord.getRow(), coord.getColumn(), Map.unknownImage);
			}
			break;
		case metalDetectorResult:
			for(Coordinate coord : map) {
				if(coord.getMetalInspected()){
					if(coord.metalHidden())
						map.setMapImage(coord.getRow(), coord.getColumn(), Map.trueImage);
					else
						map.setMapImage(coord.getRow(), coord.getColumn(), Map.falseImage);
				}
				else
					map.setMapImage(coord.getRow(), coord.getColumn(), Map.unknownImage);
			}
			break;
		}
	}

	private static TileComponent setCount(int count) {
		TileComponent image;
		image = Map.zeroImage;
		switch(count){
		case 0:
			image = Map.zeroImage;
			break;
		case 1:
			image = Map.oneImage;
			break;
		case 2:
			image = Map.twoImage;
			break;
		case 3:
			image = Map.threeImage;
			break;
		case 4:
			image = Map.fourImage;
			break;
		case 5:
			image = Map.fiveImage;
			break;
		case 6:
			image = Map.sixImage;
			break;
		case 7:
			image = Map.sevenImage;
			break;
		case 8:
			image = Map.eightImage;
			break;
		case 9:
			image = Map.nineImage;
			break;
		}
		return image;
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
