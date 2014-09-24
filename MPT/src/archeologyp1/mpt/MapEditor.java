package archeologyp1.mpt;

import java.util.Scanner;

import archeologyp1.shared.Coordinate;
import archeologyp1.shared.Map;

public class MapEditor {
	
	private Map map;
	
	public MapEditor(Map map){
		this.map = map;
	}
	
	public void editSingleCoordinate(int row, char col){
		//ask person about features, finds, excavated, date, etc etc

		Coordinate setSingle;
		Scanner input = new Scanner(System.in);
		char newSymbol;
		int amountFinds, oneFind, oneDate, twoFind1, twoDate1, twoFind2, twoDate2;
		int threeFind1, threeDate1, threeFind2, threeDate2, threeFind3, threeDate3;

		System.out.println("What would you like to change the viewing symbol to?");
		System.out.println("Y: Parched, yellow vegetation");
		System.out.println("G: Wet, bright green vegetation");
		System.out.println("N: Natural green vegetation");
		System.out.println("::> ");
		newSymbol = input.next().charAt(0);

		switch(newSymbol){
		case 'Y':
			setSingle = map.plane[row - 1][(int) (col - 'A')];
			setSingle.setFeatureSymbol(newSymbol);
		case 'G': 
			setSingle = map.plane[row - 1][(int) (col - 'A')];
			setSingle.setFeatureSymbol(newSymbol);
		case 'N':
			setSingle = map.plane[row - 1][(int) (col - 'A')];
			setSingle.setFeatureSymbol(newSymbol);
		default: 
			System.out.println("The change you want to make is not possible. Please try again.");
		}

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

	public void editRow(int row){
		Scanner input = new Scanner(System.in);
		int changeRow;
		System.out.println("What row would you like to change?");
		System.out.println("::> ");
		changeRow = input.nextInt();


	}
}
