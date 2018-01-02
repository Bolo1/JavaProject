package player;

import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import maze.Maze;
import item.Item;

public class Player {

	private String name;//name of the player
	private Point2D currentPosition;// current Position on the maze
	private ArrayList<Item> inventory = new ArrayList<Item>();//Inventory
	private int lineOfSight;//Only used in hard mode
	private String canMove="";//String mentionning a reason why player could or not move==> maze element
	private int nbOfSteps;// number of steps made in the maze
	private int score;// Score, it is calculated as a function of the size of the maze - number of steps.
	private PlayerHistory history;//Hold the history of the movement/inventory/scores/steps of the player
	//constructor
	private int blocked = 0;
	public Player(String playerName)
	{
		this.name = playerName;
		this.inventory.add(new Item("empty"));//inventory is initialized with an object called empty
		this.nbOfSteps = 0;
		this.lineOfSight=2;//default value
		this.blocked=0;//player is not blocked by default
		//copy content of the Inventory
		ArrayList<Item> invCopy = new ArrayList<Item>();
		for (int i=0; i<this.inventory.size();i++) {
			//clone the item 
			Item item = new Item(this.inventory.get(i).getName());
			//store the item
			invCopy.add(item);
		}
		//Initialize Player History ==> empty
		this.history = new PlayerHistory();
	}

	// to get the name of the player
	public String getName() {
		return (this.name);
	}

	// to set player position
	public void setPosition(Point2D newPosition) {
		this.currentPosition = newPosition;
	}
	// to set canMove 
	private void setCanMove(String reason) {
		this.canMove = reason;
	}
	//to get can move
	public String getCanMove () {
		return canMove;
	}
	//to get the current position of the player
	public Point2D getCurrentPosition(){
		return this.currentPosition;
	}
	//to update the number of steps
	public void updateNbOfSteps(int step2add) {
		this.nbOfSteps = this.nbOfSteps+step2add;
	}
	// to get the current number of steps
	public int getNbOfSteps() {
		return this.nbOfSteps;
	}
	//to get the score
	public int getScore() {
		return this.score;
	}
	// to update score
	public void updateScore(int score2add) {
		this.score = this.score+score2add;
		if (this.score<0) {//score cannot be negative
			this.score=0;
		}
	}
	//to print score in a high scores file
	public void printScore(String mazeName) {
		try (
				FileWriter fileW   = new FileWriter("HighScores.txt",true);//open file
				BufferedWriter bufferW = new BufferedWriter(fileW);
				PrintWriter printerW = new PrintWriter(bufferW);){
			printerW.println(this.getName() + ","+mazeName+","+this.nbOfSteps+","+this.score);//write in fike
		}catch(IOException i) {
			i.printStackTrace();
		}
	}
	// to get player sight (only hardMode)
	public int getSight() {
		return lineOfSight;
	}
	//to increases sight (only hardMode)
	private void increaseSight(int bonus) {
		this.lineOfSight+=bonus;
	}
	//Update History
	public void updateHistory() {
		//clone the position
		Point2D position2Add= new Point2D.Double(this.currentPosition.getX(),this.currentPosition.getY());
		//clone the inventory
		ArrayList<Item> inv2Add = new ArrayList<Item>();
		for (int i=0; i<this.inventory.size();i++) {
			//clone the item 
			Item item = new Item(this.inventory.get(i).getName());
			//store the item
			inv2Add.add(item);
		}
		//update the history
		this.history.update(position2Add, inv2Add, this.nbOfSteps, this.score);
	}
	//to get blocked and know if player is blocked or not
	public int getBlocked() {
		return this.blocked;
	}
	
	public void updateBlocked(int val) {
		this.blocked+=val;
	}

	// to get the full inventory of the player
	public ArrayList<Item> getInventory(){
		return (this.inventory);
	}
	//to store item in player inventory
	public void pickUpItem(Item item2Pick, Maze myMaze) {
		if (item2Pick.getName().equalsIgnoreCase("trap")) {
			
		}else {
		if(this.inventory.get(0).getName().equals("empty")) {
			this.inventory.set(0, item2Pick);
		}else {
			this.inventory.add(item2Pick);//store item in inventory
		}
		}
		//delete the object in the maze description
		int index1 = 1+(int)this.currentPosition.getX()*(myMaze.getSize()[0]+1)+(int) this.currentPosition.getY();
		int index2 = myMaze.getNElemDesc()-1;
		myMaze.modDescription(index1, index2, "no");

	}
	//to search for a specific object inside the inventory
	private boolean searchInventory(String name) {
		int sizeInv = this.inventory.size();
		boolean isInInv = false;
		for(int i=0;i<sizeInv;i++) {
			if (name == this.inventory.get(i).getName()) {
				isInInv = true;
			}
		}
		return isInInv;
	}
	//to display inventory
	public String displayInventory() {
		int sizeInv = this.inventory.size();
		String disp="";
		for(int i=0;i<sizeInv;i++) {
			disp = disp+this.inventory.get(i).getName()+", ";
		}
		return disp;
	}

	// to use the item when picked up
	public void useItem(Item item2Use) {
		switch (item2Use.getName()) {

		case "key":
			break;

		case "trophy":
			//increase score
			this.updateScore(item2Use.getScoreVal());
			break;

		case "hammer":
			break;

		case "torch":
			//torch increase Sight
			this.increaseSight(2);
			break;

		case "trap":
			//Traps increase the number of step you make
			this.blocked=10;
			break;
		}
	}

