package archeologyp1.adt;

import archeologyp1.shared.Charcoal;
import archeologyp1.shared.Coordinate;
import archeologyp1.shared.Map;
import archeologyp1.shared.MetalObject;
import archeologyp1.shared.Pot;
import archeologyp1.shared.Utilities;

/**
 * 
 * TOOL BAG FOR THE ARCHAEOLOGICAL DIG TOOL
 * @author Daniel
 * @author Celine
 * 
 * This class contains methods that allow the user to
 * excavate the map depending on what tool they're using,
 * and it also has methods that compute both the average and 
 * standard deviation. 
 *
 */

public class ToolBag {

	private Map map;
	private Coordinate current;

	/**
	 * 
	 * For the public ToolBag method
	 * @param map
	 * 
	 */
	public ToolBag(Map map){
		this.map = map;
	}
	
	/**
	 * 
	 * For the public void magnetoMeter method
	 * @param row
	 * @param col
	 * 
	 * This method is for the magnetometer tool, which you use to sense 
	 * charcoal types. If there is charcoal hidden in the coordinate
	 * you're checking with the tool, then the program sets the magnetometer
	 * for that square to equal true. 
	 * 
	 */
	public void magnetoMeter(int row, String col){
		int r = row - 1;
		int c = Utilities.columnToIndex(col); 
		current = map.plane[r][c];
		current.setCharcoalInspected(true);
		if(current.charcoalCount.size() != 0)
			current.setCharcoalHidden(true);
		else
			current.setCharcoalHidden(false);
	}

	/**
	 * 
	 * For the public void metalDetector method
	 * @param row
	 * @param col
	 * 
	 * This method is for the metal detector, which (obviously)
	 * is there to sense metal over any coordinate the user asks
	 * to inspect. If there is a metal object or objects there
	 * that get inspected, then the program sets the metal 
	 * detector equal to true.
	 * 
	 */
	public void metalDetector(int row, String col){
		int r = row - 1;
		int c = Utilities.columnToIndex(col);
		current = map.plane[r][c];
		current.setMetalInspected(true);
		if(current.metalCount.size() != 0)
			current.setMetalHidden(true);
		else
			current.setMetalHidden(false);
	}

	/**
	 * 
	 * For the public void visibleSpectrum method
	 * @param row
	 * @param col
	 * 
	 * This method is for sensing any pottery in the
	 * coordinate the user specifies.
	 * 
	 */
	public void visibleSpectrum(int row, String col){
		int r = row - 1;
		int c = Utilities.columnToIndex(col);
		current = map.plane[r][c];
		current.setPotInspected(true);
	}

	/**
	 * 
	 * For the public void dig method
	 * @param row
	 * @param col
	 * 
	 * In this method, the user is able to find and excavate. If they
	 * excavate and there are items within the coordinate (based off
	 * the count size), then the boolean handling if the itemFound
	 * method will change to true.
	 * 
	 */
	public void dig(int row, String col){
		int r = row - 1;
		int c = Utilities.columnToIndex(col);
		current = map.plane[r][c];
		current.setExcavated(true);
		if((current.potCount.size() != 0) || (current.charcoalCount.size() != 0) || (current.metalCount.size() != 0)){
			current.setItemFound(true);
		}
		current.updateCurrentViewableSymbol();
	}

	/**
	 * 
	 * For the public double computeAverageDate method
	 * @return the average
	 * 
	 * This method computes the average date from the specified dates. 
	 * The nested for-loop computes the dates via what type the find is
	 * if an item in that coordinate has both been found and if there's 
	 * also an object inside it, then add to the average date sum,
	 * and increment the itemCount. Finally, it will divide the average date
	 * sum by the itemCount total to get the real average date.
	 * 
	 */
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

	/**
	 * 
	 * For the public double computeStandardDeviation method
	 * @param avg
	 * @return the standard deviation
	 * 
	 * This method computes the standard deviation first by
	 * going through and getting the variance based off the
	 * dates specified. It then takes the standard deviation
	 * from that, which is the square root of the variance.
	 * 
	 */
	public double computeStandardDeviation(double avg){
		int r, c, n = 0;

		//Computes the amount of things needed to divide by to get the variance
		for(r = 0; r < map.getNumRows(); r++){
			for(c = 0; c < map.getNumColumns(); c++){
				current = map.plane[r][c];
				if(current.getExcavated() && current.itemFound()){ 
					for(int k = 0; k < current.potCount.size() + current.metalCount.size() + current.charcoalCount.size(); k++)
						n++; 
						//If it's excavated and if there's an object inside it
						//then add to the amount of things variable
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


		return sd;
	}
}
