package archeologyp1.shared;

import java.util.ArrayList;

/**
 * Represents a single coordinate on the map
 * @author Daniel
 *
 */

public class Coordinate {
	private Feature feature;
	private boolean excavated;
	private boolean inspected;
	private char currentViewableSymbol;
	public ArrayList<Pot> potCount = new ArrayList<Pot>();
	public ArrayList<MetalObject> metalCount = new ArrayList<MetalObject>();
	public ArrayList<Charcoal> charcoalCount = new ArrayList<Charcoal>();

	public Coordinate(){
		feature = Feature.dirt;
		currentViewableSymbol = 'g';
		excavated = false;
	}

	/* Updates current viewable symbol based on
	 * the Coordinate's feature type.
	 */
	public void setCurrentViewableSymbol(){
		switch(feature){
		case stone:
			if(excavated) currentViewableSymbol = 'S';
			else currentViewableSymbol = 'Y';
			break;
		case postHole:
			if(excavated) currentViewableSymbol = 'P';
			else currentViewableSymbol = 'G';
			break;
		case dirt:
			if(excavated) currentViewableSymbol = 'D';
			else currentViewableSymbol = 'g';
			break;
		}
	}
	
	/* Sets the current viewable symbol */ 
	public void setCurrentViewableSymbol(char symbol){
		currentViewableSymbol = symbol;
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

	public boolean getInspected(){
		return inspected;
	}

	public void setInspected(boolean i){
		inspected = i;
	}

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

	public char getFeature(){
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
}
