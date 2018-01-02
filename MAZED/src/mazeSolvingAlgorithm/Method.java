package mazeSolvingAlgorithm;

import java.util.ArrayList;

public class Method {
	
	public static ArrayList<int[]> getVertices(int[] mazeSize, int [] startPos,int[] endPos,int val,int sizeNodeMap){
		ArrayList<int[]> distances = new ArrayList<int[]>();
		
	for (int i = 0; i<=mazeSize[0];i++) {
	
		for (int j = 0; j<=mazeSize[1];j++) {
				
				if (i==startPos[0]*2 & j==startPos[1]*2) {//*2 because of the fact that 1 case in Description is 2 case in nodeMap
					if (val == 0) {
					distances.add(new int[] {1,sizeNodeMap-2,1});//Tremaux
					}else {
						distances.add(new int[] {0,sizeNodeMap-2,1});//Dijkstra
					}
				}else {
				distances.add(new int[] {val, (sizeNodeMap-2)-(i*2),1+(2*j)});
				}
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
	public static int[] findAdj(ArrayList<int[]> distances,int idx2Vertex,int[] mazeSize, boolean[] sptSet, boolean [][] nodeMap ) {
		int[] idx = {mazeSize[1]+1,-mazeSize[1]-1,+1,-1};
		int [] idx2Walls = {-1,1,1,-1};//order inverted because X idx is inverted in nodeMap
		int[]idx2adj = {-1,-1,-1,-1};
		
		for (int i=0; i<idx.length;i++) {

			if (idx2Vertex+idx[i]<distances.size() & idx2Vertex+idx[i]>0) {//If index is in between the Vertexes boundaries
				if(i<2) {
					if(!nodeMap[distances.get(idx2Vertex)[1]+idx2Walls[i]][distances.get(idx2Vertex)[2]] & sptSet[idx2Vertex+idx[i]]==false) {
						
					idx2adj[i] = idx2Vertex+idx[i];
					}
					}else {
						if(!nodeMap[distances.get(idx2Vertex)[1]][distances.get(idx2Vertex)[2]+idx2Walls[i]] & sptSet[idx2Vertex+idx[i]]==false) {
							idx2adj[i] = idx2Vertex+idx[i];
					}}
					
				}else {
					
				}
		}	
		return idx2adj;
	}
	
	public static int[] findAdj(ArrayList<int[]> distances,int idx2Vertex,int[] mazeSize,boolean [][] nodeMap ) {
		int[] idx = {mazeSize[1]+1,-mazeSize[1]-1,+1,-1};
		int [] idx2Walls = {-1,1,1,-1};//order inverted because X idx is inverted in nodeMap
		int[]idx2adj = {-1,-1,-1,-1};
		
		for (int i=0; i<idx.length;i++) {

			if (idx2Vertex+idx[i]<distances.size() & idx2Vertex+idx[i]>=0) {//If index is in between the Vertexes boundaries
				if(i<2) {
					if(!nodeMap[distances.get(idx2Vertex)[1]+idx2Walls[i]][distances.get(idx2Vertex)[2]]) {
						
					idx2adj[i] = idx2Vertex+idx[i];
					}
					}else {
						if(!nodeMap[distances.get(idx2Vertex)[1]][distances.get(idx2Vertex)[2]+idx2Walls[i]]) {
							idx2adj[i] = idx2Vertex+idx[i];
					}}
					
				}else {
					
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
		
		int val = distances.get(firstIdx)[0];
		int idx=firstIdx;
		
			for (int i = 0; i<distances.size();i++) {
				if(spt[i]==false) {
				int val2cmp = distances.get(i)[0];
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
			if(arrayList.get(j)[0]==elem) {
				idx = j;
				break;
				
			}
			
		}
		
		return idx;
	}

	public static int findIdxArray(int[] idx2Adj, int val) {
		int idx = -1;
		for (int i = 0;i<idx2Adj.length;i++) {
			if(idx2Adj[i] == val) {
				idx=i;
			}
		}
		return idx;
	}
	
}
