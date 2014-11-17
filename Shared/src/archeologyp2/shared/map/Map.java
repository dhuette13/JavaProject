package archeologyp2.shared.map;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * MAP IN THE SHARED RESOURCES
 * @author Daniel
 * @author Celine
 * 
 * This class represents a loaded map in memory and
 * contains map features that are shared by
 * both the ADT and the MPT. The main method
 * in this class is the viewing options method,
 * which allows the user to switch their view between
 * multiple choices.
 *
 */
public class Map<E> implements Iterable<E> {

	private ArrayList<E> plane;
	private int rows, columns;
	private ViewingOption viewingOption;

	/**
	 * For public Map
	 * @param rows
	 * @param columns
	 */
	public Map(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
		viewingOption = ViewingOption.natural;
		plane = new ArrayList<E>(rows * columns);
	}
	
	/**
	 * 
	 * For the public int getNumRows method
	 * @return the number of rows
	 * 
	 */
	public int getNumRows(){
		return rows;
	}
	
	/**
	 * 
	 * For the public int getNumColumns method
	 * @return the number of columns
	 * 
	 */
	public int getNumColumns(){
		return columns;
	}

	/**
	 * For public void addPlaneItem
	 * Adds the specified item to ArrayList
	 * 
	 * @param row 
	 * @param col
	 * @param e
	 */
	public void addPlaneItem(int row, int col, E item){
		try{
			/* Replace existing item has higher priority */
			plane.set(row * columns + col, item);
		} catch (IndexOutOfBoundsException e){
			/* If index to replace does not exist, create new one */
			plane.add(row * columns + col, item);
		}
	}
	
	/**
	 * For public E getPlaneItem
	 * Gets the item stored at the specified row and column
	 * 
	 * @param row
	 * @param col
	 * @return Item stored at given row and column
	 */
	public E getPlaneItem(int row, int col){
		return plane.get(row * columns + col);
	}
	
	/**
	 * For public ViewingOption getViewingOption
	 * Returns the map's current viewing option
	 *
	 * @return the viewing option the user specifies
	 * 
	 */
	public ViewingOption getViewingOption(){
		return viewingOption;
	}
	
	/**
	 * For public void setViewingOption
	 * Sets the map's current viewing option
	 * 
	 * @param option
	 * 
	 */
	public void setViewingOption(ViewingOption option){
		viewingOption = option;
	}

	/**
	 * For public Iterator<E> iterator
	 * Map iterator generator for use by enhanced for loop
	 * 
	 * @return it Iterator for map object
	 */
	@Override
	public Iterator<E> iterator() {
		
		/* Create new iterator method */
		Iterator<E> it = new Iterator<E>() {
			int r = 0, c = 0;

			/* Indicates when the iteration has completed */
			@Override
			public boolean hasNext() {
				if((r * columns + c) == plane.size()){
					return false;
				} else {
					return true;
				}
			}

			/* Returns next object in collection */
			@Override
			public E next() {
				if(c == columns){
					c = 0;
					r++;
				}
				return plane.get(r * columns + c++);
			}
			@Override
			public void remove() {
			}
		};
		return it;
	}
}
