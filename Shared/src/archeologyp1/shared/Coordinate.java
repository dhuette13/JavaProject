package archeologyp1.shared;

import java.util.ArrayList;

/**
 * 
 * COORDINATE FOR THE SHARED RESOURCES
 * @author Daniel
 * @author Celine
 * 
 * This class represents a single coordinate on the map.
 * Many of the methods in this class involve the symbols
 * and finds of the map. 
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
	 * 
	 * For the public Coordinate method
	 * This method initializes member variables.
	 * 
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
	 * 
	 * For the public char getCurrentViewableSymbol method
	 * @return currentViewableSymbol
	 * 
	 * This method is for use by printing method.
	 * 
	 */
	public char getCurrentViewableSymbol() { return currentViewableSymbol; }
	/**
	 * 
	 * For the public void setCurrentViewableSymbol
	 * @param symbol sets currentViewableSymbol
	 * 
	 * This method is used by printing method.
	 * 
	 */
	public void setCurrentViewableSymbol(char symbol) { currentViewableSymbol = symbol; }
	/**
	 * 
	 * For the public void updateCurrentViewableSymbol
	 * This method updates current viewable symbol based on 
	 * the Coordinate's current feature type.
	 * 
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
	 * 
	 * For the public void setFeatureSymbol method
	 * @param symbol sets either the stoneSymbol, postHoleSymbol, or dirtSymbol
	 * 
	 * This method is revolves around the current Coordinate's feature type.
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
	 * 
	 * For the public void setFeatureAlias method
	 * @param symbol sets either the stoneAlias, postHoleAlias, or dirtAlias
	 * 
	 * This method is revolves around the current Coordinate's feature type.
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
	 * 
	 * For the public Feature getFeature method
	 * @return feature the Coordinate's current stored feature type
	 * 
	 */
	public Feature getFeature() { return feature;}
	/**
	 * 
	 * For the public void setFeature method
	 * @param f sets feature
	 * 
	 */
	public void setFeature(Feature f) { feature = f; }

	/********************************************************************/
	/**
	 * 
	 * For the public void setFeature method
	 * @param featureType
	 * 
	 * This method allows the user to set the feature type. 
	 * It is used by the load method.
	 * 
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
	 * 
	 * For the public char getFeatureChar method
	 * @return Character to write to save file
	 * 
	 * This method is for use by the save method. 
	 * 
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
	 * 
	 * For the public boolean charcoalHidden method
	 * @return charcoalHidden if a charCoal Object is hidden in Coordinate
	 * 
	 */
	public boolean charcoalHidden() { return charcoalHidden; }
	/**
	 * 
	 * For the public boolean metalHidden method
	 * @return metalHidden if a metalObject is hidden in Coordinate
	 * 
	 */
	public boolean metalHidden() { return metalHidden; }

	/**
	 * 
	 * For the public void setCharcoalHidden method
	 * @param hidden
	 * 
	 * This method sets the charcoalHidden variable for use by scanning methods.
	 * 
	 */
	public void setCharcoalHidden(boolean hidden) { charcoalHidden = hidden; }
	/**
	 * 
	 * For the public void setMetalHidden method
	 * @param hidden
	 * 
	 * This method sets the metalHidden variable for use by scanning methods.
	 * 
	 */
	public void setMetalHidden(boolean hidden) { metalHidden = hidden; }
	/**
	 * 
	 * For the public void setItemFound method
	 * @param found
	 * 
	 * This method sets the itemFound variable. If excavated, Coordinate has a hidden find.
	 * 
	 */
	public void setItemFound(boolean found) { itemFound = found; }
	/**
	 * 
	 * For the public boolean itemFound method
	 * @return itemFound
	 * 
	 * This method will indicate whether an item has been found by excavating.
	 * 
	 */
	public boolean itemFound(){ return itemFound; }
	/**
	 * 
	 * For the public boolean getExcavated method
	 * @return excavated
	 * 
	 * This method will indicate whether the current Coordinate has been dug previously.
	 * 
	 */
	public boolean getExcavated() { return excavated; }
	/**
	 * 
	 * For the public void setExcavated method
	 * @param e set value of excavated
	 * 
	 * This method is for use by the digging method.
	 * 
	 */
	public void setExcavated(boolean e) { excavated = e; }
	/**
	 * 
	 * For the public boolean getPotInspected method
	 * @return potInspected
	 * 
	 * This method indicates whether the visible spectrum tool has been 
	 * previously used on this Coordinate
	 * 
	 */
	public boolean getPotInspected() { return potInspected; }
	/**
	 * 
	 * For the public boolean getCharcoalInspected method
	 * @return charCoalInspected
	 * 
	 * This method indicates whether the magnetometer tool has 
	 * been previously used on this Coordinate.
	 * 
	 */
	public boolean getCharcoalInspected() { return charcoalInspected; }
	/**
	 * 
	 * For the public boolean getMetalInspected method
	 * @return metalInspected
	 * 
	 * This method indicates whether the metal detector tool 
	 * has been previously used on this Coordinate.
	 * 
	 */
	public boolean getMetalInspected() { return metalInspected; }

	/**
	 * 
	 * For the public void setPotInspected tool
	 * @param i set value of potInspected
	 * 
	 * This method is for use by scanning methods.
	 * 
	 */
	public void setPotInspected(boolean i) { potInspected = i; }
	/**
	 * 
	 * For the public void setCharcoalInspected method
	 * @param i set value of charcoalInspected
	 * 
	 * This method is for use by scanning methods.
	 * 
	 */
	public void setCharcoalInspected(boolean i) { charcoalInspected = i; }
	/**
	 * 
	 * For the public void setMetalInspected method
	 * @param i set value of metalInspected
	 * 
	 * This method is for use by scanning methods.
	 * 
	 */
	public void setMetalInspected(boolean i) { metalInspected = i; }
	
	/**
	 * 
	 * For the public char getAliasChar method
	 * @return the alias
	 * 
	 * This method will return the alias for a feature.
	 * 
	 */
	public char getAliasChar(){
		switch(feature){
		case dirt:
			return dirtAlias;
		case stone:
			return stoneAlias;
		case postHole:
			return postHoleAlias;
		} 
		return ' ';
	}
}





