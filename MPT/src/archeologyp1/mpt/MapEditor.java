package archeologyp1.mpt;

import archeologyp1.shared.Coordinate;
import archeologyp1.shared.Map;
import archeologyp1.shared.Pot;
import archeologyp1.shared.Utilities;

public class MapEditor {
	
	private Map map;
	private Coordinate current;
	
	public MapEditor(Map map){
		this.map = map;
	}
	
	public void editSingleCoordinate(int row, char col){
		//ask person about features, finds, excavated, date, etc etc
		int r = row - 1;
		int c = Utilities.columnToIndex(Character.toString(col));
		current = map.plane[r][c];
		current.potCount.add(new Pot(300));
	}

	public void editRow(int row){
		int r = row - 1;
		for(int c = 0; c < map.getNumColumns(); c++){
			current = map.plane[r][c];
		}


	}
}
