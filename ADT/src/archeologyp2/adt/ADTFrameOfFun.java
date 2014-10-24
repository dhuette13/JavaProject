package archeologyp2.adt;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import archeologyp2.shared.gui.CompletionEvent;
import archeologyp2.shared.gui.CompletionEventListener;
import archeologyp2.shared.gui.FrameOfFun;
import archeologyp2.shared.map.MapEditor;
import archeologyp2.shared.map.Utilities;
import archeologyp2.shared.map.ViewingOption;

/**
 * 
 * ADT FRAME (OF FUN) FOR GUI
 * 
 * This class contains methods that allow the user to access the other
 * Graphical User Interfaces through the menu bar, and will show output of the
 * maps being changed by the user. 
 * 
 * @author Daniel
 * @author Celine
 * 
 */
public class ADTFrameOfFun extends FrameOfFun {

	private static final long serialVersionUID = 1L;

	/* Edit menu Items */
	private JMenuItem digMenuItem;
	private JMenuItem scanMenuItem;

	/* Menu items for special maps */
	private JMenuItem viewMagnetoMeterMenuItem;
	private JMenuItem viewMetalDetectorMenuItem;
	private JMenuItem viewPotteryMenuItem;
	private JMenuItem viewCharcoalMenuItem;
	private JMenuItem viewMetalMenuItem;
	private JMenuItem viewReportMenuItem;

	private SubController subController;

	/**
	 * For the public ADTFrameOfFun
	 * This method adds the ADT specific menu items and creates subController.
	 * 
	 * @param title
	 */
	public ADTFrameOfFun(String title) {
		super(title);
		addMenuItems();
		subController = new SubController(textArea);
	}

	/**
	 * For the private void addMenuItems
	 * 
	 * This method creates the new Menu Items. For the ADT,
	 * this would include the Dig tool, the Scan tool, 
	 * view different types of maps, and the report tool.
	 * The Dig Tool instantiates the DigDialog class and sets 
	 * the dig GUI to visible. The Scan tool instantiates the
	 * ScanDialog class and sets the scan GUI to visible. 
	 * The map different map views allow the user to toggle 
	 * between different maps by changing the viewing option
	 * and updating to the current map once they change the
	 * view. The report tool instantiates and shows the report
	 * in this ADTFrameOfFun.
	 */
	private void addMenuItems(){
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
		
		digMenuItem = new JMenuItem("Dig");
		digMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				DigDialog digDialog = new DigDialog("Dig", subController);
				digDialog.setVisible(true);
			}

		});

		editMenu.add(digMenuItem);
		scanMenuItem = new JMenuItem("Scan");
		scanMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ScanDialog scanDialog = new ScanDialog("Scan",subController);
				scanDialog.setVisible(true);
			}
		});

		editMenu.add(scanMenuItem);

		viewMagnetoMeterMenuItem = new JMenuItem("View Magnetometer Map");
		viewMagnetoMeterMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				map.setViewingOption(ViewingOption.magnetometerResult);
				MapEditor.updateView(map);
				Utilities.printMap(map, textArea);
			}
		});
		viewMenu.add(viewMagnetoMeterMenuItem);

		viewMetalDetectorMenuItem = new JMenuItem("View Metal Detector Map");
		viewMetalDetectorMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				map.setViewingOption(ViewingOption.metalDetectorResult);
				MapEditor.updateView(map);
				Utilities.printMap(map, textArea);
			}
		});
		viewMenu.add(viewMetalDetectorMenuItem);

		viewPotteryMenuItem = new JMenuItem("View Pottery Finds Map");
		viewPotteryMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				map.setViewingOption(ViewingOption.potCount);
				MapEditor.updateView(map);
				Utilities.printMap(map, textArea);
			}
		});
		viewMenu.add(viewPotteryMenuItem);

		viewCharcoalMenuItem = new JMenuItem("View Charcoal Finds Map");
		viewCharcoalMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				map.setViewingOption(ViewingOption.charcoalCount);
				MapEditor.updateView(map);
				Utilities.printMap(map, textArea);
			}
		});
		viewMenu.add(viewCharcoalMenuItem);

		viewMetalMenuItem = new JMenuItem("View Metal Finds Map");
		viewMetalMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				map.setViewingOption(ViewingOption.metalCount);
				MapEditor.updateView(map);
				Utilities.printMap(map, textArea);
			}
		});
		viewMenu.add(viewMetalMenuItem);

		aboutMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				subController.aboutADT();
			}
		});

		viewReportMenuItem = new JMenuItem("View Report");
		viewReportMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		viewMenu.add(viewReportMenuItem);
	}

}
