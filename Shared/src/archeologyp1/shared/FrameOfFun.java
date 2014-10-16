package archeologyp1.shared;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FrameOfFun extends JFrame  {
	
	private static final long serialVersionUID = 1L;

	private Map<Coordinate> map;
	
	protected JMenuBar menuBar;
	protected JMenu fileMenu;
	private JMenuItem loadMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem exitMenuItem;
	
	protected JMenu editMenu;
	private JMenuItem setSizeMenuItem;
	private JMenuItem clearMenuItem;
	
	protected JMenu viewMenu;
	private JMenuItem showMapMenuItem;
	
	private JMenu helpMenu;
	private JMenuItem aboutMenuItem;
	
	protected JTextArea textArea;
	private JScrollPane scrollPane;

	/**
	 * Initializes a default Frame with a text area, scrollpane,
	 * and menubar containing multiple menu items.
	 * e
	 * @param title
	 */
	public FrameOfFun(String title) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createTextArea();
		createMenuBar();
		this.setSize(800, 600);
	}
	
	/**
	 * 
	 * @return map
	 */
	public Map<Coordinate> getMap(){
		return map;
	}
	
	/**
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
	 * Creates the menu bar and menu items.
	 */
	private void createMenuBar(){
		menuBar = new JMenuBar();
		
		fileMenu = new JMenu("File");
		loadMenuItem = new JMenuItem("Load");
		loadMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				map = Utilities.load("res/Tikal.csv");
			}
		});
		
		saveMenuItem = new JMenuItem("Save");
		saveMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		fileMenu.add(loadMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(exitMenuItem);
		menuBar.add(fileMenu);
		
		editMenu = new JMenu("Edit");
		setSizeMenuItem = new JMenuItem("Set Size");
		setSizeMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			}
			
		});
		
		clearMenuItem = new JMenuItem("Clear");
		clearMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		editMenu.add(setSizeMenuItem);
		editMenu.add(clearMenuItem);
		menuBar.add(editMenu);
		
		viewMenu = new JMenu("View");
		showMapMenuItem = new JMenuItem("Show Map");
		showMapMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				MapEditor.updateView(map);
				Utilities.printMap(map, textArea);
			}
		});
		
		viewMenu.add(showMapMenuItem);
		menuBar.add(viewMenu);
		
		helpMenu = new JMenu("Help");
		aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		helpMenu.add(aboutMenuItem);
		menuBar.add(helpMenu);
		
		menuBar.setVisible(true);
		this.setJMenuBar(menuBar);
	}

}
