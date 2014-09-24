package archeologyp1.mpt;

import java.util.Scanner;

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

	private MapEditor mapEditor;
	private Scanner input;

	public UserInterface(MapEditor mapEditor){
		this.mapEditor = mapEditor;
		input = new Scanner(System.in);
	}

	public void changeCoordinate() {
		boolean bool = true;
		int selection;
		char c = 'A';
		int r1 = 0;
		int check1, check2; 
		int r2 = 0;

		System.out.println("What would you like to do?");
		System.out.println("1 ) Change just one coordinate");
		System.out.println("2 ) Change an entire row");
		System.out.println("::> ");
		selection = input.nextInt();

		switch(selection){
		/* Change a single coordinate */
		case 1: 		// Figuring out the specific coordinate the user wants to change

			while(bool){
				System.out.println("What column?");
				System.out.println("::> ");
				c = input.next().charAt(0);

				System.out.println("What row?");
				System.out.println("::> ");
				r1 = input.nextInt();

				System.out.println("The following coordinate is what you want to change: "+c+", "+r1);
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
			mapEditor.editSingleCoordinate(r1, c);
			break;
		/* Change a whole row */
		case 2: 
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
}
