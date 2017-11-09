package userInterface;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class MainUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public MainUI() {
		setTitle("Gotcha maze out");
		setSize(1600,900);
		setLocation(150,100);
		addWindowListener(new CloseWindow());	

		Container contentPane = getContentPane();
		Panel panel = new Panel();
		contentPane.add(panel);
		panel.setBackground(Color.darkGray);
		
		TextPane title = new TextPane();
		/*SimpleAttributeSet set = new SimpleAttributeSet();
		StyleConstants.setForeground(set, Color.white);
		StyleConstants.setBackground(set, Color.darkGray);
		title.setCharacterAttributes(set,true);*/
		title.setText("Maze Escape");
		panel.add(title);
		
		
		
	
		
	}
	
	
	public static void main(String[] args) {
		JFrame frame = new MainUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
