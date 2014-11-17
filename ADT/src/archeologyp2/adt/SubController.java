/*
 * Source code for about 
 * Author: Becker
 * URL: https://elearn.uta.edu/bbcswebdav/pid-3462119-dt-content-rid-26118645_2/
 * courses/2148-OBJECT-ORIENTED-PROGRAMMING-81534-001/SubController.java
 * Date put into the code: October 16, 2014
 * 
 */

package archeologyp2.adt;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import archeologyp2.shared.finds.Artifact;
import archeologyp2.shared.finds.FerrousMetal;
import archeologyp2.shared.finds.MetalObject;
import archeologyp2.shared.finds.NonFerrousMetal;
import archeologyp2.shared.map.Coordinate;
import archeologyp2.shared.map.Map;
import archeologyp2.shared.map.MapEditor;
import archeologyp2.shared.map.Utilities;

/**
 * 
 * SUBCONTROLLER FOR THE ARCHAEOLOGICAL DIG TOOL
 * 
 * This class contains methods that allow the user to
 * excavate the map depending on what tool they're using,
 * and it also has methods that compute both the average and 
 * standard deviation. 
 * 
 * @author Daniel
 * @author Celine
 *
 */

public class SubController {

	private Map<Coordinate> map;
	private JPanel imagePanel;
	String text;

	/**
	 * 
	 * For the public ToolBag method
	 * @param map
	 * 
	 */
	public SubController(JPanel imagePanel){
		this.imagePanel = imagePanel;
		map = null;
	}

	public void setImagePanel(JPanel imagePanel) {
		this.imagePanel = imagePanel;
	}

	/**
	 * Prints the about information to text area
	 */
	public void aboutADT(){
		text = "\n"
				+ "Team What's The Meaning Of Stonehenge!\n"
				+ "Daniel Huette: 1000947178\n"
				+ "Celine Soriano: 1000876277\n"
				+ "Archaeological Dig Tool\n"
				+ "Date: November 13, 2014\n"
				+ "Version 0.3\n"
				+ "\n";
		JOptionPane.showMessageDialog(null, text);
	}

	/**
	 * This method is for the magnetometer tool, which you use to sense 
	 * charcoal types. If there is charcoal hidden in the coordinate
	 * you're checking with the tool, then the program sets the magnetometer
	 * for that square to equal true. 
	 * 
	 * @param row
	 * @param column
	 * 
	 */
	public int magnetoMeter(int row, int column){
		try{
			Coordinate current;
			current = map.getPlaneItem(row, column);
			current.setCharcoalInspected(true);
			if(current.getCharcoalCount() != 0){
				current.setCharcoalHidden(true);
				updateMap(row, column);
				return 1;
			}
			else {
				current.setCharcoalHidden(false);
				updateMap(row, column);
				return 0;
			}
		} catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "There is no loaded map, you can't do this!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return -1;
	}

