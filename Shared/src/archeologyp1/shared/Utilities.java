/*
 * Source code for base 26 to alphabet and alphabet to base 26
 * Author: Becker
 * URL: https://elearn.uta.edu/webapps/portal/frameset.jsp?tab_group=courses&url=%2Fwebapps%
 * 2Fblackboard%2Fexecute%2Fcontent%2Ffile%3Fcmd%3Dview%26content_id%3D_3440332_1%26course_id%3D_228496_1%26framesetWrapped%3Dtrue
 * Date put into the code: Sept 18, 2014
 * 							Oct 05, 2014
 * 
 */

package archeologyp1.shared;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * UTILITIES IN THE SHARED RESOURCES
 * Holds a number of utilities used by both projects.
 * @author Daniel
 * @author Celine
 * 
 * This class contains utilities that are shared by both
 * the MPT and the ADT. Included in this class are the
 * methods that generates the map, loads the file that 
 * will generate the map, save the file, and print the map. 
 *
 */

public class Utilities {

	/**
	 * For the public static Map generateMap method
	 * @param height of map
	 * @param width of map
	 * @return  Map object
	 * 
	 * This method generates a new empty map using a nested for-loop, then
	 * returns the map object to be printed.
	 * 
	 */
	public static Map<Coordinate> generateMap(int width, int height){
		Map<Coordinate> map = new Map<>(width, height);
		int r, c;
		for(r = 0; r < map.getNumRows(); r++)
			for(c = 0; c < map.getNumColumns(); c++)
				map.addPlaneItem(r, c, new Coordinate(r, c));

		return map;
	}

	/**
	 * 
	 * For the public static Map load method
	 * This method loads a map from file into memory. 
	 * @param path
	 * @return Map object
	 * 
	 * 
	 */
	public static Map<Coordinate> load(String path){
		int rowSize, colSize, r, c;
		Scanner scan;
		Map<Coordinate> map;
		try {
			File myFile = new File(path);
			scan = new Scanner(myFile);
			String line = scan.nextLine();

			/* Get file dimensions */
			String dimensions[] = line.split(",");
			colSize = Integer.parseInt(dimensions[0]);
			rowSize = Integer.parseInt(dimensions[1]);
			map = new Map<>(rowSize, colSize);

			String dataArray[];
			Coordinate current;
			int numPots, numMetal, numCharcoal, date, i = 0;

			while(scan.hasNextLine()){
				line = scan.nextLine();
				dataArray = line.split(",");

				/* Get integer index for row and column */ 
				c = columnToIndex(dataArray[i++]);
				r = Integer.parseInt(dataArray[i++]);
				current = new Coordinate(r, c);

				/* Set the type of feature for coordinate, 
				 * and the symbol to be displayed.
				 */
				current.setFeature(dataArray[i++].charAt(0));
				current.setExcavated(Boolean.parseBoolean(dataArray[i++]));

				/* Iterate through pot input */
				numPots = Integer.parseInt(dataArray[i++]);
				while(numPots-- != 0){
					date = Integer.parseInt(dataArray[i++]);
					current.addFind(new Pottery(date));
				}

				/* Iterate through metal input */
				numMetal = Integer.parseInt(dataArray[i++]);
				while(numMetal-- != 0){
					date = Integer.parseInt(dataArray[i++]);
					current.addFind(new MetalObject(date));
				}

				/* Iterate through charcoal input */
				numCharcoal = Integer.parseInt(dataArray[i++]);
				while(numCharcoal-- != 0){
					date = Integer.parseInt(dataArray[i++]);
					current.addFind(new Charcoal(date));
				}
				current.sortDates();
				i = 0;
				map.addPlaneItem(r, c, current);
			}
			scan.close();
			return map;
		} catch (FileNotFoundException e) {
			System.out.println("Invalid Path specified");
		} catch(Exception e) {
			System.out.println("Invalid File");
		}

		return null;
	}

	/**
	 * 
	 * For the public static void save method
	 * @param path fileName to save to
	 * @param m map to save
	 * 
	 * This method saves the map from memory into a file at the
	 * user's discretion. 
	 * 
	 */
	public static void save(Map<Coordinate> map, String path){
		try {
			PrintStream out = new PrintStream(new File(path));
			out.println(map.getNumColumns() + "," + map.getNumRows());
			int k;
			/* For every element in the map array,
			 * get the data elements and write to
			 * data file in comma separated format.
			 */
			for(Coordinate coord : map){
				out.print(indexToColumn(coord.getColumn()) + "," + coord.getRow());
				out.print("," + coord.getFeatureChar());
				out.print("," + Boolean.toString(coord.getExcavated()).toUpperCase());
				out.print("," + coord.getPotCount());
				for(k = 0; k < coord.getPotCount(); k++){
					out.print("," + coord.getPot(k).getDate());
				}
				out.print("," + coord.getMetalCount());
				for(k = 0; k < coord.getMetalCount(); k++){
					out.print("," + coord.getMetal(k).getDate());
				}
				out.print("," + coord.getCharcoalCount());
				for(k = 0; k < coord.getCharcoalCount(); k++){
					out.print("," + coord.getCharcoal(k).getDate());
				}
				out.println();
			}
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error creating file");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * For the public static void printMap method
	 * @param map map to print or export
	 * @param output stream to output to
	 * 
	 * This method prints the map out and shows the user
	 * the current map. 
	 * 
	 */
	public static void printMap(Map<Coordinate> map, PrintStream output){
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
			for(int c = 0; c < map.getNumColumns(); c++){
				output.format("%c", (char) (c + 65));
			}
		}


		output.println();
		output.format("--+");
		for(int c = 0; c < map.getNumColumns(); c++){
			output.format("-");
		}
		output.println();

		/* Print the current viewable symbol for each row */
		char charMap[][] = map.getCharMap();
		for(int r = 0; r < map.getNumRows(); r++){
			output.format("%02d|", r + 1);
			for(int c = 0; c < map.getNumColumns(); c++){
				output.format("%c", charMap[r][c]);
			}
			output.println();
		}
	}
	/**
	 * 
	 * This method converts the column the user specifies in so-and-so
	 * and converts it into a usable number for other general
	 * purposes.
	 * 
	 * @param column to convert
	 * @return index of given column
	 * 
	 */
	public static int columnToIndex(String column){
		float power = 1;
		int coeff = 0;
		char letter = ' ';
		int term = 0;
		int total = 0;
		column = column.toUpperCase();


		for(int i = column.length() - 1; i >= 0; i--){
			letter = column.charAt(i);
			coeff = letter - 'A' + 1;
			term = (int) (coeff * power);
			total = total + term;
			power = power * 26;
		}
		return total - 1;
	}

	/**
	 * 
	 * This method takes an integer and converts it to 
	 * a character that should come out to be a column on 
	 * the map.
	 * 
	 * @param index
	 * @return result 
	 * 
	 */
	public static String indexToColumn(int index){
		String result = new String();
		ArrayList <Integer> tempArray = new ArrayList<Integer>();
		int modulus = 0;

		//Convert from Base 10 to Base 26
		while (true) {
			modulus = index % 26;
			index = index / 26;
			tempArray.add(modulus);

			if (index < 1)
				break;
		}

		//Convert Base 26 to Character Set 
		for (int i = 0; i < tempArray.size(); i++) {
			//Else, we want there to be a blank, not an '@'
			if (i == 0)
				result = (char)('A'+ tempArray.get(i)) + result;
			//If the current character is higher than the first significant column.
			else
				result = (char)('A'+ tempArray.get(i) - 1) + result;
		}
		return result;
	}
}