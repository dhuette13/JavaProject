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
import javax.swing.JPanel;

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
	private JPanel imagePanel;
	String text;

	/**
	 * For public SubController
	 * 
	 * @param imagePanel
	 */
	public SubController(JPanel imagePanel){
		this.imagePanel = imagePanel;
	}
	
	public void setImagePanel(JPanel imagePanel){
		this.imagePanel = imagePanel;
	}

	/**
	 * For public void aboutMPT
	 * 
	 * Prints the about information to text area
	 */
	public void aboutMPT(){
		text = "\n"
				+ "Team What's The Meaning Of Stonehenge!\n"
				+ "Daniel Huette: 1000947178\n"
				+ "Celine Soriano: 1000876277\n"
				+ "Map Population Tool\n"
				+ "Date: November 13, 2014\n"
				+ "Version 0.3\n"
				+ "\n";
		JOptionPane.showMessageDialog(null, text);
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
	 * @param column
	 * @param feature they'd like to change to
	 * 
	 */
	public void changeFeature(int row, int column, int feature, boolean loopFlag){
		try{
			Coordinate current;
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
					current = map.getPlaneItem(row, j);
					current.setFeature(f);
				}
			} else {
				current = map.getPlaneItem(row, column);
				current.setFeature(f);
			}
			updateMap();
		} catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "There is no loaded map, you can't do this!", "Error", JOptionPane.ERROR_MESSAGE);
		}
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
	public void addFind(int r, int c, int type, int date, String data, boolean loopFlag){
		try {
			Coordinate current;
			Artifact artifact = null;
			int row = r + 1;
			String col = Utilities.indexToColumn(c);
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
				artifact = new StoragePottery(date, Double.parseDouble(data), row, col);
				break;
				/* Add to kiln collection */
			case 4:
				artifact = new Kiln(date, Double.parseDouble(data), row, col);
				break;
				/* Add to hearth collection */
			case 5:
				String info[] = data.split(",");
				artifact = new Hearth(date, Double.parseDouble(info[0]), Double.parseDouble(info[1]), row, col);
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
						JOptionPane.showMessageDialog(imagePanel, "Removing Gold from row " + gold.getGoldRow() + " column " + gold.getGoldColumn());
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
			updateMap();
		} catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "There is no loaded map, you can't do this!", "Error", JOptionPane.ERROR_MESSAGE);
		}
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
		try{
			MapEditor.updateView(map, imagePanel);
		} catch(NullPointerException e){
			e.printStackTrace();
		}
	}

	/**
	 * For public void markHeritage
	 * 
	 * Marks a coordinate as heritage
	 * 
	 * @param row
	 * @param column
	 */
	public void toggleHeritage(int row, int column) {
		try{
			Coordinate current = map.getPlaneItem(row, column);
			current.setHeritage(!current.isHeritage());
		} catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "There is no loaded map, you can't do this!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * @param row
	 * @param column
	 */
	public void toggleExcavated(int row, int column) {
		try{
			Coordinate current = map.getPlaneItem(row, column);
			current.setExcavated(!current.getExcavated());
			updateMap();
		} catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "There is no loaded map, you can't do this!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
