package archeologyp2.mpt;

import java.util.ArrayList;  
import java.util.InputMismatchException;
import java.util.Scanner;

import archeologyp2.shared.map.Coordinate;
import archeologyp2.shared.map.Map;
import archeologyp2.shared.map.Utilities;

/**
 * USERINTERFACE FOR THE MAP POPULATION TOOL
 * @author Daniel
 * @author Celine
 * 
 * This class handles most of the UI for the
 * mpt, which includes adding a feature 
 * or a find, updating a coordinate, and changing
 * a coordinate based on a single coordinate or
 * an entire row.
 *
 */

public class UserInterface {
	private int selection;
	private int row;
	private String col;
	private int feature, amountFinds, findType;
	private boolean featureFlag = false, findFlag = false;
	
	private ArrayList <Integer> dateList = new ArrayList<>();

	private SubController controller;
	private Scanner input;
	private Map<Coordinate> map;

	/**
	 * 
	 * For the public UserInterface method
	 * @param the mapEditor object
	 * @param the map object
	 * 
	 */
	public UserInterface(Map<Coordinate> map){
		this.map = map;
		input = new Scanner(System.in);
		controller = new SubController(map);
	}

	/**
	 * 
	 * For the public void pollFeatureorFind method
	 * 
	 * This method asks whether the user wants to change a
	 * feature or add a find to the map. If they want to change a 
	 * feature, the menu will ask them for the option (among the
	 * unexcavated options available) they want to change it 
	 * to, or whether they want to add a (collection of) find(s).
	 * If they want to add a find, the menu will then push them
	 * into a loop based on how many finds they want to add, 
	 * and make them add the dates for each find.
	 * 
	 */
	public void pollFeatureorFind(){
		int date;

		System.out.println("\t1) Change a Feature");
		System.out.println("\t2) Add a Find");
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
			System.out.print("::> ");
			findType = input.nextInt();

			System.out.println("How many of these finds would you like to have? Please input an integer.");
			System.out.print("::> ");
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

	/**
	 * 
	 * For the public void updateCoordinate method
	 * 
	 * This method updates the coordinate in the 
	 * map that's been changed.
	 * 
	 */
	public void updateCoordinate(){
		if(featureFlag) {
			controller.changeFeature(row, col, feature);
			featureFlag = false;
		}
		else if(findFlag){
			for(int i = 0; i < amountFinds; i++)
				controller.addFind(row, col, findType, dateList.get(i));
			
			findFlag = false;
		}
	}
	
	/**
	 * 
	 * For the public void changeCoordinate method
	 * 
	 * This method asks what type of change to the menu
	 * the user wants to make: a single coordinate, or 
	 * an entire row on the map. 
	 * 
	 */
	public void changeCoordinate() {
		int selection;
		try{
			System.out.println("What would you like to do?");
			System.out.println("1 ) Change just one coordinate");
			System.out.println("2 ) Change an entire row");
			System.out.print("::> ");
			selection = input.nextInt();
			
			switch(selection){
			/* Change a single coordinate */
			case 1:
				System.out.println("\tPlease specify a row and column. (Example: 4 d)");
				System.out.print("::> ");
				row = input.nextInt();
				col = input.next();
				pollFeatureorFind();
				updateCoordinate();
				break;
			/* Change a whole row */
			case 2: 
				System.out.println("Which row would you like to edit? (Example: b)");
				System.out.println("::> ");
				row = input.nextInt();
				pollFeatureorFind();
				col = "A";
				boolean findTemp = false, featureTemp = false;
				if(featureFlag) featureTemp = true;
				else if(findFlag) findTemp = true;
				for(int i = 0; i < map.getNumColumns(); i++){
					col = Utilities.indexToColumn(i);
					featureFlag = featureTemp;
					findFlag = findTemp;
					updateCoordinate();
				}
				break;
			default:
				System.out.println("Not a valid option. Please try again.");
			}
		} catch(InputMismatchException e){
			System.out.println("Invalid choice");
			input = new Scanner(System.in);
		} catch(Exception e){
			System.out.println("Invalid input");
			input = new Scanner(System.in);
		}
	}
}
