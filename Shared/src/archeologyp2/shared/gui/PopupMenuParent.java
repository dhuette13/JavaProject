package archeologyp2.shared.gui;

import javax.swing.JPopupMenu;

public abstract class PopupMenuParent extends JPopupMenu {

	private static final long serialVersionUID = 1L;
	protected int row, column;

	public void setRowAndColumn(int row, int column){
		this.row = row;
		this.column = column;
	}
}
