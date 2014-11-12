/*
 * Source code for base 26 to alphabet and alphabet to base 26 
 * Author: Becker
 * URL: https://elearn.uta.edu/webapps/portal/frameset.jsp?tab_group=courses&url=%2Fwebapps%
 * 2Fblackboard%2Fexecute%2Fcontent%2Ffile%3Fcmd%3Dview%26content_id%3D_3440332_1%26course_id%3D_228496_1%26framesetWrapped%3Dtrue
 * Date put into the code: Sept 18, 2014
 * 							Oct 05, 2014
 * 
 */

package archeologyp2.shared.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import archeologyp2.shared.finds.Artifact;
import archeologyp2.shared.finds.Charcoal;
import archeologyp2.shared.finds.DecoratedPottery;
import archeologyp2.shared.finds.FerrousMetal;
import archeologyp2.shared.finds.Hearth;
import archeologyp2.shared.finds.Kiln;
import archeologyp2.shared.finds.MetalObject;
import archeologyp2.shared.finds.NonFerrousMetal;
import archeologyp2.shared.finds.Pottery;
import archeologyp2.shared.finds.StoragePottery;
import archeologyp2.shared.finds.SubmergedPottery;

/**
 * UTILITIES IN THE SHARED RESOURCES
 * This class contains utilities that are shared by both
 * the MPT and the ADT. Included in this class are the
 * methods that generates the map, loads the file that 
 * will generate the map, save the file, and print the map. 
 * 
 * @author Daniel
 * @author Celine
 *
 */

public class Utilities {

