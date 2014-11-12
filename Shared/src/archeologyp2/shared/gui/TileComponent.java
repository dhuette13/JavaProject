package archeologyp2.shared.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;

public class TileComponent extends JComponent implements MouseListener {

	private static final long serialVersionUID = 1L;

	private Tile tile;
	private JPopupMenu popUpMenu;
	
	public TileComponent(Tile tile){
		this.tile = tile;
		setSize(tile.getWidth(), tile.getHeight());
		addMouseListener(this);
	}
	
	public void setTile(Tile tile){
		this.tile = tile;
	}
	
	public Tile getTile(){
		return tile;
	}
	
	@Override
	public void paint(Graphics g){
		g.drawImage(tile.getImage(), 0, 0, null);
	}
	
	public void setPopupMenu(JPopupMenu menu){
		popUpMenu = menu;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		popUpMenu.setLocation(e.getX(), e.getY());
		popUpMenu.setVisible(true);
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
