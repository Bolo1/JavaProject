package maze;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
//import java.util.List;
import java.util.Scanner;

import userInterface.TextArea;

public class Maze {
	
	private String name;//Contain the name of the maze to later write it in the high score txt file
	private ArrayList <ArrayList<String>> description;//Contain the description of the Maze
	private  int [] startPos= {0,0};
	private  int[]  endPos={0,0};
	private int[] sizeMaze= {0,0};
	private int nElemDesc = 0;
	
	// Let us create a constructor
	Maze(String mazeName, ArrayList <ArrayList<String>> mazeDescription )
	{
		//fill up the attribute with the input of the constructor
		this.name = mazeName;
		this.description = mazeDescription;
		
		endPos = setEndPos();
		startPos = setStartPos();
		sizeMaze = setSize();
		nElemDesc = this.description.get(0).size();
	}
	
	public String getName() {
		return this.name;
	}
	
	//Let us create a method to dipslay/refresh the maze. The function takes into account the player position
	public void display(Point2D playerPositions, TextArea mazeConsole)
	
	{			
			char [][] maze2Display = this.toChar(this.description);
			mazeConsole.clearText();
			int sizeMazeY = maze2Display.length;
			
			if(playerPositions.getX() == this.startPos[0] & playerPositions.getY() == this.startPos[1]) {
				maze2Display[(sizeMazeY-1) - (this.startPos[0]*2 + 1)][2 + this.startPos[1] * 4] = 'P';
			}else {
				
				maze2Display[(sizeMazeY-1) - (this.startPos[0]*2 + 1)][2 + this.startPos[1] * 4] = 'S';
				maze2Display[(sizeMazeY-1) - ((int) playerPositions.getX()*2 + 1)][2 + (int) playerPositions.getY() * 4] = 'P';
			}

			for (char[] u:maze2Display)
			{
				String toPrint ="";
				for(char v:u)
				{
					 toPrint =toPrint+v;
				}				
				mazeConsole.updateText(toPrint+"\n");				
			}		
		
		}
					
	public void display()
	{
		char [][] maze2Display = this.toChar(this.description);
			for (char[] u:maze2Display)
			{
				String toPrint ="";
				for(char v:u)
				{
					 toPrint =toPrint+v;
				}
				System.out.println(toPrint);
			}
		
		}
	public  String getDescription(int index1, int index2){
		
		return this.description.get(index1).get(index2);
	}
	
	public void modDescription(int index1, int index2, String mod) {
		this.description.get(index1).set(index2,mod);
	}
	
	public ArrayList<ArrayList<String>> getDescription () {
		return this.description;
	}

	public int[] getEnd() {
		return endPos;
	}
	public int[] getStart() {
		return startPos;
	}
	
	public int[] setEndPos() {
		int sizeMazeX = this.description.get(0).size();
		int sizeMazeY = this.description.size();
		int[] endPos={0,0};
		for (int i=0; i<sizeMazeY; i++) {
			String mazeElem = this.description.get(i).get(sizeMazeX-1);
			switch (mazeElem) {
			case "end": endPos[0] = Integer.parseInt(this.description.get(i).get(0));
					  endPos[1] = Integer.parseInt(this.description.get(i).get(1));
			break;	
			case "E": endPos[0] = Integer.parseInt(this.description.get(i).get(0));
			  endPos[1] = Integer.parseInt(this.description.get(i).get(1));
			  break;	
			default:
			break;
			}
		}
		return endPos;
	}
	
	public int[] setStartPos() {
		int sizeMazeX = this.description.get(0).size();
		int sizeMazeY = this.description.size();
		int[] startPos={0,0};
		for (int i=0; i<sizeMazeY; i++) {
			String mazeElem = this.description.get(i).get(sizeMazeX-1);
			
			switch (mazeElem) {
			case "start":
				startPos[0] = Integer.parseInt(this.description.get(i).get(0));
				startPos[1] = Integer.parseInt(this.description.get(i).get(1));
			break;
			case "S":
				startPos[0] = Integer.parseInt(this.description.get(i).get(0));
				startPos[1] = Integer.parseInt(this.description.get(i).get(1));
			break;
			default:
			break;
			}
		}
		return startPos;
	}
	
	public int[] setSize() {
		int sizeMazeY  = this.description.size();
		int[] sizeMaze = {Integer.parseInt(this.description.get(sizeMazeY-1).get(0)),Integer.parseInt(this.description.get(sizeMazeY-1).get(1))};
 		return sizeMaze;
	}
	
