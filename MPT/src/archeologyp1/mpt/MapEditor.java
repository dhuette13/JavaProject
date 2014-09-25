package archeologyp1.mpt;

import archeologyp1.shared.Charcoal;
import archeologyp1.shared.Coordinate;
import archeologyp1.shared.Feature;
import archeologyp1.shared.Map;
import archeologyp1.shared.MetalObject;
import archeologyp1.shared.Pot;
import archeologyp1.shared.Utilities;

public class MapEditor {

	private Map map;
	private Coordinate current;

	public MapEditor(Map map) {
		this.map = map;
	}

	public void changeFeature(int row, String col, int feature){
		int r = row - 1;
		int c = Utilities.columnToIndex(col);
		current = map.plane[r][c];
		switch(feature){
		case 1:
			current.setFeature(Feature.dirt);
			break;
		case 2:
			current.setFeature(Feature.stone);
			break;
		case 3:
			current.setFeature(Feature.postHole);
			break;
		}
	}

	public void changeDate(int row, String col, int type, int amount, int date){
		int r = row - 1;
		int c = Utilities.columnToIndex(col); 
		current = map.plane[r][c];
		switch(type){
			/* Add to pot collection */
		case 1:
			current.potCount.add(new Pot(date));
			break;
			/* Add to charcoal collection */
		case 2:
			current.charcoalCount.add(new Charcoal(date));
			break;
			/* Add to metal collection */
		case 3:
			current.metalCount.add(new MetalObject(date));
			break;
		}
		//Inputting the dates
	}

	public void editRow(int row){
		UserInterface ui = new UserInterface(this);
		int r = row - 1;
		for(int c = 0; c < map.getNumColumns(); c++){
			current = map.plane[row][c];
			ui.changeCoordinate();

		}
	}
}
