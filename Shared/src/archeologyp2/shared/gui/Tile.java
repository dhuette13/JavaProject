package archeologyp2.shared.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * TILE FOR SHARED GUI
 * 
 * Represents a graphical image to be drawn
 * onto a tile component. Has a Buffered Image
 * and a number of static final pre-instantiated 
 * tiles for use by the program.
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class Tile {
	
	private BufferedImage image;
	
	/* Main tiles to be used by program */
	
	public static final Tile naturalImage = new Tile("FancyTiles/NATURAL.png");
	public static final Tile deadGrassImage = new Tile("FancyTiles/YELLOW.png");
	public static final Tile chlorophyllImage = new Tile("FancyTiles/BRIGHTGREEN.png");
	public static final Tile dirtImage = new Tile("FancyTiles/DIRT.png");
	public static final Tile stoneImage = new Tile("FancyTiles/STONE.png");
	public static final Tile pitImage = new Tile("FancyTiles/PIT.png");
	public static final Tile trueImage = new Tile("FancyTiles/TRUE.png");
	public static final Tile falseImage = new Tile("FancyTiles/FALSE.png");
	public static final Tile unknownImage = new Tile("FancyTiles/UNKNOWN.png");
	
	public static final Tile zeroImage = new Tile("FancyTiles/0.png");
	public static final Tile oneImage = new Tile("FancyTiles/1.png");
	public static final Tile twoImage = new Tile("FancyTiles/2.png");
	public static final Tile threeImage = new Tile("FancyTiles/3.png");
	public static final Tile fourImage = new Tile("FancyTiles/4.png");
	public static final Tile fiveImage = new Tile("FancyTiles/5.png");
	public static final Tile sixImage = new Tile("FancyTiles/6.png");
	public static final Tile sevenImage = new Tile("FancyTiles/7.png");
	public static final Tile eightImage = new Tile("FancyTiles/8.png");
	public static final Tile nineImage = new Tile("FancyTiles/9.png");
	
	/**
	 * Reads the image specifed by given path
	 * 
	 * @param path
	 */
	public Tile(String path) {
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns width of the read Buffered Image.
	 * 
	 * @return width
	 */
	public int getWidth() {
		return image.getWidth();
	}
	
	/**
	 * Returns height of the read Buffered Image.
	 * 
	 * @return height
	 */
	public int getHeight() {
		return image.getHeight();
	}
	
	/**
	 * Returns the Tiles Buffered Image for drawing.
	 * 
	 * @return image
	 */
	public BufferedImage getImage(){
		return image;
	}
}
