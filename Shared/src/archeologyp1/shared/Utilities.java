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
		for(r = 0; r < map.getHeight(); r++)
			for(c = 0; c < map.getWidth(); c++)
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
			rowSize = Integer.parseInt(dimensions[0]);
			colSize = Integer.parseInt(dimensions[1]);
			map = new Map(colSize, rowSize);
			
			String dataArray[];
			for(r = 0; r < map.getHeight(); r++)
				for(c = 0; c < map.getWidth(); c++)
					map.plane[r][c] = new Coordinate();
			
			Coordinate current;
			int numPots, numMetal, numCharcoal, date, i = 0;
			
			while(scan.hasNextLine()){
				line = scan.nextLine();
				dataArray = line.split(",");
				
				/* Get integer index for row and column */ 
				c = ((int) (dataArray[i++].charAt(0))) - 65;
				r = Integer.parseInt(dataArray[i++]) - 1;
				current = map.plane[r][c];

				/* Set the type of feature for coordinate, 
				 * and the symbol to be displayed.
				 */
				Feature f = getFeatureType(dataArray[i]);
				current.setFeature(f);
				current.setCurrentViewableSymbol(dataArray[i++].charAt(0));
				current.setExcavated(Boolean.parseBoolean(dataArray[i++]));
				
				/* Iterate through pot input */
				numPots = Integer.parseInt(dataArray[i++]);
				while(numPots-- != 0){
					date = Integer.parseInt(dataArray[i++]);
					current.addPot(new Pot(date));
				}
				
				/* Iterate through metal input */
				numMetal = Integer.parseInt(dataArray[i++]);
				while(numMetal-- != 0){
					date = Integer.parseInt(dataArray[i++]);
					current.addMetal(new MetalObject(date));
				}
				
				/* Iterate through charcoal input */
				numCharcoal = Integer.parseInt(dataArray[i++]);
				while(numCharcoal-- != 0){
					date = Integer.parseInt(dataArray[i++]);
					current.addCharcoal(new Charcoal(date));
				}
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
	 * @param fileName
	 * @param map
	 */
	public static void save(String path, Map m){
		
	}
	
	public static void printMap(Map map, PrintStream output){
		int r, c;
		Coordinate current;
		output.format("  | ");
		for(c = 0; c < map.getWidth(); c++){
			output.format("%c ", (char) (c + 65));
		}
		output.println();
		output.format("_ +");
		for(c = 0; c < map.getWidth(); c++){
			output.format(" _");
		}
		output.println();
		for(r = 0; r < map.getHeight(); r++){
			output.format("%02d |", r + 1);
			for(c = 0; c < map.getWidth(); c++){
				current = map.plane[r][c];
				output.format("%c ", current.getCurrentViewableSymbol());
			}
			output.println();
		}
		output.println();
	}

	/**
	 * 
	 * @param featureType
	 * @return feature enumeration based on character input
	 */
	public static Feature getFeatureType(String featureType) {
		switch(featureType){
			case "Y":
				return Feature.parchedGrass;
			case "G":
				return Feature.wetGrass;
			case "N":
				return Feature.naturalGrass;
			case "S":
				return Feature.stone;
			case "P":
				return Feature.postHole;
			case "D":
				return Feature.exposedDirt;
			default:
				System.out.println("Invalid case");
		}
		return null;
	}
}
