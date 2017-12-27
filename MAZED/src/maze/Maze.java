package maze;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import userInterface.TextArea;

public class Maze {
	
	public String name;//Contain the name of the maze to later write it in the high score txt file
	private final ArrayList <ArrayList<String>> description;//Contain the description of the Maze
	private char[][] dispMaze;//Contain the "picture" of the maze 
	private static final int [] START_POS = {0,0};
	private static int[]  END_POS={0,0};
	
	// Let us create a constructor
	Maze(String mazeName, ArrayList <ArrayList<String>> mazeDescription, char[][] displayOfMaze )
	{
		//fill up the attribute with the input of the constructor
		this.name = mazeName;
		this.description = mazeDescription;
		this.dispMaze = displayOfMaze;
		END_POS[0] = Integer.parseInt(mazeDescription.get(mazeDescription.size()-1).get(0));
		END_POS[1] = Integer.parseInt(mazeDescription.get(mazeDescription.size()-1).get(0));		
	}
	
	//Let us create a method to dipslay/refresh the maze. The function takes into account the player position
	public void displayMaze(List<Point2D> playerPositions, TextArea mazeConsole)
	
	{			
			mazeConsole.clearText();
			int sizeMazeY = this.dispMaze.length;
			//int sizeMazeX = this.dispMaze[0].length;
					
					//((sizeMaze[0]+1)*2)-(i*2+1)][2+4*j]
			switch (playerPositions.size()) {
				case 1:{
					this.dispMaze[(sizeMazeY-1) - ((int) playerPositions.get(0).getX()*2 + 1)][2 + (int) playerPositions.get(0).getY() * 4] = 'P';
					break;
				}
				case 2:{
					this.dispMaze[(sizeMazeY-1) - ((int) playerPositions.get(0).getX()*2 + 1)][2 + (int) playerPositions.get(0).getY() * 4] = 'S';
					this.dispMaze[(sizeMazeY-1) - ((int) playerPositions.get(1).getX()*2 + 1)][2 + (int) playerPositions.get(1).getY() * 4] = 'P';
					break;
				}
				default:{
					this.dispMaze[(sizeMazeY-1) - ((int) playerPositions.get(0).getX()*2 + 1)][2 + (int) playerPositions.get(0).getY() * 4] = 'S';
					this.dispMaze[(sizeMazeY-1) - ((int) playerPositions.get(playerPositions.size()-2).getX()*2 + 1)][2 + (int) playerPositions.get(playerPositions.size()-2).getY() * 4] = ' ';
					this.dispMaze[(sizeMazeY-1) - ((int) playerPositions.get(playerPositions.size()-1).getX()*2 + 1)][2 + (int) playerPositions.get(playerPositions.size()-1).getY() * 4] = 'P';
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

	public int[] getEndMaze() {
		return END_POS;
	}
	public int[] getStartMaze() {
		return START_POS;
	}


}
