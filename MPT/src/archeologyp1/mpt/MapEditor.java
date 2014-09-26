package archeologyp1.mpt;

import archeologyp1.shared.Charcoal;
import archeologyp1.shared.Coordinate;
import archeologyp1.shared.Feature;
import archeologyp1.shared.Map;
import archeologyp1.shared.MetalObject;
import archeologyp1.shared.Pot;
import archeologyp1.shared.Utilities;
import archeologyp1.shared.ViewingOption;

public class MapEditor {

	private Map map;
	private Coordinate current;

	public MapEditor(Map map) {
		this.map = map;
	}

	public void changeFeature(int row, String col, int feature){
		int r = row - 1;
		int c = Utilities.columnToIndex(col);
		Feature f = Feature.dirt;
		current = map.plane[r][c];
		switch(feature){
		case 1:
			f = Feature.dirt;
			current.setFeature(f);
			break;
		case 2:
			f = Feature.stone;
			current.setFeature(f);
			break;
		case 3:
			f = Feature.postHole;
			current.setFeature(f);
			break;
		default:
			System.out.println("Invalid option");
			return;
		}
		
		boolean done = false;
		if(map.getViewingOption() == ViewingOption.userModified){
			for(int i = 0; (i < map.getNumRows()) && !done; i++){
				for(int j = 0; j < map.getNumColumns(); j++){
					if(map.plane[i][j].getFeature() == f){
						current.setFeatureAlias(map.plane[i][j].getAliasChar());
						done = true;
						break;
					}
				}
			}
		}
		map.updateView();
	}

	public void changeDate(int row, String col, int type, int date){
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
		map.updateView();
	}
}
