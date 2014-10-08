package archeologyp1.mpt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import archeologyp1.shared.Coordinate;
import archeologyp1.shared.Map;
import archeologyp1.shared.MapEditor;
import archeologyp1.shared.Utilities;
import archeologyp1.shared.ViewingOption;

/**
 * ENTRY POINT FOR THE MAP POPULATION TOOL
 * @author Daniel
 * @author Celine
 * 
 * This class includes the main method for the MPT, as well
 * as the initial user menu. 
 *
 */

public class EntryPoint {
	int selection;
	boolean flag;
	String path;
	Map<Coordinate> map;
	UserInterface ui;
	int row, col, width, height;
	Scanner input;
	ViewingOption option;

	/**
	 * 
	 * For the public EntryPoint method
	 * This method handles the objects that will be 
	 * used in this class.
	 * 
	 */
	public EntryPoint(){
		input = new Scanner(System.in);
		handleLoad();
		ui = new UserInterface(map);
		MapEditor.updateView(map);
		flag = true;
	}

	public void go(){
		handleMenu();
	}

	/**
	 * 
	 * For the public void handleLoad method
	 * This method handles the initial menu for the entire project. 
	 * It runs on a do-while loop that will continue going until 
	 * the boolean is set to false.
	 * 
	 */
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
					System.out.print("Enter the desired dimensions (height width): " );
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

	/**
	 * 
	 * For the private void handleMenu method
	 * 
	 * This method handles the menu that runs right after a map is
	 * either loaded or generated by the user. It offers
	 * a diverse selection of things to do to the map, including
	 * editing the map, changing the viewing symbols, exporting the map,
	 * saving the map, and counting the number of finds. 
	 * 
	 */
	private void handleMenu(){
		flag = true;
		while(flag) {
			try{
				Utilities.printMap(map, System.out);
				System.out.println("Please pick what you would like to do.");
				System.out.println("1 ) Edit the map");
				System.out.println("2 ) Change viewing symbols");
				System.out.println("3 ) Export the map");
				System.out.println("4 ) Save the map");
				System.out.println("5 ) Count number of Finds");
				System.out.println("0 ) Exit");
				System.out.print("::> ");
				selection = input.nextInt();
				switch(selection){
				/* Edit a coordinate or row of coordinates */
				case 1:
					ui.changeCoordinate();
					break;
				/* Change the Viewing Options */
				case 2:                		
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
							map.setDirtAlias(symbol);
							break;
						case 2:
							map.setPostHoleAlias(symbol);
							break;
						case 3:
							map.setStoneAlias(symbol);
							break;
						case 4:
							map.setDirtSymbol(symbol);
							break;
						case 5:
							map.setPostHoleSymbol(symbol);
							break;
						case 6:
							map.setStoneSymbol(symbol);
							break;
						default:
							System.out.println("Invalid selection");
						}
						MapEditor.updateView(map);
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
						MapEditor.updateView(map);
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
					System.out.print("Enter a path name to save to: ");
					path = input.next();
					Utilities.save(map, path);
					break;
				/* Number of finds */
				case 5:
					System.out.print("Number of finds in map: " + MapEditor.countNumberOfFinds(map));
					break;
				/* Exit */
				case 0:
					System.out.println("Goodbye. ");
					flag = false;
					break;
				default:
					System.out.println("That is not a valid choice. Please choose again.");
				}
				System.out.println();
			} catch(InputMismatchException e){
				System.out.println("Invalid Input");
				input = new Scanner(System.in);
			} catch (FileNotFoundException e) {
				System.out.println("Invalid file name");
			} catch (ArrayIndexOutOfBoundsException e){
				System.out.println("Row or Column entered were incorrect. Try agian");
				input = new Scanner(System.in);
			}
			System.out.println();
		}
	}

	/**
	 * 
	 * For the public static void main method
	 * @param args
	 * 
	 * This method starts the entire program. 
	 * 
	 */
	public static void main(String[] args) {
		EntryPoint entry = new EntryPoint();
		entry.go();
	}
}