	public int[] getSize() {
		return this.sizeMaze;
	}
	
	public int getNElemDesc() {
		return this.nElemDesc;
	}
	
	// Declare a public method that will read the maze file
		public static ArrayList <ArrayList<String>>  read(String mazeFilePath)
		{
			//Declare a 2D array List of string to store each value of the text file individually
			ArrayList <ArrayList<String>> maze = new ArrayList <ArrayList<String>>();
			// Create an object file based on the file path input
			File file = new File(mazeFilePath);

			try {	
				//Create a scanner object that will scan lines
				Scanner scannerFile = new Scanner(file);
				
				// Initialise a counter
				int i=0;

				while (scannerFile.hasNextLine()) //Continue until there is no line to read
				{
					//Read the next line and store it in a string
					String currentLine = scannerFile.nextLine();
					
					// Create a scanner object that scan the line previously stored
					Scanner scannerLine = new Scanner(currentLine);
					
					//Add a new row to the 2D array
					maze.add(new ArrayList<String>(i));

					while(scannerLine.hasNext()) //Continue until there is no element to read
					{
						//Set the delimiter to ","
						scannerLine.useDelimiter(",");
						//Add in row i a new element (each time in a new column
						maze.get(i).add(scannerLine.next());

					}
					
					//Increment the counter
					i++;
					
					//Close the scannerLine
					scannerLine.close();

				}
				//Close the scannerFile
				scannerFile.close();					
			}
			catch(FileNotFoundException e) {
				e.printStackTrace();
			}

			//To print the content of the 2D array and check that everything is fine.
			//for (int i=0; i< maze.size(); i++)
			//{
			//	String toPrint = "";
			//	for (int j=0; j<7;j++)
			//	{
			//		toPrint = toPrint+maze.get(i).get(j)+" ";
			//	}
			//	System.out.println(toPrint);

			//}

			return (maze);


		}
	
