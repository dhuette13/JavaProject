package archeologyp2.shared.finds;


/**
 * FERROUSMETAL FOR THE SHRAED FINDS
 * @author Daniel
 * @author Celine
 *
 */
public class FerrousMetal extends MetalObject {

	private int signalStrength;
	
	/**
	 * For public FerrousMetal
	 * @param date
	 */
	public FerrousMetal(int date) {
		super(date);
	}
	
	/**
	 * For public FerrousMetal
	 * @param date
	 * @param signalStength
	 */
	public FerrousMetal(int date, int signalStength) {
		super(date);
		this.signalStrength = signalStength;
	}
	
	/**
	 * For public int getSigalStrength
	 * @return signalStrength
	 */
	public int getSignalStrength(){
		return signalStrength;
	}

	@Override
	/**
	 * For public int respondToMetalDetector
	 * If the user uses the metal detector of the ADT, and finds a ferrous metal
	 * then this will return a 2. 
	 * @return 2
	 */
	public int respondToMetalDetector() {
		return 2;
	}

}
