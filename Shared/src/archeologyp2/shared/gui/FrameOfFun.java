package archeologyp2.shared.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	private JMenuItem exportMenuItem;
	private JMenuItem exitMenuItem;

	/* Edit Menu */
	protected JMenu editMenu;

	/* View Menu */
	protected JMenu viewMenu;
	private JMenuItem showMapMenuItem;

	/* Help Menu */
	protected JMenu helpMenu;
	protected JMenuItem aboutMenuItem;
	private JMenuItem baseConverterMenuItem;

	/* Panel and layout for displaying images */
	protected JPanel imagePanel;
	protected GridLayout layout;

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
		createImagePanel();
		createMenuBar();
		this.setSize(800, 700);
		this.setResizable(false);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();

		int x = (screenSize.width - getWidth()) / 2;
		int y = (screenSize.height - getHeight()) / 2;

		setLocation(x, y);
	}

	/**
	 * 
	 * @return map
	 */
	public Map<Coordinate> getMap(){
		return map;
	}

	/**
	 * When a new map is loaded or generated, the current panel
	 * must be replaced to account for a new map width and height.
	 * This method removes the old image panel, and creates a new
	 * one with a Grid Layout of the given width and height.
	 * 
	 * @param width
	 * @param height
	 */
	public void replacePanel(int width, int height){
		this.remove(imagePanel);

		imagePanel = new JPanel();
		layout = new GridLayout(height, width);
		imagePanel.setLayout(layout);

		this.add(imagePanel);
	}

	/**
	 * Creates a default image panel when the frame is first instantiated.
	 */
	private void createImagePanel(){
		imagePanel = new JPanel();
		layout = new GridLayout();
		imagePanel.setLayout(layout);
		this.add(imagePanel);
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
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("./res"));
				int returnVal = fileChooser.showSaveDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					try {
						Utilities.save(map, fileChooser.getSelectedFile().getAbsolutePath());
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Invalid file. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		//From "File" Menu, Export
		exportMenuItem = new JMenuItem("Export");
		exportMenuItem.setMnemonic('x');
		exportMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Export");
				fileChooser.setCurrentDirectory(new File("./res"));
				int returnVal = fileChooser.showDialog(null, "Export");
				if(returnVal == JFileChooser.APPROVE_OPTION){
					try {
						Utilities.exportMap(map, fileChooser.getSelectedFile().getAbsolutePath());
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Invalid file. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
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
		fileMenu.add(exportMenuItem);
		fileMenu.add(exitMenuItem);
		menuBar.add(fileMenu);

		/* Edit Menu */
		editMenu = new JMenu("Edit");
		editMenu.setMnemonic('E');
		menuBar.add(editMenu);

		/* View Menu */
		viewMenu = new JMenu("View");
		viewMenu.setMnemonic('V');

		showMapMenuItem = new JMenuItem("View Current Image Map");
		showMapMenuItem.setMnemonic('V');
		showMapMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					map.setViewingOption(ViewingOption.natural);
					MapEditor.updateView(map, imagePanel);
				}
				catch(NullPointerException n){
					JOptionPane.showMessageDialog(null,
							"Uh oh! Looks like you forgot to load a map first. Please try again.",
							"Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		viewMenu.add(showMapMenuItem);

		menuBar.add(viewMenu);

		helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');

		// From "Help" Menu, About
		aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.setMnemonic('A');
		helpMenu.add(aboutMenuItem);

		baseConverterMenuItem = new JMenuItem("Base Converter");
		baseConverterMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BaseConverterDialog converter = new BaseConverterDialog();
				converter.setVisible(true);
			}
		});

		baseConverterMenuItem.setMnemonic('B');
		helpMenu.add(baseConverterMenuItem);
		menuBar.add(helpMenu);

		menuBar.setVisible(true);
		this.setJMenuBar(menuBar);
	}
}

