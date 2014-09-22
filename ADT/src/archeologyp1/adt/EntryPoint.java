package archeologyp1.adt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import archeologyp1.shared.Coordinate;
import archeologyp1.shared.Map;
import archeologyp1.shared.Utilities;
import archeologyp1.shared.ViewingOption;

public class EntryPoint {
	Scanner input;
	int selection;
	boolean flag;
	int row;
	char column;
	int average;
	String path;
	Map map;
	ToolBag toolBag;
	Coordinate current;
	ViewingOption option;

	public EntryPoint(){
		input = new Scanner(System.in);
		handleLoad();
		toolBag = new ToolBag(map);
	}

	public void go(){
		/*
		 * 1) Survey
		 * 	a) Light Specturm
		 * 	b) Magnetometer
		 *  c) Metal Detector
		 * 2) Dig
		 * 3) Date Average
		 * 4) Change a Viewing Option
		 * 5) Print
		 * 6) Export
		 */
		flag = true;
		while(flag){
			Utilities.printMap(map, System.out);
			System.out.println("Please pick what you would like to do.");
			System.out.println("1 ) Survey an Area");
			System.out.println("2 ) Dig an Area");
			System.out.println("3 ) Find Average of Found Dates");
			System.out.println("4 ) Change a Viewing Option");
			System.out.println("5 ) Print the map");
			System.out.println("6 ) Export the map");
			System.out.println("0 ) Exit");
			try{
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
						column = Character.toUpperCase(input.next().charAt(0));
						toolBag.visibleSpectrum(row, column);
						map.updateView();
						break;
						/* Metal Detector */
					case 2:
						System.out.println("\tPlease specify a row and column.");
						System.out.print("::> ");
						row = input.nextInt();
						column = Character.toUpperCase(input.next().charAt(0));
						toolBag.metalDetector(row, column);
						map.updateView();
						break;
						/* Magnetometer */
					case 3:
						System.out.println("\tPlease specify a row and column.");
						System.out.print("::> ");
						row = input.nextInt();
						column = Character.toUpperCase(input.next().charAt(0));
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
					column = Character.toUpperCase(input.next().charAt(0));
					toolBag.dig(row, column);
					break;
					/* Find Average of Found Dates */
				case 3:
					average = toolBag.computeAverageDate();
					System.out.println("The average was: " + average);
					break;
					/* Change Viewing Option */
				case 4:
					System.out.println("\t1) Change individual element");
					System.out.println("\t2) Change whole map");
					System.out.print("::> ");
					selection = input.nextInt();
					switch(selection){
					/* Switch an individual element */
					case 1:
						System.out.println("\tPlease specify a row and column.");
						System.out.print("::> ");
						row = input.nextInt();
						column = Character.toUpperCase(input.next().charAt(0));
						System.out.println("\tEnter a character to change to.");
						System.out.print("::> ");
						char symbol = input.next().charAt(0);
						
						current = map.plane[row - 1][(int) (column - 'A')];
						current.setFeatureSymbol(symbol);
						current.setCurrentViewableSymbol();
						break;
					/* Change the map's viewing option*/
					case 2:
						System.out.println("\t\t1) Natual option");
						System.out.println("\t\t2) Readable option");
						System.out.println("\t\t3) PotCount option");
						System.out.println("\t\t4) MetalCount option");
						System.out.println("\t\t5) CharcoalCount option");
						System.out.println("\t\t6) Detector Results option");
						System.out.print("::> ");
						selection = input.nextInt();
						switch(selection){
						case 1:
							option = ViewingOption.natural;
							break;
						case 2:
							option = ViewingOption.readable;
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
							option = ViewingOption.detectorResult;
							break;
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
					/* Print map */
				case 5:
					Utilities.printMap(map, System.out);
					break;
					/* Export map */
				case 6:
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
				e.printStackTrace();
				System.out.println("Input was invalid.");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("Error creating file");
			}
		}
	}
	/**
	 * @param args
	 */

	public static void main(String[] args) {
		EntryPoint entry = new EntryPoint();
		entry.go();
	}

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
