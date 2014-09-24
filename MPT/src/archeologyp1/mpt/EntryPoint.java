package archeologyp1.mpt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import archeologyp1.shared.Feature;
import archeologyp1.shared.Map;
import archeologyp1.shared.Utilities;
import archeologyp1.shared.ViewingOption;

/**
 * Main Class for Map Population Tool
 * @author Daniel
 *
 */
 
public class EntryPoint {
	Scanner input;
	int selection;
	int userCoord;
	int userView;
	boolean flag;
	String path;
	Map map;
	UserInterface ui;
	MapEditor mapEditor;
	int row, col, width, height;
	ViewingOption option;

	public EntryPoint(){
		input = new Scanner(System.in);
		handleLoad();
		mapEditor = new MapEditor(map);
		ui = new UserInterface(mapEditor);
		flag = true;
	}

	public void go(){
		handleMenu();
	}

	private void handleLoad(){
		flag = true;
		do{
			try{
				System.out.println("1 ) Load Map ");
				System.out.println("2 ) Generate Map ");
				System.out.print("::> ");
				selection = input.nextInt();
				switch(selection){ 
				case 1:
					System.out.print("Enter path name: ");
					path = input.next();
					map = Utilities.load(path);
					if(map != null)
						flag = false;
					break;
				case 2:
					System.out.print("Enter the desired dimensions (width height): " );
					width = input.nextInt();
					height = input.nextInt();
					map = Utilities.generateMap(width, height);
					if(map != null)
						flag = false;
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			} catch (InputMismatchException e){
				System.out.println("Incorrect input, try again.");
				input.nextLine();
			}
		} while(flag);
	}

	private void handleMenu(){
		flag = true;
		while(flag) {
			Utilities.printMap(map, System.out);
			System.out.println("Please pick what you would like to do.");
			System.out.println("1 ) Change a coordinate");
			System.out.println("2 ) Change a viewing symbol");
			System.out.println("3 ) Export the map");
			System.out.println("4 ) Save the map");
			System.out.println("0 ) Exit");
			System.out.println("::> ");
			try{
				selection = input.nextInt();
				
				switch(selection){
				/* Edit a coordinate or row of coordinates */
				case 1:
					ui.changeCoordinate();
					break;
					/* Change the Viewing Options */
				case 2:                		
//					ui.changeViewing();
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
						System.out.print("::> ");
						selection = input.nextInt();
						System.out.print("\tEnter symbol to change to: ");
						symbol = input.next().charAt(0);
						switch(selection){
						case 1:
							map.updateView(Feature.dirt, symbol);
							break;
						case 2:
							map.updateView(Feature.postHole, symbol);
							break;
						case 3:
							map.updateView(Feature.stone, symbol);
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
				case 3:
					System.out.print("Enter a path name to export to: ");
					path = input.next();
					Utilities.printMap(map, new PrintStream(new File(path)));
					break;
					/* Save map */
				case 4: 
					break;
					/* Exit */
				case 0:
					System.out.println("Goodbye. ");
					flag = false;
					break;
				default:
					System.out.println("That is not a valid choice. Please choose again.");
				}
				
				// 1) Change a coordinate
				//  a) Change a single coordinate
				//  b) Change a row
				//    - wizard: feature, viewing symbol, excavated?, find 
				// 3) Change a viewing symbol
				//  a) Whole map
				//  b) Group of symbols
				// 4) Print map
				// 5) Export map
				// 6) Save map
				System.out.println();
			} catch(InputMismatchException e){
				System.out.println("Invalid Input");
			} catch (FileNotFoundException e) {
				System.out.println("Invalid file name");
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
}
