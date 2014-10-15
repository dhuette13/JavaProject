package archeologyp1.shared;

/**
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class StoragePottery extends Pottery {

	private double volume;
	
	/**
	 * 
	 * @param date
	 */
	public StoragePottery(int date) {
		super(date);
	}
	
	/**
	 * 
	 * @param date
	 * @param volume
	 */
	public StoragePottery(int date, double volume){
		super(date);
		this.volume = volume;
	}

	/**
	 * 
	 * @return volume
	 */
	public double getVolume(){
		return volume;
	}
}
