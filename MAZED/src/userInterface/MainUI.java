package userInterface;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;

import java.awt.*;
import java.awt.event.KeyEvent;


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
		
		
		//Create a third panel to put buttons
		
		JPanel buttonPanel = new JPanel();
		GridBagLayout layoutButtonPanel = new GridBagLayout();
		
		buttonPanel.setLayout(layoutButtonPanel);
		GridBagConstraints c = new GridBagConstraints();
		buttonPanel.setBackground(Color.darkGray);
		contentPane.add(buttonPanel);
		
		
		//Create buttons
		//JButton buttonUp = new JButton("Up");
		BasicArrowButton buttonUp = new BasicArrowButton ((BasicArrowButton.NORTH));
		buttonUp.setBackground(Color.white);
		buttonUp.setForeground(Color.blue);
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 60;
		c.ipady = 60;
		
		c.gridx = 1;
		c.gridy = 0;
		
		buttonUp.setMnemonic(KeyEvent.VK_W);
		buttonUp.setToolTipText("Alt+W");
		
		buttonPanel.add(buttonUp, c);
		
		BasicArrowButton buttonLeft = new BasicArrowButton ((BasicArrowButton.WEST));
		buttonLeft.setBackground(Color.white);
		buttonLeft.setForeground(Color.blue);
		c.gridx = 0;
		c.gridy = 1;
		buttonPanel.add(buttonLeft, c);
		
		buttonLeft.setMnemonic(KeyEvent.VK_A);
		buttonLeft.setToolTipText("Alt+A");
		
		BasicArrowButton buttonRight= new BasicArrowButton ((BasicArrowButton.EAST));
		buttonRight.setBackground(Color.white);
		buttonRight.setForeground(Color.blue);
		c.gridx = 2;
		c.gridy = 1;
		
		buttonRight.setMnemonic(KeyEvent.VK_D);
		buttonRight.setToolTipText("Alt+D");
		
		buttonPanel.add(buttonRight, c);
		
		
		BasicArrowButton buttonDown = new BasicArrowButton ((BasicArrowButton.SOUTH));
		buttonDown.setBackground(Color.white);
		buttonDown.setForeground(Color.blue);
		c.gridx = 1;
		c.gridy = 1;
		
		buttonDown.setMnemonic(KeyEvent.VK_S);
		buttonDown.setToolTipText("Alt+S");
		buttonPanel.add(buttonDown, c);
		
		
	}
	
	
	public static void main(String[] args) {
		JFrame frame = new MainUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		
	}
}
