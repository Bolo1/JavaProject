package maze;
import player.Player;
import userInterface.MainUI;
import userInterface.TextArea;
import miscellaneousItem.Item;
import mazeSolvingAlgorithm.Dijkstra;
import mazeSolvingAlgorithm.Tremaux;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;



public class Game {
	public static void initGame(String fileName)
	{
		//UI initiation
		MainUI frame = new MainUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//frame.setUndecorated(true);
		frame.setVisible(true);
		//Intro Text on UI
		frame.mazeText.updateText("Hello, you are the Prince of Persia, right? I was waiting for you,\nwhat is your name again ? ");

		//Open dialog for name
		String input = JOptionPane.showInputDialog(
				null, "Please Enter Your Name");
		String playerName = input;
		if (input==null) {
			playerName = "Player";
		}
		
		Point2D playerPosition = new Point2D.Double(0,0);
		Player player = new Player(playerName,playerPosition);

		//Follow up intro on UI
		frame.mazeText.clearText();
		frame.mazeText.updateText(player.getName() +", the Dahaka put you in a terrible maze full of trap and\ndangers! It is also coming for you ! So hurry up!\n");
		frame.mazeText.updateText("You need to get out of there ! To help you here is a map of the maze\non which I will update your position.");

		//Load, read and set the maze
		File mazeFile = new File(fileName);
		String fileNameNoExt = mazeFile.getName().substring(0, mazeFile.getName().lastIndexOf('.'));
		//Call the read method from the class ReadsMazeFile giving the path of the file as input and store it in an 2D array list of string
		ArrayList <ArrayList<String>> mazeDescription = Maze.read(mazeFile.getAbsolutePath());
		Maze myMaze = new Maze (fileNameNoExt,mazeDescription);

		//display maze on console and on UI
		myMaze.display();
		myMaze.display(player.getCurrentPosition(),frame.mazeConsole);

		int delay = 500; //To Refresh game every 0.5 sec
		//Update initial score depending on the size of the maze
		player.updateScore((myMaze.getSize()[0]+1)*(myMaze.getSize()[1]+1));
		// Game is played with a timer that perform action every delay
		final Timer gameTimer = new Timer(delay,null);
		gameTimer.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent evt) {

				//First task check if button was pressed
				if (frame.buttonListener.getWasPressed()!="") {
					switch (frame.buttonListener.getTypePressed()) {
					case "dir":
						//Check if player is allowed to move in that direction
						boolean canMove = player.canMove(frame.buttonListener.getWasPressed(),myMaze);
						if (canMove){

							// Move player
							player.move(frame.buttonListener.getWasPressed(), myMaze.getDescription());
							
							//Refresh the maze
							myMaze.display(player.getCurrentPosition(), frame.mazeConsole);
							
							//Refresh text
							frame.mazeText.clearText();
							frame.mazeText.updateText("Inventory:"+player.displayInventory()+"\nNumber of Steps: " +  player.getNbOfSteps()+"\nScore: "+ player.getScore());
							
							//Add text when player cross special elements
							displaySpeInfo(player.getCanMove(), frame.mazeText, canMove);

							//Reset the button indicator
							frame.buttonListener.resetWasPressed();		
							
							//Check for objects to pickup on the new position
							int xPosPlayer = (int) player.getCurrentPosition().getX();
							int yPosPlayer = (int) player.getCurrentPosition().getY();
							String object  = myMaze.getDescription(1+xPosPlayer*(myMaze.getSize()[0]+1)+yPosPlayer,myMaze.getNElemDesc()-1);
							
							switch (object) {
							case "no":
								break;

							case "start":
								break;

							case "end":
								frame.mazeText.clearText();
								frame.mazeText.updateText("You managed to escape the Dahaka ! In only "+player.getNbOfSteps() + " steps.\nYour score is "+ player.getScore()); 
								player.printScore(myMaze.getName());
								gameTimer.stop();//End game if players arrived at Exit
								break;

							default:
								Item item = new Item(object);
								player.pickUpItem(item, myMaze);
								player.useItem(item);
								//int tmpScore   = score-nbOfSteps + player.scoreInventory();
								frame.mazeText.clearText();
								frame.mazeText.updateText("Inventory:"+player.displayInventory()+"\nNumber of Steps: " + player.getNbOfSteps()+ "\nScore: "+player.getScore()+"\nYou Picked up a "+ item.getName());
								break;
							}
							
							//Update player History
							player.updateHistory();
							
						}else {
							//Update text
							frame.mazeText.clearText();
							frame.mazeText.updateText("Inventory:"+player.displayInventory()+"\nNumber of Steps: " + player.getNbOfSteps()+"\nScore: "+player.getScore());
							//Add text to explain player why he could not move
							displaySpeInfo(player.getCanMove(), frame.mazeText, canMove);
						}
						break;
					case "undo":
						//Undo last move
						if (player.getCurrentPosition().getX()==0 & player.getCurrentPosition().getY()==0) {
							//update text
							frame.mazeText.clearText();
							frame.mazeText.updateText("Inventory:"+player.displayInventory()+"\nNumber of Steps: " +  player.getNbOfSteps()+"\nScore: "+ player.getScore());
							frame.mazeText.updateText("\nYou cannot escape the maze by trying to go back in time!");
						}else {
						frame.buttonListener.resetWasPressed();					
						player.undoMove();
						//update text
						frame.mazeText.clearText();
						frame.mazeText.updateText("Inventory:"+player.displayInventory()+"\nNumber of Steps: " +  player.getNbOfSteps()+"\nScore: "+ player.getScore());
						frame.mazeText.updateText("\nYou went back in time");
						//update maze
						myMaze.display(player.getCurrentPosition(), frame.mazeConsole);
						}
						
						break;
					case "AIsolving":
						switch(frame.buttonListener.getWasPressed()) {
						case "Dijkstra":
							int minStepDijkstra = Dijkstra.calc(myMaze);
							frame.mazeText.clearText();
							frame.mazeText.updateText("Dijkstra calculation was succesful !\nThe minimum number of step to go out of this maze is "+ minStepDijkstra +" steps.");
							break;
						case "Tremaux":
							int stepTremaux = Tremaux.mainCalc(myMaze);
							frame.mazeText.clearText();
							frame.mazeText.updateText("Tremaux calculation was succesful !\nThe minimum number of step to go out of this maze is "+ stepTremaux +" steps.");
							break;
						}
						frame.buttonListener.resetWasPressed();	
						
						break;
					}
				}
			}
		});

		gameTimer.start();
	}
	
	public static void displaySpeInfo(String couldMove, TextArea mazeText, boolean canMove) {
		
		if(canMove) {
			switch (couldMove) {
			case "breakable":
				mazeText.updateText("\nYou broke down the wall using your hammer !");
				break;
			case "door":
				mazeText.updateText("\nYou opened the door with your key !");
				break;
			case "fake":
				mazeText.updateText("\nYou went through a fake wall !");
				break;
			}	
		}else {
			switch (couldMove) {
		case "breakable":
			mazeText.updateText("\nYou need a Hammer to break this wall !");
			break;
		case "door":
			mazeText.updateText("\nYou need a key to open this door !");
			break;
		default:
			mazeText.updateText("\nYou cannot go through this wall !");
		}
		}
	}
}
