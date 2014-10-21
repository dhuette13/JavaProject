package archeologyp2.adt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import archeologyp2.shared.gui.FrameOfFun;

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
	
	private SubController subController;
	
	public ADTFrameOfFun(String title) {
		super(title);
		addMenuItems();
		subController = new SubController(map, textArea);
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
		
		viewMagnetoMeterMenuItem = new JMenuItem("View Magnetometer Map");
		viewMagnetoMeterMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		viewMenu.add(viewMagnetoMeterMenuItem);
		
		viewMetalDetectorMenuItem = new JMenuItem("View Metal Detector Map");
		viewMetalDetectorMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		viewMenu.add(viewMetalDetectorMenuItem);
		
		viewPotteryMenuItem = new JMenuItem("View Pottery Finds Map");
		viewPotteryMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		viewMenu.add(viewPotteryMenuItem);
		
		viewCharcoalMenuItem = new JMenuItem("View Charcoal Finds Map");
		viewCharcoalMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		viewMenu.add(viewCharcoalMenuItem);
		
		viewMetalMenuItem = new JMenuItem("View Metal Finds Map");
		viewMetalMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		viewMenu.add(viewMetalMenuItem);
		
		aboutMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				subController.aboutADT();
			}
		});
	}
	
}
