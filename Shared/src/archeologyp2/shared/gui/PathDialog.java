package archeologyp2.shared.gui;

import javax.swing.*;   
import java.awt.*;
import java.awt.event.*;

public class PathDialog extends JFrame implements ActionListener{
	
	JLabel text1;
	JTextField x;
	JButton button;
	
	PathDialog(){
		super("Title Here");

		setBounds(200,200,500,100);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container pane = getContentPane();
		pane.setLayout(new FlowLayout());
		
		text1 = new JLabel("Path: ");
		x = new JTextField(25);
		button = new JButton("OK");
		button.addActionListener(this);
		
		pane.add(text1);
		pane.add(x);
		pane.add(button);
		
		paintComponents(getGraphics());
	}
	
	public static void main(String[] args){
		PathDialog frame = new PathDialog();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if((e.getSource()==button) && (e.getSource()==x))
			text1.setText("Ay man"); //just getting it to do whatever
		
	}
	
}
