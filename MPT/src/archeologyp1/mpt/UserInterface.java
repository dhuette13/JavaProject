package archeologyp1.mpt;

import java.util.ArrayList;
import java.util.Scanner;

import archeologyp1.shared.Map;

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
	private int row;
	private String col;
	private int feature, amountFinds, findType;
	private boolean featureFlag = false, findFlag = false;
	
	private ArrayList <Integer> dateList = new ArrayList<>();

	private MapEditor mapEditor;
	private Scanner input;
	private Map map;

	public UserInterface(MapEditor mapEditor, Map map){
		this.mapEditor = mapEditor;
		this.map = map;
		input = new Scanner(System.in);
	}

	public void pollFeatureorFind(){
		int date;

		System.out.println("\t1) Change a Feature");
		System.out.println("\t2) Change a Find");
		System.out.print("::> ");
		selection = input.nextInt();
		switch(selection){
		/* Feature */
		case 1:
			featureFlag = true;
			System.out.println("\t\t1) Natural Surface");
			System.out.println("\t\t2) Stone");
			System.out.println("\t\t3) Post Hole");
			System.out.print("::> ");
			feature = input.nextInt();
			break;
			/* Find */
		case 2:
			findFlag = true;
			System.out.println("What find would you like to add?");
			System.out.println("1 ) A Pot");
			System.out.println("2 ) Charcoal");
			System.out.println("3 ) A Metal Object");
			System.out.println("::> ");
			findType = input.nextInt();

			System.out.println("How many of these finds would you like to have? Please input an integer.");
			System.out.println("::> ");
			amountFinds = input.nextInt();
			for(int i = 0; i < amountFinds; i++){
				System.out.println("What date would you like your " + (i+1) + " find to have?");
				System.out.print("::> ");
				date = input.nextInt();
				dateList.add(date);
			}
			break;
		default:
			System.out.println("Invalid input.");
		}
	}

	public void updateCoordinate(){
		if(featureFlag) {
			mapEditor.changeFeature(row, col, feature);
			featureFlag = false;
		}
		else if(findFlag){
			for(int i = 0; i < amountFinds; i++)
				mapEditor.addFind(row, col, findType, dateList.get(i));
			findFlag = false;
		}
	}
	public void changeCoordinate() {

		boolean flag = true;
		int selection;

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
			col = input.next();
			pollFeatureorFind();
			updateCoordinate();
			break;
			/* Change a whole row */
		case 2: 
			System.out.println("Which row would you like to edit?");
			System.out.println("::> ");
			row = input.nextInt();
			pollFeatureorFind();
			col = "A";
			boolean findTemp = false, featureTemp = false;
			if(featureFlag) featureTemp = true;
			else if(findFlag) findTemp = true;
			for(int i = 0; i < map.getNumColumns(); i++){
				col = Character.toString((char) ('A' + i));
				featureFlag = featureTemp;
				findFlag = findTemp;
				updateCoordinate();
			}
			break;
		default:
			System.out.println("Not a valid option. Please try again.");
		}
	}
}
