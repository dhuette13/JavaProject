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
		feature = Feature.naturalGrass;
		currentViewableSymbol = 'g';
		excavated = false;
	}

	public void setCurrentViewableSymbol(char symbol){
		if(excavated){
			currentViewableSymbol = symbol;
		}
		else {
			switch(symbol){
			case 'D':
				currentViewableSymbol = 'g';
				break;
			case 'P':
				currentViewableSymbol = 'G';
				break;
			case 'S':
				currentViewableSymbol = 'Y';
				break;
			}
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

	public boolean getInspected(){
		return inspected;
	}

	public void setInspected(boolean i){
		inspected = i;
	}

	public void setFeature(char featureType){
		switch(featureType){
		case 'Y':
			feature = Feature.parchedGrass;
			break;
		case 'G':
			feature = Feature.wetGrass;
			break;
		case 'N':
			feature = Feature.naturalGrass;
			break;
		case 'S':
			feature = Feature.stone;
			break;
		case 'P':
			feature = Feature.postHole;
			break;
		case 'D':
			feature = Feature.exposedDirt;
			break;
		default:
			System.out.println("Invalid case");
		}
	}

	public char getFeature(){
		switch(feature){
		case parchedGrass:
			return 'Y';
		case wetGrass:
			return 'G';
		case naturalGrass:
			return 'N';
		case stone:
			return 'S';
		case postHole:
			return 'P';
		case exposedDirt:
			return 'D';
		default:
			System.out.println("Invalid case");
		}
		return currentViewableSymbol;
	}
}
