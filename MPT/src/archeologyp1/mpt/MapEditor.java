package archeologyp1.mpt;

import archeologyp1.shared.Charcoal;
import archeologyp1.shared.Coordinate;
import archeologyp1.shared.Feature;
import archeologyp1.shared.Map;
import archeologyp1.shared.MetalObject;
import archeologyp1.shared.Pot;
import archeologyp1.shared.Utilities;

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

	private Map<Coordinate> map;
	private Coordinate current;

	/**
	 * 
	 * For the public MapEditor method
	 * @param map object
	 * 
	 */
	public MapEditor(Map<Coordinate> map) {
		this.map = map;
	}

	/**
	 * 
	 * For the public void changeFeature method
	 * @param row
	 * @param col
	 * @param feature they'd like to change to
	 * 
	 * This method involves changing the feature to
	 * what the user specifies. After changing the 
	 * feature, it then sets the alias (or "natural") features so
	 * the user can access view them again, before updating the
	 * map view.
	 * 
	 */
	public void changeFeature(int row, String col, int feature){
		int r = row - 1;
		int c = Utilities.columnToIndex(col);
		Feature f = Feature.dirt;
		current = map.get(r, c);
		switch(feature){
		case 1:
			f = Feature.dirt;
			current.setFeature(f);
			break;
		case 2:
			f = Feature.stone;
			current.setFeature(f);
			break;
		case 3:
			f = Feature.postHole;
			current.setFeature(f);
			break;
		default:
			System.out.println("Invalid option");
			return;
		}

		updateView();
	}

	/**
	 * 
	 * For the public void addFind method
	 * @param row
	 * @param col
	 * @param type of find
	 * @param date(s) for the find
	 * 
	 * This method adds finds based on the user preference,
	 * and adds the dates of those finds. 
	 * 
	 */
	public void addFind(int row, String col, int type, int date){
		int r = row - 1;
		int c = Utilities.columnToIndex(col); 
		current = map.get(r, c);
		switch(type){
		/* Add to pot collection */
		case 1:
			current.potCount.add(new Pot(date));
			break;
			/* Add to charcoal collection */
		case 2:
			current.charcoalCount.add(new Charcoal(date));
			break;
			/* Add to metal collection */
		case 3:
			current.metalCount.add(new MetalObject(date));
			break;
		}
		updateView();
	}

	public void updateView(){
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
					map.setMapSymbol(coord.getRow(), coord.getColumn(), Integer.toString(current.potCount.size()).charAt(0));
				else
					map.setMapSymbol(coord.getRow(), coord.getColumn(), ' ');
			}
			break;
		case metalCount:
			for(Coordinate coord : map){
				if(coord.getExcavated())
					map.setMapSymbol(coord.getRow(), coord.getColumn(), Integer.toString(current.metalCount.size()).charAt(0));
				else
					map.setMapSymbol(coord.getRow(), coord.getColumn(), ' ');
			}
			break;
		case charcoalCount:
			for(Coordinate coord : map){
				if(coord.getExcavated())
					map.setMapSymbol(coord.getRow(), coord.getColumn(), Integer.toString(current.charcoalCount.size()).charAt(0));
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
	public int countNumberOfFinds(){
		int count = 0;
		for(Coordinate coord : map){
			count = count + coord.potCount.size() + coord.metalCount.size() + coord.charcoalCount.size();
		}
		return count;
	}

}
