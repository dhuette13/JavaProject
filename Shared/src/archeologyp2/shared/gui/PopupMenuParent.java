package archeologyp2.shared.gui;

import javax.swing.JPopupMenu;

/**
 * POP UP MENU PARENT FOR THE SHARED GUI 
 * 
 * Parent PopupMenu for the ADT and MPT PopupMenus. Gives
 * both menus access to a row and column field.
 * 
 * @author Daniel
 * @author Celine
 *
 */
public abstract class PopupMenuParent extends JPopupMenu {

	private static final long serialVersionUID = 1L;
	protected int row, column;

	/**
	 * Allows tile component to pass row and column information 
	 * to the popup menu.
	 * 
	 * @param row
	 * @param column
	 */
	public void setRowAndColumn(int row, int column){
		this.row = row;
		this.column = column;
	}
}
