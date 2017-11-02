package maze;

import java.io.File;
import java.util.ArrayList;

public class MazeMain {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 
		String path = "D:\\Documents\\Documents\\Unif\\PhD\\Courses\\Basic Programming\\Project\\javaProject\\MazeLvl1.txt";
		File mazeFile = new File("D:\\Documents\\Documents\\Unif\\PhD\\Courses\\Basic Programming\\Project\\javaProject\\MazeLvl1.txt");
		ArrayList <ArrayList<String>> mazeDescription = ReadsMazeFile.read(path);
		Maze myMaze = new Maze (mazeFile.getName(),mazeDescription);

	}

}
