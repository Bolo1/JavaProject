package mazeSolvingAlgorithm;


import java.util.ArrayList;

import maze.Maze;

public class Dijkstra {

	public static int calc(Maze myMaze) {
		//extract a node map where wall are marked as true and other element are marked as false
		boolean [][] nodeMap = myMaze.toBool(myMaze.getDescription());
		ArrayList<int[]> distances = new ArrayList<int[]>();
		int idx2EndMaze=0;
		if (Maze.checkMaze(myMaze.getDescription())) {//if maze description does not contain fake wall, doors,...
			//get a list of vertex with there corresponding position on the nodeMap and a distance value
			distances = Method.getVertices(myMaze.getSize(), myMaze.getStart(), myMaze.getEnd(),Integer.MAX_VALUE,nodeMap.length);
			// initialize a set to keep track of which vertex we already visited
			boolean[] sptSet = new boolean[distances.size()];
			//get index to the end of maze
			idx2EndMaze = (myMaze.getEnd()[0]+1)*(myMaze.getEnd()[1]+1)-1;
			//Initialize a counter, will be used to constrain while loop to a finite number of run
			int count = 0;
			int distVertex = 1;//nb of step between Vertex
			//loop until all nodes are considered

			while (sptSet[idx2EndMaze] == false) {//Stop when the vertex of the exit of the maze is treated
				//update counter
				count++;
				//find closest adjacent vertex not included in the set
				int index2Vertex = Method.findMin(distances,sptSet);
				//include it in the set
				sptSet[index2Vertex] = true;
				//find adjacent vertex that are directly accessible (no diagonal, no walls)
				int[] idx2adj = Method.findAdj(distances,index2Vertex,myMaze.getSize(),sptSet,nodeMap);
				//loop through adjacent vertex and update their distances
				for (int m = 0; m<idx2adj.length; m++) {
					if(idx2adj[m]>0) {
						if(distances.get(index2Vertex)[0]+distVertex<distances.get(idx2adj[m])[0]) {
							distances.get(idx2adj[m])[0] = distances.get(index2Vertex)[0]+distVertex;
						}
					}					
				}				
				//constrain while loop to 1000 iteration
				if(count>1000) {
					System.err.println("Exited the loop after 1000 iterations");//Safety in case of infinite loop
					break;
				}
			}

		}else {
			System.err.println("Calculation aborted, the maze contains element (e.g. Breakable, fake wall) that cannot currently be handled by the implemented Dijkstra algorithm");
		}
		return distances.get(idx2EndMaze)[0];

	}

}
