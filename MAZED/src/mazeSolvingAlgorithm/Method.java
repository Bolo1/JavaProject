package mazeSolvingAlgorithm;

import java.util.ArrayList;

public class Method {
	
	public static ArrayList<int[]> getVertices(boolean[][] nodeMap, int [] startPos,int[] endPos,int val){
		ArrayList<int[]> distances = new ArrayList<int[]>();
		
	for (int i = 0; i<nodeMap.length-2;i++) {//-2 we do not consider edges
		
		for (int j = 0; j<nodeMap[0].length-2;j++) {//-2 we do not consider edges
			
			if (nodeMap[nodeMap.length-2-i][1+j] == false) {//-2 and j+1 because we want the starting point node to be at 0,0
				
				if (i==startPos[0]*2 & j==startPos[1]*2) {//*2 because of the fact that 1 case in Description is 2 case in nodeMap
					if (val == 0) {
					distances.add(new int[] {i*(nodeMap.length-2)+j,1});//Tremaux
					}else {
						distances.add(new int[] {i*(nodeMap.length-2)+j,0});//Dijkstra
					}
				}else {
				distances.add(new int[] {i*(nodeMap.length-2)+j,val});
				}
				
			}else {}
		}
	}
	return distances;
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
	public static int[] findAdj(ArrayList<int[]> distances,int idx2Vertex,int nodeMapSize, boolean[] sptSet) {
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
	
	public static int[] findAdj(ArrayList<int[]> distances,int idx2Vertex,int nodeMapSize) {
		int[] idx = {nodeMapSize,-nodeMapSize,+1,-1};
		int[]idx2adj = {-1,-1,-1,-1};
		for (int i=0; i<idx.length;i++) {

			if (distances.get(idx2Vertex)[0]+idx[i]<distances.get(distances.size()-1)[0]+1 & distances.get(idx2Vertex)[0]+idx[i]>0) {
				for (int j=0; j<distances.size();j++) {
					if (distances.get(j)[0]==distances.get(idx2Vertex)[0]+idx[i]) {
						//cond 1: An element in distances in j should have the same value as a potential adjacent to the vertex 
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

	public static int findIdxFromVal(int elem, ArrayList<int[]> arrayList) {
		int idx =-1;
		for (int j=0; j<arrayList.size();j++) {
			if(arrayList.get(j)[1]==elem) {
				idx = j;
				break;
				
			}
			
		}
		
		return idx;
	}
	
}
