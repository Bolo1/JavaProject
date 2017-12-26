package maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadsMazeFile {

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
			
			// Initialize a counter
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
	public static char[][]  mazeToChar(ArrayList <ArrayList<String>> description)
	{
		int [] sizeMaze = {0,0};//Declare 
		sizeMaze[0] = Integer.parseInt(description.get(description.size()-1).get(0));
		sizeMaze[1] = sizeMaze[0];// Integer.parseInt(description.get(description.size()-1).get(1));

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
					default: System.err.println("Unknown Type of wall");
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
				default: System.err.println("Unknown Type of wall");
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
				default: System.err.println("Unknown Type of wall");
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
					default: System.err.println("Unknown Type of wall");
					}
					
						
				}
				//Check For Object
				
				String objInCase =  description.get(1+i*(sizeMaze[0]+1)+j).get(6);
				
				switch (objInCase)
				{
				case "S" : dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][2+4*j] = 'S';  
					break;
				case "E" : dispMaze[((sizeMaze[0]+1)*2)-(i*2+1)][2+4*j] = 'E';
					break;
				default: //Do nothing by default, avoid having an empty case for "no" and allow to disregard object that would not be handled by the program yet
				}	
			}
		}
		//dispMaze[1+0*2][0] = 'S';// Place the Starting point
		//dispMaze[1+sizeMaze[0]*2][2+sizeMaze[0]*4] = 'E';// Place the end point 
		return (dispMaze);
	}


}
