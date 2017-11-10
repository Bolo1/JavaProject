package userInterface;

import javax.swing.*;
import java.awt.*;


public class MainUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public MainUI() {
		setTitle("Maze Escape");
		setSize(1600,900);
		setLocation(150,100);
		addWindowListener(new CloseWindow());	
		
		//Create a first Panel: Title Panel
		JPanel titlePanel = new JPanel();
		Container contentPane = getContentPane();
		BoxLayout layout = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
		contentPane.setLayout(layout);
		titlePanel.setBackground(Color.darkGray);
		contentPane.add(titlePanel);
		
		//Create a text Area inside the panel
		TextArea title = new TextArea();
		title.setBackground(Color.darkGray);
		title.setForeground(Color.white);
		Font font = new Font ("Candara", Font.BOLD, 60);
		title.setFont(font);
		title.setText("Maze Escape");
		titlePanel.add(title);
		
		//Create a second panel: Main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.darkGray);
		contentPane.add(mainPanel);
		
		//Create a second Text area for displaying console
		TextArea mazeConsole = new TextArea();
		Dimension dim = new Dimension(700,500);
		mazeConsole.setPreferredSize(dim);
		mazeConsole.setBackground(Color.black);
		mainPanel.add(mazeConsole);
		
		
		
	
		
	}
	
	
	public static void main(String[] args) {
		JFrame frame = new MainUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
	}
}
