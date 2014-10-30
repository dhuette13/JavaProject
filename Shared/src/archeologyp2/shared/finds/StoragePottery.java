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
	 * @param row
	 * @param column
	 */
	public StoragePottery(int date, int row, String column) {
		super(date, row, column);
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
	 * For public StoragePottery
	 * @param date
	 * @param volume
	 * @param row
	 * @param column
	 */
	public StoragePottery(int date, double volume, int row, String column){
		super(date, row, column);
		this.volume = volume;
	}

	/**
	 * For public double getVolume
	 * @return volume
	 */
	public double getVolume(){
		return volume;
	}
}
