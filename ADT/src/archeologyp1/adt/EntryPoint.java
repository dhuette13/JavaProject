package archeologyp1.adt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import archeologyp1.shared.Map;
import archeologyp1.shared.Utilities;

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

	public EntryPoint(){
		input = new Scanner(System.in);
		toolBag = new ToolBag();
	}

	public void go(){
		handleLoad();
		Utilities.printMap(map, System.out);

		flag = true;
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
		while(flag){
			System.out.println("Please pick what you would like to do.");
			System.out.println("1 ) Survey an Area");
			System.out.println("2 ) Dig an Area");
			System.out.println("3 ) Find Average of Found Dates");
			System.out.println("4 ) Change a Viewing Option");
			System.out.println("5 ) Print the map");
			System.out.println("6 ) Export the map");
			System.out.println("0 ) Exit");
			try{
				System.out.println("::> ");
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
						column = input.next().charAt(0);
						toolBag.visibleSpectrum(row, column);
						break;
					/* Metal Detector */
					case 2:
						System.out.println("\tPlease specify a row and column.");
						System.out.print("::> ");
						row = input.nextInt();
						column = input.next().charAt(0);
						toolBag.metalDetector(row, column);
						break;
					/* Magnetometer */
					case 3:
						System.out.println("\tPlease specify a row and column.");
						System.out.print("::> ");
						row = input.nextInt();
						column = input.next().charAt(0);
						toolBag.magnetoMeter(row, column);
						break;
					default:
						System.out.println("Invalid Selection.");
					}
					break;
					/* Dig an Area */
				case 2:
					System.out.println("Please specify a row and column.");
					System.out.print("::> ");
					row = input.nextInt();
					column = input.next().charAt(0);
					toolBag.dig(row, column);
					break;
					/* Find Average of Found Dates */
				case 3:
					average = toolBag.computeAverageDate();
					System.out.println("The average was: " + average);
					break;
					/* Change Viewing Option */
				case 4:
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
