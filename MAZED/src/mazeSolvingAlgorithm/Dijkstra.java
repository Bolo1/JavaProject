package mazeSolvingAlgorithm;


import java.util.ArrayList;

import maze.Maze;

public class Dijkstra {
		
	public static int calc(Maze myMaze) {
		boolean [][] nodeMap = myMaze.toBool(myMaze.getDescription());
		ArrayList<int[]> distances = new ArrayList<int[]>();
		int idx2EndMaze=0;
		if (checkMaze(myMaze.getDescription())) {
			
			
			for (int i = 0; i<nodeMap.length-2;i++) {//-2 we do not consider edges
				
				for (int j = 0; j<nodeMap[0].length-2;j++) {//-2 we do not consider edges
					
					if (nodeMap[nodeMap.length-2-i][1+j] == false) {//-2 and j+1 because we want the starting point node to be at 0,0
						
						if (i==myMaze.getStart()[0]*2 & j==myMaze.getStart()[1]*2) {//*2 because of the fact that 1 case in Description is 2 case in nodeMap
							
							distances.add(new int[] {i*(nodeMap.length-2)+j,0});
						}else {
						distances.add(new int[] {i*(nodeMap.length-2)+j,Integer.MAX_VALUE});
						}
						
					}else {}
				}
			}
			boolean[] sptSet = new boolean[distances.size()];
			
			int  lastElem= (myMaze.getEnd()[0]*2+1)*(myMaze.getEnd()[1]*2+1)-1;
			 idx2EndMaze = findIdx(lastElem,distances);
			int count = 0;
			int distVertex = 1;// 2 steps here equals 1 step in normal player maze but we'll /2 later
			//loop until all nodes are considered
			while (sptSet[idx2EndMaze] == false) {//Stop when the path to the end of the maze is determined
				
				count++;
				//find closest neighbour not included in the set
				int index2Vertex = findMin(distances,sptSet);
				//include it in the set
				sptSet[index2Vertex] = true;
				//loop through adjacent vertex and update distance
				int[] idx2adj = findAdj(distances,index2Vertex,nodeMap.length-2,sptSet);
			
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
	
	private static int[] findAdj(ArrayList<int[]> distances,int idx2Vertex,int nodeMapSize, boolean[] sptSet) {
		int[] idx = {nodeMapSize,-nodeMapSize,+1,-1};
		int[]idx2adj = {-1,-1,-1,-1};
		for (int i=0; i<idx.length;i++) {

			if (distances.get(idx2Vertex)[0]+idx[i]<distances.get(distances.size()-1)[0]+1 & distances.get(idx2Vertex)[0]+idx[i]>0) {
				for (int j=0; j<distances.size();j++) {
					if (distances.get(j)[0]==distances.get(idx2Vertex)[0]+idx[i] & sptSet[j] ==false  ) {
						//cond 1: An element in distances in j should have the same value as a potential adjacent to the vertex 
						//cond 2: The adjacent should not already be part of the set
						if(i>1) {
							if (distances.get(j)[0]/idx[0] == distances.get(idx2Vertex)[0]/idx[0]) {//cond 3: The adjacent along line ("horizontal") should be on the same line as the vertex
								idx2adj[i] = j;
								}
						}else {
							idx2adj[i] = j;
						}
					}
				}
			}
		}
		
		return idx2adj;
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
	
	public static int findMin(ArrayList<int[]> distances,boolean [] spt) {
		int firstIdx = 0;
		//only consider element that are still in the set
		for (int j=0; j<spt.length; j++) {
			
			if (spt[j]==false) {
			 firstIdx = j;
			 break; //Stop once the first non-included element  is found
			}
		}
		
		int val = distances.get(firstIdx)[1];
		int idx=firstIdx;
		
			for (int i = 0; i<distances.size();i++) {
				if(spt[i]==false) {
				int val2cmp = distances.get(i)[1];
				int idx2cmp = i;
				if (val2cmp<val) {
					val = val2cmp;
					idx = idx2cmp;
				}
			}
			}
			return idx;
	}
			
	public static int countTrue(boolean [] spt) {
		int count=0;
		for (int i = 0;i < spt.length;i++) {
			if (spt[i]) {
				count++;
			}
		}
		return count;
	}
	public static int findIdx (int elem, ArrayList<int[]> arrayList) {
		int idx =-1;
		for (int j=0; j<arrayList.size();j++) {
			if(arrayList.get(j)[0]==elem) {
				idx = j;
				break;
				
			}
			
		}
		
		
		return idx;
		
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
