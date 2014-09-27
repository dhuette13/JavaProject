package archeologyp1.adt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import archeologyp1.shared.Coordinate;
import archeologyp1.shared.Feature;
import archeologyp1.shared.Map;
import archeologyp1.shared.Utilities;
import archeologyp1.shared.ViewingOption;

/**
 * ENTRY POINT OF THE ARCHAEOLOGICAL DIG TOOL 
 * @author Daniel
 * @author Celine
 * 
 * This class contains the Main method for the ADT. It runs the 
 * user input menu, which asks the user if they'd like to 
 * survey, which tool they'd like to use if they do survey, 
 * if they'd like to excavate, if they'd like to see the date
 * average (with the bonus of standard deviation) of the finds 
 * they have, change the viewing option, print, or export. 
 *
 */
public class EntryPoint {
	Scanner input;
	int selection;
	boolean flag;
	int row;
	String column;
	double average;
	String path;
	Map map;
	ToolBag toolBag;
	Coordinate current;
	ViewingOption option;

	/**
	 * 
	 */
	public EntryPoint(){
		input = new Scanner(System.in);
		handleLoad();
		toolBag = new ToolBag(map);
	}

	/**
	 * This is the go-method, which handles most of the user input for the ADT.
	 */
	public void go(){
		flag = true;
		while(flag){
			try{
				Utilities.printMap(map, System.out);
				System.out.println("Please pick what you would like to do.");
				System.out.println("1 ) Survey an Area");
				System.out.println("2 ) Dig an Area");
				System.out.println("3 ) Find Average of Found Dates");
				System.out.println("4 ) Change a Viewing Option");
				System.out.println("5 ) Export the map");
				System.out.println("0 ) Exit");
				System.out.print("::> ");
				selection = input.nextInt();

				switch(selection){
				/* Survey an Area */
				case 1:
					System.out.println("How would you like to scan?");
					System.out.println("\t1) Visible Spectrum");
					System.out.println("\t2) Metal Detector");
					System.out.println("\t3) Magnetometer");
					System.out.print("::> ");
					selection = input.nextInt();
					switch(selection){
					/* Visible Spectrum */
					case 1:
						System.out.println("\tPlease specify a row and column.");
						System.out.print("::> ");
						row = input.nextInt();
						column = input.next();
						toolBag.visibleSpectrum(row, column);
						map.updateView();
						break;
						/* Metal Detector */
					case 2:
						System.out.println("\tPlease specify a row and column.");
						System.out.print("::> ");
						row = input.nextInt();
						column = input.next();
						toolBag.metalDetector(row, column);
						map.updateView();
						break;
						/* Magnetometer */
					case 3:
						System.out.println("\tPlease specify a row and column.");
						System.out.print("::> ");
						row = input.nextInt();
						column = input.next();
						toolBag.magnetoMeter(row, column);
						map.updateView();
						break;
					default:
						System.out.println("\tInvalid Selection.");
					}
					break;
					/* Dig an Area */
				case 2:
					System.out.println("\tPlease specify a row and column.");
					System.out.print("::> ");
					row = input.nextInt();
					column = input.next();
					toolBag.dig(row, column);
					map.updateView();
					break;
					/* Find Average of Found Dates */
				case 3:
					average = toolBag.computeAverageDate();
					double sd = toolBag.computeStandardDeviation(average);
					double minus = average - sd;
					double plus = average + sd;
					
					System.out.format("Average of found dates is %.2f\n", average);
					System.out.format("Standard Deviation is: %.2f\n", sd);
					System.out.format("The average minus standard deviation is %.2f and the average plus the standard deviation is %.2f.\n", minus, plus);
					break;
					/* Change Viewing Option */
				case 4:
					System.out.println("\t1) Change group of elements");
					System.out.println("\t2) Change whole map");
					System.out.print("::> ");
					selection = input.nextInt();
					switch(selection){
					/* Switch an individual element */
					case 1:
						char symbol;
						map.setViewingOption(ViewingOption.userModified);
						System.out.println("\t1) Change Natural Surface");
						System.out.println("\t2) Change Post Hole");
						System.out.println("\t3) Change Stone");
						System.out.println("\t4) Change Excavated Natural Surface");
						System.out.println("\t5) Change Excavated Post Hole");
						System.out.println("\t6) Change Excavated Stone");
						System.out.print("::> ");
						selection = input.nextInt();
						System.out.print("\tEnter symbol to change to: ");
						symbol = input.next().charAt(0);
						switch(selection){
						case 1:
							map.updateView(Feature.dirt, symbol, true);
							break;
						case 2:
							map.updateView(Feature.postHole, symbol, true);
							break;
						case 3:
							map.updateView(Feature.stone, symbol, true);
							break;
						case 4:
							map.updateView(Feature.dirt, symbol, false);
							break;
						case 5:
							map.updateView(Feature.postHole, symbol, false);
							break;
						case 6:
							map.updateView(Feature.stone, symbol, false);
							break;
						default:
							System.out.println("Invalid selection");
						}
						break;
						/* Change the map's viewing option*/
					case 2:
						System.out.println("\t\t1) Natual option");
						System.out.println("\t\t2) User Modified option");
						System.out.println("\t\t3) PotCount option");
						System.out.println("\t\t4) MetalCount option");
						System.out.println("\t\t5) CharcoalCount option");
						System.out.println("\t\t6) Magnetometer Results option");
						System.out.println("\t\t7) Metal Detector Results option");
						System.out.print("::> ");
						selection = input.nextInt();
						switch(selection){
						case 1:
							option = ViewingOption.natural;
							break;
						case 2:
							option = ViewingOption.userModified;
							break;
						case 3:
							option = ViewingOption.potCount;
							break;
						case 4:
							option = ViewingOption.metalCount;
							break;
						case 5:
							option = ViewingOption.charcoalCount;
							break;
						case 6:
							option = ViewingOption.magnetometerResult;
							break;
						case 7:
							option = ViewingOption.metalDetectorResult;
						default:
							System.out.println("\tInvalid Selection");
							break;
						}
						map.setViewingOption(option);
						map.updateView();
						break;
					default:
						System.out.println("\tInvalid selection");
					}
					break;
					/* Export map */
				case 5:
					System.out.print("Enter a path name to export to: ");
					path = input.next();
					Utilities.printMap(map, new PrintStream(new File(path)));
					break;
					/* Exit */
				case 0:
					System.out.println("Thank you. Goodbye");
					flag = false;
					break;
				default:
					System.out.println("Selection not valid. Try again");
				}

			} catch(InputMismatchException e){
				System.out.println("Input was invalid.");
				input = new Scanner(System.in);
			} catch (FileNotFoundException e) {
				System.out.println("Error creating file");
			} catch (ArrayIndexOutOfBoundsException e){
				System.out.println("Specified row or column were not valid. Please try again");
				input = new Scanner(System.in);
			}
			System.out.println();
		}
	}
	
	/**
	 * @param args
	 * 
	 * This method creates an object, and 
	 * begins the class. 
	 * 
	 */
	public static void main(String[] args) {
		EntryPoint entry = new EntryPoint();
		entry.go();
	}

	/**
	 * This method handles the user input for the file and
	 * continues looping if the file path the user specifies
	 * isn't there.
	 */
	private void handleLoad(){
		flag = true;
		while(flag){
			try{
				System.out.print("Enter path name of file to load: ");
				path = input.next();
				map = Utilities.load(path);
				if(map != null)
					flag = false;
			} catch (InputMismatchException e){
				System.out.println("Incorrect input, try again.");
				input.nextLine();
			}
		}
	}

}
