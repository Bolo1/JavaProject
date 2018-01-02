package mazeSolvingAlgorithm;


import java.util.ArrayList;

import maze.Maze;

public class Dijkstra {
		
	public static int calc(Maze myMaze) {
		boolean [][] nodeMap = myMaze.toBool(myMaze.getDescription());
		ArrayList<int[]> distances = new ArrayList<int[]>();
		int idx2EndMaze=0;
		if (Maze.checkMaze(myMaze.getDescription())) {
			distances = Method.getVertices(nodeMap, myMaze.getStart(), myMaze.getEnd(),Integer.MAX_VALUE);
			boolean[] sptSet = new boolean[distances.size()];
			
			int  lastElem= (myMaze.getEnd()[0]*2+1)*(myMaze.getEnd()[1]*2+1)-1;
			 idx2EndMaze = Method.findIdx(lastElem,distances);
			int count = 0;
			int distVertex = 1;// 2 steps here equals 1 step in normal player maze but we'll /2 later
			//loop until all nodes are considered
			
			while (sptSet[idx2EndMaze] == false) {//Stop when the path to the end of the maze is determined
				
				count++;
				//find closest neighbour not included in the set
				int index2Vertex = Method.findMin(distances,sptSet);
				//include it in the set
				sptSet[index2Vertex] = true;
				//loop through adjacent vertex and update distance
				int[] idx2adj = Method.findAdj(distances,index2Vertex,nodeMap.length-2,sptSet);
			
				for (int m = 0; m<idx2adj.length; m++) {
					if(idx2adj[m]>0) {
						if(distances.get(index2Vertex)[1]+distVertex<distances.get(idx2adj[m])[1]) {
							distances.get(idx2adj[m])[1] = distances.get(index2Vertex)[1]+distVertex;
						}
					}					
				}				
				if(count>1000) {
					System.err.println("Exited the loop after 1000 iterations");//Safety in case of infinite loop
					break;
				}
			}

		}else {
			System.err.println("Calculation aborted, the maze contains element (e.g. Breakable, fake wall) that cannot currently be handled by the implemented Dijkstra algorithm");
		}
		return distances.get(idx2EndMaze)[1]/2;
		
	}

}
