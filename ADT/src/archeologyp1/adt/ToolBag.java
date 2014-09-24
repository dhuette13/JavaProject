package archeologyp1.adt;

import archeologyp1.shared.Charcoal;
import archeologyp1.shared.Coordinate;
import archeologyp1.shared.Map;
import archeologyp1.shared.MetalObject;
import archeologyp1.shared.Pot;
import archeologyp1.shared.Utilities;


public class ToolBag {

	private Map map;
	private Coordinate current;

	public ToolBag(Map map){
		this.map = map;
	}

	public void magnetoMeter(int row, char col){
		int r = row - 1;
		int c = Utilities.columnToIndex(Character.toString(col));
		current = map.plane[r][c];
		current.setCharcoalInspected(true);
		if(current.charcoalCount.size() != 0)
			current.setCharcoalHidden(true);
		else
			current.setCharcoalHidden(false);
	}

	public void metalDetector(int row, char col){
		int r = row - 1;
		int c = Utilities.columnToIndex(Character.toString(col));
		current = map.plane[r][c];
		current.setMetalInspected(true);
		if(current.metalCount.size() != 0)
			current.setMetalHidden(true);
		else
			current.setMetalHidden(false);
	}
	
	public void visibleSpectrum(int row, char col){
		int r = row - 1;
		int c = Utilities.columnToIndex(Character.toString(col));
		current = map.plane[r][c];
		current.setPotInspected(true);
	}
	
	public void dig(int row, char col){
		int r = row - 1;
		int c = Utilities.columnToIndex(Character.toString(col));
		current = map.plane[r][c];
		current.setExcavated(true);
		if((current.potCount.size() != 0) || (current.charcoalCount.size() != 0) || (current.metalCount.size() != 0)){
			current.setItemFound(true);
		}
	}

	public double computeAverageDate(){
		int r, c, itemCount = 0;
		double average = 0.0d;
		for(r = 0; r < map.getNumRows(); r++){
			for(c = 0; c < map.getNumColumns(); c++){
				current = map.plane[r][c];
				if(current.itemFound()){
					for(Pot p : current.potCount){
						average += p.getDate();
						itemCount++;
					}
					for(Charcoal ch : current.charcoalCount){
						average += ch.getDate();
						itemCount++;
					}
					for(MetalObject m : current.metalCount){
						average += m.getDate();
						itemCount++;
					}
					average = average / itemCount;
				}
			}
		}
		return average;
	}
	
	public double standaredDeviation(double average){
		int r, c, itemCount = 0;
		double sum = 0.0d;
		for(r = 0; r < map.getNumRows(); r++){
			for(c = 0; c < map.getNumColumns(); c++){
				current = map.plane[r][c];
				if(current.itemFound()){
					for(Pot p : current.potCount){
						sum += p.getDate();
						itemCount++;
					}
					for(Charcoal ch : current.charcoalCount){
						average += ch.getDate();
						itemCount++;
					}
					for(MetalObject m : current.metalCount){
						average += m.getDate();
						itemCount++;
					}
					average = average / itemCount;
				}
			}
		}
		return average;
	}
}