	/**
	 * For the public static Map generateMap method
	 * 
	 * This method generates a new empty map using a nested for-loop, then
	 * returns the map object to be printed.
	 * 
	 * @param height of map
	 * @param width of map
	 * @return  Map object
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
	 * @throws Exception 
	 * 
	 */
	public static Map<Coordinate> load(String path) throws Exception{
		int rowSize, colSize, r, c;
		int row;
		String column;
		Scanner scan;
		Map<Coordinate> map;
		String dataArray[];
		Coordinate current;
		int numPots, numMetal, numCharcoal, date, i = 0;
		String artifactIndicator;
		Pottery pot = null;
		Charcoal charcoal = null;
		MetalObject metal = null;
		double volume, radius, width, length;
		int depth, strength;
		String decorationString, metalType;

		try {
			File myFile = new File(path);
			scan = new Scanner(myFile);
			String line = scan.nextLine();

			/* Get file dimensions */
			String dimensions[] = line.split(",");
			colSize = Integer.parseInt(dimensions[0]);
			rowSize = Integer.parseInt(dimensions[1]);
			map = new Map<>(rowSize, colSize);

			/* Force the map to be of proper size */
			for(int m = 0; m < map.getNumRows(); m++){
				for(int n = 0; n < map.getNumColumns(); n++){
					map.addPlaneItem(m, n, new Coordinate(m, n));
				}
			}

			while(scan.hasNextLine()){
				line = scan.nextLine();
				dataArray = line.split(",");

				/* Get row and column from file */
				column = dataArray[i++];
				row = Integer.parseInt(dataArray[i++]) + 1;

				/* Get integer index for row and column */ 
				c = columnToIndex(column);
				r = row - 1;
				current = new Coordinate(r, c);

				/* Set the type of feature for coordinate, 
				 * and the symbol to be displayed.
				 */
				current.setFeature(dataArray[i++].charAt(0));
				current.setExcavated(Boolean.parseBoolean(dataArray[i++]));
				current.setHeritage(Boolean.parseBoolean(dataArray[i++]));

				/* Iterate through pot input */
				numPots = Integer.parseInt(dataArray[i++]);
				while(numPots-- != 0){
					artifactIndicator = dataArray[i++];
					switch(artifactIndicator){
					case "Storage":
						volume = Double.parseDouble(dataArray[i++]);
						date = Integer.parseInt(dataArray[i++]);
						pot = new StoragePottery(date, volume, row, column);
						break;
					case "Decorated":
						decorationString = dataArray[i++];
						date = Integer.parseInt(dataArray[i++]);
						pot = new DecoratedPottery(date, decorationString, row, column);
						break;
					case "Submerged":
						depth = Integer.parseInt(dataArray[i++]);
						date = Integer.parseInt(dataArray[i++]);
						pot = new SubmergedPottery(date, depth, row, column);
						break;
					}
					current.addFind(pot);
				}

				/* Iterate through charcoal input */
				numCharcoal = Integer.parseInt(dataArray[i++]);
				while(numCharcoal-- != 0){
					artifactIndicator = dataArray[i++];
					switch(artifactIndicator){
					case "Kiln":
						radius = Double.parseDouble(dataArray[i++]);
						date = Integer.parseInt(dataArray[i++]);
						charcoal = new Kiln(date, radius, row, column);
						break;
					case "Hearth":
						length = Double.parseDouble(dataArray[i++]);
						width = Double.parseDouble(dataArray[i++]);
						date = Integer.parseInt(dataArray[i++]);
						charcoal = new Hearth(date, length, width, row, column);
						break;
					}
					current.addFind(charcoal);
				}

				/* Iterate through metal input */
				numMetal = Integer.parseInt(dataArray[i++]);
				while(numMetal-- != 0){
					artifactIndicator = dataArray[i++];
					switch(artifactIndicator){
					case "Non-Ferrous":
						metalType = dataArray[i++];
						date = Integer.parseInt(dataArray[i++]);
						metal = new NonFerrousMetal(date, metalType, row, column);
						if(metalType.toLowerCase().equals("gold")){
							((NonFerrousMetal) metal).setGoldExists(true);
							((NonFerrousMetal) metal).setGoldRow(row);
							((NonFerrousMetal) metal).setGoldColumn(column);
						}
						break;
					case "Ferrous":
						strength = Integer.parseInt(dataArray[i++]);
						date = Integer.parseInt(dataArray[i++]);
						metal = new FerrousMetal(date, strength, row, column);
						break;
					}
					current.addFind(metal);
				}

				i = 0;
				map.addPlaneItem(r, c, current);
			}
			scan.close();
			return map;
		} catch (FileNotFoundException e) {
			throw e;
		} catch(Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * For the public static void save method
	 * This method saves the map from memory into a file at the
	 * user's discretion. 
	 * 
	 * @param path fileName to save to
	 * @param m map to save
	 * @throws Exception 
	 * 
	 */
	public static void save(Map<Coordinate> map, String path) throws Exception{
		PrintWriter out = null;
		Artifact artifact;
		if(map == null) throw new NullPointerException("Map is null");
		try {
			out = new PrintWriter(new File(path));
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
				out.print("," + Boolean.toString(coord.isHeritage()).toUpperCase());

				/* Save Pot  data */
				out.print("," + coord.getPotCount());
				for(k = 0; k < coord.getPotCount(); k++){
					artifact = coord.getPot(k);
					if(artifact instanceof DecoratedPottery){
						out.print("," + "Decorated");
						out.print("," + ((DecoratedPottery) artifact).getDescription());
					} else if(artifact instanceof SubmergedPottery){
						out.print("," + "Submerged");
						out.print("," + ((SubmergedPottery) artifact).getDepth());
					} else if(artifact instanceof StoragePottery){
						out.print("," + "Storage");
						out.print("," + ((StoragePottery) artifact).getVolume());
					}
					out.print("," + artifact.getDate());
				}

				/* Print Charcoal data */
				out.print("," + coord.getCharcoalCount());
				for(k = 0; k < coord.getCharcoalCount(); k++){
					artifact = coord.getCharcoal(k);
					if(artifact instanceof Hearth){
						out.print("," + "Hearth");
						out.print("," + ((Hearth) artifact).getLength());
						out.print("," + ((Hearth) artifact).getWidth());
					} else if (artifact instanceof Kiln){
						out.print("," + "Kiln");
						out.print("," + ((Kiln) artifact).getRadius());
					}
					out.print("," + artifact.getDate());
				}

				/* Print Metal  data */
				out.print("," + coord.getMetalCount());
				for(k = 0; k < coord.getMetalCount(); k++){
					artifact = coord.getMetal(k);
					if(artifact instanceof FerrousMetal){
						out.print("," + "Ferrous");
						out.print("," + ((FerrousMetal) artifact).getSignalStrength());
					} else if (artifact instanceof NonFerrousMetal){
						out.print("," + "Non-Ferrous");
						out.print("," + ((NonFerrousMetal) artifact).getType());
					}
					out.print("," + artifact.getDate());
				}

				out.println();
			}
			out.close();
		} catch (Exception e){
			throw e;
		}
		finally {
			if(out != null)
				out.close();
		}
	}

//	/**
//	 * For public static void exportMap
//	 * This method exports the current map to the given path name
//	 * the user specifies.
//	 * 
//	 * @param map map to print or export
//	 * @param output stream to output to
//	 * 
//	 */
//	public static void exportMap(Map<Coordinate> map, String path) {
//		char columnCharacter = 'A';
//		/* Print the Column labels 
//		 * 
//		 * If there are more than 26 columns, a second row
//		 * will be needed for the ruler.
//		 */
//
//		PrintWriter output = null;
//		try {
//			output = new PrintWriter(new File(path));
//			if(map.getNumColumns() > 26){
//				output.println();
//				output.format("  |");
//				/* Print 0's as a buffer for first iteration of the alphabet */
//				for(int k = 0; k < 26; k++) output.print(0);
//
//				/* Print a sequence common characters for every
//				 * 26 characters.
//				 */
//				for(int k = 1; k <= map.getNumColumns() - 26; k++){
//					if((k % 26) == 0) columnCharacter = (char) (columnCharacter + 1);
//					output.print(columnCharacter);
//				}
//				output.println();
//				output.format("  |");
//
//				/* Print the second row of full alphabets */
//				for(int k = 0; k < map.getNumColumns() / 26; k++){
//					for(int p = 0; p < 26; p++){
//						output.print((char) ('A' + p));
//					}
//				}
//
//				/* Print the remaining characters for the last alphabet */
//				for(int k = 0; k < map.getNumColumns() % 26; k++){
//					output.print((char) ('A' + k));
//				}
//
//			} else {
//				/* If the number of columns is less than 26,
//				 * a second row is not needed.
//				 */
//				output.format("  |");
//				for(int c = 0; c < map.getNumColumns(); c++){
//					output.format("%c", (char) (c + 65));
//				}
//			}
//
//
//			output.println();
//			output.format("--+");
//			for(int c = 0; c < map.getNumColumns(); c++){
//				output.format("-");
//			}
//			output.println();
//
//			/* Print the current viewable symbol for each row */
//			char charMap[][] = map.getCharMap();
//			for(int r = 0; r < map.getNumRows(); r++){
//				output.format("%02d|", r + 1);
//				for(int c = 0; c < map.getNumColumns(); c++){
//					output.format("%c", charMap[r][c]);
//				}
//				output.println();
//			}
//		} catch (FileNotFoundException e) {
//			System.out.println("Invalid path");
//		} finally {
//			if(output != null)
//				output.close();
//		}
//	}
//
	
	/**
	 * For public static int columnToIndex
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
	 * For public static String indexToColumn
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

	public static void exit(){
		System.exit(0);
	}
}