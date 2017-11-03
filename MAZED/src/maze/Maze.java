package maze;

import java.util.ArrayList;

public class Maze {
	
	public String name;//Contain the name of the maze to later write it in the high score txt file
	private ArrayList <ArrayList<String>> description;//Contain the description of the file

	Maze(String mazeName,ArrayList <ArrayList<String>> mazeDescription)
	{
		name = mazeName;
		description = mazeDescription;
	}
	
	public void displayMaze(int [] positionPlayer )
	{
		int [] sizeMaze = {0,0};
		sizeMaze[0] = Integer.parseInt(description.get(description.size()-1).get(0));
		sizeMaze[1] = sizeMaze[0];// Integer.parseInt(description.get(description.size()-1).get(1));
		
		String[] dispMaze = new String [(sizeMaze[0]+1)*2+1];
		for (int l=0; l<(sizeMaze[0]+1)*2+1;l++)
		{
			dispMaze[l]="";
		}
		
		
		for (int i=0; i<sizeMaze[0]+1;i++)
		{
			
			for(int j=0; j<sizeMaze[1]+1;j++)
			{
				
				if (i == sizeMaze[0])
				{
					String mazeElementSouth = description.get(1+i+j*(sizeMaze[0])).get(3);
					
					switch (mazeElementSouth)
					{
					case "wall": dispMaze[dispMaze.length-1]=dispMaze[dispMaze.length-1]+"+---+";
								 break;
					case "no"  : dispMaze[dispMaze.length-1]=dispMaze[dispMaze.length-1]+"   +";
								 break;
					}
					
				}
				
				String mazeElementNorth = description.get(1+i+j*(sizeMaze[0])).get(2);
				String mazeElementWest  = description.get(1+i+j*(sizeMaze[0])).get(5);
				
				switch (mazeElementNorth)
				{
				case "wall": dispMaze[i*2]=dispMaze[i*2]+"+---";
							 break;
				case "no"  : dispMaze[i*2]=dispMaze[i*2]+"+   ";
							 break;
				}
				
				switch (mazeElementWest)
				{
				case "wall": dispMaze[i*2+1]=dispMaze[i*2+1]+"|   ";
							 break;
				case "no"  : dispMaze[i*2+1]=dispMaze[i*2+1]+"+   ";
							 break;
				}
				

				if (j == sizeMaze[0])
				{
					dispMaze[i*2] = dispMaze[i*2]+"+";
					String mazeElementEast = description.get(1+i+j*(sizeMaze[0])).get(4);
					
					switch (mazeElementWest)
					{
					case "wall": dispMaze[i*2+1]=dispMaze[i*2+1]+"   |";
								 break;
					case "no"  : dispMaze[i*2+1]=dispMaze[i*2+1]+"   +";
								 break;
					}
					
					
				}
				
				
				
					
				
				}
				// Stopped Here !!!!!!!!! NEEED TO FIND A WAY TO READ THE DATA AND PRINT THE MAZE IN A ELEGANT WAY
				
			}
			for (String k:dispMaze)
			{
				System.out.println(k);
			}
		
		}
		
		
		





}
