package archeologyp2.shared.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;

public class TileComponent extends JComponent implements MouseListener {

	private static final long serialVersionUID = 1L;

	private int row, column;
	private Tile tile;
	private JPopupMenu popUpMenu;
	
	public TileComponent(Tile tile, int row, int column){
		this.tile = tile;
		this.row = row;
		this.column = column;
		setSize(tile.getWidth(), tile.getHeight());
		addMouseListener(this);
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
		System.out.println("Mouse clicked at: " + e.getXOnScreen() + " " + e.getYOnScreen());
		popUpMenu.setLocation(e.getXOnScreen(), e.getYOnScreen());
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

	public void setTile(Tile tile){
		this.tile = tile;
	}
	
	public Tile getTile(){
		return tile;
	}
	

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
}
