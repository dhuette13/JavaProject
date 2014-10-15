package archeologyp1.shared;

/**
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class FerrousMetal extends MetalObject {

	private int signalStrength;
	
	/**
	 * 
	 * @param date
	 */
	public FerrousMetal(int date) {
		super(date);
	}
	
	/**
	 * 
	 * @param date
	 * @param signalStength
	 */
	public FerrousMetal(int date, int signalStength) {
		super(date);
		this.signalStrength = signalStength;
	}
	
	/**
	 * 
	 * @return signalStrength
	 */
	public int getSignalStrength(){
		return signalStrength;
	}

}
