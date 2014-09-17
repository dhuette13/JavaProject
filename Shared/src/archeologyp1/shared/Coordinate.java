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
	private ArrayList<Pot> potCount = new ArrayList<Pot>();
	private ArrayList<MetalObject> metalCount = new ArrayList<MetalObject>();
	private ArrayList<Charcoal> charcoalCount = new ArrayList<Charcoal>();
	
	public Coordinate(){
		feature = Feature.naturalGrass;
		currentViewableSymbol = 'g';
		excavated = false;
	}
	
	public void setCurrentViewableSymbol(char symbol){
		if(excavated)
			currentViewableSymbol = symbol;
		else{
			switch(symbol){
			case 'D':
				currentViewableSymbol = 'N';
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
	
	public void setExcavated(boolean value){
		excavated = value;
	}
	
	public boolean getInspected(){
		return inspected;
	}
	
	public void setInspected(boolean i){
		inspected = i;
	}
	public void setFeature(Feature f){
		feature = f;
	}
	
	public void addPot(Pot... pots){
		for(Pot p: pots){
			potCount.add(p);
		}
	}
	
	public void addMetal(MetalObject... metals){
		for(MetalObject m: metals){
			metalCount.add(m);
		}
	}
	
	public void addCharcoal(Charcoal... charcoals){
		for(Charcoal c: charcoals){
			charcoalCount.add(c);
		}
	}
}
