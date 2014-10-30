package archeologyp2.shared.map;

import java.util.ArrayList;
import java.util.Collections;

import archeologyp2.shared.finds.Artifact;
import archeologyp2.shared.finds.Charcoal;
import archeologyp2.shared.finds.MetalObject;
import archeologyp2.shared.finds.NonFerrousMetal;
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
	 * For public void removeGold
	 * Removes the instance of gold stored in the current coordinate
	 */
	public void removeGold(){
		MetalObject m;
		/* Search for the gold object */
		for(int i = 0; i < metalCount.size(); i++){
			m = metalCount.get(i);
			if(m instanceof NonFerrousMetal){
				if(((NonFerrousMetal) m).getType().toLowerCase().equals("gold")){
					/* When found, remove from list */
					metalCount.remove(i);
				}
			}
		}
	}

	/**
	 * For public int getPotCount
	 * @return size of potCount ArrayList
	 */
	public int getPotCount(){
		return potCount.size();
	}
	
	/**
	 * For public int getMetalCount
	 * @return size of metalCount ArrayList
	 */
	public int getMetalCount(){
		return metalCount.size();
	}
	
	/**
	 * For public int getCharcoalCount
	 * @return size of charcoalCount ArrayList
	 */
	public int getCharcoalCount(){
		return charcoalCount.size();
	}
	
	/**
	 * For public Pottery getPot
	 * @param index
	 * @return item stored at given index of potCount ArrayList
	 */
	public Pottery getPot(int index){
		return potCount.get(index);
	}
	
	/**
	 * For public MetalObject getMetal
	 * @param index
	 * @return item stored at given index of metalCount ArrayList
	 */
	public MetalObject getMetal(int index){
		return metalCount.get(index);
	}
	
	/**
	 * For public Charcoal getCharcoal
	 * @param index
	 * @return item stored at given index of charcoalCount ArrayList
	 */
	public Charcoal getCharcoal(int index){
		return charcoalCount.get(index);
	}
	/**
	 * For public int getRow
	 * @return row
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * For public int getColumn
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
	 * This method sets the charcoalHidden variable for use by scanning methods.
	 * @param hidden
	 * 
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
	 * This method sets the itemFound variable. If excavated, Coordinate has a hidden find.
	 * @param found
	 * 
	 */
	public void setItemFound(boolean found) { itemFound = found; }
	/**
	 * 
	 * For the public boolean itemFound method
	 * This method will indicate whether an item has been found by excavating.
	 * @return itemFound
	 * 
	 */
	public boolean itemFound(){ return itemFound; }
	/**
	 * 
	 * For the public boolean getExcavated method
	 * This method will indicate whether the current Coordinate has been dug previously.
	 * @return excavated
	 * 
	 */
	public boolean getExcavated() { return excavated; }
	/**
	 * 
	 * For the public void setExcavated method
	 * This method is for use by the digging method.
	 * @param e set value of excavated
	 * 
	 */
	public void setExcavated(boolean e) { excavated = e; }
	/**
	 * 
	 * For the public boolean getPotInspected method
	 * This method indicates whether the visible spectrum tool has been 
	 * previously used on this Coordinate
	 * @return potInspected
	 * 
	 */
	public boolean getPotInspected() { return potInspected; }
	/**
	 * 
	 * For the public boolean getCharcoalInspected method
	 * This method indicates whether the magnetometer tool has 
	 * been previously used on this Coordinate.
	 * @return charCoalInspected
	 * 
	 */
	public boolean getCharcoalInspected() { return charcoalInspected; }
	/**
	 * 
	 * For the public boolean getMetalInspected method
	 * This method indicates whether the metal detector tool 
	 * has been previously used on this Coordinate.
	 * @return metalInspected
	 * 
	 */
	public boolean getMetalInspected() { return metalInspected; }

	/**
	 * 
	 * For the public void setPotInspected tool
	 * This method is for use by scanning methods.
	 * @param i set value of potInspected
	 * 
	 */
	public void setPotInspected(boolean i) { potInspected = i; }
	/**
	 * 
	 * For the public void setCharcoalInspected method
	 * This method is for use by scanning methods.
	 * @param i set value of charcoalInspected
	 * 
	 */
	public void setCharcoalInspected(boolean i) { charcoalInspected = i; }
	/**
	 * 
	 * For the public void setMetalInspected method
	 * This method is for use by scanning methods.
	 * @param i set value of metalInspected
	 * 
	 */
	public void setMetalInspected(boolean i) { metalInspected = i; }

	/**
	 * For public void setHeritage
	 * Sets the coordinate's current heritage status
	 * @param heritage
	 */
	public void setHeritage(boolean heritage){
		this.heritage = heritage;
	}
	
	/**
	 * For public boolean isHeritage
	 * Gets the coordinate's current heritage status
	 * 
	 * @return heritage
	 */
	public boolean isHeritage(){
		return heritage;
	}
	
	/**
	 * For public void sortDates
	 * Sorts each of the artifact collections based on date
	 */
//	public void sortDates() {
//		Collections.sort(potCount);
//		Collections.sort(metalCount);
//		Collections.sort(charcoalCount);
//	}
}





