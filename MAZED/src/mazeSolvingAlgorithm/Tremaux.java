package mazeSolvingAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;

import maze.Maze;

public class Tremaux {
	public static int mainCalc(Maze myMaze) {

		boolean [][] nodeMap = myMaze.toBool(myMaze.getDescription());

		ArrayList<int[]> marks = new ArrayList<int[]>();
		int idx2EndMaze=0;
		int count = 0;
		int nStep = 0;

		if (Maze.checkMaze(myMaze.getDescription())) {
			marks = Method.getVertices(myMaze.getSize(), myMaze.getStart(), myMaze.getEnd(),0,nodeMap.length);
			int currentNode = Method.findIdxFromVal(1, marks);
			idx2EndMaze = (myMaze.getEnd()[0]+1)*(myMaze.getEnd()[1]+1)-1;
			int prevNode = currentNode;
			marks.set(currentNode, new int [] {0,marks.get(currentNode)[1],marks.get(currentNode)[2]});
			
			while (currentNode != idx2EndMaze) {
				count++;
				int[] idx2Adj = Method.findAdj(marks, currentNode, myMaze.getSize(),nodeMap);
				int [] mark = {-1,-1,-1,-1};
				

				for (int n = 0; n<idx2Adj.length;n++) {
					if (idx2Adj[n]>=0) {
						mark[n] = marks.get(idx2Adj[n])[0];
					}
				}
				int newNode = choseNextNode(mark,idx2Adj,prevNode,currentNode,marks);
				prevNode = currentNode;
				currentNode = newNode;
				marks.set(currentNode, new int [] {marks.get(currentNode)[0]+1,marks.get(currentNode)[1],marks.get(currentNode)[2]});
				
				if (count>1000) {//avoid infinite loop
					System.err.println("Calculation exited after 1000 iterations");
					break;
				}
			}
			//Count number of step
			nStep = stepCalc(marks,currentNode,myMaze.getSize(),nodeMap);
			
		}else {
			System.err.println("Calculation aborted, the maze contains element (e.g. Breakable, fake wall) that cannot currently be handled by the implemented Trémeaux algorithm");
		}

		return nStep;

	}

	private static int stepCalc(ArrayList<int[]> marks,int currentNode,int[] mazeSize, boolean[][]nodeMap) {
		int count= 0;
		int newNode= currentNode;
		int step=0;
		int prevNode = currentNode;
		while (currentNode != 0) {
			count++;
			
			int [] idx2Adj = Method.findAdj(marks, currentNode, mazeSize, nodeMap);
			
			for (int i = 0; i<idx2Adj.length;i++) {
				if(idx2Adj[i]>=0) {
					if(marks.get(idx2Adj[i])[0]==1 & idx2Adj[i]!= prevNode) {
						newNode = idx2Adj[i];
						step++;
						break;
					}
				}
			}
			if(newNode==currentNode) {
				for (int i = 0; i<idx2Adj.length;i++) {
					if(idx2Adj[i]>=0) {
						if(marks.get(idx2Adj[i])[0]==2 & idx2Adj[i]!= prevNode) {
							newNode = idx2Adj[i];
							step++;
						}
					}
				}
			}else {
				prevNode = currentNode;
				currentNode = newNode;
			}
			
			if(count>1000) {
				System.err.println("Calculation exited after 1000 iterations");
				break;
			}
		}
		return step;
	}

	private static int choseNextNode(int[] mark, int[] idx2Adj, int prevNode,int currentNode, ArrayList<int[]> marks) {
		int counter=0;
		int idx2NewNode=0;
		int idx2PrevNode = 0;
		int markCounter0=0;
		
		ArrayList<Integer> idx2Mark0 = new ArrayList<Integer>();
		ArrayList<Integer> idx2Mark1 = new ArrayList<Integer>();
		ArrayList<Integer> idx2Choices = new ArrayList<Integer>();

		for (int i =0;i<idx2Adj.length;i++) {
			if (idx2Adj[i]>=0) {
				if(mark[i]==0) {//if path was not taken
					markCounter0++;
					idx2Mark0.add(i);
				}
				if(mark[i]==1) {// if path was taken once
					idx2Mark1.add(i);
				}

				counter++;
				idx2Choices.add(i);

				if(idx2Adj[i] == prevNode) {
					idx2PrevNode = i;
				}

				if(mark[i] >1) {// if path was taken twice
					if(mark[i]>2) {
						System.err.println("Error, passed more than twice on the same place");
					}

					idx2Choices = removeIdx(i,idx2Choices);
					//counter--;

				}
			}
		}
		
		if(counter==1 & marks.get(currentNode)[0]<2) {
			marks.set(currentNode, new int [] {marks.get(currentNode)[0]+1,marks.get(currentNode)[1],marks.get(currentNode)[2]});
			idx2NewNode = idx2Adj[idx2Choices.get(0)];
		}else {
			if(marks.get(prevNode)[0]<2) {
				
				if(markCounter0 == counter-1) {
					int idx2Dir = -1;
					switch(currentNode-prevNode){
					case 10:{
						 idx2Dir = Method.findIdxArray(idx2Adj,currentNode+10);
						break;
					}
					case -10:{
						 idx2Dir = Method.findIdxArray(idx2Adj,currentNode-10);
						break;
					}
					case 1:{
						 idx2Dir = Method.findIdxArray(idx2Adj,currentNode+1);
						break;
						
					}
					case -1:{
						 idx2Dir = Method.findIdxArray(idx2Adj,currentNode-1);
						break;
					}
					default:{
						System.err.println("Error finding direction");
					}					
					}
					 if(idx2Dir>0){
						idx2NewNode = idx2Adj[idx2Dir];
					}else {
						idx2NewNode = idx2Adj[idx2Mark0.get(0)];
					}

					

				}else {
					idx2NewNode = idx2Adj[idx2PrevNode];
				}

				
			}else {
				if(markCounter0 == counter-1) {
					int idx2Dir = -1;
					switch(currentNode-prevNode){
					case 10:{
						 idx2Dir = Method.findIdxArray(idx2Adj,currentNode+10);
						break;
					}
					case -10:{
						 idx2Dir = Method.findIdxArray(idx2Adj,currentNode-10);
						break;
					}
					case 1:{
						 idx2Dir = Method.findIdxArray(idx2Adj,currentNode+1);
						break;
						
					}
					case -1:{
						 idx2Dir = Method.findIdxArray(idx2Adj,currentNode-1);
						break;
					}
					default:{
						System.err.println("Error finding direction");
					}					
					}
					 if(idx2Dir>0){
						idx2NewNode = idx2Adj[idx2Dir];
						
					}else {
						idx2NewNode = idx2Adj[idx2Mark0.get(0)];
				
					}
				}else {
					if(!idx2Mark0.isEmpty())
						
						idx2NewNode = idx2Adj[idx2Mark0.get(0)];
					
					else if(!idx2Mark1.isEmpty())
						idx2NewNode = idx2Adj[idx2Mark1.get(0)];
					else
						System.err.println("Error no possibilities to move");
					
				}
				
				
			}
				
			}

		return idx2NewNode;

	}
	public static ArrayList<Integer> removeIdx(int idx,ArrayList<Integer> arrayList){
		int idx2remove=-1;
		for (int i = 0; i<arrayList.size();i++) {
			if(arrayList.get(i)== idx) {
				idx2remove = i;
			}
		}
		arrayList.remove(idx2remove);
		return arrayList;
	}
}