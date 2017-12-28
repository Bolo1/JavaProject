package maze;
import player.Player;
import userInterface.MainUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import miscellaneousItem.Item;

public class MazeMain {
	public static void initGame()
	{
		//UI initiation
		MainUI frame = new MainUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.pack();
		frame.setVisible(true);
		//Intro Text on UI
		frame.mazeText.updateText("Hello, you are the Prince of Persia, right? I was waiting for you,\nwhat is your name again ? ");
		
		//Open dialog for name
		String input = JOptionPane.showInputDialog(
				null, "Please Enter Your Name");
		String playerName = input;
		Point2D playerPosition = new Point2D.Double(0,0);
		Player player = new Player(playerName,playerPosition);
		
		//Follow up intro on UI
		frame.mazeText.clearText();
		frame.mazeText.updateText(player.getName() +", the Dahaka put you in a terrible maze full of trap and\ndangers! It is also coming for you ! So hurry up!\n");
		frame.mazeText.updateText("You need to get out of there ! To help you here is a map of the maze\non which I will update your position.");

		//Load, read and set the maze
		File mazeFile = new File("mazeEscape.txt");
		String fileName = mazeFile.getName().substring(0, mazeFile.getName().lastIndexOf('.'));
		//Call the read method from the class ReadsMazeFile giving the path of the file as input and store it in an 2D array list of string
		ArrayList <ArrayList<String>> mazeDescription =Maze.read(mazeFile.getAbsolutePath());
		char [][] displayOfMaze = Maze.toChar(mazeDescription);
		Maze myMaze = new Maze (fileName,mazeDescription,displayOfMaze);

		//display maze on console and on UI
		myMaze.display();
		myMaze.display(player.getPosition(),frame.mazeConsole);

		int delay = 500; //Refresh game every 0.5 sec
		
		int score = (myMaze.getSize()[0]+1)*(myMaze.getSize()[1]+1);
		
		// Game is played with a timer
		final Timer gameTimer = new Timer(delay,null);
		gameTimer.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent evt) {
				
				//First task check if button was pressed
				if (frame.buttonListener.getWasPressed()!="") {
					//Then check if player is allowed to move in that direction
					if (player.canMove(frame.buttonListener.getWasPressed(),myMaze,frame.mazeText)){
						// Move player
						player.move(frame.buttonListener.getWasPressed(), myMaze.getDescription());
					}else {}
					
					int nbOfSteps  = (int) player.getPosition().size()-1;
					int newScore   = score-nbOfSteps + player.scoreInventory();
					frame.mazeText.clearText();
					frame.mazeText.updateText("Inventory:"+player.displayInventory()+"\nNumber of Steps: " +  nbOfSteps+"\nScore: "+newScore);
					frame.buttonListener.resetWasPressed();					
					myMaze.display(player.getPosition(), frame.mazeConsole);

				}
				int nbOfSteps  = (int) player.getPosition().size()-1;
				int newScore   = score-nbOfSteps + player.scoreInventory();
				int xPosPlayer = (int) player.getPosition().get(nbOfSteps).getX();
				int yPosPlayer = (int) player.getPosition().get(nbOfSteps).getY();
				String object  = myMaze.getDescription(1+xPosPlayer*(myMaze.getSize()[0]+1)+yPosPlayer,myMaze.getNElemDesc()-1);

				switch (object) {
				case "no":
					break;
				case "S":
					break;
				case "E":
					frame.mazeText.clearText();
					frame.mazeText.updateText("You managed to escape the Dahaka ! In only "+nbOfSteps + " steps.\nYour score is "+ newScore); 
					player.printScore(nbOfSteps, myMaze);
					gameTimer.stop();//End game if players arrived at Exit
					break;
					
				default:
					Item item = new Item(object,player);
					player.pickUpItem(item, myMaze);
					frame.mazeText.clearText();
					frame.mazeText.updateText("Inventory:"+player.displayInventory()+"\nNumber of Steps: " + nbOfSteps+ "\nScore: "+newScore+"\nYou Picked up a "+ item.getType());
					break;
				}	
				

			}
		});

		gameTimer.start();
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Create a maze file object

		MazeMain.initGame();

	}

}
