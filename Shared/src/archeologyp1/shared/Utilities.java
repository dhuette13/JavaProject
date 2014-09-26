/*
 * Source code for base 26 to alphabet
 * Author: Becker
 * URL: https://elearn.uta.edu/webapps/portal/frameset.jsp?tab_group=courses&url=%2Fwebapps%
 * 2Fblackboard%2Fexecute%2Fcontent%2Ffile%3Fcmd%3Dview%26content_id%3D_3440332_1%26course_id%3D_228496_1%26framesetWrapped%3Dtrue
 * Date put into the code: Sept 18, 2014
 * 
 */

package archeologyp1.shared;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * UTILITIES
 * Holds a number of utilities used by both projects.
 * @author Daniel
 * @author Celine
 * 
 * This class contains utilities that are shared by both
 * the MPT and the ADT. Included in this class are the
 * methods 
 *
 */
public class Utilities {
	/**
	 * Generates new empty map
	 * @param height of map
	 * @param width of map
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
				c = columnToIndex(dataArray[i++]);
				r = Integer.parseInt(dataArray[i++]);
				current = map.plane[r][c];

				/* Set the type of feature for coordinate, 
				 * and the symbol to be displayed.
				 */
				current.setFeature(dataArray[i++].charAt(0));
				current.updateCurrentViewableSymbol();
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
			/* For every element in the map array,
			 * get the data elements and write to
			 * data file in comma separated format.
			 */
			for(r = 0; r < m.getNumRows(); r++){
				for(c = 0; c < m.getNumColumns(); c++){
					current = m.plane[r][c];
					out.print(indexToColumn(c) + "," + r);
					out.print("," + current.getFeatureChar());
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

	/**
	 * 
	 * @param map map to print or export
	 * @param output stream to output to
	 */
	public static void printMap(Map map, PrintStream output){
		int r, c;
		Coordinate current;
		char columnCharacter = 'A';
		/* Print the Column labels 
		 * 
		 * If there are more than 26 columns, a second row
		 * will be needed for the ruler.
		 */
		if(map.getNumColumns() > 26){
			output.println();
			output.format("  |");
			/* Print 0's as a buffer for first iteration of the alphabet */
			for(int k = 0; k < 26; k++) output.print(0);
			
			/* Print a sequence common characters for every
			 * 26 characters.
			 */
			for(int k = 1; k <= map.getNumColumns() - 26; k++){
				if((k % 26) == 0) columnCharacter = (char) (columnCharacter + 1);
				output.print(columnCharacter);
			}
			output.println();
			output.format("  |");
			
			/* Print the second row of full alphabets */
			for(int k = 0; k < map.getNumColumns() / 26; k++){
				for(int p = 0; p < 26; p++){
					output.print((char) ('A' + p));
				}
			}
			
			/* Print the remaining characters for the last alphabet */
			for(int k = 0; k < map.getNumColumns() % 26; k++){
				output.print((char) ('A' + k));
			}
			
		} else {
			/* If the number of columns is less than 26,
			 * a second row is not needed.
			 */
			output.format("  |");
			for(c = 0; c < map.getNumColumns(); c++){
				output.format("%c", (char) (c + 65));
			}
		}
		

		output.println();
		output.format("--+");
		for(c = 0; c < map.getNumColumns(); c++){
			output.format("-");
		}
		output.println();

		/* Print the current viewable symbol for each row */
		for(r = 0; r < map.getNumRows(); r++){
			output.format("%02d|", r + 1);
			for(c = 0; c < map.getNumColumns(); c++){
				current = map.plane[r][c];
				output.format("%c", current.getCurrentViewableSymbol());
			}
			output.println();
		}
		output.println();
	}
	
	public static int columnToIndex(String column){
		column = column.toUpperCase();
		if(column.length() == 1){
			return column.charAt(0) - 'A';
		} else {
			return ((column.charAt(0) - 'A' + 1) * 25) + (column.charAt(1) - 'A') + 1;
		}
	}
	
	public static String indexToColumn(int index){
		String result = new String();
		ArrayList <Integer> tempArray = new ArrayList<Integer>();
		int modulus = 0;
		
		//Convert from Base 10 to Base 26
		while (true) {
			modulus=index % 26;
			index = index / 26;
			tempArray.add(modulus);
			
			if (index < 1)
				break;
		}

		//Convert Base 26 to Character Set 
		for (int i = 0; i < tempArray.size(); i++) {
			//Else, we want there to be a blank, not an '@'
			if (i == 0)
				result +=( char)('A'+ tempArray.get(i));
			//If the current character is higher than the first significant column.
			else
				result += (char)('A'+ tempArray.get(i) - 1);
		}
		return result;
	}
}