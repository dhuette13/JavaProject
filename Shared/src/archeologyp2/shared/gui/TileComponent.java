package archeologyp2.shared.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

/**
 * Represents a single rectangular component to be added to the grid
 * of tile components. Contains a mouse listener for detecting clicks,
 * a Tile object to draw on paint invocation, and a row / column.
 * 
 * @author Daniel
 * @author Celine
 *
 */
public class TileComponent extends JComponent implements MouseListener {

	private static final long serialVersionUID = 1L;

	private int row, column;
	private Tile tile;
	private PopupMenuParent popUpMenu;

	/**
	 * Initializes variables, sets size of component to tile
	 * image's size, and gives component a mouse listener.
	 * 
	 * @param tile
	 * @param row
	 * @param column
	 */
	public TileComponent(Tile tile, int row, int column){
		this.tile = tile;
		this.row = row;
		this.column = column;
		setSize(tile.getWidth(), tile.getHeight());
		addMouseListener(this);
	}

	/**
	 * When the tile component is drawn, display the tile component's
	 * tile image.
	 */
	@Override
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(tile.getImage(), 0, 0, null);
	}

	/**
	 * Gives the tile component a popup menu to display
	 * when clicked
	 * 
	 * @param menu
	 */
	public void setPopupMenu(PopupMenuParent menu){
		popUpMenu = menu;
	}

	/**
	 * On left click, display popup menu and give menu
	 * the row and column for which the click occurred
	 * 
	 * On right click, deactivate the popup menu
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == 3){
			popUpMenu.setLocation(e.getXOnScreen(), e.getYOnScreen());
			popUpMenu.setRowAndColumn(row, column);
			popUpMenu.setVisible(true);
		} else if(e.getButton() == 1){
			popUpMenu.setVisible(false);
		}
	}

	/**
	 * Sets the current tile
	 * 
	 * @param tile
	 */
	public void setTile(Tile tile){
		this.tile = tile;
	}

	/**
	 * Returns the current tile
	 * 
	 * @return tile
	 */
	public Tile getTile(){
		return tile;
	}

	/**
	 * Returns the current row
	 * 
	 * @return row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Sets the current row
	 * 
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Returns the current column
	 * 
	 * @return column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Sets the current column
	 * 
	 * @param column
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	
	

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
}
