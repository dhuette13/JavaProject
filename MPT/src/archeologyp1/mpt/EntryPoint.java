package archeologyp1.mpt;

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
			case 1:
				System.out.print("Enter path name: ");
				path = input.next();
				map = Utilities.load(path);
				flag = false;
				break;
			case 2:
				System.out.print("Enter the desired dimensions (x, y): " );
				x = input.nextInt();
				y = input.nextInt();
				flag = false;
				break;
			default:
				System.out.println("Try again.");
				
			}
		} while(flag);
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