package archeologyp2.shared.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {
	
	private BufferedImage image;
	
	public static final Tile naturalImage = new Tile("Tiles/NATURAL.png");
	public static final Tile deadGrassImage = new Tile("Tiles/YELLOW.png");
	public static final Tile chlorophyllImage = new Tile("Tiles/BRIGHTGREEN.png");
	public static final Tile dirtImage = new Tile("Tiles/DIRT.png");
	public static final Tile stoneImage = new Tile("Tiles/STONE.png");
	public static final Tile pitImage = new Tile("Tiles/PIT.png");
	public static final Tile trueImage = new Tile("Tiles/TRUE.png");
	public static final Tile falseImage = new Tile("Tiles/FALSE.png");
	public static final Tile unknownImage = new Tile("Tiles/UNKNOWN.png");
	
	public static final Tile zeroImage = new Tile("Tiles/0.png");
	public static final Tile oneImage = new Tile("Tiles/1.png");
	public static final Tile twoImage = new Tile("Tiles/2.png");
	public static final Tile threeImage = new Tile("Tiles/3.png");
	public static final Tile fourImage = new Tile("Tiles/4.png");
	public static final Tile fiveImage = new Tile("Tiles/5.png");
	public static final Tile sixImage = new Tile("Tiles/6.png");
	public static final Tile sevenImage = new Tile("Tiles/7.png");
	public static final Tile eightImage = new Tile("Tiles/8.png");
	public static final Tile nineImage = new Tile("Tiles/9.png");
	
	public Tile(String path) {
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getWidth() {
		return image.getWidth();
	}
	
	public int getHeight() {
		return image.getHeight();
	}
	
	public BufferedImage getImage(){
		return image;
	}
}
