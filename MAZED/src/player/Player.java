package player;

import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import maze.Maze;
import miscellaneousItem.Item;

public class Player {
	private String name;
	private Point2D currentPosition;
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private int lineOfSight;//Only used in hard mode
	private String canMove="";
	private int nbOfSteps;
	private int score;
	private PlayerHistory history;

	public Player(String playerName, Point2D playerPosition)
	{
		this.name = playerName;
		this.currentPosition = playerPosition;
		//this.positions.add(this.currentPosition);
		this.inventory.add(new Item("empty"));
		this.nbOfSteps = 0;
		this.score = 100;
		this.lineOfSight=2;
		
		ArrayList<Item> invCopy = new ArrayList<Item>();
		for (int i=0; i<this.inventory.size();i++) {
			//clone the item 
			Item item = new Item(this.inventory.get(i).getType());
			//store the item
			invCopy.add(item);
		}
		
		this.history = new PlayerHistory(new Point2D.Double(0,0), invCopy, this.nbOfSteps, this.score);
	}
	

	public String getName() {
		return (this.name);
	}


	public void setPosition(Point2D newPosition) {
		this.currentPosition = newPosition;
	}
		
	public void setCanMove(String reason) {
		this.canMove = reason;
	}
	public String getCanMove () {
		return canMove;
	}
	public Point2D getCurrentPosition(){
		return this.currentPosition;
	}
	
	public void updateNbOfSteps(int step2add) {
		this.nbOfSteps = this.nbOfSteps+step2add;
	}
	
	public int getNbOfSteps() {
		return this.nbOfSteps;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void updateScore(int score2add) {
		this.score = this.score+score2add;
	}
	
	public void printScore(String mazeName) {
		try (
				FileWriter fileW   = new FileWriter("HighScores.txt",true);
				BufferedWriter bufferW = new BufferedWriter(fileW);
				PrintWriter printerW = new PrintWriter(bufferW);){
			printerW.println(this.getName() + ","+mazeName+","+this.nbOfSteps+","+this.score);
		}catch(IOException i) {
			i.printStackTrace();
		}

	}
	public ArrayList<Item> getInventory(){
		return (this.inventory);

	}

	public int getSight() {
		return lineOfSight;
	}

	public void increaseSight(int bonus) {
		this.lineOfSight+=bonus;
	}

	
	public void updateHistory() {
		
		Point2D position2Add= new Point2D.Double(this.currentPosition.getX(),this.currentPosition.getY());
		ArrayList<Item> inv2Add = new ArrayList<Item>();
		for (int i=0; i<this.inventory.size();i++) {
			//clone the item 
			Item item = new Item(this.inventory.get(i).getType());
			//store the item
			inv2Add.add(item);
		}
		this.history.update(position2Add, inv2Add, this.nbOfSteps, this.score);
	}
	
	public List<Point2D> getPosHistory(){
		return this.history.getPosition();
	}
	
	public void pickUpItem(Item item2Pick, Maze myMaze) {
		if(this.inventory.get(0).getType().equals("empty")) {
			this.inventory.set(0, item2Pick);
		}else {
		this.inventory.add(item2Pick);
		}
		int index1 = 1+(int)this.currentPosition.getX()*(myMaze.getSize()[0]+1)+(int) this.currentPosition.getY();
		int index2 = myMaze.getNElemDesc()-1;
		myMaze.modDescription(index1, index2, "no");
		
	}
	public boolean searchInventory(String type) {
		int sizeInv = this.inventory.size();
		boolean isInInv = false;
		for(int i=0;i<sizeInv;i++) {
			if (type == this.inventory.get(i).getName()) {
				isInInv = true;
			}
		}
		return isInInv;
	}
	public String displayInventory() {
		int sizeInv = this.inventory.size();
		String disp="";
		for(int i=0;i<sizeInv;i++) {
			disp = disp+this.inventory.get(i).getName()+", ";
		}
		return disp;
	}

	public int countInventory(String type) {
		int sizeInv = this.inventory.size();
		int counter = 0;
		for(int i=0;i<sizeInv;i++) {
			if (type == this.inventory.get(i).getName()) {
				counter++;
			}
		}
		return counter;
	}
	public int scoreInventory() {
		
		int sizeInv = this.inventory.size();
		int score = 0;
		for(int i=0;i<sizeInv;i++) {
			score = score + this.inventory.get(i).getScoreVal();
		}
		return score;
	}

	public void useItem(Item item2Use) {
		switch (item2Use.getName()) {

		case "Key":
			break;

		case "Trophy":
			this.updateScore(item2Use.getScoreVal());
			break;

		case "Hammer":
			break;

		case "Light":
			//Light increase Sight
			this.increaseSight(2);
			break;

		case "Trap":
			//Traps increase the number of step you make
			this.updateNbOfSteps(5);
			break;
		}
	}


	public boolean canMove(String dir, Maze myMaze) {

		boolean canMove = false;
		switch (dir) {

		case "Up":{
			int indexWall = 2;
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
			canMove =  false;
		}
		return canMove;
	}
	
public boolean checkWall(Maze myMaze, int indexWall, String dir) {
		
		int indexX = (int) this.currentPosition.getX();
		int indexY = (int) this.currentPosition.getY();
		int sizeMaze = myMaze.getSize()[0];
		int index1 = 1 + indexX * (sizeMaze + 1) + indexY;
		String mazeElem = myMaze.getDescription(index1,indexWall);
		boolean canMove = false;
		
		switch (mazeElem) {
		
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
			canMove = searchInventory("Hammer");
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
			canMove = searchInventory("Key");
			break;
		}
			this.setCanMove(mazeElem);
		return canMove;
		
	}
	
	public void move(String direction, ArrayList <ArrayList<String>> mazeDescription) {

		switch (direction) {

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
	
	public void undoMove() {

		//Erase last move
		this.history.eraseHistory();
		//Update player current: position,inventory,step and score
		this.currentPosition = new Point2D.Double(this.history.getPosition(this.history.getPosition().size()-1).getX(),this.history.getPosition(this.history.getPosition().size()-1).getY());
		ArrayList<Item> inv2Add = new ArrayList<Item>();
		for (int i=0; i<this.history.getInv(this.history.getInv().size()-1).size();i++) {
			//clone the item 
			Item item = new Item(this.history.getInv(this.history.getInv().size()-1).get(i).getType());
			//store the item
			inv2Add.add(item);
		}
		this.inventory = this.history.getInv(this.history.getInv().size()-1);
		this.nbOfSteps = this.history.getStep(this.history.getStep().size()-1);
		this.score     = this.history.getScore(this.history.getScore().size()-1);
	}


}
	
	

