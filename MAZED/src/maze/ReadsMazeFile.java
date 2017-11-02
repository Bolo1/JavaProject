package maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadsMazeFile {
	
	
	public static ArrayList <ArrayList<String>>  read(String mazeFilePath)
	{
		String path = mazeFilePath;


		ArrayList <ArrayList<String>> maze = new ArrayList <ArrayList<String>>();

		File file = new File(path);
		try {	
			Scanner scannerFile = new Scanner(file);
			String currentLine = scannerFile.nextLine();
			int i=0;

			while (scannerFile.hasNextLine()) 
			{

				Scanner scannerLine = new Scanner(currentLine);
				//
				maze.add(new ArrayList<String>(i));
				System.out.println("i equal "+i);
				//int j=0;
				while(scannerLine.hasNext())
				{


					scannerLine.useDelimiter(",");
					maze.get(i).add(scannerLine.next());
					//j++;
					//System.out.println("j equal "+j);
				}
				i++;
				currentLine = scannerFile.nextLine();
				scannerLine.close();

			}
			scannerFile.close();					
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return (maze);
		//System.out.println("Size of Array is "+maze.size());
		//for (int i=0; i< maze.size(); i++)
		//{
		//	for (int j=0; j<7;j++)
		//	System.out.println(maze.get(i).get(j));
		//}
	}


}
