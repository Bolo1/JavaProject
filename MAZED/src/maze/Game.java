package maze;
import player.Player;
import userInterface.MainUI;
import userInterface.TextArea;
import item.Item;
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
	static String mode;
	public static void initGame(String fileName)
	{
		//UI initiation
MainUI frame = new MainUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //Full screen
		frame.setVisible(true);//show the UI

		//Intro Text on UI
		frame.mazeText.updateText("Hello, you are the Prince of Persia, right? I was waiting for you,\nwhat is your name again ? ");

		//Open dialog for player name
		String input = JOptionPane.showInputDialog(
				null, "Please Enter Your Name");
		String playerName = input;
		if (input==null) {
			playerName = "Player";
		}
		//Create player object
		Player player = new Player(playerName);

		//Follow up intro on UI
		frame.mazeText.clearText();
		frame.mazeText.updateText(player.getName() +", the Dahaka put you in a terrible maze full of trap and\ndangers! It is also coming for you ! So hurry up!\n");
		frame.mazeText.updateText("You need to get out of there ! To help you here is a map of the maze\non which I will update your position.");

		//Load, read and set the maze
		File mazeFile = new File(fileName);
		String fileNameNoExt = mazeFile.getName().substring(0, mazeFile.getName().lastIndexOf('.'));
		//Call the read method from the class ReadsMazeFile giving the path of the file as input and store it in an 2D array list of string
		ArrayList <ArrayList<String>> mazeDescription = Maze.read(mazeFile.getAbsolutePath());
		//create maze Object based on name and description
		Maze myMaze = new Maze (fileNameNoExt,mazeDescription);
		//Update player position to the start of the maze (in case it is not in 0,0)
		player.setPosition(new Point2D.Double(myMaze.getStart()[0],myMaze.getStart()[1]));
		
		String input2 = JOptionPane.showInputDialog(
				null, "Do you want to play in hard mode (yes or no)");
		if(input2!=null) {
		switch(input2) {
		case "yes":
			Game.mode = "hard";
			break;
		case "Yes":
			Game.mode = "hard";
			break;
		case "YES":
			Game.mode = "hard";
			break;
		case "y":
			Game.mode = "hard";
			break;
		case "Y":
			Game.mode = "hard";
			break;
		default:
			Game.mode = "normal";
			myMaze.display();//console
		}}else {
			Game.mode ="normal";
			System.out.println("User cancel the choice of the mode, continue with Normal");
			myMaze.display();//console
		}
		
		//display maze on console and on UI
		myMaze.display(player.getCurrentPosition(),frame.mazeConsole,Game.mode,player.getSight());//UI
		

		int delay = 500; //To Refresh game every 0.5 sec
		//Update initial score depending on the size of the maze
		player.updateScore((myMaze.getSize()[0]+1)*(myMaze.getSize()[1]+1));
		//initialize player history
		player.updateHistory();

		// Game is played with a timer that perform action every delay
		final Timer gameTimer = new Timer(delay,null);
		gameTimer.addActionListener(new ActionListener(){
			//Action to be repeated at each timer
			public void actionPerformed (ActionEvent evt) {

				//Check if a button was pressed
				if (frame.buttonListener.getWasPressed()!="") {
					//action depending on which button type
					switch (frame.buttonListener.getTypePressed()) {
					case "dir"://a direction was pressed
						//Check if player is allowed to move in that direction==> no wall?
						boolean canMove = player.canMove(frame.buttonListener.getWasPressed(),myMaze);
						if (canMove){

							// Move player
							player.move(frame.buttonListener.getWasPressed(), myMaze.getDescription());

							//Refresh the maze
							myMaze.display(player.getCurrentPosition(),frame.mazeConsole,Game.mode,player.getSight());

							//Refresh text
							frame.mazeText.clearText();
							frame.mazeText.updateText("Inventory:"+player.displayInventory()+"\nNumber of Steps: " +  player.getNbOfSteps()+"\nScore: "+ player.getScore());

							//Add text when player cross special elements
							displaySpeInfo(player.getCanMove(), frame.mazeText, canMove);

							//Reset the button indicator
							frame.buttonListener.resetWasPressed();
							frame.buttonListener.resetTypePressed();

							//Check for objects to pickup on the new position
							int xPosPlayer = (int) player.getCurrentPosition().getX();
							int yPosPlayer = (int) player.getCurrentPosition().getY();
							String object  = myMaze.getDescription(1+xPosPlayer*(myMaze.getSize()[0]+1)+yPosPlayer,myMaze.getNElemDesc()-1);

							switch (object) {
							case "no"://no object, no action
								break;

							case "start"://no object, no action
								break;

							case "end"://end of Maze
								//update text
								frame.mazeText.clearText();
								frame.mazeText.updateText("You managed to escape the Dahaka ! In only "+player.getNbOfSteps() + " steps.\nYour score is "+ player.getScore()); 
								//save score
								player.printScore(myMaze.getName());
								//End game if players arrived at Exit
								gameTimer.stop();
								break;

							default:// if there is an item
								//create an item corresponding to the item in the maze description
								Item item = new Item(object);
								// store the item in player inventory
								player.pickUpItem(item, myMaze);
								//use item==> only use instantanious object e.g. Torch 
								player.useItem(item);
								//Update Text to inform player
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
					case "undo"://Allow user to go back on his move
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
							myMaze.display(player.getCurrentPosition(),frame.mazeConsole,Game.mode,player.getSight());
						}

						break;
					case "AIsolving"://Allow to solve the maze automatically ==> give a number of step to go out of the maze
						switch(frame.buttonListener.getWasPressed()) {
						case "Dijkstra":

							//Reset the button indicator
							frame.buttonListener.resetWasPressed();
							frame.buttonListener.resetTypePressed();

							//create a player for the AI
							Player dijkstraAlg = new Player("Dijsktra");
							dijkstraAlg.updateScore((myMaze.getSize()[0]+1)*(myMaze.getSize()[1]+1));

							//Dijkstra implementation
							int minStepDijkstra = Dijkstra.calc(myMaze);
							//update score
							dijkstraAlg.updateScore(-minStepDijkstra);
							//update steps
							dijkstraAlg.updateNbOfSteps(minStepDijkstra);
							//update text
							frame.mazeText.clearText();
							frame.mazeText.updateText("Dijkstra calculation was succesful !\nThe minimum number of step to go out of this maze is "+ minStepDijkstra +" steps.\nThe equivalent score is "+dijkstraAlg.getScore());
							//Print score
							dijkstraAlg.printScore(myMaze.getName());

							break;

						case "Tremaux":

							//Reset the button indicator
							frame.buttonListener.resetWasPressed();
							frame.buttonListener.resetTypePressed();

							//create a player for the AI
							Player tremauxAlg = new Player("Tremaux");
							tremauxAlg.updateScore((myMaze.getSize()[0]+1)*(myMaze.getSize()[1]+1));
							//Tremaux calculation
							int stepTremaux = Tremaux.mainCalc(myMaze);
							//update score
							tremauxAlg.updateScore(-stepTremaux);
							//update steps
							tremauxAlg.updateNbOfSteps(stepTremaux);
							//update text
							frame.mazeText.clearText();
							frame.mazeText.updateText("Tremaux calculation was succesful !\nThe algorithm exited the maze in "+ stepTremaux +" steps.\nThe equivalent score is "+tremauxAlg.getScore());
							//printScore
							tremauxAlg.printScore(myMaze.getName());
							break;
						}
						//reset button 
						frame.buttonListener.resetWasPressed();	

						break;
					}
				}
			}
		});
		//Start the timer
		gameTimer.start();
	}
	//Method to display info to the player when special events occur
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
			default:
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
