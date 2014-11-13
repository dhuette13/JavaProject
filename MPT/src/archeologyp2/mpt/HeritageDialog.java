////=========================================================================
//// CLASS TO EVENTUALLY DELETE
//// CLASS TO EVENTUALLY DELETE
//// CLASS TO EVENTUALLY DELETE
//// CLASS TO EVENTUALLY DELETE
//// CLASS TO EVENTUALLY DELETE
//// CLASS TO EVENTUALLY DELETE
//// CLASS TO EVENTUALLY DELETE
//// CLASS TO EVENTUALLY DELETE
//// CLASS TO EVENTUALLY DELETE
//// CLASS TO EVENTUALLY DELETE
////=========================================================================
//
//
//package archeologyp2.mpt;
//
//import java.awt.GridBagConstraints; 
//import java.awt.GridBagLayout;
//import java.awt.Insets;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//import javax.swing.JButton;
//import javax.swing.JComponent;
//import javax.swing.JDialog;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JTextField;
//
///**
// * THE HERITAGE DIALOG FOR MPT GUI
// * 
// * This class makes the Heritage dialog box when the 
// * user would like to mark a certain coordinate that cannot
// * be dug up. 
// * 
// * @author Daniel
// * @author Celine
// *
// */
//public class HeritageDialog extends JDialog implements KeyListener {
//
//	private static final long serialVersionUID = 1L;
//	
//	private JLabel rowLabel;
//	private JLabel columnLabel;
//	private JTextField rowTextField;
//	private JTextField columnTextField;
//	private JButton confirmButton;
//	private JButton cancelButton;
//	
//	private GridBagConstraints constraints;
//	
//	private int row;
//	private String column;
//	private SubController subController;
//	
//	private JFrame frame;
//	
//	/**
//	 * For public HeritageDialog
//	 * 
//	 * This includes two labels, one for row, and one for column.
//	 * And then it adds the two labels, two text fields, and
//	 * two buttons into the GridBagLayout to make a simple dialog 
//	 * pop-up. 
//	 * 
//	 * @param title
//	 * @param subController
//	 */
//	public HeritageDialog(String title, SubController subController){
//		this.setTitle(title);
//		this.subController = subController;
//		setSize(200, 120);
//		setResizable(false);
//		setLayout(new GridBagLayout());
//
//		createTextFields();
//		createButtons();
//		
//		constraints = new GridBagConstraints();
//		constraints.insets = new Insets(3, 3, 3, 3);
//		
//		rowLabel = new JLabel("Row: ");
//		columnLabel = new JLabel("Column: ");
//		constraints.anchor = GridBagConstraints.WEST;
//		addComponent(rowLabel, 0, 0, 1, 1);
//		addComponent(columnLabel, 0, 1, 1, 1);
//		constraints.anchor = GridBagConstraints.EAST;
//		addComponent(rowTextField, 1, 0, 1, 1);
//		addComponent(columnTextField, 1, 1, 1, 1);
//		addComponent(confirmButton, 0, 2, 1, 1);
//		addComponent(cancelButton, 1, 2, 1, 1);
//	}
//
//	/**
//	 * For public void addComponent
//	 * 
//	 * @param component
//	 * @param column
//	 * @param row
//	 * @param width
//	 * @param height
//	 */
//	public void addComponent(JComponent component, int column, int row, int width, int height){
//		constraints.gridx = column;
//		constraints.gridy = row;
//		constraints.gridwidth = width;
//		constraints.gridheight = height;
//		this.add(component, constraints);
//	}
//	
//	/**
//	 * For private void createTextFields
//	 * 
//	 * This creates the two text fields (one for row,
//	 * and one for column) for the coordinate the user wants
//	 * to mark as "Heritage".
//	 */
//	private void createTextFields() {
//		rowTextField = new JTextField(5);
//		columnTextField = new JTextField(5);
//		
//		rowTextField.addKeyListener(this);
//		columnTextField.addKeyListener(this);
//	}
//	
//	/**
//	 * For private void createButtons
//	 * 
//	 * This creates exactly two buttons, each with ActionListeners.
//	 * The first is the "OK" button, which when clicked will take the
//	 * user input, and run it through other called methods. The second
//	 * one is the "Cancel" button, which will let the user click out
//	 * of the GUI without having to click the red X. 
//	 */
//	private void createButtons() {
//		confirmButton = new JButton("OK");
//		confirmButton.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				try{
//					row = Integer.parseInt(rowTextField.getText());
//					column = columnTextField.getText();
//					subController.markHeritage(row, column);
//				}
//				catch(NumberFormatException n){
//					JOptionPane.showMessageDialog(frame,
//						    "Uh oh! Looks like you typed in something wrong. Please try again.",
//						    "Error",
//						    JOptionPane.ERROR_MESSAGE);
//				}
//				catch(NullPointerException n){
//					JOptionPane.showMessageDialog(frame,
//						    "Uh oh! Looks like you typed in something wrong. Please try again.",
//						    "Error",
//						    JOptionPane.ERROR_MESSAGE);
//				}
//				finally{
//					dispose();
//				}
//			}
//		});
//		confirmButton.addKeyListener(this);
//		
//		cancelButton = new JButton("Cancel");
//		cancelButton.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				dispose();
//			}
//		});
//		cancelButton.addKeyListener(new KeyAdapter(){
//			@Override
//			public void keyPressed(KeyEvent e){
//				if(e.getKeyCode() == KeyEvent.VK_ENTER){
//					dispose();
//				}
//			}
//		});
//	}
//
//	/* (non-Javadoc)
//	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
//	 */
//	@Override
//	public void keyPressed(KeyEvent e) {
//		if(e.getKeyCode() == KeyEvent.VK_ENTER){
//			confirmButton.doClick();
//		}
//	}
//
//	/* (non-Javadoc)
//	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
//	 */
//	@Override
//	public void keyReleased(KeyEvent e) {
//	}
//
//	/* (non-Javadoc)
//	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
//	 */
//	@Override
//	public void keyTyped(KeyEvent e) {
//	}
//}
