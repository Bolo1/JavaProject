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
	private JPanel titlePanel;
	private TextArea title;
	public TextArea mazeConsole;
	public TextArea mazeText;

	public MainUI() {
		setTitle("Maze Escape");
		setSize(1600,900);
		setLocation(150,100);
		addWindowListener(new CloseWindow());	
		
		//Create a first Panel: Title Panel
		this.titlePanel = new JPanel();
		Container contentPane = getContentPane();
		BoxLayout layout = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
		contentPane.setLayout(layout);
		this.titlePanel.setBackground(Color.darkGray);
		contentPane.add(this.titlePanel);
		
		//Create a text Area inside the panel
		this.title = new TextArea();
		this.title.setBackground(Color.darkGray);
		this.title.setForeground(Color.white);
		Font font = new Font ("Candara", Font.BOLD, 60);
		this.title.setFont(font);
		this.title.setText("Maze Escape");
		this.titlePanel.add(this.title);
		
		//Create a second panel for text: panelText
		
		JPanel panelText = new JPanel();
		panelText.setBackground(Color.black);
		contentPane.add(panelText);
		
		//Create a second Text area for displaying console
		this.mazeText = new TextArea();
		Dimension minDimText = new Dimension(500,400);
		Dimension maxDimText = new Dimension(1500,1200);
		//this.mazeConsole.setPreferredSize(maxDim);
		this.mazeText.setMaximumSize(maxDimText);
		this.mazeText.setMinimumSize(minDimText);
		this.mazeText.setBackground(Color.black);
		this.mazeText.setForeground(Color.white);
		this.mazeText.setFont( new Font("monospaced", Font.PLAIN, 20) );
		panelText.add(this.mazeText);
		
		
		
		//Create a second panel: Main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.black);
		contentPane.add(mainPanel);
		
		//Create a second Text area for displaying console
		this.mazeConsole = new TextArea();
		Dimension minDim = new Dimension(500,400);
		Dimension maxDim = new Dimension(1500,1200);
		//this.mazeConsole.setPreferredSize(maxDim);
		this.mazeConsole.setMaximumSize(maxDim);
		this.mazeConsole.setPreferredSize(minDim);
		this.mazeConsole.setBackground(Color.black);
		this.mazeConsole.setForeground(Color.white);
		this.mazeConsole.setFont( new Font("monospaced", Font.PLAIN, 40) );
		
		
		mainPanel.add(this.mazeConsole);
		
		
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
	
	/*public TextArea getMazeConsole(){
		return this.mazeConsole;
	}*/
	
	
	public static void main(String[] args) {
		//JFrame frame = new MainUI();
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.pack();
		//frame.setVisible(true);
		
		
	}
}
