package archeologyp1.adt;

import archeologyp1.shared.Coordinate;
import archeologyp1.shared.Map;


public class ToolBag {

	private Map map;
	private Coordinate current;

	public ToolBag(Map map){
		this.map = map;
	}

	public void magnetoMeter(int row, char col){
		int r = row - 1;
		int c = col - 'A';
		current = map.plane[r][c];
		current.setCharcoalInspected(true);
		if(current.charcoalCount.size() != 0)
			current.setCharcoalHidden(true);
		else
			current.setCharcoalHidden(false);
	}

	public void metalDetector(int row, char col){
		int r = row - 1;
		int c = col - 'A';
		current = map.plane[r][c];
		current.setMetalInspected(true);
		if(current.metalCount.size() != 0)
			current.setMetalHidden(true);
		else
			current.setMetalHidden(false);
	}
	
	public void visibleSpectrum(int row, char col){
		int r = row - 1;
		int c = col - 'A';
		current = map.plane[r][c];
		current.setPotInspected(true);
	}
	
	public void dig(int row, char col){
		int r = row - 1;
		int c = col - 'A';
		current = map.plane[r][c];
		current.setExcavated(true);
		map.updateView();
	}

	public int computeAverageDate(){
		return 0;
	}
}
