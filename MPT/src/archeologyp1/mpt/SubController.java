package archeologyp1.mpt;

import archeologyp1.shared.Charcoal;
import archeologyp1.shared.Coordinate;
import archeologyp1.shared.Feature;
import archeologyp1.shared.Map;
import archeologyp1.shared.MapEditor;
import archeologyp1.shared.MetalObject;
import archeologyp1.shared.Pot;
import archeologyp1.shared.Utilities;

/**
 * @author Daniel
 * @author Celine
 *
 */
public class SubController {
	
	private Map<Coordinate> map;
	
	public SubController(Map<Coordinate> map){
		this.map = map;
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
	public void changeFeature(int row, String col, int feature){
		Coordinate current;
		int r = row - 1;
		int c = Utilities.columnToIndex(col);
		Feature f = Feature.dirt;
		current = map.getPlaneItem(r, c);
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
	public void addFind(int row, String col, int type, int date){
		Coordinate current;
		int r = row - 1;
		int c = Utilities.columnToIndex(col); 
		current = map.getPlaneItem(r, c);
		switch(type){
		/* Add to pot collection */
		case 1:
			current.addFind(new Pot(date));
			break;
			/* Add to charcoal collection */
		case 2:
			current.addFind(new Charcoal(date));
			break;
			/* Add to metal collection */
		case 3:
			current.addFind(new MetalObject(date));
			break;
		}
		current.sortDates();
		MapEditor.updateView(map);
	}
}
