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
	 * Adds the ADT specifice menu items and creates subcontroller
	 * 
	 * @param title
	 */
	public ADTFrameOfFun(String title) {
		super(title);
		addMenuItems();
		subController = new SubController(textArea);
	}

	/**
	 * Creates the new Menu Items
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
				subController.magnetoMeter(5, "C");
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
