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
			//Read the next line and store it in a string
			String currentLine = scannerFile.nextLine();
			// Initialize a counter
			int i=0;

			while (scannerFile.hasNextLine()) //Continue until there is no line to read
			{
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
				//read the next line and store it into currentLine
				currentLine = scannerFile.nextLine();
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

		//String[] dispMaze = new String [(sizeMaze[0]+1)*2+1];

		char [] [] dispMaze = new char [(sizeMaze[0]+1)*2+1][(sizeMaze[0]+1)*4+1];

		/*for (int l=0; l<(sizeMaze[0]+1)*2+1;l++)
		{
			dispMaze[l]="";
		}*/


		for (int i=0; i<sizeMaze[0]+1;i++)
		{

			for(int j=0; j<sizeMaze[1]+1;j++)
			{

				if (i == sizeMaze[0])
				{
					String mazeElementSouth = description.get(1+i+j*(sizeMaze[0])).get(3);

					switch (mazeElementSouth)
					{
					case "wall":// dispMaze[dispMaze.length-1]=dispMaze[dispMaze.length-1]+"+---";
						dispMaze[(sizeMaze[0]+1)*2][4*j]   = '+';
						dispMaze[(sizeMaze[0]+1)*2][4*j+1] = '-';
						dispMaze[(sizeMaze[0]+1)*2][4*j+2] = '-';
						dispMaze[(sizeMaze[0]+1)*2][4*j+3] = '-';
						break;

					case "no"  : //dispMaze[dispMaze.length-1]=dispMaze[dispMaze.length-1]+"+   ";
						dispMaze[(sizeMaze[0]+1)*2][4*j]   = '+';
						dispMaze[(sizeMaze[0]+1)*2][4*j+1] = ' ';
						dispMaze[(sizeMaze[0]+1)*2][4*j+2] = ' ';
						dispMaze[(sizeMaze[0]+1)*2][4*j+3] = ' ';
						break;
					}

					if (j== sizeMaze[0])
					{
						//dispMaze[dispMaze.length-1]=dispMaze[dispMaze.length-1]+"+";
						dispMaze[i*2+2][(sizeMaze[0]+1)*4]   = '+';

					}

				}

				String mazeElementNorth = description.get(1+i+j*(sizeMaze[0])).get(2);
				String mazeElementWest  = description.get(1+i+j*(sizeMaze[0])).get(5);

				switch (mazeElementNorth)
				{
				case "wall": //dispMaze[i*2]=dispMaze[i*2]+"+---";
					dispMaze[i*2][4*j]   = '+';
					dispMaze[i*2][4*j+1] = '-';
					dispMaze[i*2][4*j+2] = '-';
					dispMaze[i*2][4*j+3] = '-';
					break;

				case "no"  : //dispMaze[i*2]=dispMaze[i*2]+"+   ";
					dispMaze[i*2][4*j]   = '+';
					dispMaze[i*2][4*j+1] = ' ';
					dispMaze[i*2][4*j+2] = ' ';
					dispMaze[i*2][4*j+3] = ' ';
					break;
				}

				switch (mazeElementWest)
				{
				case "wall": //dispMaze[i*2+1]=dispMaze[i*2+1]+"|   ";
					dispMaze[i*2+1][4*j]   = '|';
					dispMaze[i*2+1][4*j+1] = ' ';
					dispMaze[i*2+1][4*j+2] = ' ';
					dispMaze[i*2+1][4*j+3] = ' ';
					break;

				case "no"  : //dispMaze[i*2+1]=dispMaze[i*2+1]+"    ";
					dispMaze[i*2+1][4*j]   = ' ';
					dispMaze[i*2+1][4*j+1] = ' ';
					dispMaze[i*2+1][4*j+2] = ' ';
					dispMaze[i*2+1][4*j+3] = ' ';
					break;
				}


				if (j == sizeMaze[0])
				{
					//dispMaze[i*2] = dispMaze[i*2]+"+";
					dispMaze[i*2][(sizeMaze[0]+1)*4]   = '+';
					String mazeElementEast = description.get(1+i+j*(sizeMaze[0])).get(4);

					switch (mazeElementEast)
					{
					case "wall": //dispMaze[i*2+1]=dispMaze[i*2+1]+"|";
						dispMaze[i*2+1][(sizeMaze[0]+1)*4]   = '|';
						break;
					case "no"  : //dispMaze[i*2+1]=dispMaze[i*2+1]+"";
						dispMaze[i*2+1][(sizeMaze[0]+1)*4]   = ' ';
						break;
					}


				}	

			}
		}
		dispMaze[1+0*2][2+0*4] = 'S';
		dispMaze[1+sizeMaze[0]*2][2+sizeMaze[0]*4] = 'E';
		return (dispMaze);
	}


}
