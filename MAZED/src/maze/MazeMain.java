package maze;

import java.io.File;
import java.util.ArrayList;

public class MazeMain {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Create a maze file object
		File mazeFile = new File("D:\\Documents\\Documents\\Unif\\PhD\\Courses\\Basic Programming\\Project\\javaProject\\MazeLvl1.txt");
		//Call the read method from the class ReadsMazeFile giving the path of the file as input and store it in an 2D array list of string
		ArrayList <ArrayList<String>> mazeDescription = ReadsMazeFile.read(mazeFile.getAbsolutePath());
		Maze myMaze = new Maze (mazeFile.getName(),mazeDescription);
		
		int[] positionPlayer = {0,0};
		myMaze.displayMaze(positionPlayer);

	}

}
