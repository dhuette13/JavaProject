package archeologyp2.adt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import archeologyp2.shared.gui.FrameOfFun;

public class ADTFrameOfFun extends FrameOfFun {

	private static final long serialVersionUID = 1L;
	
	private JMenuItem digMenuItem;
	private JMenuItem scanMenuItem;
	
	public ADTFrameOfFun(String title) {
		super(title);
		addMenuItems();
	}
	
	private void addMenuItems(){
		digMenuItem = new JMenuItem("Dig");
		digMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		
		editMenu.add(digMenuItem);
		scanMenuItem = new JMenuItem("Scan");
		scanMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		editMenu.add(scanMenuItem);
	}
	
}
