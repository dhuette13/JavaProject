package archeologyp1.mpt;

import java.util.Scanner; 

import archeologyp1.shared.Map;
import archeologyp1.shared.Coordinate;

// 1) Change a coordinate
// a) Change a single coordinate
// b) Change a row
// - wizard: feature, viewing symbol, excavated?, find
// 3) Change a viewing symbol
// a) Whole map
// b) Group of symbols
// 4) Print map
// 5) Export map
// 6) Save map
public class UserInterface {
	private int selection;

	private MapEditor mapEditor;
	private Scanner input;

	public UserInterface(MapEditor mapEditor){
		this.mapEditor = mapEditor;
		input = new Scanner(System.in);
	}

	public void addFeatureorFind(int row, String col){
		int findType, amount;
		char changeFeature;
		int date;

		System.out.println("\t1) Change a Feature");
		System.out.println("\t2) Change a Find");
		System.out.print("::> ");
		selection = input.nextInt();
		switch(selection){
		/* Feature */
		case 1:
			System.out.println("\t\t1) Natural Surface");
			System.out.println("\t\t2) Stone");
			System.out.println("\t\t3) Post Hole");
			System.out.print("::> ");
			selection = input.nextInt();
			mapEditor.changeFeature(row, col, selection);
			break;
			/* Find */
		case 2:
			System.out.println("What find would you like to add?");
			System.out.println("1 ) A Pot");
			System.out.println("2 ) Charcoal");
			System.out.println("3 ) A Metal Object");
			System.out.println("::> ");
			findType = input.nextInt();

			System.out.println("How many of these finds would you like to have? Please input an integer.");
			System.out.println("::> ");
			amount = input.nextInt();
			for(int i = 0; i < amount; i++){
				System.out.println("What date would you like your " + (i+1) + " find to have?");
				System.out.print("::> ");
				date = input.nextInt();
				mapEditor.changeDate(row, col, findType, amount, date);
			}
			break;
		default:
			System.out.println("Invalid input.");
		}
	}

	public void getCoordinate(){
		
	}
	public void changeCoordinate() {
		boolean flag = true;
		int selection;
		String column;
		int row;

		System.out.println("What would you like to do?");
		System.out.println("1 ) Change just one coordinate");
		System.out.println("2 ) Change an entire row");
		System.out.print("::> ");
		selection = input.nextInt();
		
		switch(selection){
			/* Change a single coordinate */
		case 1:
			System.out.println("\tPlease specify a row and column.");
			System.out.print("::> ");
			row = input.nextInt();
			column = input.next();
			addFeatureorFind(row, column);
			break;
			/* Change a whole row */
		case 2: 
			flag = true;
			while(flag){
				System.out.println("Which row would you like to edit?");
				System.out.println("::> ");
				row = input.nextInt();
				mapEditor.editRow(row);
			}
			break;
		default:
			System.out.println("Not a valid option. Please try again.");
		}
	}
}