	public char[][]  toChar(ArrayList <ArrayList<String>> description)
	{
		int [] sizeMaze = {0,0};//Declare 
		sizeMaze[0] = Integer.parseInt(description.get(description.size()-1).get(0));
		sizeMaze[1] = Integer.parseInt(description.get(description.size()-1).get(1));
		char [] [] dispMaze = new char [(sizeMaze[0]+1)*2+1][(sizeMaze[0]+1)*4+1];// Declare 2D array of char 

		for (int i=0; i<sizeMaze[0]+1;i++)//loop through the different row index
		{
			for(int j=0; j<sizeMaze[1]+1;j++)//loop through the different column index
			{
				if (i == sizeMaze[0])//if i reached the last row then consider the southwall...
				{
					String mazeElementNorth = description.get(1+i*(sizeMaze[0]+1)+j).get(2);// check southwall of the element

					switch (mazeElementNorth)// action depending on the value contain in the wall
					{
					case "wall": dispMaze[0][4*j]   = '+';
								 dispMaze[0][4*j+1] = '-';
								 dispMaze[0][4*j+2] = '-';
								 dispMaze[0][4*j+3] = '-';
								 break;
						

					case "no"  : dispMaze[0][4*j]   = '+';
								 dispMaze[0][4*j+1] = ' ';
								 dispMaze[0][4*j+2] = ' ';
								 dispMaze[0][4*j+3] = ' ';
								 break;
								 
					case "breakable": dispMaze[0][4*j]   = '+';
									  dispMaze[0][4*j+1] = '-';
									  dispMaze[0][4*j+2] = 'b';
									  dispMaze[0][4*j+3] = '-';
									  break;
									  
					case "door":dispMaze[0][4*j]   = '+';
					  			dispMaze[0][4*j+1] = '-';
					  			dispMaze[0][4*j+2] = 'd';
							    dispMaze[0][4*j+3] = '-';
							    break;
							    
					default: dispMaze[0][4*j]   = '+';
					 dispMaze[0][4*j+1] = '-';
					 dispMaze[0][4*j+2] = '-';
					 dispMaze[0][4*j+3] = '-';
					 break;
					}

					if (j== sizeMaze[1])//if last column
					{
						dispMaze[0][(sizeMaze[1]+1)*4]   = '+';
					}

				}
				
				String mazeElementSouth = description.get(1+i*(sizeMaze[0]+1)+j).get(3);//Check South wall
				String mazeElementWest  = description.get(1+i*(sizeMaze[0]+1)+j).get(5);//Check west wall

				switch (mazeElementSouth)//Printing depending on the type of wall
				{
				case "wall": dispMaze[((sizeMaze[0]+1)*2)-(i*2)][4*j]   = '+';
							 dispMaze[((sizeMaze[0]+1)*2)-(i*2)][4*j+1] = '-';
							 dispMaze[((sizeMaze[0]+1)*2)-(i*2)][4*j+2] = '-';
							 dispMaze[((sizeMaze[0]+1)*2)-(i*2)][4*j+3] = '-';
							 break;

				case "no":   dispMaze[((sizeMaze[0]+1)*2)-(i*2)][4*j]   = '+';
							 dispMaze[((sizeMaze[0]+1)*2)-(i*2)][4*j+1] = ' ';
							 dispMaze[((sizeMaze[0]+1)*2)-(i*2)][4*j+2] = ' ';
							 dispMaze[((sizeMaze[0]+1)*2)-(i*2)][4*j+3] = ' ';
							 break;
							 
				case "breakable": dispMaze[((sizeMaze[0]+1)*2)-(i*2)][4*j]   = '+';
				 				  dispMaze[((sizeMaze[0]+1)*2)-(i*2)][4*j+1] = '-';
				 				  dispMaze[((sizeMaze[0]+1)*2)-(i*2)][4*j+2] = 'b';
				 				  dispMaze[((sizeMaze[0]+1)*2)-(i*2)][4*j+3] = '-';
				 				  break;
				 
				case "door": dispMaze[((sizeMaze[0]+1)*2)-(i*2)][4*j]   = '+';
				 			 dispMaze[((sizeMaze[0]+1)*2)-(i*2)][4*j+1] = '-';
				 			 dispMaze[((sizeMaze[0]+1)*2)-(i*2)][4*j+2] = 'd';
				 			 dispMaze[((sizeMaze[0]+1)*2)-(i*2)][4*j+3] = '-';
				 			 break;
				 
				default: dispMaze[((sizeMaze[0]+1)*2)-(i*2)][4*j]   = '+';
				 dispMaze[((sizeMaze[0]+1)*2)-(i*2)][4*j+1] = '-';
				 dispMaze[((sizeMaze[0]+1)*2)-(i*2)][4*j+2] = '-';
				 dispMaze[((sizeMaze[0]+1)*2)-(i*2)][4*j+3] = '-';
				 break;
				}
				
				switch (mazeElementWest)//Action depending on the type of wall
				{
				case "wall": dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][4*j]   = '|';
							 dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][4*j+1] = ' ';
							 dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][4*j+2] = ' ';
							 dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][4*j+3] = ' ';
							 break;
							 
