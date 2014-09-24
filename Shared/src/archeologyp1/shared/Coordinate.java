package archeologyp1.shared;

import java.util.ArrayList;

/**
 * Represents a single coordinate on the map
 * @author Daniel
 *
 */
public class Coordinate {
	private Feature feature;
	private char currentViewableSymbol;
	private boolean excavated, itemFound;
	private boolean charcoalHidden, metalHidden;
	private boolean charcoalInspected, metalInspected, potInspected;
	/* For Excavated Coordinates */
	private char stoneSymbol, postHoleSymbol, dirtSymbol;
	/* For Not Excavated Coordinates */
	private char stoneAlias, postHoleAlias, dirtAlias;
	public ArrayList<Pot> potCount = new ArrayList<Pot>();
	public ArrayList<MetalObject> metalCount = new ArrayList<MetalObject>();
	public ArrayList<Charcoal> charcoalCount = new ArrayList<Charcoal>();

	/* Default symbols to use */
	public static final char defaultDirtSymbol = 'D';
	public static final char defaultStoneSymbol = 'R';
	public static final char defaultPostHoleSymbol = 'H';
	
	public static final char defaultDirtAlias = 'g';
	public static final char defaultStoneAlias = 'Y';
	public static final char defaultPostHoleAlias = 'G';

	/**
	 * Initialize member variables
	 */
	public Coordinate(){
		feature = Feature.dirt;
		currentViewableSymbol = defaultDirtAlias;
		
		excavated = false;
		itemFound = false;
		charcoalHidden = false; metalHidden = false;
		charcoalInspected = false; metalInspected = false; potInspected = false;
		
		stoneSymbol = defaultStoneSymbol;
		postHoleSymbol = defaultPostHoleSymbol;
		dirtSymbol = defaultDirtSymbol;
		stoneAlias = defaultStoneAlias;
		postHoleAlias = defaultPostHoleAlias;
		dirtAlias = defaultDirtAlias;
	}

	/**
	 * @return currentViewableSymbol
	 * For use by printing method
	 */
	public char getCurrentViewableSymbol() { return currentViewableSymbol; }
	/**
	 * @param symbol sets currentViewableSymbol
	 * currentViewableSymbol used by printing method
	 */
	public void setCurrentViewableSymbol(char symbol) { currentViewableSymbol = symbol; }
	/**
	 * Updates current viewable symbol based on the Coordinate's
	 * current feature type.
	 */
	public void updateCurrentViewableSymbol(){
		switch(feature){
		case stone:
			if(excavated) currentViewableSymbol = stoneSymbol;
			else currentViewableSymbol = stoneAlias;
			break;
		case postHole:
			if(excavated) currentViewableSymbol = postHoleSymbol;
			else currentViewableSymbol = postHoleAlias;
			break;
		case dirt:
			if(excavated) currentViewableSymbol = dirtSymbol;
			else currentViewableSymbol = dirtAlias;
			break;
		}
	}
	/********************************************************************/
	/**
	 * @param symbol sets either the stoneSymbol, postHoleSymbol, or dirtSymbol
	 * Based on the current Coordinate's feature type
	 */
	public void setFeatureSymbol(char symbol){
		switch(feature){
		case stone:
			stoneSymbol = symbol;
			break;
		case postHole:
			postHoleSymbol = symbol;
			break;
		case dirt:
			dirtSymbol = symbol;
			break;
		}
	}
	/**
	 * @param symbol sets either the stoneAlias, postHoleAlias, or dirtAlias
	 * Based on the current Coordinate's feature type
	 */
	public void setFeatureAlias(char symbol){
		switch(feature){
		case stone:
			stoneAlias = symbol;
			break;
		case postHole:
			postHoleAlias = symbol;
			break;
		case dirt:
			dirtAlias = symbol;
			break;
		}
	}
	/********************************************************************/
	/**
	 * @return feature the Coordinate's current stored feature type
	 */
	public Feature getFeature() { return feature;}
	/**
	 * @param f sets feature
	 */
	public void setFeature(Feature f) { feature = f; }

	/********************************************************************/
	/**
	 * @param featureType
	 * For use by the load method
	 */
	public void setFeature(char featureType){
		switch(featureType){
		case 'N':
			feature = Feature.dirt;
			break;
		case 'S':
			feature = Feature.stone;
			break;
		case 'P':
			feature = Feature.postHole;
			break;
		default:
			System.out.println("Invalid case");
		}
	}
	/**
	 * @return Character to write to save file
	 * For use by the save method
	 */
	public char getFeatureChar(){
		switch(feature){
		case dirt:
			return 'N';
		case stone:
			return 'S';
		case postHole:
			return 'P';
		}
		return ' ';
	}
	/********************************************************************/
	/**
	 * @return charcoalHidden if a charCoal Object is hidden in Coordinate
	 */
	public boolean charcoalHidden() { return charcoalHidden; }
	/**
	 * @return metalHidden if a metalObject is hidden in Coordinate
	 */
	public boolean metalHidden() { return metalHidden; }

	/**
	 * @param hidden
	 * Sets charcoalHidden variable for use by scanning methods
	 */
	public void setCharcoalHidden(boolean hidden) { charcoalHidden = hidden; }
	/**
	 * @param hidden
	 * Sets metalHidden variable for use by scanning methods
	 */
	public void setMetalHidden(boolean hidden) { metalHidden = hidden; }
	/**
	 * @param found
	 * Sets itemFound variable, if excavated Coordinate has a hidden find.
	 */
	public void setItemFound(boolean found) { itemFound = found; }
	/**
	 * @return itemFound
	 * Indicate whether an item has been found by excavating
	 */
	public boolean itemFound(){ return itemFound; }
	/**
	 * @return excavated
	 * Indicates whether the current Coordinate has been dug previously
	 */
	public boolean getExcavated() { return excavated; }
	/**
	 * @param e set value of excavated
	 * For use by digging method
	 */
	public void setExcavated(boolean e) { excavated = e; }
	/**
	 * @return potInspected
	 * Indicates whether visible spectrum has been previously used on this Coordinate
	 */
	public boolean getPotInspected() { return potInspected; }
	/**
	 * @return charCoalInspected
	 * Indicates whether magnetometer has been previously used on this Coordinate
	 */
	public boolean getCharcoalInspected() { return charcoalInspected; }
	/**
	 * @return metalInspected
	 * Indicates whether metal detector has been previously used on this Coordinate
	 */
	public boolean getMetalInspected() { return metalInspected; }

	/**
	 * @param i set value of potInspected
	 * For use by scanning methods
	 */
	public void setPotInspected(boolean i) { potInspected = i; }
	/**
	 * @param i set value of charcoalInspected
	 * For use by scanning methods
	 */
	public void setCharcoalInspected(boolean i) { charcoalInspected = i; }
	/**
	 * @param i set value of metalInspected
	 * For use by scanning methods
	 */
	public void setMetalInspected(boolean i) { metalInspected = i; }
}









