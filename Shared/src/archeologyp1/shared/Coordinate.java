package archeologyp1.shared;

import java.util.ArrayList;

/**
 * Represents a single coordinate on the map
 * @author Daniel
 *
 */

public class Coordinate {
	private Feature feature;
	private boolean excavated, charcoalInspected, metalInspected, potInspected;
	private boolean charcoalHidden, metalHidden;
	private char currentViewableSymbol;
	/* For Natural Viewing Option */
	private char stoneSymbol, postHoleSymbol, dirtSymbol;
	/* For Readable Viewing Option */
	private char stoneAlias, postHoleAlias, dirtAlias;
	public ArrayList<Pot> potCount = new ArrayList<Pot>();
	public ArrayList<MetalObject> metalCount = new ArrayList<MetalObject>();
	public ArrayList<Charcoal> charcoalCount = new ArrayList<Charcoal>();

	private static final char defaultStoneSymbol = 'S';
	private static final char defaultPostHoleSymbol = 'P';
	private static final char defaultDirtSymbol = 'D';
	private static final char defaultStoneAlias = 'Y';
	private static final char defaultPostHoleAlias = 'G';
	private static final char defaultDirtAlias = 'g';
	
	private static final char readableStoneSymbol = '$';
	private static final char readablePostHoleSymbol = '-';
	private static final char readableDirtSymbol = ',';
	private static final char readableStoneAlias = '#';
	private static final char readablePostHoleAlias = '*';
	private static final char readableDirtAlias = '.';
	
	
	public Coordinate(){
		feature = Feature.dirt;
		currentViewableSymbol = defaultDirtAlias;
		excavated = false;
		charcoalHidden = false;
		metalHidden = false;
		stoneSymbol = defaultStoneSymbol;
		postHoleSymbol = defaultPostHoleSymbol;
		dirtSymbol = defaultDirtSymbol;
		stoneAlias = defaultStoneAlias;
		postHoleAlias = defaultPostHoleAlias;
		dirtAlias = defaultDirtAlias;
	}

	public void setCurrentViewableSymbol(char symbol){
		currentViewableSymbol = symbol;
	}
	/* Updates current viewable symbol based on
	 * the Coordinate's feature type.
	 */
	public void setCurrentViewableSymbol(){
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
	
	public void setFeatureSymbol(ViewingOption option){
		switch(option){
		case natural:
			stoneSymbol = defaultStoneSymbol;
			postHoleSymbol = defaultPostHoleSymbol;
			dirtSymbol = defaultDirtSymbol;
			stoneAlias = defaultStoneAlias;
			postHoleAlias = defaultPostHoleAlias;
			dirtAlias = defaultDirtAlias;
			break;
		case readable:
			stoneSymbol = readableStoneSymbol;
			postHoleSymbol = readablePostHoleSymbol;
			dirtSymbol = readableDirtSymbol;
			stoneAlias = readableStoneAlias;
			postHoleAlias = readablePostHoleAlias;
			dirtAlias = readableDirtAlias;
			break;
		default:
			System.out.println("Invalid viewing option");
		}
	}
	
	/* Sets the current viewable symbol */ 
	public void setFeatureSymbol(char symbol){
		switch(feature){
		case stone:
			if(excavated) stoneSymbol = symbol;
			else stoneAlias = symbol;
			break;
		case postHole:
			if(excavated) postHoleSymbol = symbol;
			else postHoleAlias = symbol;
			break;
		case dirt:
			if(excavated) dirtSymbol = symbol;
			else dirtAlias = symbol;
			break;
		}
	}

	public char getCurrentViewableSymbol(){
		return currentViewableSymbol;
	}

	public boolean getExcavated() {
		return excavated;
	}

	public void setExcavated(boolean value){
		excavated = value;
	}

	public boolean getPotInspected(){
		return potInspected;
	}
	
	public void setPotInspected(boolean i){
		potInspected = i;
	}
	public boolean getCharcoalInspected(){
		return charcoalInspected;
	}

	public void setCharcoalInspected(boolean i){
		charcoalInspected = i;
	}

	public boolean getMetalInspected(){
		return metalInspected;
	}
	
	public void setMetalInspected(boolean i){
		metalInspected = i;
	}

	/* For use by load method */
	public void setFeature(char featureType){
		switch(featureType){
		case 'S':
			feature = Feature.stone;
			break;
		case 'P':
			feature = Feature.postHole;
			break;
		case 'D':
			feature = Feature.dirt;
			break;
		default:
			System.out.println("Invalid case");
		}
	}

	public Feature getFeature(){
		return feature;
	}
	
	/* For use by save method */
	public char getFeatureChar(){
		switch(feature){
		case stone:
			return 'S';
		case postHole:
			return 'P';
		case dirt:
			return 'D';
		}
		return currentViewableSymbol;
	}
	
	public boolean charcoalHidden(){
		return charcoalHidden;
	}
	
	public boolean metalHidden(){
		return metalHidden;
	}

	public void setCharcoalHidden(boolean hidden) {
		charcoalHidden = hidden;
	}
	
	public void setMetalHidden(boolean hidden) {
		metalHidden = hidden;
	}
	
	
}
