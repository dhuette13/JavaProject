package archeologyp1.mpt;

import java.io.File;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import archeologyp1.shared.Map;
import archeologyp1.shared.Utilities;
//import java.io.File;
/**
 * Main Class for Map Population Tool
 * @author Daniel
 *
 */
public class EntryPoint {
	Scanner input;
	int selection;
	boolean flag;
	String path;
	Map map;
	int row, col, width, height;
	
	public EntryPoint(){
		input = new Scanner(System.in);
		flag = true;
	}

	public void go(){
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
							//File f = new File(path);
							//if(f.exists() == false)
							//{
							//   throw new FileNotFoundException("This file cannot be found. Please try again.");
							//}
							//probably need an else-if and an else in here too, need to think more on what they'd do though
							map = Utilities.load(path);
							if(map != null)
								flag = false;
							break;
						case 2:
							System.out.print("Enter the desired dimensions (width height): " );
							// Check format of the user's input? (width height) or (width, height) 
							width = input.nextInt();
							height = input.nextInt();
							map = Utilities.generateMap(width, height);
							if(map != null)
								flag = false;
							break;
						default:
							System.out.println("Invalid Choice, Try again.");
					}
				} catch (InputMismatchException e){
					System.out.println("Incorrect input, try again.");
					input.nextLine();
				}
			} while(flag);
			
		/* Print map to standard out */
		Utilities.printMap(map, System.out);
		
		/* Export map to new file */
		try{
			Utilities.printMap(map, new PrintStream(new File("resources/export1.txt")));
		} catch (Exception e){
			System.err.println("Could not create file");
			e.printStackTrace();
		}
		System.out.println("Goodbye");
		
//		flag = true;
//		while(flag){
//			System.out.println();
//			
//		}
		
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
