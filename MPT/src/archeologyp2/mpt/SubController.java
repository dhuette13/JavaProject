/*
 * Source code for about 
 * Author: Becker
 * URL: https://elearn.uta.edu/bbcswebdav/pid-3462119-dt-content-rid-26118645_2/
 * courses/2148-OBJECT-ORIENTED-PROGRAMMING-81534-001/SubController.java
 * Date put into the code: October 16, 2014
 * 
 */

package archeologyp2.mpt;

import javax.swing.JTextArea;

import archeologyp2.shared.finds.Artifact;
import archeologyp2.shared.finds.Charcoal;
import archeologyp2.shared.finds.MetalObject;
import archeologyp2.shared.finds.Pottery;
import archeologyp2.shared.map.Coordinate;
import archeologyp2.shared.map.Feature;
import archeologyp2.shared.map.Map;
import archeologyp2.shared.map.MapEditor;
import archeologyp2.shared.map.Utilities;

/**
 * @author Daniel
 * @author Celine
 *
 */
public class SubController {
	
	private Map<Coordinate> map;
	private JTextArea output;
	String text;
	
	public SubController(JTextArea output){
		this.output = output;
	}
	
	/**
	 * Prints the about information to text area
	 */
	public void aboutMPT(){
		text = output.getText();
		text = "\n"
				+ "Team What's The Meaning Of Stonehenge!\n"
				+ "Daniel Huette: 1000947178\n"
				+ "Celine Soriano: 1000876277\n"
				+ "Map Population Tool\n"
				+ "Date: \n"
				+ "Version 0.2\n"
				+ "\n";
		output.setText(text);
	}
	
	/**
	 * 
	 * For the public void changeFeature method
	 * @param row
	 * @param col
	 * @param feature they'd like to change to
	 * 
	 * This method involves changing the feature to
	 * what the user specifies. After changing the 
	 * feature, it then sets the alias (or "natural") features so
	 * the user can access view them again, before updating the
	 * map view.
	 * 
	 */
	public void changeFeature(int row, String col, int feature, boolean loopFlag){
		Coordinate current;
		int r = row - 1;
		Feature f = Feature.dirt;
		switch(feature){
		case 1:
			f = Feature.dirt;
			break;
		case 2:
			f = Feature.stone;
			break;
		case 3:
			f = Feature.postHole;
			break;
		default:
			System.out.println("Invalid option");
			return;
		}
		
		if(loopFlag) {
			for(int j = 0; j < map.getNumColumns(); j++){
				current = map.getPlaneItem(r, j);
				current.setFeature(f);
			}
		} else {
			int c = Utilities.columnToIndex(col);
			current = map.getPlaneItem(r, c);
			current.setFeature(f);
		}
		MapEditor.updateView(map);
	}

	/**
	 * 
	 * For the public void addFind method
	 * @param row
	 * @param col
	 * @param type of find
	 * @param date(s) for the find
	 * 
	 * This method adds finds based on the user preference,
	 * and adds the dates of those finds. 
	 * 
	 */
	public void addFind(int row, String col, int type, int date, boolean loopFlag){
		Coordinate current;
		Artifact artifact = new Pottery(date);
		int r = row - 1;
		int c = Utilities.columnToIndex(col); 
		switch(type){
		/* Add to pot collection */
		case 1:
			artifact = new Pottery(date);
			break;
			/* Add to charcoal collection */
		case 2:
			artifact = new Charcoal(date);
			break;
			/* Add to metal collection */
		case 3:
			artifact = new MetalObject(date);
			break;
		}
		if(loopFlag){
			for(int j = 0; j < map.getNumColumns(); j++){
				current = map.getPlaneItem(r, j);
				current.addFind(artifact);
				current.sortDates();
			}
		} else {
			current = map.getPlaneItem(r, c);
			current.addFind(artifact);
			current.sortDates();
		}
		MapEditor.updateView(map);
	}

	/**
	 * @param map2
	 */
	public void setMap(Map<Coordinate> map) {
		this.map = map;
	}
	
	public void updateMap(){
		MapEditor.updateView(map);
		Utilities.printMap(map, output);
	}

	/**
	 * @param row
	 * @param column
	 */
	public void markHeritage(int row, String column) {
		
	}
}
