package archeologyp2.shared.finds;


/**
 * STORAGE POTTERY FOR SHARED FINDS
 * @author Daniel
 * @author Celine
 */
public class StoragePottery extends Pottery {

	private double volume;
	
	/**
	 * For public StoragePottery
	 * @param date
	 */
	public StoragePottery(int date) {
		super(date);
	}
	
	/**
	 * For public StoragePottery
	 * @param date
	 * @param volume
	 */
	public StoragePottery(int date, double volume){
		super(date);
		this.volume = volume;
	}

	/**
	 * For public double getVolumn
	 * @return volume
	 */
	public double getVolume(){
		return volume;
	}
}
