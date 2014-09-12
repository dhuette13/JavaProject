package archeologyp1.shared;

/**
 * 
 * @author Daniel
 *
 */
public class Map {
	
	public Coordinate[][] plane;
	private int width, height;
	private ViewingOption viewingOption;
	
	/**
	 * 
	 * @param width
	 * @param height
	 */
	public Map(int width, int height){
		this.width = width;
		this.height = height;
		viewingOption = ViewingOption.natural;
		plane = new Coordinate[width][height];
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setViewingOption(ViewingOption option){
		viewingOption = option;
	}
	
	public ViewingOption getViewingOption(){
		return viewingOption;
	}
}
