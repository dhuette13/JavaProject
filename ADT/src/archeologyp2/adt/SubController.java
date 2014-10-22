/*
 * Source code for about 
 * Author: Becker
 * URL: https://elearn.uta.edu/bbcswebdav/pid-3462119-dt-content-rid-26118645_2/
 * courses/2148-OBJECT-ORIENTED-PROGRAMMING-81534-001/SubController.java
 * Date put into the code: October 16, 2014
 * 
 */

package archeologyp2.adt;

import javax.swing.JTextArea;

import archeologyp2.shared.finds.Artifact;
import archeologyp2.shared.map.Coordinate;
import archeologyp2.shared.map.Map;
import archeologyp2.shared.map.MapEditor;
import archeologyp2.shared.map.Utilities;

/**
 * 
 * TOOL BAG FOR THE ARCHAEOLOGICAL DIG TOOL
 * @author Daniel
 * @author Celine
 * 
 * This class contains methods that allow the user to
 * excavate the map depending on what tool they're using,
 * and it also has methods that compute both the average and 
 * standard deviation. 
 *
 */

public class SubController {

	private Map<Coordinate> map;
	private JTextArea output;
	String text;

	/**
	 * 
	 * For the public ToolBag method
	 * @param map
	 * 
	 */
	public SubController(JTextArea output){
		this.output = output;
	}
	
	/**
	 * Prints the about information to text area
	 */
	public void aboutADT(){
		text = output.getText();
		text = "\n"
				+ "Team What's The Meaning Of Stonehenge!\n"
				+ "Daniel Huette: 1000947178\n"
				+ "Celine Soriano: 1000876277\n"
				+ "Archaeological Dig Tool\n"
				+ "Date: \n"
				+ "Version 0.2\n"
				+ "\n";
		output.setText(text);
	}

	/**
	 * 
	 * For the public void magnetoMeter method
	 * @param row
	 * @param col
	 * 
	 * This method is for the magnetometer tool, which you use to sense 
	 * charcoal types. If there is charcoal hidden in the coordinate
	 * you're checking with the tool, then the program sets the magnetometer
	 * for that square to equal true. 
	 * 
	 */
	public void magnetoMeter(int row, String col){
		Coordinate current;
		int r = row - 1;
		int c = Utilities.columnToIndex(col); 
		current = map.getPlaneItem(r, c);
		current.setCharcoalInspected(true);
		if(current.getCharcoalCount() != 0)
			current.setCharcoalHidden(true);
		else
			current.setCharcoalHidden(false);
	}

	/**
	 * 
	 * For the public void metalDetector method
	 * @param row
	 * @param col
	 * 
	 * This method is for the metal detector, which (obviously)
	 * is there to sense metal over any coordinate the user asks
	 * to inspect. If there is a metal object or objects there
	 * that get inspected, then the program sets the metal 
	 * detector equal to true.
	 * 
	 */
	public void metalDetector(int row, String col){
		Coordinate current;
		int r = row - 1;
		int c = Utilities.columnToIndex(col);
		current = map.getPlaneItem(r, c);
		current.setMetalInspected(true);
		if(current.getMetalCount() != 0)
			current.setMetalHidden(true);
		else
			current.setMetalHidden(false);
	}

	/**
	 * 
	 * For the public void visibleSpectrum method
	 * @param row
	 * @param col
	 * 
	 * This method is for sensing any pottery in the
	 * coordinate the user specifies.
	 * 
	 */
	public void visibleSpectrum(int row, String col){
		Coordinate current;
		int r = row - 1;
		int c = Utilities.columnToIndex(col);
		current = map.getPlaneItem(r, c);
		current.setPotInspected(true);
	}

