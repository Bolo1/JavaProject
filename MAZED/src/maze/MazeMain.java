package maze;
import player.Player;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class MazeMain {
	public static void initGame()
	{
		Scanner userInput = new Scanner(System.in);
		
		
		System.out.println(" Hello, Prince of Persia ! What is your name ? ");
		String playerName = userInput.next();
		userInput.close();
		int [] playerPosition = {0,0};
		Player player = new Player(playerName,playerPosition);
		
		System.out.println(player.getPlayerName() +" The Dahaka put you in a terrible maze full of trap and dangers and, it is coming coming for you ! So hurry up! ");
		System.out.println("You need to get out of there ! To help you I put a map of the maze on which I will update your position.");
		
		
		File mazeFile = new File("D:\\Documents\\Documents\\Unif\\PhD\\Courses\\Basic Programming\\Project\\javaProject\\MazeLvl1.txt");
		//Call the read method from the class ReadsMazeFile giving the path of the file as input and store it in an 2D array list of string
		ArrayList <ArrayList<String>> mazeDescription = ReadsMazeFile.read(mazeFile.getAbsolutePath());
		char [][] displayOfMaze = ReadsMazeFile.mazeToChar(mazeDescription);
		Maze myMaze = new Maze (mazeFile.getName(),mazeDescription,displayOfMaze);
		
		
		myMaze.displayMaze();
		myMaze.displayMaze(player.getPlayerPosition());
		
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Create a maze file object
		
		MazeMain.initGame();

	}

}
