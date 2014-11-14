package archeologyp2.shared.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
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
	public static final Tile naturalImage = new Tile("ScaledTiles/NATURAL.png");
	public static final Tile deadGrassImage = new Tile("ScaledTiles/YELLOW.png");
	public static final Tile chlorophyllImage = new Tile("ScaledTiles/BRIGHTGREEN.png");
	public static final Tile dirtImage = new Tile("ScaledTiles/DIRT.png");
	public static final Tile stoneImage = new Tile("ScaledTiles/STONE.png");
	public static final Tile pitImage = new Tile("ScaledTiles/PIT.png");
	public static final Tile trueImage = new Tile("ScaledTiles/TRUE.png");
	public static final Tile falseImage = new Tile("ScaledTiles/FALSE.png");
	public static final Tile unknownImage = new Tile("ScaledTiles/UNKNOWN.png");
	
	public static final Tile zeroImage = new Tile("ScaledTiles/0.png");
	public static final Tile oneImage = new Tile("ScaledTiles/1.png");
	public static final Tile twoImage = new Tile("ScaledTiles/2.png");
	public static final Tile threeImage = new Tile("ScaledTiles/3.png");
	public static final Tile fourImage = new Tile("ScaledTiles/4.png");
	public static final Tile fiveImage = new Tile("ScaledTiles/5.png");
	public static final Tile sixImage = new Tile("ScaledTiles/6.png");
	public static final Tile sevenImage = new Tile("ScaledTiles/7.png");
	public static final Tile eightImage = new Tile("ScaledTiles/8.png");
	public static final Tile nineImage = new Tile("ScaledTiles/9.png");
	
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