	/**
	 * 
	 * For the public void dig method
	 * @param row
	 * @param col
	 * 
	 * In this method, the user is able to find and excavate. If they
	 * excavate and there are items within the coordinate (based off
	 * the count size), then the boolean handling if the itemFound
	 * method will change to true.
	 * 
	 */
	public void dig(int row, String col){
		Coordinate current;
		int r = row - 1;
		int c = Utilities.columnToIndex(col);
		current = map.getPlaneItem(r, c);
		current.setExcavated(true);
		if((current.getPotCount() != 0) || (current.getCharcoalCount() != 0) || (current.getMetalCount() != 0)){
			current.setItemFound(true);
		}
	}

	/**
	 * 
	 * For the public double computeAverageDate method
	 * @return the average
	 * 
	 * This method computes the average date from the specified dates. 
	 * The nested for-loop computes the dates via what type the find is
	 * if an item in that coordinate has both been found and if there's 
	 * also an object inside it, then add to the average date sum,
	 * and increment the itemCount. Finally, it will divide the average date
	 * sum by the itemCount total to get the real average date.
	 * 
	 */
	public double computeAverageDate(){
		int itemCount = 0;
		double average = 0;
		Artifact a;
		for(Coordinate coord : map){
			if(coord.getExcavated() && coord.itemFound()){
				for(int k = 0; k < coord.getPotCount(); k++){
					a = coord.getPot(k);
					average += a.getDate();
					itemCount++;
				}
				
				for(int k = 0; k < coord.getCharcoalCount(); k++){
					a = coord.getCharcoal(k);
					average += a.getDate();
					itemCount++;
				}
				
				for(int k = 0; k < coord.getMetalCount(); k++){
					a = coord.getMetal(k);
					average += a.getDate();
					itemCount++;
				}
			}
		}
		average = average / itemCount;
		return average;
	}

	/**
	 * 
	 * For the public double computeStandardDeviation method
	 * @param avg
	 * @return the standard deviation
	 * 
	 * This method computes the standard deviation first by
	 * going through and getting the variance based off the
	 * dates specified. It then takes the standard deviation
	 * from that, which is the square root of the variance.
	 * 
	 */
	public double computeStandardDeviation(double avg){
		int n = 0;

		double power = 0;
		double sub = 0;
		double sum = 0;
		
		Artifact a;
//		Computes the amount of things needed to divide by to get the variance
//		for(Coordinate coord : map) {
//			if(coord.getExcavated() && coord.itemFound()){ 
//				for(int k = 0; k < coord.potCount.size() + coord.metalCount.size() + coord.charcoalCount.size(); k++)
//					n++; 
//				//If it's excavated and if there's an object inside it
//				//then add to the amount of things variable
//			}
//		}


		/* The variance equation is 
		 * s^2 = (x1-xbar)^2+(x2-xbar)^2+...+(xn-xbar)^2 / n-1
		 * where n-1 is the degrees of freedom and represents the
		 * amount of data points currently
		 */
		for(Coordinate coord : map) {
			if(coord.itemFound()){
				for(int k = 0; k < coord.getPotCount(); k++) {
					a = coord.getPot(k);
					sub = a.getDate() - avg;
					power = Math.pow(sub, 2);
					sum += power;
					n++;
				}
				
				for(int k = 0; k < coord.getCharcoalCount(); k++) {
					a = coord.getCharcoal(k);
					sub = a.getDate() - avg;
					power = Math.pow(sub, 2);
					sum += power;
					n++;
				}
				
				for(int k = 0; k < coord.getMetalCount(); k++) {
					a = coord.getMetal(k);
					sub = a.getDate() - avg;
					power = Math.pow(sub, 2);
					sum += power;
					n++;
				}
			}
		}
		n -= 1; //Degrees of freedom as per the variance equation

		double variance = 0;
		variance = sum / n;

		double sd = 0;
		sd = Math.sqrt(variance);


		return sd;
	}

	public void setMap(Map<Coordinate> map) {
		this.map = map;
	}

	public void updateMap() {
		MapEditor.updateView(map);
		Utilities.printMap(map, output);
	}
}
