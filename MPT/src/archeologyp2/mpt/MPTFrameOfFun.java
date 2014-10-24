/** 
 * 
 */
package archeologyp2.mpt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import archeologyp2.shared.gui.CompletionEvent;
import archeologyp2.shared.gui.CompletionEventListener;
import archeologyp2.shared.gui.FrameOfFun;
import archeologyp2.shared.map.MapEditor;
import archeologyp2.shared.map.Utilities;

/**
 * @author Daniel
 * @author Celine
 *
 */
public class MPTFrameOfFun extends FrameOfFun {

	private static final long serialVersionUID = 1L;
	
	private SubController subController;
	/* Edit Menu Items */
	private JMenuItem generateMapMenuItem;
	private JMenuItem addFeatureMenuItem;
	private JMenuItem addFindMenuItem;
	private JMenuItem heritageMenuItem;
	
	final protected GenerateDialog generateDialog;
	

	/**
	 * Adds MPT specific menu items and creates subcontroller
	 * @param title
	 */
	public MPTFrameOfFun(String title) {
		super(title);
		addMenuItems();
		subController = new SubController(textArea);
		generateDialog = new GenerateDialog("Generate Map");
	}

	/**
	 *  Creates the new Menu Items
	 */
	private void addMenuItems() {
		
		loadMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadDialog.setVisible(true);
				relay.addMyEventListener(new CompletionEventListener(){
					@Override
					public void myEventOccurred(CompletionEvent evt) {
						map = loadDialog.getMap();
						subController.setMap(map);
						MapEditor.updateView(map);
						Utilities.printMap(map, textArea);
					}
				});
				loadDialog.setRelay(relay);
			}
		});
		
		// From "Edit" Menu, Generate
		generateMapMenuItem = new JMenuItem("Generate Map");
		generateMapMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				generateDialog.setVisible(true);
				relay.addMyEventListener(new CompletionEventListener(){
					@Override
					public void myEventOccurred(CompletionEvent evt) {
						map = generateDialog.getMap();
						subController.setMap(map);
						MapEditor.updateView(map);
						Utilities.printMap(map, textArea);
					}
				});
				generateDialog.setRelay(relay);
			}
			
		});
		editMenu.add(generateMapMenuItem);
		
		addFeatureMenuItem = new JMenuItem("Add Feature");
		addFeatureMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				AddFeatureDialog addFeature = new AddFeatureDialog("Add Feature", subController);
				addFeature.setVisible(true);
			}
			
		});
		editMenu.add(addFeatureMenuItem);
		
		addFindMenuItem = new JMenuItem("Add Find");
		addFindMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				FindDialog findDialog = new FindDialog("Add Finds", subController);
				findDialog.setVisible(true);
			}
			
		});
		editMenu.add(addFindMenuItem);
		
		heritageMenuItem = new JMenuItem("Mark Heritage");
		heritageMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				HeritageDialog heritage = new HeritageDialog("Heritage", subController);
				heritage.setVisible(true);
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
