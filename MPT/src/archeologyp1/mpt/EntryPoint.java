package archeologyp1.mpt;

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
	int row, col, x, y;
	
	public EntryPoint(){
		input = new Scanner(System.in);
		flag = true;
	}

	public void go(){
		System.out.println("1 ) Load Map ");
		System.out.println("2 ) Generate Map ");
		System.out.print("::> ");
		do{
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
				flag = false;
				break;
			case 2:
				System.out.print("Enter the desired dimensions (x, y): " );
				x = input.nextInt();
				y = input.nextInt();
				//more error checking is probably needed here
				flag = false;
				break;
			default:
				System.out.println("Try again.");
				
			}
		} while(flag);
		
		Utilities.printMap(map, System.out);
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
