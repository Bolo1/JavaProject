package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel titlePanel;
	private TextArea title;
	public TextArea mazeConsole;
	public TextArea mazeText;
	public MyArrowButton buttonUp;
	public MyArrowButton buttonLeft;
	public MyArrowButton buttonRight;
	public MyArrowButton buttonDown;
	public MyButtonListener buttonListener;
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
		Dimension maxDim = new Dimension(2000,1600);
		//this.mazeConsole.setPreferredSize(maxDim);
		this.mazeConsole.setMaximumSize(maxDim);
		this.mazeConsole.setMinimumSize(minDim);
		this.mazeConsole.setBackground(Color.black);
		this.mazeConsole.setForeground(Color.white);
		this.mazeConsole.setFont( new Font("monospaced", Font.PLAIN, 40) );
		this.mazeConsole.setEditable(false);
		
		mainPanel.add(this.mazeConsole);
		
		
		//Create a third panel to put buttons
		
		JPanel buttonPanel = new JPanel();
		GridBagLayout layoutButtonPanel = new GridBagLayout();
		
		buttonPanel.setLayout(layoutButtonPanel);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 60;
		c.ipady = 60;
		
		buttonPanel.setBackground(Color.darkGray);
		contentPane.add(buttonPanel);
		
		// Let us create a button listener
		
		this.buttonListener = new MyButtonListener();
		
		this.buttonUp = new MyArrowButton ((MyArrowButton.NORTH));
		c.gridx = 1;
		c.gridy = 0;
		
		this.buttonUp.setMnemonic(KeyEvent.VK_W);
		this.buttonUp.setToolTipText("Alt+W");
		this.buttonUp.addActionListener(this.buttonListener);
		this.buttonUp.setActionCommand("Up");
		
		buttonPanel.add(this.buttonUp, c);
		
		this.buttonLeft = new MyArrowButton ((MyArrowButton.WEST));
		c.gridx = 0;
		c.gridy = 1;
		
		this.buttonLeft.setMnemonic(KeyEvent.VK_A);
		this.buttonLeft.setToolTipText("Alt+A");
		this.buttonLeft.setActionCommand("Left");
		this.buttonLeft.addActionListener(this.buttonListener);
		buttonPanel.add(this.buttonLeft, c);
		
		
		this.buttonRight= new MyArrowButton((MyArrowButton.EAST));
		c.gridx = 2;
		c.gridy = 1;
		
		this.buttonRight.setMnemonic(KeyEvent.VK_D);
		this.buttonRight.setToolTipText("Alt+D");
		this.buttonRight.setActionCommand("Right");
		this.buttonRight.addActionListener(this.buttonListener);
		buttonPanel.add(this.buttonRight, c);
		
		this.buttonDown = new MyArrowButton ((MyArrowButton.SOUTH));
		c.gridx = 1;
		c.gridy = 1;
		
		this.buttonDown.setMnemonic(KeyEvent.VK_S);
		this.buttonDown.setToolTipText("Alt+S");
		this.buttonDown.setActionCommand("Down");
		this.buttonDown.addActionListener(this.buttonListener);
		buttonPanel.add(this.buttonDown, c);
		
	}
	

	
	public static void main(String[] args) {
	
		
	}
	

}
