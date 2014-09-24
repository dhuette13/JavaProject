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
	private static Pot p; 
	private static MetalObject m;
	private static Charcoal ch;

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
		computeStandardDeviation(average);
		return 0;
	}
	
	/* Computing the standard deviation first by going
	 * through and getting the variance, before
	 * taking the square root of that, which is the
	 * standard deviation
	 */
	public double computeStandardDeviation(double avg){
		int r, c, n = 0;
		
		//Computes the amount of things needed to divide by to get the variance
		for(r = 0; r < map.getNumRows(); r++){
			for(c = 0; c < map.getNumColumns(); c++){
				current = map.plane[r][c];
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
		
		/* The variance equation is 
		 * s^2 = (x1-xbar)^2+(x2-xbar)^2+...+(xn-xbar)^2 / n-1
		 * where n-1 is the degrees of freedom and represents the
		 * amount of data points currently
		 */
		for(r = 0; r < map.getNumRows(); r++){
			for(c = 0; c < map.getNumColumns(); c++){
				current = map.plane[r][c];
				
				//To Do: Instantiate
				if(current.itemFound()){
					if(current.potCount.size() != 0){
<<<<<<< HEAD
						for(Pot p : current.potCount){
							sub = p.getDate() - avg;
							power = Math.pow(sub, 2);
						}
					} 
					else if(current.charcoalCount.size() != 0){
						for(Charcoal ch : current.charcoalCount){
							sub = ch.getDate() - avg;
							power = Math.pow(sub, 2);
						}
					}
					else if(current.metalCount.size() != 0){
						for(MetalObject m : current.metalCount){
							sub = m.getDate() - avg;
							power = Math.pow(sub, 2);
						}
=======
						sub = p.getDate() - avg;
						power = Math.pow(sub, 2);
					} 
					else if(current.charcoalCount.size() != 0){
						sub = ch.getDate() - avg;
						power = Math.pow(sub, 2);
					}
					else if(current.metalCount.size() != 0){
						sub = m.getDate() - avg;
						power = Math.pow(sub, 2);
>>>>>>> eeeb4f738248dc5db7fd0398b73110aab98648a6
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
		
<<<<<<< HEAD
		System.out.println("The average minus standard deviation is " + minus + " and the average plus the standard deviation is " + plus + ".");
		return sd;
=======
		System.out.println("The average minus standard deviation is "+minus+" and the average plus the standard deviation is "+plus+".");
		return 0;
>>>>>>> eeeb4f738248dc5db7fd0398b73110aab98648a6
	}
}
