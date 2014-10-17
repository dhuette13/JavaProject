package archeologyp2.shared.gui;

import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;

public class PathDialogue extends JFrame{
	
	JComboBox cB = new JComboBox();
	
	PathDialogue(String title){
		super(title);
		pathFrame();
	}
	
	public void pathFrame(){
		JFrame frame = new JFrame();
		frame.setBounds(200,200,300,300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container pane = getContentPane();
		pane.setLayout(new FlowLayout());
	}
	
	public static void main(String[] args, String title){
		PathDialogue frame = new PathDialogue(title);
	}
	
}
