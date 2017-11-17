package maze;
import player.Player;
import userInterface.MainUI;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

public class MazeMain {
	public static void initGame()
	{

		MainUI frame = new MainUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.pack();
		frame.setVisible(true);
		
		Scanner userInput = new Scanner(System.in);
		
		frame.mazeText.updateText(" Hello, Prince of Persia ! What is your name ? ");
		System.out.println(frame.mazeConsole.getText());
		String playerName = userInput.next();
		userInput.close();
		int [] playerPosition = {0,0};
		Player player = new Player(playerName,playerPosition);
		frame.mazeText.clearText();
		frame.mazeText.updateText(player.getPlayerName() +", the Dahaka put you in a terrible maze full of trap and dangers!\nIt is also coming coming for you ! So hurry up! ");
		frame.mazeText.updateText("You need to get out of there !\nTo help you I put a map of the maze on which I will update your position.");
		
		
		File mazeFile = new File("D:\\Documents\\Documents\\Unif\\PhD\\Courses\\Basic Programming\\Project\\javaProject\\MazeLvl1.txt");
		//Call the read method from the class ReadsMazeFile giving the path of the file as input and store it in an 2D array list of string
		ArrayList <ArrayList<String>> mazeDescription = ReadsMazeFile.read(mazeFile.getAbsolutePath());
		char [][] displayOfMaze = ReadsMazeFile.mazeToChar(mazeDescription);
		Maze myMaze = new Maze (mazeFile.getName(),mazeDescription,displayOfMaze);
		
		
		myMaze.displayMaze();
		myMaze.displayMaze(player.getPlayerPosition(),frame.mazeConsole);
		
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Create a maze file object
		
		MazeMain.initGame();

	}

}
