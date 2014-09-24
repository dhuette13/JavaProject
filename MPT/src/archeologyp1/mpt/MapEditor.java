package archeologyp1.mpt;

import java.util.Scanner;

import archeologyp1.shared.Coordinate;
import archeologyp1.shared.Map;
import archeologyp1.shared.Utilities;
import archeologyp1.shared.Pot;

public class MapEditor {
	
	private Map map;
	private Coordinate current;
	
	public MapEditor(Map map){
		this.map = map;
	}
	
	//how to parameter
	public void editSingleCoordinate(int row, char col, int type, int amount, char featureChange){
		Scanner input = new Scanner(System.in);
		int date = 0;
		int r = row - 1;
		int c = Utilities.columnToIndex(Character.toString(col)); 
		current = map.plane[row][col];
		
		//Inputting the dates
		for(int i=0; i<amount-1; i++){
			System.out.println("What date would you like your " + (i+1) + " find to have?");
			System.out.println("::> ");
			date = input.nextInt();
			current.potCount.add(new Pot(date));
		}
		
		//current.setFeature(char)
	}

	public void editRow(int row){
		int r = row - 1;
		for(int c = 0; c < map.getNumColumns(); c++){
			current = map.plane[row][c];
		}
	}
	
	//?!?!?!?!?!
	public void editDates(){
		
	}
	
	public void editFeatures(){
		
	}
}
