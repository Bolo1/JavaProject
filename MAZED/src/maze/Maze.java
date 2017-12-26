package maze;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import userInterface.TextArea;

public class Maze {
	
	public String name;//Contain the name of the maze to later write it in the high score txt file
	private final ArrayList <ArrayList<String>> description;//Contain the description of the Maze
	private final char[][] dispMaze;//Contain the "picture" of the maze 
	public static final int [] START_POS = {0,0};
	public static int[] endPos = {0,0};
	
	// Let us create a constructor
	Maze(String mazeName, ArrayList <ArrayList<String>> mazeDescription, char[][] displayOfMaze )
	{
		//fill up the attribute with the input of the constructor
		this.name = mazeName;
		this.description = mazeDescription;
		this.dispMaze = displayOfMaze;
		endPos[0] = Integer.parseInt(mazeDescription.get(mazeDescription.size()-1).get(0));
		endPos[1] = Integer.parseInt(mazeDescription.get(mazeDescription.size()-1).get(0));		
	}
	
	//Let us create a method to dipslay/refresh the maze. The function takes into account the player position
	public void displayMaze(List<Point2D> playerPositions, TextArea mazeConsole)
	
	{			
			mazeConsole.clearText();
			switch (playerPositions.size()) {
				case 1:{
					this.dispMaze[1+(int) playerPositions.get(0).getY()*2][2+(int) playerPositions.get(0).getX()*4] = 'P';
					break;
				}
				case 2:{
					this.dispMaze[1+(int) playerPositions.get(0).getY()*2][2+(int) playerPositions.get(0).getX()*4] = 'S';
					this.dispMaze[1+(int) playerPositions.get(1).getY()*2][2+(int) playerPositions.get(1).getX()*4] = 'P';
					break;
				}
				default:{
					this.dispMaze[1+(int) playerPositions.get(0).getY()*2][2+(int) playerPositions.get(0).getX()*4] = 'S';
					this.dispMaze[1+(int) playerPositions.get(playerPositions.size()-2).getY()*2][2+(int) playerPositions.get(playerPositions.size()-2).getX()*4] = ' ';
					this.dispMaze[1+(int) playerPositions.get(playerPositions.size()-1).getY()*2][2+(int) playerPositions.get(playerPositions.size()-1).getX()*4] = 'P';
				}
				
			}
			for (char[] u:this.dispMaze)
			{
				String toPrint ="";
				for(char v:u)
				{
					 toPrint =toPrint+v;
				}				
				mazeConsole.updateText(toPrint+"\n");				
			}		
		
		}
			
//	public void displayMaze(ArrayList<int[]> arrayList, TextArea mazeConsole)
//	{
//
//		
//			mazeConsole[1+arrayList[0]*2][2+arrayList[1]*4] = 'P';
//		
//		
//			for (char[] u:mazeConsole)
//			{
//				String toPrint ="";
//				for(char v:u)
//				{
//					 toPrint =toPrint+v;
//				}
//				System.out.println(toPrint);
//				
//			}
//			
//		}
//			
	public void displayMaze()
	{
		
			for (char[] u:this.getDispMaze())
			{
				String toPrint ="";
				for(char v:u)
				{
					 toPrint =toPrint+v;
				}
				System.out.println(toPrint);
			}
		
		}
	public  String getMazeDescription(int index1, int index2){
		
		return this.description.get(index1).get(index2);
	}
	
	public ArrayList<ArrayList<String>> getMazeDescription () {
		return this.description;
	}
	
		
	public char[][] getDispMaze(){
		return this.dispMaze;
	}




}
