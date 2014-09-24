package archeologyp1.mpt;

import java.io.File;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import archeologyp1.shared.Map;
import archeologyp1.shared.Utilities;

/**
 * Main Class for Map Population Tool
 * @author Daniel
 *
 */
 
public class EntryPoint {
	Scanner input;
	int selection;
	int mapselect1;
	int userCoord;
	int userView;
	boolean flag;
	String path;
	Map map;
	UserInterface ui;
	int row, col, width, height;

	public EntryPoint(){
		handleLoad();
		input = new Scanner(System.in);
		flag = true;
	}

	public void go(){
		/* Print map to standard out */
		Utilities.printMap(map, System.out);
		System.out.println("After print");
		/* Export map to new file */
		try{
			Utilities.printMap(map, new PrintStream(new File("res/export1.txt")));
			System.out.println("After export");
		} catch (Exception e){
			System.err.println("Could not create file");
			e.printStackTrace();
		}
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
			System.out.println("Please pick what you would like to do.");
			System.out.println("1 ) Change a coordinate");
			System.out.println("2 ) Change a viewing symbol"); //Should we do it so that they have to have more than one symbol before they start changing it?
			//We'll also have to make sure we keep track of what they use on the map so we know what to prompt them to change.
			System.out.println("3 ) Print the map");
			System.out.println("4 ) Export the map");
			System.out.println("5 ) Save the map");
			System.out.println("::> ");
			mapselect1 = input.nextInt();
			
			switch(mapselect1){
			case 1:
				//System.out.println("What would you like to change?");
				//System.out.println("1 ) A single coordinate");
				//System.out.println("2 ) An entire row");
				//System.out.println("::> ");
				//userCoord = input.nextInt();
				
				//map = mpt.changeCoordinate(userCoord);
				
				//flag = false;
				break;
			case 2:                		
                //map = mpt.changeViewing(userView);
                		
				//flag = false;
				break;
			case 3:
				//flag = false;
				break;
			case 4: 
				//flag = false;
				break;
			case 5: 
				//flag = false;
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

		}
	}
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EntryPoint entry = new EntryPoint();
		entry.go();
	}

}
