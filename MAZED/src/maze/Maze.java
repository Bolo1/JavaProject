package maze;

import java.util.ArrayList;

import userInterface.TextArea;

public class Maze {
	
	public String name;//Contain the name of the maze to later write it in the high score txt file
	private ArrayList <ArrayList<String>> description;//Contain the description of the Maze
	public char[][] dispMaze;//Contain the "picture" of the maze 
	public static final int [] START_POS = {0,0};
	public static int[] endPos = {0,0};
	
	// Let us create a constructor
	Maze(String mazeName, ArrayList <ArrayList<String>> mazeDescription, char[][] displayOfMaze )
	{
		//fill up the attribute with the input of the constructor
		name = mazeName;
		description = mazeDescription;
		dispMaze = displayOfMaze;
		Maze.endPos[0] = Integer.parseInt(mazeDescription.get(mazeDescription.size()-1).get(0));
		Maze.endPos[1] = Integer.parseInt(mazeDescription.get(mazeDescription.size()-1).get(0));		
	}
	
	//Let us create a method to dipslay/refresh the maze. The function takes into account the player position
	public void displayMaze(int [] positionPlayer, TextArea mazeConsole)
	{
			mazeConsole.clearText();
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
	
	//public void setEndPos() {
		//this.endPos[0] = Integer.parseInt(this.description.get(this.description.size()-1).get(0));
		//this.endPos[1] = Integer.parseInt(this.description.get(this.description.size()-1).get(0));
	//}
		





}
