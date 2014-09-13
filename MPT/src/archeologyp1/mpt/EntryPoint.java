package archeologyp1.mpt;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
//import java.io.File;

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
			System.out.println("1 ) Load Map ");
			System.out.println("2 ) Generate Map ");
			System.out.print("::> ");
			selection = input.nextInt();
			switch(selection){ 
			case 1: //need to make sure they enter an existing path name, maybe by exists();
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
				System.out.print("Enter the desired dimensions (x, y): " );
				width = input.nextInt();
				height = input.nextInt();
				map = Utilities.generateMap(width, height);
				//more error checking is probably needed here
				if(map != null)
					flag = false;
				break;
			default:
				System.out.println("Invalid Choice, Try again.");
				
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
