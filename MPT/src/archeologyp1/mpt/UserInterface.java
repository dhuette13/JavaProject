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

	// changeCoord case 1 variables
	// changeCoord case 2 variables


	public static void changeCoordinate(int OR){
		Scanner input = new Scanner(System.in);
		boolean bool = true;
		int a, a2, a3;
		char c = 'A';
		int r = 0;
		int check1, check2; 
		int r1 = 0;

		System.out.println("What would you like to do?");
		System.out.println("1 ) Change just one coordinate");
		System.out.println("2 ) Change an entire row");
		System.out.println("::> ");
		a = input.nextInt();

		switch(a){
		case 1:

			while(bool){
				System.out.println("What column?");
				System.out.println("::> ");
				c = input.next().charAt(0);

				System.out.println("What row?");
				System.out.println("::> ");
				r = input.nextInt();

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

		case 2: 
			bool = true;

			while(bool){

				System.out.println("Which row would you like to edit?");
				System.out.println("::> ");
				r1 = input.nextInt();

				System.out.println("The row you entered to edit is: "+r1);
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
		int selection;
		Scanner input = new Scanner(System.in);
		System.out.println("What would you like to change?");
		System.out.println("1 ) The whole map");
		System.out.println("2 ) A group of symbols");
		System.out.println("::> ");
		selection = input.nextInt();

	}

	public static void singleCoord(char col, int row1){

	}

	public static void rowCoord(int row2){

	}

}