				case "no":   dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][4*j]   = ' ';
							 dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][4*j+1] = ' ';
							 dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][4*j+2] = ' ';
							 dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][4*j+3] = ' ';
							 break;
							 
				case "breakable": dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][4*j]   = 'b';
								  dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][4*j+1] = ' ';
								  dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][4*j+2] = ' ';
								  dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][4*j+3] = ' ';
								  break;
								  
				case "door": dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][4*j]   = 'd';
							 dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][4*j+1] = ' ';
							 dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][4*j+2] = ' ';
							 dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][4*j+3] = ' ';
								  break;
								  
				default: dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][4*j]   = '|';
				 dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][4*j+1] = ' ';
				 dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][4*j+2] = ' ';
				 dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][4*j+3] = ' ';
				}
				
				if (j == sizeMaze[0])// if the last column is reach take into account the Eastern wall
				{
					dispMaze[((sizeMaze[0]+1)*2)-(i*2)][(sizeMaze[0]+1)*4]   = '+';//add a "+" one line out of two
					String mazeElementEast = description.get(1+i*(sizeMaze[0]+1)+j).get(4);//Check the East element

					switch (mazeElementEast)
					{
					case "wall": dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][(sizeMaze[0]+1)*4]   = '|';
						break;
						
					case "no"  : dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][(sizeMaze[0]+1)*4]   = ' ';
						break;
						
					case "breakable": dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][(sizeMaze[0]+1)*4]   = 'b';
					break;
					
					case "door": dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][(sizeMaze[0]+1)*4]   = 'd';
					break;
					
					default: dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][(sizeMaze[0]+1)*4]   = '|';
					}
				}
				//Check For Object
				
				String objInCase =  description.get(1+i*(sizeMaze[0]+1)+j).get(6);
				
				switch (objInCase)
				{
				case "start" : dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][2+4*j] = 'S';  
					break;
					
				case "S"     : dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][2+4*j] = 'S';  
				break;
				
				case "end"   : dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][2+4*j] = 'E';
					break;
				case "E"     : dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][2+4*j] = 'E';
				break;
				default: //Do nothing by default, avoid having an empty case for "no" and allow to disregard object that would not be handled by the program yet
				}	
			}
		}
		return (dispMaze);
	}
	
	public boolean[][] toBool(ArrayList <ArrayList<String>> description)
	{
		sizeMaze[0] = Integer.parseInt(description.get(description.size()-1).get(0));
		boolean [] [] dispMaze = new boolean [(sizeMaze[0]+1)*2+1][(sizeMaze[0]+1)*2+1];// Declare 2D array of char 

		for (int i=0; i<sizeMaze[0]+1;i++)//loop through the different row index
		{
			for(int j=0; j<sizeMaze[1]+1;j++)//loop through the different column index
			{
				if (i == sizeMaze[0])//if i reached the last row then consider the northhwall...
				{
					String mazeElementNorth = description.get(1+i*(sizeMaze[0]+1)+j).get(2);// check northwall of the element

					switch (mazeElementNorth)// action depending on the value contain in the wall
					{
					case "wall": dispMaze[0][2*j]   = true;
								 dispMaze[0][2*j+1] = true;
								 break;
						
					case "no"  : dispMaze[0][2*j]   = true;
								 dispMaze[0][2*j+1] = false;
								 break;
							    
					default: dispMaze[0][2*j]   = true;
					 dispMaze[0][2*j+1] = true;
					 break;
					}

					if (j== sizeMaze[1])//if last column
					{
						dispMaze[0][(sizeMaze[1]+1)*2]   = true;
					}

				}
				
				String mazeElementSouth = description.get(1+i*(sizeMaze[0]+1)+j).get(3);//Check South wall
				String mazeElementWest  = description.get(1+i*(sizeMaze[0]+1)+j).get(5);//Check west wall

				switch (mazeElementSouth)//Printing depending on the type of wall
				{
				case "wall": dispMaze[((sizeMaze[0]+1)*2)-(i*2)][2*j]   = true;
							 dispMaze[((sizeMaze[0]+1)*2)-(i*2)][2*j+1] = true;
							 break;

				case "no":   dispMaze[((sizeMaze[0]+1)*2)-(i*2)][2*j]   = true;
							 dispMaze[((sizeMaze[0]+1)*2)-(i*2)][2*j+1] = false;
							 break;

				default: dispMaze[((sizeMaze[0]+1)*2)-(i*2)][2*j]   = true;
				 dispMaze[((sizeMaze[0]+1)*2)-(i*2)][2*j+1] = false;
				 break;
				}
				
				switch (mazeElementWest)//Action depending on the type of wall
				{
				case "wall": dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][2*j]   = true;
							 dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][2*j+1] = false;
							 break;
							 
				case "no":   dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][2*j]   = false;
							 dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][2*j+1] = false;

							 break;
								  
				default: dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][2*j]   = true;
				 dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][2*j+1] = false;
				}
				
				if (j == sizeMaze[0])// if the last column is reach take into account the Eastern wall
				{
					dispMaze[((sizeMaze[0]+1)*2)-(i*2)][(sizeMaze[0]+1)*2]   = true;//add a "+" one line out of two
					String mazeElementEast = description.get(1+i*(sizeMaze[0]+1)+j).get(4);//Check the East element

					switch (mazeElementEast)
					{
					case "wall": dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][(sizeMaze[0]+1)*2]   = true;
						break;
						
					case "no"  : dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][(sizeMaze[0]+1)*2]   = false;
						break;
					
					default: dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][(sizeMaze[0]+1)*2]   = true;
					}
				}
			}
		}	
		return dispMaze;
	}
	
	public static boolean checkMaze(ArrayList<ArrayList<String>> mazeDescription){
		boolean startCalc = true;
		
		for (int i=0; i<mazeDescription.size();i++) {
			if (startCalc) {
				
				for(int j=0; j<mazeDescription.get(i).size();j++) {
					if (startCalc) {
						if(mazeDescription.get(i).get(j).equals("fake")|mazeDescription.get(i).get(j).equals("breakable")|mazeDescription.get(i).get(j).equals("door")) {
							startCalc = false;
						}else {}
					}else {break;}
				}
			}else {break;}
		}
		return startCalc;
	}
}
