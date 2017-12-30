package mazeSolvingAlgorithm;


import java.util.ArrayList;

import maze.Maze;

public class Dijkstra {
		
	public static void calc(Maze myMaze) {
		if (checkMaze(myMaze.getDescription())) {
			boolean [][] nodeMap = myMaze.toBool(myMaze.getDescription());
			
		}else {
			System.err.println("Calculation aborted, the maze contains element (e.g. Breakable, fake wall) that cannot currently be handled by the Dijkstra algorithm");
		}
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
	
//	public static boolean [] extractNodeMap(Maze maze) {
//		
//		boolean[][] nodeMap = maze.toBool(maze.getDescription());
//		
//		//printing
//		for (boolean[] u:nodeMap)
//		{
//			String toPrint ="";
//			for(boolean v:u)
//			{
//				String tmp = "";
//				if (v==false) {
//					tmp = "0";
//				}else {
//					 tmp  ="1";
//				}
//				 toPrint =toPrint+tmp;
//			}				
//			System.out.println(toPrint+"\n");				
//		}		
//		

		
	//	return null;
//	}

}
