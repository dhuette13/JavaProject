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

	public static final String naturalToken = "Natural";
	public static final String stoneToken = "Stone";
	public static final String postHoleToken = "Post Hole";
	public static final String excavatedNaturalToken = "Excavated Natural";
	public static final String excavatedStoneToken = "Excavated Stone";
	public static final String excavatedPostHoleToken = "Excavated Post Hole";


	public static void updateImages(Map<Coordinate> map, JPanel panel) {
		//		Tile imageMap[][] = map.getImageMap();
		//		for(int r = 0; r < map.getNumRows(); r++){
		//			for(int c = 0; c < map.getNumColumns(); c++){
		//				panel.add(imageMap[r][c]);
		//				imageMap[r][c].repaint();
		//			}
		//		}
		panel.repaint();

		for(Coordinate coord : map){
			panel.add(coord.getTileComponent());
			coord.getTileComponent().repaint();
		}
	}

	/**
	 * For public static void updateView
	 * This method updates the passed map's view.
	 * We call it to make sure the user can always see
	 * the current version of their working map. 
	 *  
	 * @param map
	 */
	public static void updateView(final Map<Coordinate> map){
		switch(map.getViewingOption()){
		case natural:
			for(Coordinate coord : map){
				switch(coord.getFeature()){
				case stone:
					if(coord.getExcavated()){
						map.setMapTile(coord.getRow(), coord.getColumn(), Tile.stoneImage);
						coord.setTileComponent(new TileComponent(Tile.stoneImage));
					}
					else {
						map.setMapTile(coord.getRow(), coord.getColumn(), Tile.deadGrassImage);
						coord.setTileComponent(new TileComponent(Tile.deadGrassImage));
					}
					break;
				case postHole:
					if(coord.getExcavated()) {
						map.setMapTile(coord.getRow(), coord.getColumn(), Tile.pitImage);
						coord.setTileComponent(new TileComponent(Tile.pitImage));
					}
					else {
						map.setMapTile(coord.getRow(), coord.getColumn(), Tile.chlorophyllImage);
						coord.setTileComponent(new TileComponent(Tile.chlorophyllImage));
					}
					break;
				case dirt:
					if(coord.getExcavated()) {
						map.setMapTile(coord.getRow(), coord.getColumn(), Tile.dirtImage);
						coord.setTileComponent(new TileComponent(Tile.dirtImage));
					}
					else {
						map.setMapTile(coord.getRow(), coord.getColumn(), Tile.naturalImage);
						coord.setTileComponent(new TileComponent(Tile.naturalImage));
					}
					break;
				}
			}
			break;
		case potCount:
			for(Coordinate coord : map){
				if(coord.getExcavated()){
					map.setMapTile(coord.getRow(), coord.getColumn(), setCount(coord.getPotCount()));
					coord.setTileComponent(new TileComponent(setCount(coord.getPotCount())));
				}
				else{
					map.setMapTile(coord.getRow(), coord.getColumn(), Tile.unknownImage);
					coord.setTileComponent(new TileComponent(Tile.unknownImage));
				}
			}
			break;
		case metalCount:
			for(Coordinate coord : map){
				if(coord.getExcavated()){
					map.setMapTile(coord.getRow(), coord.getColumn(), setCount(coord.getMetalCount()));
					coord.setTileComponent(new TileComponent(setCount(coord.getMetalCount())));
				}
				else{
					map.setMapTile(coord.getRow(), coord.getColumn(), Tile.unknownImage);
					coord.setTileComponent(new TileComponent(Tile.unknownImage));
				}
			}
			break;
		case charcoalCount:
			for(Coordinate coord : map){
				if(coord.getExcavated()){
					map.setMapTile(coord.getRow(), coord.getColumn(), setCount(coord.getCharcoalCount()));
					coord.setTileComponent(new TileComponent(setCount(coord.getCharcoalCount())));
				}
				else{
					map.setMapTile(coord.getRow(), coord.getColumn(), Tile.unknownImage);
					coord.setTileComponent(new TileComponent(Tile.unknownImage));
				}
			}
			break;
		case magnetometerResult:
			for(Coordinate coord : map) {
				if(coord.getCharcoalInspected()){
					if(coord.charcoalHidden()){
						map.setMapTile(coord.getRow(), coord.getColumn(), Tile.trueImage);
						coord.setTileComponent(new TileComponent(Tile.trueImage));
					}
					else {
						map.setMapTile(coord.getRow(), coord.getColumn(), Tile.falseImage);
						coord.setTileComponent(new TileComponent(Tile.falseImage));
					}
				}
				else{
					map.setMapTile(coord.getRow(), coord.getColumn(), Tile.unknownImage);
					coord.setTileComponent(new TileComponent(Tile.unknownImage));
				}
			}
			break;
		case metalDetectorResult:
			for(Coordinate coord : map) {
				if(coord.getMetalInspected()){
					if(coord.metalHidden()){
						map.setMapTile(coord.getRow(), coord.getColumn(), Tile.trueImage);
						coord.setTileComponent(new TileComponent(Tile.trueImage));
					}
					else {
						map.setMapTile(coord.getRow(), coord.getColumn(), Tile.falseImage);
						coord.setTileComponent(new TileComponent(Tile.falseImage));
					}
				}
				else {
					map.setMapTile(coord.getRow(), coord.getColumn(), Tile.unknownImage);
					coord.setTileComponent(new TileComponent(Tile.unknownImage));
				}
			}
			break;
		}
	}

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
