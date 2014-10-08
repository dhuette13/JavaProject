package archeologyp1.shared;

public abstract class Artifact implements Comparable <Artifact>{
	int date;

	/**
	 * 
	 * For the public int getDate method
	 * @return the date for the pot finds
	 * 
	 */
	public int getDate(){
		return date;
	}

	@Override
	public int compareTo(Artifact a) {
		if(this.date < a.date){
			return -1;
		}else if(this.date == a.date){
			return 0;
		}
		// TODO Auto-generated method stub
		return 1;
	}
}
