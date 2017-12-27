package maze;
import player.Player;
import userInterface.MainUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.Timer;

public class MazeMain {
	public static void initGame()
	{

		//Game Initiation 
		MainUI frame = new MainUI();//Creation of the UI
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.pack();
		frame.setVisible(true);

		Scanner userInput = new Scanner(System.in);//Scanner for user input

		frame.mazeText.updateText(" Hello, Prince of Persia ! What is your name ? ");
		System.out.println(frame.mazeText.getText());
		String playerName = userInput.next();
		userInput.close();
		Point2D playerPosition = new Point2D.Double(0,0);
		Player player = new Player(playerName,playerPosition);
		frame.mazeText.clearText();
		frame.mazeText.updateText(player.getPlayerName() +", the Dahaka put you in a terrible maze full of trap and dangers!\nIt is also coming coming for you ! So hurry up! ");
		frame.mazeText.updateText("You need to get out of there !\nTo help you I put a map of the maze on which I will update your position.");


		File mazeFile = new File("MazeLvl1.txt");
		String fileName = mazeFile.getName().substring(0, mazeFile.getName().lastIndexOf('.'));
		//Call the read method from the class ReadsMazeFile giving the path of the file as input and store it in an 2D array list of string
		ArrayList <ArrayList<String>> mazeDescription = ReadsMazeFile.read(mazeFile.getAbsolutePath());
		char [][] displayOfMaze = ReadsMazeFile.mazeToChar(mazeDescription);
		Maze myMaze = new Maze (fileName,mazeDescription,displayOfMaze);


		myMaze.displayMaze();
		myMaze.displayMaze(player.getPlayerPosition(),frame.mazeConsole);

		int delay = 500;

		// Game is played here:
		final Timer gameTimer = new Timer(delay,null);
		gameTimer.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent evt) {
				//First task check if button was pressed

				if (frame.buttonListener.getWasPressed()!="") {

					if (player.canMove(frame.buttonListener.getWasPressed(),myMaze)){
						player.movePlayer(frame.buttonListener.getWasPressed(), myMaze.getMazeDescription());
					}else {}

					frame.buttonListener.resetWasPressed();					
					myMaze.displayMaze(player.getPlayerPosition(), frame.mazeConsole);

				}
				int nbOfSteps = (int) player.getPlayerPosition().size();
				int xPosPlayer = (int) player.getPlayerPosition().get(nbOfSteps-1).getX();
				int yPosPlayer = (int) player.getPlayerPosition().get(nbOfSteps-1).getY();

				if (xPosPlayer == myMaze.getEndMaze()[0] & yPosPlayer == myMaze.getEndMaze()[1]) {
					int bestSteps = 8;
					frame.mazeText.clearText();
					frame.mazeText.updateText("You managed to escape the Dahaka ! In only "+nbOfSteps + " steps. The best score till now is " + bestSteps +" steps"); 
					player.printPlayerScore(nbOfSteps, myMaze);
					gameTimer.stop();//End game if players arrived at Exit
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
