package archeologyp2.mpt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import archeologyp2.shared.gui.CompletionEvent;
import archeologyp2.shared.gui.CompletionEventListener;
import archeologyp2.shared.gui.FrameOfFun;
import archeologyp2.shared.gui.Relay;
import archeologyp2.shared.gui.Tile;
import archeologyp2.shared.map.Coordinate;
import archeologyp2.shared.map.Utilities;

/**
 * THE MPT FRAME OF FUN GUI
 * 
 * This calls the other classes and includes a fully functional 
 * menu bar, which allows the user to choose from multiple options
 * on what they'd like to do, including generating an entirely new
 * map. 
 * 
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
//	private JMenuItem heritageMenuItem;
	
	final protected GenerateDialog generateDialog;

	private JPopupMenu popupMenu;

	/**
	 * For public MPTFrameOfFun
	 * 
	 * This adds MPT specific menu items and creates subController
	 * 
	 * @param title
	 */
	public MPTFrameOfFun(String title) {
		super(title);
		addMenuItems();
		subController = new SubController(imagePanel);
		generateDialog = new GenerateDialog("Generate Map");
		popupMenu = new MPTPopUpMenu();
	}

	/**
	 * For private void addMenuItems
	 * 
	 * This creates the new Menu Items, including generate a 
	 * new map, add a find, add a feature, mark heritage, view the
	 * current symbol map, the viewing options, and the about. 
	 */
	private void addMenuItems() {
		
		loadMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("./res"));
				int error = fileChooser.showOpenDialog(null);
				if(error == JFileChooser.APPROVE_OPTION){
					try {
 						map = Utilities.load(fileChooser.getSelectedFile().getAbsolutePath());
						replacePanel(map.getNumColumns(), map.getNumRows());
						setSize(map.getNumColumns() * Tile.naturalImage.getWidth(), map.getNumRows() * Tile.naturalImage.getHeight());
						subController.setMap(map);
						subController.setImagePanel(imagePanel);
						for(Coordinate coord : map){
							coord.getTileComponent().setPopupMenu(popupMenu);
							imagePanel.add(coord.getTileComponent());
						}
						subController.updateMap();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Invalid file. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} 
			}
		});
		
		// From "Edit" Menu, Generate
		generateMapMenuItem = new JMenuItem("Generate Map");
		generateMapMenuItem.setMnemonic('G');
		generateMapMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				generateDialog.setVisible(true);
				Relay relay = new Relay();
				relay.addMyEventListener(new CompletionEventListener(){
					@Override
					public void myEventOccurred(CompletionEvent evt) {
						map = generateDialog.getMap();
						replacePanel(map.getNumColumns(), map.getNumRows());
						setSize(map.getNumColumns() * Tile.naturalImage.getWidth(), map.getNumRows() * Tile.naturalImage.getHeight());
						subController.setMap(map);
						subController.setImagePanel(imagePanel);
						for(Coordinate coord : map)
							imagePanel.add(coord.getTileComponent());
						subController.updateMap();
					}
				});
				generateDialog.setRelay(relay);
			}
			
		});
		editMenu.add(generateMapMenuItem);
		
		addFeatureMenuItem = new JMenuItem("Add Feature");
		addFeatureMenuItem.setMnemonic('e');
		addFeatureMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				AddFeatureDialog addFeature = new AddFeatureDialog("Add Feature", subController);
				addFeature.setVisible(true);
			}
			
		});
		editMenu.add(addFeatureMenuItem);
		
		addFindMenuItem = new JMenuItem("Add Find");
		addFindMenuItem.setMnemonic('i');
		addFindMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				FindDialog findDialog = new FindDialog("Add Finds", subController);
				findDialog.setVisible(true);
			}
			
		});
		editMenu.add(addFindMenuItem);
		
//		heritageMenuItem = new JMenuItem("Mark Heritage");
//		heritageMenuItem.setMnemonic('M');
//		heritageMenuItem.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				HeritageDialog heritage = new HeritageDialog("Heritage", subController);
//				heritage.setVisible(true);
//			}
//			
//		});
//		editMenu.add(heritageMenuItem);
		
		aboutMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				subController.aboutMPT();
			}
		});
	}
}
