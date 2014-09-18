package archeologyp1.shared;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Holds a number of utilities used by both projects.
 * @author Daniel
 *
 */
public class Utilities {
	/**
	 * Generates new empty map
	 * @param height 
	 * @param width 
	 * @return  Map object
	 */
	public static Map generateMap(int width, int height){
		Map map = new Map(width, height);
		int r, c;
		for(r = 0; r < map.getNumRows(); r++)
			for(c = 0; c < map.getNumColumns(); c++)
				map.plane[r][c] = new Coordinate();
		
		return map;
	}
	
	/**
	 * Loads map from file into memory
	 * @param path
	 * @return Map object
	 * 
	 */
	public static Map load(String path){
		int rowSize, colSize, r, c;
		Scanner scan;
		Map map;
		try {
			File myFile = new File(path);
			scan = new Scanner(myFile);
			String line = scan.nextLine();
			
			/* Get file dimensions */
			String dimensions[] = line.split(",");
			colSize = Integer.parseInt(dimensions[0]);
			rowSize = Integer.parseInt(dimensions[1]);
			map = new Map(rowSize, colSize);
			
			String dataArray[];
			for(r = 0; r < map.getNumRows(); r++)
				for(c = 0; c < map.getNumColumns(); c++)
					map.plane[r][c] = new Coordinate();
			
			Coordinate current;
			int numPots, numMetal, numCharcoal, date, i = 0;
			
			while(scan.hasNextLine()){
				line = scan.nextLine();
				dataArray = line.split(",");
				
				/* Get integer index for row and column */ 
				c = ((int) (dataArray[i++].charAt(0))) - 65;
				r = Integer.parseInt(dataArray[i++]);
				current = map.plane[r][c];

				/* Set the type of feature for coordinate, 
				 * and the symbol to be displayed.
				 */
				current.setFeature(dataArray[i].charAt(0));
				current.setCurrentViewableSymbol(dataArray[i++].charAt(0));
				current.setExcavated(Boolean.parseBoolean(dataArray[i++]));
				
				/* Iterate through pot input */
				numPots = Integer.parseInt(dataArray[i++]);
				while(numPots-- != 0){
					date = Integer.parseInt(dataArray[i++]);
					current.potCount.add(new Pot(date));
				}
				
				/* Iterate through metal input */
				numMetal = Integer.parseInt(dataArray[i++]);
				while(numMetal-- != 0){
					date = Integer.parseInt(dataArray[i++]);
					current.metalCount.add(new MetalObject(date));
				}
				
				/* Iterate through charcoal input */
				numCharcoal = Integer.parseInt(dataArray[i++]);
				while(numCharcoal-- != 0){
					date = Integer.parseInt(dataArray[i++]);
					current.charcoalCount.add(new Charcoal(date));
				}
				i = 0;
			}
			scan.close();
			return map;
		} catch (FileNotFoundException e) {
			System.out.println("Invalid Path specified");
		}
		
		return null;
	}
	
	/**
	 * Saves map from memory into file
	 * @param path fileName to save to
	 * @param m map to save
	 */
	public static void save(Map m, String path){
		try {
			PrintStream out = new PrintStream(new File(path));
			out.println(m.getNumColumns() + "," + m.getNumRows());
			Coordinate current;
			int r, c, k;
			for(r = 0; r < m.getNumRows(); r++){
				for(c = 0; c < m.getNumColumns(); c++){
					current = m.plane[r][c];
					out.print(((char)(c + 65)) + "," + r);
					out.print("," + current.getFeature());
					out.print("," + Boolean.toString(current.getExcavated()).toUpperCase());
					out.print("," + current.potCount.size());
					for(k = 0; k < current.potCount.size(); k++){
						out.print("," + current.potCount.get(k).getDate());
					}
					out.print("," + current.metalCount.size());
					for(k = 0; k < current.metalCount.size(); k++){
						out.print("," + current.metalCount.get(k).getDate());
					}
					out.print("," + current.charcoalCount.size());
					for(k = 0; k < current.charcoalCount.size(); k++){
						out.print("," + current.charcoalCount.get(k).getDate());
					}
					out.println();
				}
			}
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error creating file");
			e.printStackTrace();
		}
	}
	
	public static void printMap(Map map, PrintStream output){
		int r, c;
		Coordinate current;
		/* Print the Column labels */
		output.format("  | ");
		for(c = 0; c < map.getNumColumns(); c++){
			output.format("%c ", (char) (c + 65));
		}
		
		output.println();
		output.format("_ +");
		for(c = 0; c < map.getNumColumns(); c++){
			output.format(" _");
		}
		output.println();
		
		/* Print the current viewable symbol for each row */
		for(r = 0; r < map.getNumRows(); r++){
			output.format("%02d |", r + 1);
			for(c = 0; c < map.getNumColumns(); c++){
				current = map.plane[r][c];
				output.format("%c ", current.getCurrentViewableSymbol());
			}
			output.println();
		}
		output.println();
	}
}
