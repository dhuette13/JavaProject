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
		current.setCurrentViewableSymbol();
	}

	public double computeAverageDate(){
		int r, c, itemCount = 0;
		double average = 0;
		for(r = 0; r < map.getNumRows(); r++){
			for(c = 0; c < map.getNumColumns(); c++){
				current = map.plane[r][c];
				if(current.getExcavated() && current.itemFound()){
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
				}
			}
		}
		average = average / itemCount;
		return average;
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
					for(int k = 0; k < current.potCount.size() + current.metalCount.size() + current.charcoalCount.size(); k++)
						n++;
				}
			}
		}

		n -= 1; //Degrees of freedom as per the variance equation

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

				if(current.itemFound()){
					for(Pot p : current.potCount){
						sub = p.getDate() - avg;
						power = Math.pow(sub, 2);
						sum += power;
					}
					for(Charcoal ch : current.charcoalCount){
						sub = ch.getDate() - avg;
						power = Math.pow(sub, 2);
						sum += power;
					}
					for(MetalObject m : current.metalCount){
						sub = m.getDate() - avg;
						power = Math.pow(sub, 2);
						sum += power;
					}
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

		System.out.println("The average minus standard deviation is " + minus + " and the average plus the standard deviation is " + plus + ".");
		return sd;
	}
}
