/**
 * 
 */
package archeologyp2.mpt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import archeologyp2.shared.gui.FrameOfFun;

/**
 * @author Daniel
 *
 */
public class MPTFrameOfFun extends FrameOfFun {

	private SubController subController;
	
	private JMenuItem generateMapMenuItem;
	private JMenuItem addFeatureMenuItem;
	private JMenuItem addFindMenuItem;
	private JMenuItem heritageMenuItem;
	
	private static final long serialVersionUID = 1L;

	/**
	 * @param title
	 */
	public MPTFrameOfFun(String title) {
		super(title);
		addMenuItems();
		subController = new SubController(map, textArea);
	}

	/**
	 * 
	 */
	private void addMenuItems() {
		// From "Edit" Menu, Generate
		generateMapMenuItem = new JMenuItem("Generate Map");
		generateMapMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			}
			
		});
		editMenu.add(generateMapMenuItem);
		
		addFeatureMenuItem = new JMenuItem("Add Feature");
		addFeatureMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			}
			
		});
		editMenu.add(addFeatureMenuItem);
		
		addFindMenuItem = new JMenuItem("Add Find");
		addFindMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			}
			
		});
		editMenu.add(addFindMenuItem);
		
		heritageMenuItem = new JMenuItem("Mark Heritage");
		heritageMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			}
			
		});
		editMenu.add(heritageMenuItem);
		
		aboutMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				subController.aboutMPT();
			}
		});

	}
}
