package mazeSolvingAlgorithm;

import java.util.ArrayList;

//The point of this class is to contain method that might be useful for both maze-solving implementations
public class Method {

	//To get vertices contained in sizeNodeMap
	public static ArrayList<int[]> getVertices(int[] mazeSize, int [] startPos,int[] endPos,int val,int sizeNodeMap){
		ArrayList<int[]> distances = new ArrayList<int[]>();
		//Double loop allow to go from 2 indices to "linear" indices	
		for (int i = 0; i<=mazeSize[0];i++) {

			for (int j = 0; j<=mazeSize[1];j++) {
				// in case of Tremaux, we want the start Pos to have a different mark, in this case 1 so it is easy to set the starting point;
				if (i==startPos[0]*2 & j==startPos[1]*2) {//*2 because of the fact that 1 case in Description is 2 case in nodeMap
					if (val == 0) {
						distances.add(new int[] {1,sizeNodeMap-2,1});//Tremaux
					}else {
						//in case of Dijkstra, the starting point value is 0 (distance) all other distances are set to val (MAX.INTEGER)
						distances.add(new int[] {0,sizeNodeMap-2,1});//Dijkstra
					}
				}else {
					distances.add(new int[] {val, (sizeNodeMap-2)-(i*2),1+(2*j)});
				}
			}
		}
		return distances;
	}

	// find adjacent for dijkstra (only difference is that it looks whether the adjacent are already included in the set or not
	public static int[] findAdj(ArrayList<int[]> distances,int idx2Vertex,int[] mazeSize, boolean[] sptSet, boolean [][] nodeMap ) {
		int[] idx = {mazeSize[1]+1,-mazeSize[1]-1,+1,-1};
		int [] idx2Walls = {-1,1,1,-1};//order inverted because X idx is inverted in nodeMap
		int[]idx2adj = {-1,-1,-1,-1};
		//loop through 4 possibles adjacents (we forbid displacement in diagonal here
		for (int i=0; i<idx.length;i++) {

			if (idx2Vertex+idx[i]<distances.size() & idx2Vertex+idx[i]>0) {//If index is in between the Vertexes boundaries
				if(i<2) {//if we consider up/down direction
					//check if there is a wall between or if it is already included in the set
					if(!nodeMap[distances.get(idx2Vertex)[1]+idx2Walls[i]][distances.get(idx2Vertex)[2]] & sptSet[idx2Vertex+idx[i]]==false) {
						//store value
						idx2adj[i] = idx2Vertex+idx[i];
					}
				}else {
					//check if there is a wall between or if it is already included in the set
					if(!nodeMap[distances.get(idx2Vertex)[1]][distances.get(idx2Vertex)[2]+idx2Walls[i]] & sptSet[idx2Vertex+idx[i]]==false) {
						idx2adj[i] = idx2Vertex+idx[i];
					}}

			}else {

			}
		}	
		return idx2adj;
	}

	//same code but we do not look at the set (one input is missing)
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

	// find the smallest value that is not yet in the set
	public static int findMin(ArrayList<int[]> distances,boolean [] spt) {
		int firstIdx = 0;
		//only consider element that are still in the set
		for (int j=0; j<spt.length; j++) {

			if (spt[j]==false) {
				firstIdx = j;
				break; //Stop once the first non-included element  is found
			}
		}
		//store value to compare and find smaller values
		int val = distances.get(firstIdx)[0];
		int idx=firstIdx;

		for (int i = 0; i<distances.size();i++) {
			if(spt[i]==false) {
				int val2cmp = distances.get(i)[0];
				int idx2cmp = i;
				if (val2cmp<val) {//store value if new value is smaller than the previous one
					val = val2cmp;
					idx = idx2cmp;
				}
			}
		}
		return idx;
	}

	//allow to index to a certain value of element 0 in an arrayList of int
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

	//allow to index to a certain value of element 0 in a int[]
	public static int findIdxArray(int[] array, int val) {
		int idx = -1;
		for (int i = 0;i<array.length;i++) {
			if(array[i] == val) {
				idx=i;
			}
		}
		return idx;
	}

}