	/**
	 * This method is for the metal detector, which (obviously)
	 * is there to sense metal over any coordinate the user asks
	 * to inspect. If there is a metal object or objects there
	 * that get inspected, then the program sets the metal 
	 * detector equal to true.
	 * 
	 * @param row
	 * @param column
	 * 
	 */
	public int metalDetector(int row, int column){
		int detectorResults = 0;
		try{
			MetalObject metal;
			Coordinate current;
			current = map.getPlaneItem(row, column);
			current.setMetalInspected(true);
			if(current.getMetalCount() != 0) {
				current.setMetalHidden(true);

				/* Look for first instance of Ferrous Metal, increment results */
				for(int i = 0; i < current.getMetalCount(); i++){
					metal = current.getMetal(i);
					if(metal instanceof FerrousMetal){
						detectorResults += metal.respondToMetalDetector();
						break;
					} 
				}

				/* Look for first instance of NonFerrous Metal, increment results */
				for(int i = 0; i < current.getMetalCount(); i++){
					metal = current.getMetal(i);
					if(metal instanceof NonFerrousMetal){
						detectorResults += metal.respondToMetalDetector();
						break;
					}
				}
			}
			else {
				current.setMetalHidden(false);
			}
		} catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "There is no loaded map, you can't do this!", "Error", JOptionPane.ERROR_MESSAGE);
			detectorResults = -1;
		}

		updateMap(row, column);
		return detectorResults;
	}

	/**
	 * 
	 * This method is for sensing any pottery in the
	 * coordinate the user specifies.
	 * 
	 * @param row
	 * @param col
	 * 
	 */
	public void visibleSpectrum(int row, String col){
		try{
			Coordinate current;
			int r = row - 1;
			int c = Utilities.columnToIndex(col);
			current = map.getPlaneItem(r, c);
			current.setPotInspected(true);
		} catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "There is no loaded map, you can't do this!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * In this method, the user is able to find and excavate. If they
	 * excavate and there are items within the coordinate (based off
	 * the count size), then the boolean handling if the itemFound
	 * method will change to true.
	 * 
	 * In the event the user tries to dig a heritage coordinate,
	 * a new heritage exception will be thrown
	 * 
	 * @param row
	 * @param column
	 * 
	 * @throws HeritageException 
	 * 
	 */
	public Report dig(int row, int column) throws HeritageException {
		Report foundReport = new Report();

		try{
			Coordinate current;
			current = map.getPlaneItem(row, column);
			if(current.isHeritage()){
				throw new HeritageException("This coordinate is heritage!");
			} else {
				current.setExcavated(true);
				if((current.getPotCount() != 0) || (current.getCharcoalCount() != 0) || (current.getMetalCount() != 0)){
					current.setItemFound(true);
					for(int i = 0; i < current.getPotCount(); i++){
						foundReport.addFoundItem(current.getPot(i));
					}
					for(int i = 0; i < current.getMetalCount(); i++){
						foundReport.addFoundItem(current.getMetal(i));
					}
					for(int i = 0; i < current.getCharcoalCount(); i++){
						foundReport.addFoundItem(current.getCharcoal(i));
					}
				}
				foundReport.generateReport();
				return foundReport;
			}
		} catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "There is no loaded map, you can't do this!", "Error", JOptionPane.ERROR_MESSAGE);
		} 
		return foundReport;
	}


	/**
	 * For public void setMap
	 * 
	 * @param map
	 */
	public void setMap(Map<Coordinate> map) {
		this.map = map;
	}

	/**
	 * For public void updateMap
	 * 
	 * Updates the maps viewing symbols
	 */
	public void updateMap() {
		try{
			MapEditor.updateView(map, imagePanel);
		} catch(NullPointerException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Updates a single map coordinate
	 * 
	 * @param row
	 * @param column
	 */
	public void updateMap(int row, int column) {
		try{
			MapEditor.updateView(map, imagePanel, row, column);
		} catch(NullPointerException e){
			e.printStackTrace();
		}
	}

	/**
	 * Fills the report with found items, gets the average and 
	 * standard deviation, and shows finished report on text area
	 */
	public void printReport(){
		double averageDate;
		double standardDeviation;
		try{
			Report report = new Report();
			for(Coordinate coord : map){
				if(coord.getExcavated() && coord.itemFound()){
					for(int i = 0; i < coord.getPotCount(); i++)
						report.addFoundItem(coord.getPot(i));
					for(int i = 0; i < coord.getCharcoalCount(); i++)
						report.addFoundItem(coord.getCharcoal(i));
					for(int i = 0; i < coord.getMetalCount(); i++)
						report.addFoundItem(coord.getMetal(i));
				}
			}
			report.generateReport();
			averageDate = computeAverageDate();
			standardDeviation = computeStandardDeviation(averageDate);
			new ReportDialog(report, averageDate, standardDeviation).setVisible(true);
		} catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "There is no loaded map, you can't do this!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * This method computes the average date from the specified dates. 
	 * The nested for-loop computes the dates via what type the find is
	 * if an item in that coordinate has both been found and if there's 
	 * also an object inside it, then add to the average date sum,
	 * and increment the itemCount. Finally, it will divide the average date
	 * sum by the itemCount total to get the real average date.
	 * 
	 * @return the average
	 * 
	 */
	public double computeAverageDate(){
		int itemCount = 0;
		double average = 0;
		Artifact a;
		try{
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
		} catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "There is no loaded map, you can't do this!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return average;
	}

	/**
	 * 
	 * This method computes the standard deviation first by
	 * going through and getting the variance based off the
	 * dates specified. It then takes the standard deviation
	 * from that, which is the square root of the variance.
	 * 
	 * @param avg
	 * @return the standard deviation
	 */
	public double computeStandardDeviation(double avg){
		int n = 0;

		double power = 0;
		double sub = 0;
		double sum = 0;
		double variance = 0;
		double sd = 0;

		Artifact a;
		try{
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

			/* Degrees of freedom as per the variance equation */
			n -= 1; 
			variance = sum / n;
			sd = Math.sqrt(variance);

		} catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "There is no loaded map, you can't do this!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return sd;
	}

	/**
	 * This method computes the average date from the given artifact collection. 
	 * It iterates over the collection, summing up all of the given dates and 
	 * creating a total counter. Average is then computed and returned.
	 * 
	 * @param foundItems
	 * @return the average
	 * 
	 */
	public double computeAverageDate(ArrayList<Artifact> foundItems) {
		int itemCount = 0;
		double average = 0;
		
		for(Artifact a : foundItems){
			average += a.getDate();
			itemCount++;
		}
		average = average / itemCount;

		return average;
	}

	/**
	 * 
	 * This method computes the standard deviation first by
	 * going through and getting the variance based off the
	 * dates given from the collection. It then takes the 
	 * standard deviation from that, which is the square root 
	 * of the variance.
	 * 
	 * @param avg 
	 * @param foundItems
	 * @return the standard deviation
	 */
	public double computeStandardDeviation(double avg, ArrayList<Artifact> foundItems) {
		int n = 0;

		double power = 0;
		double sub = 0;
		double sum = 0;
		double variance = 0;
		double sd = 0;

		for(Artifact a : foundItems) {
			sub = a.getDate() - avg;
			power = Math.pow(sub, 2);
			sum += power;
			n++;
		}

		/* Degrees of freedom as per the variance equation */
		n -= 1; 
		variance = sum / n;
		sd = Math.sqrt(variance);

		return sd;
	}

}
