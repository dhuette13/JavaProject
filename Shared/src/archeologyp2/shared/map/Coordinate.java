package archeologyp2.shared.map;

import java.util.ArrayList;
import java.util.Collections;

import archeologyp2.shared.finds.Artifact;
import archeologyp2.shared.finds.Charcoal;
import archeologyp2.shared.finds.MetalObject;
import archeologyp2.shared.finds.Pottery;

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
	int row, column;
	private Feature feature;
	private boolean excavated, itemFound;
	private boolean charcoalHidden, metalHidden;
	private boolean charcoalInspected, metalInspected, potInspected;
	
	private boolean heritage;

	private ArrayList<Pottery> potCount = new ArrayList<Pottery>();
	private ArrayList<MetalObject> metalCount = new ArrayList<MetalObject>();
	private ArrayList<Charcoal> charcoalCount = new ArrayList<Charcoal>();

	/**
	 * 
	 * For the public Coordinate method
	 * This method initializes member variables.
	 * 
	 */
	public Coordinate(int row, int column){
		this.row = row;
		this.column = column;
		feature = Feature.dirt;

		excavated = false;
		itemFound = false;
		charcoalHidden = false; metalHidden = false;
		charcoalInspected = false; metalInspected = false; potInspected = false;
	}

	/**
	 * Determines the type of item passed, and adds the item
	 * to an appropriate collection
	 * 
	 * @param item to add to a collection
	 */
	public void addFind(Artifact item){
		if(item instanceof Pottery)
			potCount.add((Pottery) item);
		if(item instanceof MetalObject)
			metalCount.add((MetalObject) item);
		if(item instanceof Charcoal)
			charcoalCount.add((Charcoal) item);
	}

	/**
	 * 
	 * @return size of potCount ArrayList
	 */
	public int getPotCount(){
		return potCount.size();
	}
	
	/**
	 * 
	 * @return size of metalCount ArrayList
	 */
	public int getMetalCount(){
		return metalCount.size();
	}
	
	/**
	 * 
	 * @return size of charcoalCount ArrayList
	 */
	public int getCharcoalCount(){
		return charcoalCount.size();
	}
	
	/**
	 * 
	 * @param index
	 * @return item stored at given index of potCount ArrayList
	 */
	public Pottery getPot(int index){
		return potCount.get(index);
	}
	
	/**
	 * 
	 * @param index
	 * @return item stored at given index of metalCount ArrayList
	 */
	public MetalObject getMetal(int index){
		return metalCount.get(index);
	}
	
	/**
	 * 
	 * @param index
	 * @return item stored at given index of charcoalCount ArrayList
	 */
	public Charcoal getCharcoal(int index){
		return charcoalCount.get(index);
	}
	/**
	 * 
	 * @return row
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * 
	 * @return column
	 */
	public int getColumn() {
		return column;
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
	 * Sets the coordinate's current heritage status
	 * 
	 * @param heritage
	 */
	public void setHeritage(boolean heritage){
		this.heritage = heritage;
	}
	
	/**
	 * Gets the coordinate's current heritage status
	 * 
	 * @return heritage
	 */
	public boolean isHeritage(){
		return heritage;
	}
	/**
	 * Sorts each of the artifact collections based on date
	 */
	public void sortDates() {
		Collections.sort(potCount);
		Collections.sort(metalCount);
		Collections.sort(charcoalCount);
	}
}





