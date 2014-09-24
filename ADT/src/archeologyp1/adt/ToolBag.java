package archeologyp1.adt;

import archeologyp1.shared.Charcoal;
import archeologyp1.shared.Coordinate;
import archeologyp1.shared.Map;
import archeologyp1.shared.MetalObject;
import archeologyp1.shared.Pot;


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
		if((current.potCount.size() != 0) || (current.charcoalCount.size() != 0) || (current.metalCount.size() != 0)){
			current.setItemFound(true);
		}
	}

	public int computeAverageDate(){
		int r, c, itemCount = 0;
		double average = 0;
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
		return 0;
	}
	
	
	public int computeStandardDeviation(int avg){
		
		int r, c, n = 0;
		
		//Computes the amount of things needed to divide by to get the variance
		for(r = 0; r < map.getNumRows(); r++){
			for(c = 0; c < map.getNumColumns(); c++){
				if(current.getExcavated() && current.itemFound()){ //If it's excavated and if there's an object inside it
					n += c;
				}
			}
			if(current.getExcavated() && current.itemFound()){
				n += r;
			}
		}
		
		n -= 1; //Degrees of freedom as per the variance equation
		
		int copy;
		copy = n + 1;
		
		double power = 0;
		double sub = 0;
		double sum = 0;
		
		for(r = 0; r < map.getNumRows(); r++){
			
			for(c = 0; c < map.getNumColumns(); c++){
				
				current = map.plane[r][c];
				
				if(current.itemFound()){
					
					if(current.potCount.size() != 0){
						Pot p;
						sub = p.getDate() - avg;
						power = Math.pow(sub, sub);
					} 
					else if(current.charcoalCount.size() != 0){
						Charcoal ch;
						sub = ch.getDate() - avg;
						power = Math.pow(sub, sub);
					}
					else if(current.metalCount.size() != 0){
						MetalObject m;
						sub = m.getDate() - avg;
						power = Math.pow(sub, sub);
					}
					
					sum += power;
				}
			}
		}
		
		double variance = 0;
		variance = sum / n;
		
		double sd = 0;
		sd = Math.sqrt(variance);
		
		double minus = 0;
		minus = avg - sd;
		double plus = 0;
		plus = avg + sd;
		
		System.out.println("The average minus standard deviation is "+minus+" and the average plus the standard deviation is "+plus+".");
			
	}
}
