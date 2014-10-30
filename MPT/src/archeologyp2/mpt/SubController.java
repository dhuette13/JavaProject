/*
 * Source code for about 
 * Author: Becker
 * URL: https://elearn.uta.edu/bbcswebdav/pid-3462119-dt-content-rid-26118645_2/
 * courses/2148-OBJECT-ORIENTED-PROGRAMMING-81534-001/SubController.java
 * Date put into the code: October 16, 2014
 * 
 */

package archeologyp2.mpt;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import archeologyp2.shared.finds.Artifact;
import archeologyp2.shared.finds.DecoratedPottery;
import archeologyp2.shared.finds.FerrousMetal;
import archeologyp2.shared.finds.Hearth;
import archeologyp2.shared.finds.Kiln;
import archeologyp2.shared.finds.NonFerrousMetal;
import archeologyp2.shared.finds.StoragePottery;
import archeologyp2.shared.finds.SubmergedPottery;
import archeologyp2.shared.map.Coordinate;
import archeologyp2.shared.map.Feature;
import archeologyp2.shared.map.Map;
import archeologyp2.shared.map.MapEditor;
import archeologyp2.shared.map.Utilities;

/**
 * THE SUBCONTROLLER FOR THE MPT
 * 
 * This class contains all the logic done in the MPT, so that
 * the GUI is by itself. It includes multiple things like changing
 * the features, adding finds, making sure only one gold is on the
 * map, and many more. 
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class SubController {

	private Map<Coordinate> map;
	private JTextArea output;
	String text;

	/**
	 * For public SubController
	 * 
	 * @param output
	 */
	public SubController(JTextArea output){
		this.output = output;
	}

	/**
	 * For public void aboutMPT
	 * 
	 * Prints the about information to text area
	 */
	public void aboutMPT(){
		text = output.getText();
		text = "\n"
				+ "Team What's The Meaning Of Stonehenge!\n"
				+ "Daniel Huette: 1000947178\n"
				+ "Celine Soriano: 1000876277\n"
				+ "Map Population Tool\n"
				+ "Date: October 30, 2014\n"
				+ "Version 0.2\n"
				+ "\n";
		output.setText(text);
	}

	/**
	 * For public void changeFeature
	 * 
	 * This method involves changing the feature to
	 * what the user specifies. After changing the 
	 * feature, it then sets the alias (or "natural") features so
	 * the user can access view them again, before updating the
	 * map view.
	 * 
	 * @param row
	 * @param col
	 * @param feature they'd like to change to
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
	 * For public void addFind
	 * 
	 * This method adds finds based on the user preference,
	 * and adds the dates of those finds. 
	 * 
	 * @param row
	 * @param col
	 * @param type of find
	 * @param data 
	 * @param date(s) for the find
	 * 
	 */
	public void addFind(int row, String col, int type, int date, String data, boolean loopFlag){
		Coordinate current;
		Artifact artifact = null;
		int r = row - 1;
		int c = Utilities.columnToIndex(col); 
		switch(type){
		/* Add to decorated pot collection */
		case 1:
			artifact = new DecoratedPottery(date, data, row, col);
			break;
			/* Add to submerged pot collection */
		case 2:
			artifact = new SubmergedPottery(date, Integer.parseInt(data), row, col);
			break;
			/* Add to storage pot collection */
		case 3:
			artifact = new StoragePottery(date, Integer.parseInt(data), row, col);
			break;
			/* Add to kiln collection */
		case 4:
			artifact = new Kiln(date, Integer.parseInt(data), row, col);
			break;
			/* Add to hearth collection */
		case 5:
			String info[] = data.split(",");
			artifact = new Hearth(date, Integer.parseInt(info[0]), Integer.parseInt(info[1]), row, col);
			break;
			/* Add to ferrous collection */
		case 6:
			artifact = new FerrousMetal(date, Integer.parseInt(data), row, col);
			break;
			/* Add to non-ferrous collection */
		case 7:
			artifact = new NonFerrousMetal(date, data, row, col);
			if(data.toLowerCase().equals("gold")){
				NonFerrousMetal gold = (NonFerrousMetal) artifact;
				if(gold.goldExists()){
					int goldRow = gold.getGoldRow() - 1;
					int goldColumn = Utilities.columnToIndex(gold.getGoldColumn());
					current = map.getPlaneItem(goldRow, goldColumn);
					JOptionPane.showMessageDialog(output, "Removing Gold from row " + gold.getGoldRow() + " column " + gold.getGoldColumn());
					current.removeGold();
					gold.setGoldRow(row);
					gold.setGoldColumn(col);
					current = map.getPlaneItem(r, c);
					current.addFind(artifact);
				} else {
					gold.setGoldExists(true);
					gold.setGoldRow(row);
					gold.setGoldColumn(col);
				}
				return;
			}
			break;
		default:
			return;
		}
		if(loopFlag){
			for(int j = 0; j < map.getNumColumns(); j++){
				current = map.getPlaneItem(r, j);
				current.addFind(artifact);
			}
		} else {
			current = map.getPlaneItem(r, c);
			current.addFind(artifact);
		}
		MapEditor.updateView(map);
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
	public void updateMap(){
		MapEditor.updateView(map);
		Utilities.printMap(map, output);
	}

	/**
	 * For public void markHeritage
	 * 
	 * Marks a coordinate as heritage
	 * 
	 * @param row
	 * @param column
	 */
	public void markHeritage(int row, String column) {
		int r = row - 1;
		int c = Utilities.columnToIndex(column);
		Coordinate current = map.getPlaneItem(r, c);
		current.setHeritage(true);
	}
}
