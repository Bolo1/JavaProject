package mazeSolvingAlgorithm;
import maze.Maze;

import java.util.ArrayList;

public class Tremaux {
	public static int mainCalc(Maze myMaze) {
		//extract a node map where wall are marked as true and other element are marked as false
		boolean [][] nodeMap = myMaze.toBool(myMaze.getDescription());
		//initalize a list where we will keep track of how many times we passed on the element and the corresponding indices on nodeMap
		ArrayList<int[]> marks = new ArrayList<int[]>();
		int idx2EndMaze=0;
		int count = 0;
		int nStep = 0;

		if (Maze.checkMaze(myMaze.getDescription())) {
			//get a list of vertex with there corresponding position on the nodeMap and a distance value
			marks = Method.getVertices(myMaze.getSize(), myMaze.getStart(), myMaze.getEnd(),0,nodeMap.length);
			//find the index of the first node that was given value 1 in getVertices
			int currentNode = Method.findIdxFromVal(1, marks);
			//define the idx of the exit of the maze
			idx2EndMaze = (myMaze.getEnd()[0]+1)*(myMaze.getEnd()[1]+1)-1;
			//initialize previous Node to current node as there is no move history yet
			int prevNode = currentNode;
			//put the mark of the current node to 0 (unvisited)
			marks.set(currentNode, new int [] {0,marks.get(currentNode)[1],marks.get(currentNode)[2]});

			while (currentNode != idx2EndMaze) {//as long as the current Node is not the exit of the maze, keep iterating
				//update counter
				count++;
				//find adjacent
				int[] idx2Adj = Method.findAdj(marks, currentNode, myMaze.getSize(),nodeMap);
				//initalize the mark to -1
				int [] mark = {-1,-1,-1,-1};
				//extract the mark of the adjacent vertices
				for (int n = 0; n<idx2Adj.length;n++) {
					if (idx2Adj[n]>=0) {
						mark[n] = marks.get(idx2Adj[n])[0];
					}
				}
				// determine the next node to go to based on the mark of the adjacent vertices
				int newNode = choseNextNode(mark,idx2Adj,prevNode,currentNode,marks);
				//previous node becomes current node
				prevNode = currentNode;
				//add 1 to the mark of the current node ==> visited 
				marks.set(currentNode, new int [] {marks.get(currentNode)[0]+1,marks.get(currentNode)[1],marks.get(currentNode)[2]});
				//current Node becomes new node
				currentNode = newNode;

				//avoid infinite loop
				if (count>1000) {
					System.err.println("Calculation exited after 1000 iterations");
					break;
				}
			}
			//Count number of steps for "most straightforward" path found
			nStep = stepCalc(marks,currentNode,myMaze.getSize(),nodeMap);

		}else {
			System.err.println("Calculation aborted, the maze contains element (e.g. Breakable, fake wall) that cannot currently be handled by the implemented Trémeaux algorithm");
		}

		return nStep;

	}
	//calculation of the steps
	private static int stepCalc(ArrayList<int[]> marks,int currentNode,int[] mazeSize, boolean[][]nodeMap) {
		int count= 0;
		int newNode= currentNode;
		int step=0;
		int prevNode = currentNode;
		//as long as we are not at the starting node, we keep going
		while (currentNode != 0) {
			count++;
			//find adjacent
			int [] idx2Adj = Method.findAdj(marks, currentNode, mazeSize, nodeMap);
			//check adjacent that are marked only once and are not the previous node (==> most straightforward path)
			for (int i = 0; i<idx2Adj.length;i++) {
				if(idx2Adj[i]>=0) {
					if(marks.get(idx2Adj[i])[0]==1 & idx2Adj[i]!= prevNode) {
						newNode = idx2Adj[i];
						step++;
						break;
					}
				}
			}
			//if there was no adjacent marked once look at the one marked twice
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
				//update prev/current node
				prevNode = currentNode;
				currentNode = newNode;
			}
			//safety
			if(count>1000) {
				System.err.println("Calculation exited after 1000 iterations");
				break;
			}
		}
		return step;
	}
	//Chose next node based on Tremaux idea
	private static int choseNextNode(int[] mark, int[] idx2Adj, int prevNode,int currentNode, ArrayList<int[]> marks) {
		//variables initialization
		int counter=0;
		int idx2NewNode=0;
		int idx2PrevNode = 0;
		int markCounter0=0;
		ArrayList<Integer> idx2Mark0 = new ArrayList<Integer>();
		ArrayList<Integer> idx2Mark1 = new ArrayList<Integer>();
		ArrayList<Integer> idx2Choices = new ArrayList<Integer>();
		//loop through adjacent
		for (int i =0;i<idx2Adj.length;i++) {
			if (idx2Adj[i]>=0) {//adj  should be >=0 to not have a wallblocking
				if(mark[i]==0) {//if path was not taken
					markCounter0++;// count the number of "free path"
					idx2Mark0.add(i);//get the indices
				}
				if(mark[i]==1) {// if path was taken once
					idx2Mark1.add(i);//get the indices
				}
				//count number of possible path
				counter++;
				idx2Choices.add(i);

				if(idx2Adj[i] == prevNode) {
					idx2PrevNode = i;//keep track of previous node
				}

				if(mark[i] >1) {// if path was taken twice
					if(mark[i]>2) {
						System.err.println("Error, passed more than twice on the same place");
					}
					//remove idx if a path was marked more than once so we do not go there again					
					idx2Choices.remove(Integer.valueOf(i));
				}
			}
		}

		if(counter==1 & marks.get(currentNode)[0]<2) {//if only 1 path dead end
			marks.set(currentNode, new int [] {marks.get(currentNode)[0]+1,marks.get(currentNode)[1],marks.get(currentNode)[2]});//mark it once more so we do not go there again
			idx2NewNode = idx2Adj[idx2Choices.get(0)];//the only choice available is taken
		}else {

			if(markCounter0 == counter-1) {//if unmarked possibilities = possibilities (except back) then keep going in the same direction
				int idx2Dir = -1;
				//keep going in the same direction
				int dirVal = currentNode-prevNode;
				switch(dirVal){
				case 10:{
					idx2Dir = Method.findIdxArray(idx2Adj,currentNode+dirVal);
					break;
				}
				case -10:{
					idx2Dir = Method.findIdxArray(idx2Adj,currentNode+dirVal);
					break;
				}
				case 1:{
					idx2Dir = Method.findIdxArray(idx2Adj,currentNode+dirVal);
					break;

				}
				case -1:{
					idx2Dir = Method.findIdxArray(idx2Adj,currentNode+dirVal);
					break;
				}
				default:{
					System.err.println("Error finding direction");
				}					
				}
				if(idx2Dir>0){//if direction was found (no wall in front)

					idx2NewNode = idx2Adj[idx2Dir];

				}else {//otherwise

					idx2NewNode = idx2Adj[idx2Mark0.get(0)];//take one of the unmarked path
				}

			}else {//if the different possibilities have different marking (some explored some not)

				if(marks.get(prevNode)[0]<2) {// if we can potentially go back, do so

					idx2NewNode = idx2Adj[idx2PrevNode];
				}else {//if we cannot go back (visited twice) take the path with the smallest mark

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
}