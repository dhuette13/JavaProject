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

	/* Default symbols to use */
	public static final char defaultDirtSymbol = 'D';
	public static final char defaultStoneSymbol = 'R';
	public static final char defaultPostHoleSymbol = 'H';
	public static final char defaultDirtAlias = 'g';
	public static final char defaultStoneAlias = 'Y';
	public static final char defaultPostHoleAlias = 'G';

	/* For Excavated Coordinates */
	private char stoneSymbol, postHoleSymbol, dirtSymbol;
	/* For Not Excavated Coordinates */
	private char stoneAlias, postHoleAlias, dirtAlias;

	private char charMap[][];
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
		charMap = new char[rows][columns];
		plane = new ArrayList<E>(rows * columns);

		stoneSymbol = defaultStoneSymbol;
		postHoleSymbol = defaultPostHoleSymbol;
		dirtSymbol = defaultDirtSymbol;
		stoneAlias = defaultStoneAlias;
		postHoleAlias = defaultPostHoleAlias;
		dirtAlias = defaultDirtAlias;

		/* Initialize character map */
		for(int r = 0; r < rows; r++)
			for(int c = 0; c < columns; c++)
				charMap[r][c] = defaultDirtAlias;
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
		plane.add(row * columns + col, item);
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
	 * For public char getMapSymbol
	 * Gets the character map symbol at given row and column
	 * 
	 * @param r
	 * @param c
	 * @return Character Map symbol at specified row and column
	 */
	public char getMapSymbol(int row, int column){
		return charMap[row][column];
	}
	
	/**
	 * For public char getCharMap
	 * Returns a copy of the map's current character map
	 * 
	 * @return charMap
	 */
	public char[][] getCharMap(){
		char[][] copy = new char[rows][columns];
		for(int r = 0; r < rows; r++)
			for(int c = 0; c < columns; c++)
				copy[r][c] = charMap[r][c];
		return copy;
	}

	/**
	 * For public void setMapSymbol
	 * Sets the character map at given row and column to given symbol
	 * 
	 * @param r
	 * @param c
	 * @param symbol 
	 */
	public void setMapSymbol(int row, int column, char symbol){
		charMap[row][column] = symbol;
	}

	/**
	 * For public void setStoneSymbol
	 * @param symbol
	 */
	public void setStoneSymbol(char symbol) { stoneSymbol = symbol; }
	
	/**
	 * For public void setDirtSymbol
	 * @param symbol
	 */
	public void setDirtSymbol(char symbol) { dirtSymbol = symbol; }
	
	/**
	 * For public void setPostHoleSymbol
	 * @param symbol
	 */
	public void setPostHoleSymbol(char symbol) { postHoleSymbol = symbol; }
	
	/**
	 * For public void setStoneAlias
	 * @param symbol
	 */
	public void setStoneAlias(char symbol) { stoneAlias = symbol; }
	
	/**
	 * For public void setDirtAlias
	 * @param symbol
	 */
	public void setDirtAlias(char symbol) { dirtAlias = symbol; }
	
	/**
	 * For public void setPostHoleAlias
	 * @param symbol
	 */
	public void setPostHoleAlias(char symbol) { postHoleAlias = symbol; }

	/**
	 * For public char getStoneSymbol
	 * @return stoneSymbol
	 */
	public char getStoneSymbol() { return stoneSymbol; }
	
	/**
	 * For public char getDirtSymbol
	 * @return dirtSymbol
	 */
	public char getDirtSymbol() { return dirtSymbol; }
	
	/**
	 * For public char getPostHoleSymbol
	 * @return postHoleSymbol
	 */
	public char getPostHoleSymbol() { return postHoleSymbol; }
	
	/**
	 * For public char getStoneAlias
	 * @return stoneAlias
	 */
	public char getStoneAlias() { return stoneAlias; }
	
	/**
	 * For public char getDirtAlias
	 * @return dirtAlias
	 */
	public char getDirtAlias() { return dirtAlias; }
	
	/**
	 * For public char getPostHoleAlias
	 * @return postHoleAlias
	 */
	public char getPostHoleAlias() { return postHoleAlias; }

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
