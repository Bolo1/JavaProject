package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Keeping track of these allow to modify them from outside ==> in game update
	public TextArea mazeConsole;//Where maze will be display
	public TextArea mazeText;//text will be displayed to the user
	public MyButtonListener buttonListener;

	public MainUI() {

		//Give a title
		setTitle("Maze Escape");
		//to properly exit when windows is closed
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
		Font font = new Font ("Candara", Font.BOLD, 40);
		title.setFont(font);
		title.setText("Maze Escape");
		titlePanel.add(title);

		//Create a second panel for text: panelText
		JPanel panelText = new JPanel();
		panelText.setBackground(Color.black);
		contentPane.add(panelText);

		//Create a Text area to display text to the user
		this.mazeText = new TextArea();
		Dimension prefDimText = new Dimension(800,150);
		this.mazeText.setPreferredSize(prefDimText);
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
		this.mazeConsole.setBackground(Color.black);
		this.mazeConsole.setForeground(Color.white);
		this.mazeConsole.setFont( new Font("monospaced", Font.PLAIN, 20) );
		this.mazeConsole.setEditable(false);

		mainPanel.add(this.mazeConsole);

		//Create a third panel to put buttons
		JPanel buttonPanel = new JPanel();
		GridBagLayout layoutButtonPanel = new GridBagLayout();
		buttonPanel.setLayout(layoutButtonPanel);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 40;//increase the size of the button
		c.ipady = 40;

		buttonPanel.setBackground(Color.darkGray);
		contentPane.add(buttonPanel);

		// Let us create a button listener to be able to know when they are pressed and which one was pressed
		this.buttonListener = new MyButtonListener();
		//create up arrow button
		MyArrowButton buttonUp = new MyArrowButton ((MyArrowButton.NORTH));
		c.gridx = 1;
		c.gridy = 0;

		buttonUp.setMnemonic(KeyEvent.VK_W);// alt+W allow to trigger the button
		buttonUp.setToolTipText("Alt+W");
		buttonUp.addActionListener(this.buttonListener);
		buttonUp.setActionCommand("Up");

		buttonPanel.add(buttonUp, c);

		MyArrowButton buttonLeft = new MyArrowButton ((MyArrowButton.WEST));
		c.gridx = 0;
		c.gridy = 1;
		//create left arrow button
		buttonLeft.setMnemonic(KeyEvent.VK_A);
		buttonLeft.setToolTipText("Alt+A");
		buttonLeft.setActionCommand("Left");
		buttonLeft.addActionListener(this.buttonListener);
		buttonPanel.add(buttonLeft, c);
		//create right arrow button
		MyArrowButton buttonRight= new MyArrowButton((MyArrowButton.EAST));
		c.gridx = 2;
		c.gridy = 1;

		buttonRight.setMnemonic(KeyEvent.VK_D);
		buttonRight.setToolTipText("Alt+D");
		buttonRight.setActionCommand("Right");
		buttonRight.addActionListener(this.buttonListener);
		buttonPanel.add(buttonRight, c);
		//create down arrow button
		MyArrowButton buttonDown = new MyArrowButton ((MyArrowButton.SOUTH));
		c.gridx = 1;
		c.gridy = 1;

		buttonDown.setMnemonic(KeyEvent.VK_S);
		buttonDown.setToolTipText("Alt+S");
		buttonDown.setActionCommand("Down");
		buttonDown.addActionListener(this.buttonListener);
		buttonPanel.add(buttonDown, c);

		//Add a fourth button for few more button for undo and AI
		JPanel buttonPanel2 = new JPanel();
		FlowLayout layoutButtonPanel2 = new FlowLayout();
		buttonPanel2.setLayout(layoutButtonPanel2);
		buttonPanel2.setBackground(Color.darkGray);
		contentPane.add(buttonPanel2);

		//undo button
		JButton undoButton = new JButton("undo");
		undoButton.setBackground(Color.black);
		undoButton.setForeground(Color.white);
		undoButton.addActionListener(this.buttonListener);
		undoButton.setActionCommand("undo");

		//First AI algorithm ==> Dijskra
		JButton AIAlg1 = new JButton("DijskraCalc");
		AIAlg1.setBackground(Color.black);
		AIAlg1.setForeground(Color.white);
		AIAlg1.addActionListener(this.buttonListener);
		AIAlg1.setActionCommand("AI1");

		//Second AI algorithm ==> Tremaux
		JButton AIAlg2 = new JButton("Tremaux");
		AIAlg2.setBackground(Color.black);
		AIAlg2.setForeground(Color.white);
		AIAlg2.addActionListener(this.buttonListener);
		AIAlg2.setActionCommand("AI2");

		buttonPanel2.add(undoButton);
		buttonPanel2.add(AIAlg1);
		buttonPanel2.add(AIAlg2);

	}
}
