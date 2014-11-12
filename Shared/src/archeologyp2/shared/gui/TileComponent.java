package archeologyp2.shared.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;

public class TileComponent extends JComponent implements MouseListener {

	private static final long serialVersionUID = 1L;

	private BufferedImage image;
	private JPopupMenu popUpMenu;
	
	public TileComponent(String path){
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setSize(image.getWidth(), image.getHeight());
	}
	
	
	@Override
	public void paint(Graphics g){
		g.drawImage(image, 0, 0, null);
	}
	
	public void setPopupMenu(JPopupMenu menu){
		popUpMenu = menu;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
}
