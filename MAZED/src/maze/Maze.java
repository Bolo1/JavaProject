package maze;

import java.util.ArrayList;

public class Maze {
	
	public String name;//Contain the name of the maze to later write it in the high score txt file
	private ArrayList <ArrayList<String>> description;//Contain the description of the file
	public char[][] dispMaze;

	Maze(String mazeName, ArrayList <ArrayList<String>> mazeDescription, char[][] displayOfMaze )
	{
		name = mazeName;
		description = mazeDescription;
		dispMaze = displayOfMaze;
	}
	
	public void displayMaze(int [] positionPlayer )
	{
			dispMaze[1+positionPlayer[0]*2][2+positionPlayer[1]*4] = 'P';
		
		
			for (char[] u:dispMaze)
			{
				String toPrint ="";
				for(char v:u)
				{
					 toPrint =toPrint+v;
				}
				System.out.println(toPrint);
			}
		
		}
			
	public void displayMaze()
	{
		
			for (char[] u:dispMaze)
			{
				String toPrint ="";
				for(char v:u)
				{
					 toPrint =toPrint+v;
				}
				System.out.println(toPrint);
			}
		
		}
		





}
