package maze;

import java.awt.Font;
import java.util.ArrayList;

import userInterface.TextArea;

public class Maze {
	
	public String name;//Contain the name of the maze to later write it in the high score txt file
	private ArrayList <ArrayList<String>> description;//Contain the description of the Maze
	public char[][] dispMaze;//Contain the "picture" of the maze 
	
	// Let us create a constructor
	Maze(String mazeName, ArrayList <ArrayList<String>> mazeDescription, char[][] displayOfMaze )
	{
		//fill up the attribute with the input of the constructor
		name = mazeName;
		description = mazeDescription;
		dispMaze = displayOfMaze;
	}
	
	//Let us create a method to dipslay/refresh the maze. The function takes into account the player position
	public void displayMaze(int [] positionPlayer, TextArea mazeConsole)
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
				
				mazeConsole.updateText(toPrint+"\n");
				
			}
		
		}
			
	public void displayMaze(int [] positionPlayer)
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
