package archeologyp2.shared.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import archeologyp2.shared.map.Coordinate;
import archeologyp2.shared.map.Map;
import archeologyp2.shared.map.MapEditor;
import archeologyp2.shared.map.Utilities;
import archeologyp2.shared.map.ViewingOption;

/**
 * FRAME OF FUN - THE GUI FOR THE SHARED ITEMS
 * 
 * This class handles the GUI frame of the shared items.
 * It lets the user pick between what they want to do 
 * through menu items (by setting the frame itself visible)
 * and then closing the frame once the user exits. 
 * 
 * @author Daniel
 * @author Celine
 */
public abstract class FrameOfFun extends JFrame {
	
	private static final long serialVersionUID = 1L;

	protected Map<Coordinate> map;
	
	protected JMenuBar menuBar;
	
	/* File Menu */
	protected JMenu fileMenu;
	protected JMenuItem loadMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem exitMenuItem;
	
	/* Edit Menu */
	protected JMenu editMenu;
	
	/* View Menu */
	protected JMenu viewMenu;
	private JMenuItem showMapMenuItem;
	private JMenuItem viewingMenuItem;
	
	/* Help Menu */
	private JMenu helpMenu;
	protected JMenuItem aboutMenuItem;
	
	protected JTextArea textArea;
	private JScrollPane scrollPane;
	
	final protected PathDialog loadDialog;
	
	protected Relay relay;
	
	/**
	 * For public FrameOfFun
	 * 
	 * Initializes a default Frame with a text area, scroll pane,
	 * and menu bar containing multiple menu items.
	 * 
	 * @param title
	 */
	public FrameOfFun(String title) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createTextArea();
		createMenuBar();
		this.setSize(800, 700);
		relay = new Relay();
		loadDialog = new PathDialog("Load", map);
	}
	
	/**
	 * 
	 * @return map
	 */
	public Map<Coordinate> getMap(){
		return map;
	}
	
	/**
	 * For private void createTextArea
	 * 
	 * Initializes the text area and scroll pane, placing it on the
	 * Frame. Text area is passed to Utilities for printing and exporting.
	 */
	private void createTextArea(){
		textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", 0, 14));
		textArea.setEditable(false);
		scrollPane = new JScrollPane(textArea);
		this.add(scrollPane, BorderLayout.CENTER);
	}
	
	/**
	 * For private void createMenuBar
	 * 
	 * Creates the menu bar and menu items.
	 */
	private void createMenuBar(){
		menuBar = new JMenuBar();
		
		/* File Menu */
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		
		// From "File" Menu, Load
		loadMenuItem = new JMenuItem("Load");
		loadMenuItem.setMnemonic('L');
		
		// From "File" Menu, Save
		saveMenuItem = new JMenuItem("Save");
		saveMenuItem.setMnemonic('S');
		saveMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PathDialog saveDialog = new PathDialog("Save", map);
				saveDialog.setVisible(true);
			}
		});
		
		// From "File" Menu, Exit
		exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.setMnemonic('E');
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Utilities.exit();
			}
		});
		
		fileMenu.add(loadMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(exitMenuItem);
		menuBar.add(fileMenu);
		
		/* Edit Menu */
		editMenu = new JMenu("Edit");
		editMenu.setMnemonic('E');
		menuBar.add(editMenu);
		
		/* View Menu */
		viewMenu = new JMenu("View");
		viewMenu.setMnemonic('V');
		
		showMapMenuItem = new JMenuItem("View Current Symbol Map");
		showMapMenuItem.setMnemonic('V');
		showMapMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(map != null){
					map.setViewingOption(ViewingOption.userModified);
					MapEditor.updateView(map);
					Utilities.printMap(map, textArea);
				}
			}
		});
		viewMenu.add(showMapMenuItem);
		
		viewingMenuItem = new JMenuItem("Viewing Options");
		viewingMenuItem.setMnemonic('O');
		viewingMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewingDialog dialog = new ViewingDialog("Viewing Options", map);
				dialog.setVisible(true);
				relay.addMyEventListener(new CompletionEventListener(){
					@Override
					public void myEventOccurred(CompletionEvent evt) {
						Utilities.printMap(map, textArea);
					}
					
				});
				dialog.setRelay(relay);
			}
		});
		viewMenu.add(viewingMenuItem);
		
		menuBar.add(viewMenu);
		
		helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');
		
		// From "Help" Menu, About
		aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.setMnemonic('A');
		helpMenu.add(aboutMenuItem);
		menuBar.add(helpMenu);
		
		menuBar.setVisible(true);
		this.setJMenuBar(menuBar);
	}
}

