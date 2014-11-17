package archeologyp2.shared.map;

import javax.swing.JPanel;

import archeologyp2.shared.gui.Tile;
import archeologyp2.shared.gui.TileComponent;



/**
 * MAPEDITOR FOR THE MAP POPULATION TOOL
 * 
 * This class handles editing the map for the Map Population 
 * Tool. It includes changing features and adding finds. 
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class MapEditor {

	/**
	 * For public static void updateView
	 * This method updates the passed map's view.
	 * We call it to make sure the user can always see
	 * the current version of their working map. 
	 *  
	 * @param map
	 * @param panel
	 */
	public static void updateView(final Map<Coordinate> map, JPanel panel){
		TileComponent tileComponent;
		switch(map.getViewingOption()){
		case natural:
			for(Coordinate coord : map){
				tileComponent = coord.getTileComponent();
				switch(coord.getFeature()){
				case stone:
					if(coord.getExcavated()){
						tileComponent.setTile(Tile.stoneImage);
					}
					else {
						tileComponent.setTile(Tile.deadGrassImage);
					}
					tileComponent.repaint();
					break;
				case postHole:
					if(coord.getExcavated()) {
						tileComponent.setTile(Tile.pitImage);
					}
					else {
						tileComponent.setTile(Tile.chlorophyllImage);
					}
					tileComponent.repaint();
					break;
				case dirt:
					if(coord.getExcavated()) {
						tileComponent.setTile(Tile.dirtImage);
					}
					else {
						tileComponent.setTile(Tile.naturalImage);
					}
					tileComponent.repaint();
					break;
				}
			}
			break;
		case potCount:
			for(Coordinate coord : map){
				tileComponent = coord.getTileComponent();
				if(coord.getExcavated()){
					tileComponent.setTile(setCount(coord.getPotCount()));
				}
				else{
					tileComponent.setTile(Tile.unknownImage);
				}
				tileComponent.repaint();
			}
			break;
		case metalCount:
			for(Coordinate coord : map){
				tileComponent = coord.getTileComponent();
				if(coord.getExcavated()){
					tileComponent.setTile(setCount(coord.getMetalCount()));
				}
				else{
					tileComponent.setTile(Tile.unknownImage);
				}
				tileComponent.repaint();
			}
			break;
		case charcoalCount:
			for(Coordinate coord : map){
				tileComponent = coord.getTileComponent();
				if(coord.getExcavated()){
					tileComponent.setTile(setCount(coord.getCharcoalCount()));
				}
				else{
					tileComponent.setTile(Tile.unknownImage);
				}
				tileComponent.repaint();
			}
			break;
		case magnetometerResult:
			for(Coordinate coord : map) {
				tileComponent = coord.getTileComponent();
				if(coord.getCharcoalInspected()){
					if(coord.charcoalHidden()){
						tileComponent.setTile(Tile.trueImage);
					}
					else {
						tileComponent.setTile(Tile.falseImage);
					}
				}
				else{
					tileComponent.setTile(Tile.unknownImage);
				}
				tileComponent.repaint();
			}
			break;
		case metalDetectorResult:
			for(Coordinate coord : map) {
				tileComponent = coord.getTileComponent();
				if(coord.getMetalInspected()){
					if(coord.metalHidden()){
						tileComponent.setTile(Tile.trueImage);
					}
					else {
						tileComponent.setTile(Tile.falseImage);
					}
				}
				else {
					tileComponent.setTile(Tile.unknownImage);
				}
				tileComponent.repaint();
			}
			break;
		}

		panel.validate();
		panel.repaint();
	}

	/**
	 * For public static void updateView
	 * This method updates the passed map's view.
	 * We call it to make sure the user can always see
	 * the current version of their working map. 
	 * This version will update only the given row and column.
	 *  
	 * @param map
	 * @param panel
	 * @param row
	 * @param column
	 */
	public static void updateView(final Map<Coordinate> map, JPanel panel, int row, int column){
		TileComponent tileComponent;
		Coordinate coord = map.getPlaneItem(row, column);
		switch(map.getViewingOption()){
		case natural:
			tileComponent = coord.getTileComponent();
			switch(coord.getFeature()){
			case stone:
				if(coord.getExcavated()){
					tileComponent.setTile(Tile.stoneImage);
				}
				else {
					tileComponent.setTile(Tile.deadGrassImage);
				}
				tileComponent.repaint();
				break;
			case postHole:
				if(coord.getExcavated()) {
					tileComponent.setTile(Tile.pitImage);
				}
				else {
					tileComponent.setTile(Tile.chlorophyllImage);
				}
				tileComponent.repaint();
				break;
			case dirt:
				if(coord.getExcavated()) {
					tileComponent.setTile(Tile.dirtImage);
				}
				else {
					tileComponent.setTile(Tile.naturalImage);
				}
				tileComponent.repaint();
				break;
			}
			break;
		case potCount:
			tileComponent = coord.getTileComponent();
			if(coord.getExcavated()){
				tileComponent.setTile(setCount(coord.getPotCount()));
			}
			else{
				tileComponent.setTile(Tile.unknownImage);
			}
			tileComponent.repaint();
			break;
		case metalCount:
			tileComponent = coord.getTileComponent();
			if(coord.getExcavated()){
				tileComponent.setTile(setCount(coord.getMetalCount()));
			}
			else{
				tileComponent.setTile(Tile.unknownImage);
			}
			tileComponent.repaint();
			break;
		case charcoalCount:
			tileComponent = coord.getTileComponent();
			if(coord.getExcavated()){
				tileComponent.setTile(setCount(coord.getCharcoalCount()));
			}
			else{
				tileComponent.setTile(Tile.unknownImage);
			}
			tileComponent.repaint();
			break;
		case magnetometerResult:
			tileComponent = coord.getTileComponent();
			if(coord.getCharcoalInspected()){
				if(coord.charcoalHidden()){
					tileComponent.setTile(Tile.trueImage);
				}
				else {
					tileComponent.setTile(Tile.falseImage);
				}
			}
			else{
				tileComponent.setTile(Tile.unknownImage);
			}
			tileComponent.repaint();
			break;
		case metalDetectorResult:
			tileComponent = coord.getTileComponent();
			if(coord.getMetalInspected()){
				if(coord.metalHidden()){
					tileComponent.setTile(Tile.trueImage);
				}
				else {
					tileComponent.setTile(Tile.falseImage);
				}
			}
			else {
				tileComponent.setTile(Tile.unknownImage);
			}
			tileComponent.repaint();
			break;
		}

		panel.validate();
		panel.repaint();
	}

	/**
	 * Returns appropriate Tile for given find count
	 * 
	 * @param count
	 * @return tile
	 */
	private static Tile setCount(int count) {
		Tile image;
		image = Tile.zeroImage;
		switch(count){
		case 0:
			image = Tile.zeroImage;
			break;
		case 1:
			image = Tile.oneImage;
			break;
		case 2:
			image = Tile.twoImage;
			break;
		case 3:
			image = Tile.threeImage;
			break;
		case 4:
			image = Tile.fourImage;
			break;
		case 5:
			image = Tile.fiveImage;
			break;
		case 6:
			image = Tile.sixImage;
			break;
		case 7:
			image = Tile.sevenImage;
			break;
		case 8:
			image = Tile.eightImage;
			break;
		case 9:
			image = Tile.nineImage;
			break;
		}
		return image;
	}

	/**
	 * 
	 * For the public int countNumberOfFinds method
	 * This method goes through the map and counts the amount of
	 * finds, regardless of type of find.
	 * @return the number of finds
	 * 
	 */
	public static int countNumberOfFinds(Map<Coordinate> map){
		int count = 0;
		for(Coordinate coord : map){
			count = count + coord.getPotCount() + coord.getMetalCount() + coord.getCharcoalCount();
		}
		return count;
	}
}
