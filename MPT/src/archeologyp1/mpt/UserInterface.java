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
	private Map map;
	private int selection;

	private MapEditor mapEditor;
	private Scanner input;
	
	public UserInterface(MapEditor mapEditor){
		this.mapEditor = mapEditor;
		input = new Scanner(System.in);
	}

	public void addFeatureorFind(int row, String col){
		Scanner input = new Scanner(System.in);
		int findType, findCollection;
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
			
			//how to parameter
			mapEditor.changeFeature(row, col, selection);
			
			break;
			
		/* Find */
		case 2:
			
			System.out.println("What find would you like to add?");
			System.out.println("1 ) A pot");
			System.out.println("2 ) Charcoal");
			System.out.println("3 ) A metal object");
			System.out.println("::> ");
			findType = input.nextInt();
			
			System.out.println("How many of these finds would you like to have? Please input an integer.");
			System.out.println("::> ");
			findCollection = input.nextInt();
			for(int i=0; i<amount; i++){
				System.out.println("What date would you like your " + (i+1) + " find to have?");
				System.out.println("::> ");
				date = input.nextInt();
				mapEditor.changeDate(row, col, findType, findCollection, date);
			}

			//how to parameter
			
			break;
			
		default:
			System.out.println("Invalid input.");
		}
	}
	
	
	
	
	
	
	public void changeCoordinate() {
		boolean bool = true;
		int selection;
		char c = 'A';
		char cUpper = 'A';
		int r1 = 0;
		int check1, check2; 
		int r2 = 0;
		int amountFinds, oneFind, oneDate, twoFind1, twoDate1, twoFind2, twoDate2;
		int threeFind1, threeDate1, threeFind2, threeDate2, threeFind3, threeDate3;

		System.out.println("What would you like to do?");
		System.out.println("1 ) Change just one coordinate");
		System.out.println("2 ) Change an entire row");
		System.out.print("::> ");
		selection = input.nextInt();

		// Change Feature?
		// Change Finds?
		switch(selection){
		/* Change a single coordinate */
		case 1: 		// Figuring out the specific coordinate the user wants to change

			System.out.println("What column?");
			System.out.println("::> ");
			c = input.next().charAt(0);
			cUpper = Character.toUpperCase(c);
			System.out.println("What row?");
			System.out.println("::> ");
			r1 = input.nextInt();

			addFeatureorFind(r1, cUpper);

			//********************************** #2 in notepad
			
			break;
			
		/* Change a whole row */
		case 2: 
			bool = true;

			while(bool){

				System.out.println("Which row would you like to edit?");
				System.out.println("::> ");
				r2 = input.nextInt();

				System.out.println("The row you entered to edit is: " + r2);
				System.out.println("If correct, please enter 1. If not, please hit 0.");
				System.out.println("::> ");
				check2 = input.nextInt();

				if(check2 == 1){
					bool = false;   
				}
				else if(check2 == 0){
					continue;
				}
				else {
					System.out.println("This input is incorrect. Please try again.");
					continue;
				}
			}
			mapEditor.editRow(r1);
			break;
		default:
			System.out.println("Not a valid option. Please try again.");

		}
	}
	
	
	
	
	
	

	public void changeViewing(){
		int amountFinds;
		char y, g1, g2, n, s, p ,d;
		char symbolChange;
		int oneFind, twoFind1, twoFind2, threeFind1, threeFind2, threeFind3;

		Scanner input = new Scanner(System.in);
		// Inputting the amount of finds
		System.out.println("How many finds would you like to have in this coordinate? You can have 0 finds (meaning none), up to all 3 finds.");
		System.out.println("::> ");
		amountFinds = input.nextInt();
		
		//"Prompt the user to enter the type of find and the date, and then add that find in the appropriate collection."
		switch(amountFinds){ 
		case 0:
			break;
		case 1:
			System.out.println("What type of find would you like to enter?");
			System.out.println("1. Pottery");
			System.out.println("2. Metal Work");
			System.out.println("3. Charcoal");
			System.out.println("::> ");
			oneFind = input.nextInt();
			break;
		case 2:
			System.out.println("What type of find would you like to enter first?");
			System.out.println("1. Pottery");
			System.out.println("2. Metal Work");
			System.out.println("3. Charcoal");
			System.out.println("::> ");
			twoFind1 = input.nextInt();
			
			System.out.println("What type of find would you like to enter last?");
			System.out.println("1. Pottery");
			System.out.println("2. Metal Work");
			System.out.println("3. Charcoal");
			System.out.println("::> ");
			twoFind2 = input.nextInt();
			
			break;
			
		case 3:
			System.out.println("What type of find would you like to enter first?");
			System.out.println("1. Pottery");
			System.out.println("2. Metal Work");
			System.out.println("3. Charcoal");
			System.out.println("::> ");
			threeFind1 = input.nextInt();
			
			System.out.println("What type of find would you like to enter second?");
			System.out.println("1. Pottery");
			System.out.println("2. Metal Work");
			System.out.println("3. Charcoal");
			System.out.println("::> ");
			threeFind2 = input.nextInt();
			
			System.out.println("What type of find would you like to enter last?");
			System.out.println("1. Pottery");
			System.out.println("2. Metal Work");
			System.out.println("3. Charcoal");
			System.out.println("::> ");
			threeFind3 = input.nextInt();
			break;
			
		default: 
			System.out.println("Not a valid input. Please try again.");
		}
		
	}

	
	public static void rowCoord(int row2){
		
		Scanner input = new Scanner(System.in);
		int changeRow;
		System.out.println("What row would you like to change?");
		System.out.println("::> ");
		changeRow = input.nextInt();
	}
}