	//Determine if the player can move or not, for this, the method check if there is a wall in the path of the player
	public boolean canMove(String dir, Maze myMaze) {

		boolean canMove = false;
		switch (dir) {//depending on the direction it checks a different wall

		case "Up":{
			int indexWall = 2;
			//check if wall allow to move
			canMove=checkWall(myMaze,indexWall,dir);
			break;
		}
		case "Down":{
			int indexWall = 3;
			canMove=checkWall(myMaze,indexWall,dir);
			break;
		}
		case "Left":{
			int indexWall = 5;
			canMove=checkWall(myMaze,indexWall,dir);
			break;
		}

		case "Right":{
			int indexWall = 4;
			canMove=checkWall(myMaze,indexWall,dir);
			break;
		}
		default :
			System.err.println("Unknown Direction required by player");
		}
		return canMove;
	}
	//check walls to see if player can move
	private boolean checkWall(Maze myMaze, int indexWall, String dir) {

		int indexX = (int) this.currentPosition.getX();
		int indexY = (int) this.currentPosition.getY();
		int sizeMaze = myMaze.getSize()[0];
		int index1 = 1 + indexX * (sizeMaze + 1) + indexY;
		String mazeElem = myMaze.getDescription(index1,indexWall);
		boolean canMove = false;

		switch (mazeElem) {//Action depends on the type of maze elem between the player and the new position he wishes to go

		case "wall":
			canMove =  false;
			break;

		case "no":
			canMove =  true;
			break;

		case "fake":
			canMove = true;
			break;

		case "breakable":
			//if breakable, search inventory to find a hammer
			canMove = searchInventory("hammer");
			//if has a hammer, wall will be destroyed and erased from the description
			if (canMove) {
				switch (dir) {

				case "Up":{
					myMaze.modDescription(index1, indexWall, "no");
					int index2 = 1 + (indexX-1) * (sizeMaze + 1) + indexY;
					int index3 = 3;
					myMaze.modDescription(index2, index3, "no");
					break;
				}

				case "Down":{
					myMaze.modDescription(index1, indexWall, "no");
					int index2 = 1 + (indexX-1) * (sizeMaze + 1) + indexY;
					int index3 = 2;
					myMaze.modDescription(index2, index3, "no");
					break;
				}

				case "Left":{
					myMaze.modDescription(index1, indexWall, "no");
					int index2 = 1 + indexX * (sizeMaze + 1) + indexY-1;
					int index3 = 4;
					myMaze.modDescription(index2, index3, "no");
					break;
				}

				case "Right":{
					myMaze.modDescription(index1, indexWall, "no");
					int index2 = 1 + indexX * (sizeMaze + 1) + indexY+1;
					int index3 = 5;
					myMaze.modDescription(index2, index3, "no");
					break;
				}
				}

			}else {
			}

			break;

		case "door":
			//if door, search inventory for a key
			canMove = searchInventory("key");
			break;
		}
		//set canMove to maze elem so later we can display a specific message to the player keeping him/her aware of what he did or why it was not possible to do
		this.setCanMove(mazeElem);
		return canMove;

	}
	//Move the player
	public void move(String direction, ArrayList <ArrayList<String>> mazeDescription) {

		switch (direction) {//movement depends on the direction pressed by the player

		case "Up": {
			int yPos = (int) this.currentPosition.getY();
			int xPos = (int) this.currentPosition.getX();
			xPos +=1;
			this.currentPosition= new Point2D.Double(xPos, yPos);
			break;}

		case "Left":{ 
			int yPos = (int) this.currentPosition.getY();
			int xPos = (int) this.currentPosition.getX();
			yPos -=1;
			this.currentPosition.setLocation(xPos, yPos);
			break;}

		case "Right":{ 
			int yPos = (int) this.currentPosition.getY();
			int xPos = (int) this.currentPosition.getX();
			yPos +=1;
			this.currentPosition.setLocation(xPos, yPos);
			break;}

		case "Down":{
			int yPos = (int) this.currentPosition.getY();
			int xPos = (int) this.currentPosition.getX();
			xPos -=1;
			this.currentPosition.setLocation(xPos, yPos);
			break;}
		}
		//Increase number of step when player moves
		this.updateNbOfSteps(1);
		//Decrease the score by the number of steps
		this.updateScore(-1);
	}
	//To go back on a move
	public void undoMove() {

		//Erase last move
		this.history.eraseHistory();
		//Update player current: position,inventory,step and score
		this.currentPosition = new Point2D.Double(this.history.getPosition(this.history.getPosition().size()-1).getX(),this.history.getPosition(this.history.getPosition().size()-1).getY());
		ArrayList<Item> inv2Add = new ArrayList<Item>();
		for (int i=0; i<this.history.getInv(this.history.getInv().size()-1).size();i++) {
			//clone the item 
			Item item = new Item(this.history.getInv(this.history.getInv().size()-1).get(i).getName());
			//store the item
			inv2Add.add(item);
		}
		//update current values
		this.inventory = this.history.getInv(this.history.getInv().size()-1);
		this.nbOfSteps = this.history.getStep(this.history.getStep().size()-1);
		this.score     = this.history.getScore(this.history.getScore().size()-1);
	}


}



