package archeologyp1.mpt;

import java.util.Scanner;

import archeologyp1.shared.Coordinate;
import archeologyp1.shared.Map;
import archeologyp1.shared.Utilities;

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

	public static void changeCoordinate(int OR){
		Scanner input = new Scanner(System.in);
		boolean bool = true;
		int a;
		char c = 'A';
		int r1 = 0;
		int check1, check2; 
		int r2 = 0;

		System.out.println("What would you like to do?");
		System.out.println("1 ) Change just one coordinate");
		System.out.println("2 ) Change an entire row");
		System.out.println("::> ");
		a = input.nextInt();

		switch(a){
		case 1: 		// Figuring out the specific coordinate the user wants to change

			while(bool){
				System.out.println("What column?");
				System.out.println("::> ");
				c = input.next().charAt(0);

				System.out.println("What row?");
				System.out.println("::> ");
				r1 = input.nextInt();

				System.out.println("The following coordinate is what you want to change: "+c+", "+r);
				System.out.println("If this is correct, please hit 1. If it is not, please hit 0.");
				System.out.println("::> ");
				check1 = input.nextInt();

				if(check1 == 1){
					bool = false;
				}
				else if(check1 == 0){
					continue;
				}
				else{
					System.out.println("This input is not correct. Please try again.");
					continue;
				}
			}
			singleCoord(c, r);

		case 2: 		//Figuring out the row the user wants to change on the map
			bool = true;

			while(bool){

				System.out.println("Which row would you like to edit?");
				System.out.println("::> ");
				r2 = input.nextInt();

				System.out.println("The row you entered to edit is: "+r2);
				System.out.println("If correct, please enter 1. If not, please hit 0.");
				System.out.println("::> ");
				check2 = input.nextInt();

				if(check2 == 1){
					bool = false;   
				}
				else if(check2 == 0){
					continue;
				}
				else{
					System.out.println("This input is incorrect. Please try again.");
					continue;
				}
			}
			rowCoord(r1);

		default:
			System.out.println("Not a valid option. Please try again.");

		}
	}

	public static void changeViewing(){
		int changeMap;
		char y, g1, g2, n, s, p ,d;
		char symbolChange;
		
		Scanner input = new Scanner(System.in);
		System.out.println("What would you like to change?");
		System.out.println("1 ) The whole map");
		System.out.println("2 ) A group of symbols");
		System.out.println("::> ");
		changeMap = input.nextInt();
		
		switch(changeMap){
		case 1:
			
			System.out.println("What would you like to switch 'g' with?");
			g1 = input.next().charAt(0);
			
			System.out.println("What would you like to switch 'Y' with?");
			y = input.next().charAt(0);
			
			System.out.println("What would you like to switch 'G' with?");
			g2 = input.next().charAt(0);
			
			System.out.println("What would you like to switch 'N' with?");
			n = input.next().charAt(0);
			
			System.out.println("What would you like to swtich 'S' with?");
			s = input.next().charAt(0);
			
			System.out.println("What would you like to switch 'P' with?");
			p = input.next().charAt(0);
			
			System.out.println("What would you like to switch 'D' with?");
			d = input.next().charAt(0);
			
			break;
			
		case 2:
			
			System.out.println("Which symbol would you like to change?");
			symbolChange = input.next().charAt(0);
			
			switch(symbolChange){
			case 'Y':
				System.out.println("What symbol would you like to change "+symbolChange+" to?");
				break;
			case 'G':
				break;
			case 'N':
				break;
			case 'S':
				break;
			case 'P':
				break;
			case 'D':
				break;
			case 'g':
				break;
			default:
				break;
			}
			
			break;
			
		default:
			
			System.out.println("");
			
		}

	}

	public static void singleCoord(char col, int row1){
		//ask person about features, finds, excavated, date, etcetc
		
		Coordinate setSingle;
		char newSymbol;
		int amountFinds;
		
		System.out.println("What would you like to change the viewing symbol to?");
		System.out.println("Y: Parched, yellow vegetation");
		System.out.println("G: Wet, bright green vegetation");
		System.out.println("N: Natural green vegetation");
		System.out.println("::> ");
		newSymbol = input.next().charAt(0);
		
		switch(newSymbol){
		case 'Y':
			setSingle = map.plane[row1 - 1][(int) (col - 'A')];
			setSingle.setFeatureSymbol(newSymbol);
		case 'G': 
			setSingle = map.plane[row1 - 1][(int) (col - 'A')];
			setSingle.setFeatureSymbol(newSymbol);
		case 'N':
			setSingle = map.plane[row1 - 1][(int) (col - 'A')];
			setSingle.setFeatureSymbol(newSymbol);
		default: 
			System.out.println("The change you want to make is not possible. Please try again.");
		}
		
		// Inputting the amount of finds
		System.out.println("How many finds would you like to have in this coordinate? You can have 0 finds (meaning none), up to all 3 finds.");
		System.out.println("::> ")
		amountFinds = input.nextInt();
		
		//"Prompt the user to enter the type of find and the date, and then add that find in the appropriate collection."
		switch(amountFinds){ 
		case 0:
			break;
		case 1:
			System.out.println("What type of find would you like to enter?");
			System.out.println("");
			break;
		case 2:
			System.out.println("");
			break;
		case 3:
			System.out.println("");
			break;
		default: 
			System.out.println("Not a valid input. Please try again.")
		}
		
	}

	public static void rowCoord(int row2){

	}
}
